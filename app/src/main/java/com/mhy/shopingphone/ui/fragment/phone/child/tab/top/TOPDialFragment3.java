package com.mhy.shopingphone.ui.fragment.phone.child.tab.top;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseRecycleFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.rxbus.Subscribe;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.RecentCallsAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.phone.top.TOPDialContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.recentcalls.RecentCallsItemBean;
import com.mhy.shopingphone.model.bean.rxbus.EventBusTwo;
import com.mhy.shopingphone.model.bean.rxbus.RxClickPhoneBean;
import com.mhy.shopingphone.presenter.phone.top.TOPDialPresenter;
import com.mhy.shopingphone.widgets.DialPadView;
import com.mhy.shopingphone.widgets.phone.AnimationController;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_PHONE_DIALPAFVIEW;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_PHONE_TOP;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述： 拨号盘fragment
 */

public class TOPDialFragment3 extends BaseRecycleFragment<TOPDialContract.TOPDialPresenter,
        TOPDialContract.ITOPDialModel> implements TOPDialContract.ITOPDialView {

    @BindView(R.id.dial_banner)
    Banner dialBanner;
    @BindView(R.id.rv_wangyi)
    RecyclerView rvWangyi;
    @BindView(R.id.mdialpad_view)
    DialPadView mDialPadView;
    Unbinder unbinder;
    Unbinder unbinder1;
    private List<RecentCallsItemBean> dataList;
    private String scollText;
    private Map<String, String> params;
    private String paramstr;
    private AnimationController mAnimController;
    private boolean isture;

    public static TOPDialFragment newInstance() {
        Bundle args = new Bundle();
        TOPDialFragment fragment = new TOPDialFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return TOPDialPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_top_dial;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        RxBus.get().register(this);
        mAnimController = new AnimationController();
        dataList = new ArrayList<>();
        getDataList();
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        RecentCallsAdapter recentCallsAdapter = new RecentCallsAdapter(R.layout.item_recent_calls, dataList);
        rvWangyi.setAdapter(recentCallsAdapter);
        rvWangyi.setLayoutManager(new LinearLayoutManager(mActivity));
        params = new HashMap<>();
        loadBannerData();
    }

    //请求
    private void loadBannerData() {
        //请求参数
        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        params.put("Mobile", (String) SharedPreferencesHelper.getInstance().getData("Mobile",""));

//        String paramsstring = StringUtil.mapToJson(params);
//        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.ALLADV_URLHead;
//        mPresenter.loadBannerData(paramsstring);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.CAROUSEL_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        BannerBean bean2 = new Gson().fromJson(response,BannerBean.class);
                        List<String> strings = new ArrayList<>();
                        for (BannerBean.JsonBean bean : bean2.getJson()) {
                            if (bean.getAdtype() == 3) {
                                strings.add(Api.NEWGOODS + bean.getUrl());
                            }
                            if (bean.getAdtype() == 7) {
                                SharedPreferencesHelper.getInstance().saveData("AdressList", Api.NEWGOODS + bean.getPath());//
                            }
                        }
                        LogUtils.e("Banner" + strings.size());

                        Util.setBanner(null, strings, dialBanner);//设置轮播图
                        //设置点击事件，下标是从1开始
                        dialBanner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                XToast.info("" + position);
                            }
                        });
                    }
                });
    }

    @Override
    public void showBannerDetail(BannerBean bean2) {
    }

    @Override
    public void showNetworkError() {
    }

    /**
     * 读取数据通话记录
     *
     * @return 读取到的数据
     */
    private void getDataList() {
        // 1.获得ContentResolver
        ContentResolver resolver = getActivity().getContentResolver();
        // 2.利用ContentResolver的query方法查询通话记录数据库
        /**
         * @param uri
         *            需要查询的URI，（这个URI是ContentProvider提供的）
         * @param projection
         *            需要查询的字段
         * @param selection
         *            sql语句where之后的语句
         * @param selectionArgs
         *            ?占位符代表的数据
         * @param sortOrder
         *            排序方式
         *
         */
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
        }
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, // 查询通话记录的URI
                new String[]{CallLog.Calls.GEOCODED_LOCATION// 通话记录的归属地
                        , CallLog.Calls.CACHED_NAME// 通话记录的联系人CACHED_NAME
                        , CallLog.Calls.NUMBER// 通话记录的电话号码
                        , CallLog.Calls.DATE// 通话记录的日期
                        , CallLog.Calls.TYPE// 通话记录的类型

                }// 通话类型
                , null, null, CallLog.Calls.DEFAULT_SORT_ORDER// 按照时间逆序排列，最近打的最先显示
        );

        while (cursor.moveToNext()) {
            String phone_name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
            String phone_number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
            String address = cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION));
//            String contact_id = cursor.getString(cursor.getColumnIndex("subscription_id"));
            long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
            String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date(dateLong));
            //时长
            // int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
            int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
            String typeString = "";
            RecentCallsItemBean bean = new RecentCallsItemBean();
            bean.setName((phone_name == null) ? "未知联系人" : phone_name);
            bean.setPhone(phone_number);
//            bean.setId(contact_id);
            bean.setData(date);
            dataList.add(bean);
        }
        cursor.close();
    }

    /**
     * RxBus
     */
    @Subscribe(code = RX_BUS_CODE_PHONE_DIALPAFVIEW)
    public void rxBusEvent2(EventBusTwo event) {
        isture = event.istrue();
        if (isture) {
            // showBannerModth();
            dialBanner.setVisibility(View.VISIBLE);
            //  mLvShow.setVisibility(View.GONE);
        } else {
            //hideBannerModth();
            dialBanner.setVisibility(View.GONE);
            // mLvShow.setVisibility(View.VISIBLE);
        }
    }

    /**
     * RxBus
     */
    @Subscribe(code = RX_BUS_CODE_PHONE_TOP)
    public void rxBusEvent(RxClickPhoneBean rxClickPhoneBean) {
        XLog.e("xxxx" + rxClickPhoneBean.isClick);
        if (!rxClickPhoneBean.isClick) {
            showKeyBoard();
            showBannerModth();
        } else {
            hideBannerModth();
            hideKeyBoard();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }


    //显示键盘
    public void showKeyBoard() {
        if (mAnimController != null) {
            // mAnimController.scaleOut(mBtnShowKeyboard, 150, 0);
            mAnimController.slideTranslateInFromVBottom(mDialPadView, 250, 75);
        }
    }

    //隐藏键盘
    public void hideKeyBoard() {
        if (mAnimController != null) {
            mAnimController.slideTranslateOutToBottom(mDialPadView, 250, 0);
            // mAnimController.scaleIn(mBtnShowKeyboard, 150, 150);
        }
    }

    //显示轮播图的动画
    public void showBannerModth() {
        if (mAnimController != null) {
            mAnimController.slideFadeInFromTop(dialBanner, 250, 0);//从顶部下来
//            mDialPadView.clearInput();
        }
    }

    //隐藏轮播图的动画

    public void hideBannerModth() {
        if (mAnimController != null) {
            //mAnimController.slideTranslateInFromTop(mMZBanner, 250, 0);
            mAnimController.slideTranslateOutToTop(dialBanner, 250, 0);//从顶部上去
        }
    }

    @Override
    protected void onErrorViewClick(View view) {

    }

    @Override
    protected void showLoading() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder1 = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder1.unbind();
    }



}
