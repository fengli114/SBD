package com.mhy.shopingphone.ui.fragment.phone.child.tab.top;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.blankj.utilcode.util.EmptyUtils;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.rxbus.Subscribe;
import com.mhy.sdk.utils.CharacterParser;
import com.mhy.sdk.utils.FirstLetterUtil;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.LvAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.phone.top.TOPDialContract;
import com.mhy.shopingphone.global.MyApplication;
import com.mhy.shopingphone.model.bean.GroupMemberBean;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.greendao.DaoSession;
import com.mhy.shopingphone.model.bean.greendao.bean.BannerListBean;
import com.mhy.shopingphone.model.bean.rxbus.EventBusTwo;
import com.mhy.shopingphone.model.bean.rxbus.RxClickPhoneBean;
import com.mhy.shopingphone.presenter.phone.top.TOPDialPresenter;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.mhy.shopingphone.widgets.DialPadView;
import com.mhy.shopingphone.widgets.dialpadview.IKoyboadLister;
import com.mhy.shopingphone.widgets.dialpadview.ITextListener;
import com.mhy.shopingphone.widgets.dialpadview.sp.SpSetting;
import com.mhy.shopingphone.widgets.phone.AnimationController;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_BANNER;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_PHONE_DIALPAFVIEW;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_PHONE_TOP;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述： 拨号盘fragment
 */

public class TOPDialFragment extends BaseMVPCompatFragment<TOPDialContract.TOPDialPresenter,
        TOPDialContract.ITOPDialModel> implements TOPDialContract.ITOPDialView, DialPadView.onCallListener
        , DialPadView.onTextChangedListener
        , ITextListener, IKoyboadLister {

    @BindView(R.id.dial_banner)
    Banner dialBanner;
    @BindView(R.id.mdialpad_view)
    DialPadView mDialPadView;
    @BindView(R.id.list_phone)
    ListView mLvPhone;
    @BindView(R.id.anniu)
    com.mhy.shopingphone.ui.anniu.DragFloatingActionButton anniu;
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};

    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;
    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    private String scollText;
    private Map<String, String> params;
    private String paramstr;
    private AnimationController mAnimController;
    private boolean isture;
    private DaoSession daoInstant;
    private List<String> strUrl;
    private List<GroupMemberBean> sourceDateList = new ArrayList<>();
    private List<GroupMemberBean> sourceDateList2 = new ArrayList<>();
    private List<String> mID = new ArrayList<>();
    private Cursor phone;
    private GetPhone2 getPhone2;

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
        mDialPadView.setOnCallListener(this);
        mDialPadView.setITextListener(this);
        daoInstant = MyApplication.getDaoInstant();
        daoInstant.deleteAll(BannerListBean.class);
        RxBus.get().register(this);
        mAnimController = new AnimationController();
