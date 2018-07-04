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
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseCompatFragment;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
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
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.recentcalls.RecentCallsItemBean;
import com.mhy.shopingphone.model.bean.rxbus.EventBusTwo;
import com.mhy.shopingphone.model.bean.rxbus.RxClickPhoneBean;
import com.mhy.shopingphone.presenter.phone.top.TOPDialPresenter;
import com.mhy.shopingphone.widgets.DialPadView;
import com.mhy.shopingphone.widgets.dialpadview.IKoyboadLister;
import com.mhy.shopingphone.widgets.dialpadview.ITextListener;
import com.mhy.shopingphone.widgets.dialpadview.sp.SpSetting;
import com.mhy.shopingphone.widgets.phone.AnimationController;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.xframe.utils.log.XLog;
import com.youth.xframe.widget.XToast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_PHONE_DIALPAFVIEW;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_PHONE_TOP;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述： 拨号盘fragment
 */

public class TOPDialFragment2 extends BaseCompatFragment implements
        DialPadView.onCallListener
        , DialPadView.onTextChangedListener
        , ITextListener, IKoyboadLister {


    @BindView(R.id.dial_banner)
    Banner dialBanner;
    @BindView(R.id.rv_wangyi)
    RecyclerView rvWangyi;
    @BindView(R.id.mdialpad_view)
    DialPadView mDialPadView;
    Unbinder unbinder;
    private List<RecentCallsItemBean> dataList;
    private String scollText;
    private Map<String, String> params;
    private String paramstr;
    private AnimationController mAnimController;
    private boolean isture;

    public static TOPDialFragment2 newInstance() {
        Bundle args = new Bundle();
        TOPDialFragment2 fragment = new TOPDialFragment2();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_top_dial;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        mDialPadView.setOnCallListener(this);
        mDialPadView.setITextListener(this);

        RxBus.get().register(this);
        mAnimController = new AnimationController();
        dataList = new ArrayList<>();
        getDataList();
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        RecentCallsAdapter recentCallsAdapter = new RecentCallsAdapter(R.layout.item_recent_calls, dataList);
        rvWangyi.setAdapter(recentCallsAdapter);
        rvWangyi.setLayoutManager(new LinearLayoutManager(mActivity));
        params = new HashMap<>();
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
    public void textChange(String text) {
        //通话记录
        if (TextUtils.isEmpty(text)) {
//            adapter = new MobileAdapter(getActivity());
//            mLvShow.setAdapter(adapter);
//            adapter.setmDatas(dataList);
//            ListOncicKMotch();
            dialBanner.setVisibility(View.VISIBLE);
            showBannerModth();
//            mLvPhone.setVisibility(View.GONE);
        } else {
            //通讯录
//            mListAdapter = new MailistAdapter(mapMailList, getActivity());
//            mLvShow.setAdapter(mListAdapter);
//            initDataMailList();
//            mLvPhone.setVisibility(View.VISIBLE);
//            hideBannerModth();
            dialBanner.setVisibility(View.GONE);
//            filterData(text);
        }
    }

    @Override
    public void KeybordChange(boolean iskey) {
        isture = iskey;
        if (iskey == false) {
            showKeyBoard();

        } else {
            hideBannerModth();
        }
    }

    @Override
    public void onCall(String phone) {
        if (StringUtil.isEmpty(phone)) {
            ToastUtils.showToast("请输入手机号");
            return;
        }
    }

    @Override
    public void onTextChanged(String s) {

    }
    @Override
    public void onStart() {
        super.onStart();
        mDialPadView.onStart();
        mDialPadView.setFeedBackEnable(SpSetting.isDialFeedBackEnable(getActivity()));
    }

    @Override
    public void onPause() {
        super.onPause();
        mDialPadView.onPause();
        mDialPadView.clearInput();
    }

    @Override
    public void onStop() {
        super.onStop();
        mDialPadView.onStop();
        mDialPadView.clearInput();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mDialPadView.clearInput();
        RxBus.get().unRegister(this);
    }
}