//        getPhone = new GetPhone();
//        getPhone.execute();
        params = new HashMap<>();
        loadBannerData();
        anniu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.skip(mActivity, "http://www.lertui.com/game/dzp/index.html?m_id=40", "");
            }
        });
    }

    private void getPhoneDetail() {
        // ContentProvider展示数据类似一个单个数据库表
        // ContentResolver实例带的方法可实现找到指定的ContentProvider并获取到ContentProvider的数据
        ContentResolver reContentResolverol = mContext.getContentResolver();
        // URI,每个ContentProvider定义一个唯一的公开的URI,用于指定到它的数据集
        Uri contactData = ContactsContract.Contacts.CONTENT_URI;
        // 查询就是输入URI等参数,其中URI是必须的,其他是可选的,如果系统能找到URI对应的ContentProvider将返回一个Cursor对象.
        Cursor cursor = mActivity.managedQuery(contactData, null, null, null, null);
        if (cursor != null) {
            // 条件为联系人ID
            while (cursor.moveToNext()) {
                String contactId = cursor.getString(cursor
                        .getColumnIndex(ContactsContract.Contacts._ID));
                if (!EmptyUtils.isEmpty(contactId)) {
                    mID.add(contactId);
                }
            }
            if (Build.VERSION.SDK_INT < 14) {
                cursor.close();
            }
            ceshi();
        }
    }

    private void ceshi() {
        if (mID == null && mID.size() == 0) {
            return;
        }
        ContentResolver reContentResolverol = mContext.getContentResolver();
        for (int i = 0; i < mID.size(); i++) {
            // 获得DATA表中的电话号码，条件为联系人ID,因为手机号码可能会有多个
            phone = reContentResolverol.query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = "
                            + mID.get(i), null, null);
            if (phone != null) {
                while (phone.moveToNext()) {
                    String usernumber = phone
                            .getString(phone
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    String username = phone
                            .getString(phone
                                    .getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    Log.e("phone：", username + ":" + usernumber);
                    GroupMemberBean bean = new GroupMemberBean();
                    bean.setName(usernumber);
                    bean.setPhone(username);
                    bean.setContact_id(mID.get(i));
                    boolean isAgain = false;
                    for (GroupMemberBean groupBean : sourceDateList2) {
                        if (username.equals(groupBean.getName())) {
                            isAgain = true;
                        }
                    }
                    if (!isAgain) {
                        sourceDateList2.add(bean);
                    }

                }

            }

        }
        LogUtils.e("haha:" + sourceDateList2.size());
        if (Build.VERSION.SDK_INT < 14) {
            phone.close();
        }

    }

    /**
     * 得到手机通讯录联系人信息
     **/
    private void getPhoneContacts() {
        ContentResolver resolver = getActivity().getContentResolver();

        // 获取手机联系人  PHONES_PROJECTION
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PHONES_PROJECTION, null, null, null);
        if (phoneCursor != null) {
            sourceDateList.clear();
            while (phoneCursor.moveToNext()) {
                // 得到手机号码
                String phoneNumber = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
                // 当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;

                // 得到联系人名称
                String contactName = phoneCursor
                        .getString(PHONES_NUMBER_INDEX);
                //筛选
                String phone_cache = phoneNumber;
                if (phoneNumber.contains(" ")) {
                    phone_cache = phoneNumber.replace(" ", "").trim();
                }
                int contact_id = phoneCursor.getInt(phoneCursor.getColumnIndex("contact_id"));
                GroupMemberBean bean = new GroupMemberBean();
                bean.setName(contactName);
                bean.setPhone(phoneNumber);
                bean.setContact_id(contact_id + "");

                boolean isAgain = false;
                for (GroupMemberBean groupBean : sourceDateList) {
                    if (contactName.equals(groupBean.getName())) {
                        isAgain = true;
                    }
                }
                if (!isAgain) {
                    sourceDateList.add(bean);
                }
            }

        }
        if (Build.VERSION.SDK_INT < 14) {
            phoneCursor.close();
        }
    }

    //请求
    private void loadBannerData() {
        //请求参数
        params.put("ParentId", String.valueOf(Contant.PARENTID));//代理商id
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
//        String paramsstring = StringUtil.mapToJson(params);
////        paramstr = paramsstring.replaceAll("\n", "").trim();
////        //设置头部
////        HttpUtils.headStr = Contant.ALLADV_URLHead;
//        LogUtils.e("fengli===="+paramsstring+"555555"+params);
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
                        BannerBean bean2 = new Gson().fromJson(response, BannerBean.class);
                        List<String> strings = new ArrayList<>();
                        strUrl = new ArrayList<>();
                        List<BannerListBean> listBeans = new ArrayList<>();
                        for (BannerBean.JsonBean bean : bean2.getJson()) {
                            if (bean.getAdtype() == 2) {
                                SharedPreferencesHelper.getInstance().saveData("phonebg", bean.getPath());//
                            }
                            if (bean.getAdtype() == 3) {
                                strings.add(bean.getPath());
                                strUrl.add(bean.getUrl());
                            }
                            if (bean.getAdtype() == 6) {
                                //mListModel.add(bean);
                                SharedPreferencesHelper.getInstance().saveData("Recharge", bean.getPath());//
                            }
                            if (bean.getAdtype() == 8) {
                                //mListModel.add(bean);
                                SharedPreferencesHelper.getInstance().saveData("Recharge2", bean.getPath());//
                            }
                            if (bean.getAdtype() == 7) {
                                SharedPreferencesHelper.getInstance().saveData("AdressList", bean.getPath());//
                            }
                            if (bean.getAdtype() == 4) {
                                BannerListBean bannerListBean = new BannerListBean(null, bean.getPath(), bean.getUrl());
                                daoInstant.insert(bannerListBean);
                            }
                            RxBus.get().send(RX_BUS_CODE_BANNER);
                        }
                        LogUtils.e("fengli" + strings);
                        Util.setBanner(null, strings, dialBanner);//设置轮播图
                        //设置点击事件，下标是从1开始
                        dialBanner.setOnBannerListener(new OnBannerListener() {
                            @Override
                            public void OnBannerClick(int position) {
                                if (!TextUtils.isEmpty(strUrl.get(position))) {
                                    WebViewActivity.skip(getActivity(), strUrl.get(position), "详情");
                                }
                                LogUtils.e("地址：" + strUrl.get(position));
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
     * RxBus
     */
    @Subscribe(code = RX_BUS_CODE_PHONE_DIALPAFVIEW)
    public void rxBusEvent2(EventBusTwo event) {
        isture = event.istrue();
        if (isture) {
            dialBanner.setVisibility(View.VISIBLE);
        } else {
            dialBanner.setVisibility(View.GONE);
        }
    }

    /**
     * RxBus
     */
    @Subscribe(code = RX_BUS_CODE_PHONE_TOP)
    public void rxBusEvent(RxClickPhoneBean rxClickPhoneBean) {
        if (!rxClickPhoneBean.isClick) {
            showKeyBoard();
            showBannerModth();
        } else {
            hideBannerModth();
            hideKeyBoard();
            mLvPhone.setVisibility(View.GONE);
        }
    }


    //显示键盘
    public void showKeyBoard() {
        if (mAnimController != null) {
            mAnimController.slideTranslateInFromVBottom(mDialPadView, 250, 75);
        }
    }

    //隐藏键盘
    public void hideKeyBoard() {
        if (mAnimController != null) {
            mAnimController.slideTranslateOutToBottom(mDialPadView, 250, 0);
        }
    }

    //显示轮播图的动画
    public void showBannerModth() {
        if (mAnimController != null) {
            mAnimController.slideFadeInFromTop(dialBanner, 250, 0);//从顶部下来
        }
    }

    //隐藏轮播图的动画

    public void hideBannerModth() {
        if (mAnimController != null) {
            mAnimController.slideTranslateOutToTop(dialBanner, 250, 0);//从顶部上去
        }
    }

    @Override
    public void textChange(String text) {
        //通话记录
        if (TextUtils.isEmpty(text)) {
            dialBanner.setVisibility(View.VISIBLE);
            showBannerModth();
            mLvPhone.setVisibility(View.GONE);
        } else {
            mLvPhone.setVisibility(View.VISIBLE);
            dialBanner.setVisibility(View.GONE);
            filterData(text);
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
//        if (!getPhone.isCancelled()){
//            getPhone.cancel(true);
//        }
        if (!getPhone2.isCancelled()) {
            getPhone2.cancel(true);
        }
//        mDialPadView.clearInput();
        RxBus.get().unRegister(this);
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<GroupMemberBean> filterDateList = new ArrayList<GroupMemberBean>();
        if (sourceDateList != null && sourceDateList.size() > 0) {
            if (TextUtils.isEmpty(filterStr)) {
                filterDateList = sourceDateList;
            } else {
                try {
                    filterDateList.clear();
                    for (GroupMemberBean sortModel : sourceDateList) {
                        String name = sortModel.getName();
                        String phone_search = sortModel.getPhone();
                        if (phone_search.contains(" ")) {
                            phone_search = phone_search.replace(" ", "");
                        }
                        /**搜索条件分别为中文搜索、首字母简拼搜索、手机号码搜索*/
                     /*  if (name.indexOf(filterStr.toString()) != -1
                               || contains(name, filterStr) || phone_search.contains(filterStr)) {
                           filterDateList.add(sortModel);
                       }*/
                        if (name.indexOf(filterStr.toString()) != -1) {
                            filterDateList.add(sortModel);
                        }
                    }
                } catch (Exception e) {
                }

            }
        }
        if (filterDateList != null && filterDateList.size() > 0) {
            LvAdapter adapter = new LvAdapter(getContext());
            mLvPhone.setAdapter(adapter);
            adapter.setmDatas(filterDateList);
        }
    }


    public static boolean contains(String cotacNmae, String search) {
        if (TextUtils.isEmpty(cotacNmae)) {
            return false;
        }

        boolean flag = false;

        // 简拼匹配,如果输入在字符串长度大于6就不按首字母匹配了
        if (search.length() < 6) {
            String firstLetters = FirstLetterUtil.getFirstLetter(cotacNmae);
            // 不区分大小写
            Pattern firstLetterMatcher = Pattern.compile(search,
                    Pattern.CASE_INSENSITIVE);
            flag = firstLetterMatcher.matcher(firstLetters).find();
        }

        if (!flag) { // 如果简拼已经找到了，就不使用全拼了
            // 全拼匹配
            CharacterParser finder = CharacterParser.getInstance();
            finder.setResource(cotacNmae);
            // 不区分大小写
            Pattern pattern2 = Pattern
                    .compile(search, Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = pattern2.matcher(finder.getSpelling());
            flag = matcher2.find();
        }

        return flag;
    }

    //异步获取通讯录信息
    class GetPhone2 extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
//            showWaitDialog("通讯录加载中...");
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
//                getPhoneDetail();
                getPhoneContacts();
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getPhone2 = new GetPhone2();
        getPhone2.execute();
    }

}
