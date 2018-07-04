package com.mhy.shopingphone.ui.fragment.my.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mhy.sdk.RxManager;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.rxbus.Subscribe;
import com.mhy.sdk.utils.DownloadUtil;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.NetworkConnectionUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.AndroidBean;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.UserBean;
import com.mhy.shopingphone.model.bean.UserInfoBean;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.mhy.shopingphone.ui.activity.AboutWeActivity;
import com.mhy.shopingphone.ui.activity.OrderInquiryActivity;
import com.mhy.shopingphone.ui.activity.OrderTishiActivity;
import com.mhy.shopingphone.ui.activity.SteWardActivity;
import com.mhy.shopingphone.ui.activity.StewardLoggingActivity;
import com.mhy.shopingphone.ui.activity.TickingActivity;
import com.mhy.shopingphone.ui.activity.detail.PersonalDataActivity;
import com.mhy.shopingphone.ui.activity.order.MyOrderActivity;
import com.mhy.shopingphone.ui.activity.partner.PartnerLoginActivity;
import com.mhy.shopingphone.ui.activity.recharge.MyMoneyBgActivity;
import com.mhy.shopingphone.ui.activity.recharge.ReChargePhoneActivty;
import com.mhy.shopingphone.ui.activity.recharge.RechargeActivity;
import com.mhy.shopingphone.ui.activity.recharge.RechargeCardActivty;
import com.mhy.shopingphone.ui.activity.recharge.RechargeRecordActivity;
import com.mhy.shopingphone.ui.activity.recharge.RechargeZFBActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.mhy.shopingphone.ui.activity.tixian.BindUserEntity;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.mhy.shopingphone.widgets.XCRoundRectImageView;
import com.umeng.analytics.MobclickAgent;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.yuyh.library.EasyGuide;
import com.yuyh.library.constant.Constants;
import com.yuyh.library.support.HShape;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.xutils.common.util.LogUtil;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_MY;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_UP_LOGO;

/**
 * 作者： "RedRainM" on 2018/1/24 0024.
 * 描述：
 */

public class MyFragment extends BaseMVPCompatFragment {

    @BindView(R.id.al_my_share)
    LinearLayout alMyShare;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.rl_kefu)
    RelativeLayout rl_kefu;
    @BindView(R.id.civ_head)
    XCRoundRectImageView civHead;
    @BindView(R.id.tv_my_account)
    TextView tvMyAccount;
    @BindView(R.id.tv_my_data)
    TextView tvMyData;
    @BindView(R.id.tv_namees)
    TextView tv_namees;
    @BindView(R.id.lin_account)
    LinearLayout linAccount;
    @BindView(R.id.tv_card_money)
    TextView tvCardMoney;
    @BindView(R.id.tv_card_data)
    TextView tvCardData;
    @BindView(R.id.tv_is_shoper)
    TextView tv_is_shoper;
    @BindView(R.id.ll_my_discounts)
    LinearLayout llMyDiscounts;
    @BindView(R.id.ll_corporate_steward)
    LinearLayout ll_corporate_steward;
    @BindView(R.id.ll_partner_center)
    LinearLayout ll_partner_center;
    @BindView(R.id.ll_my_money)
    LinearLayout llMyMoney;
    @BindView(R.id.ll_my_kefu)
    LinearLayout llMyKefu;
    @BindView(R.id.ll_help_center)
    LinearLayout llHelpCenter;
    @BindView(R.id.ll_call_we)
    LinearLayout llCallWe;
    @BindView(R.id.ll_gwj)    //购物余额
            LinearLayout ll_gwj;
    @BindView(R.id.ll_yue)   //账户余额
            LinearLayout llyue;
    @BindView(R.id.btn_exit)
    Button btnExit;
    @BindView(R.id.ll_wangluo)
    LinearLayout ll_wangluo;
    @BindView(R.id.ll_about_we)      //关于我们
            LinearLayout llAboutWe;
    @BindView(R.id.ll_about_wes)     //商务反馈
            LinearLayout lltickling;
    @BindView(R.id.ll_my_discount)    // 话单查询
            LinearLayout myDiscount;
    @BindView(R.id.ll_my_mone)    // 交易明细
            LinearLayout myMone;
    @BindView(R.id.ll_my_kef)    // 商城订单
            LinearLayout myShoping;
    @BindView(R.id.iv_gengxin)
    ImageView iv_gengxin;
    Unbinder unbinder;

    //    private boolean isFirst = false;//更新
    private float version;
    private String upatepath;
    private UserInfoBean infoBean;
    private EasyGuide easyGuide;
    private String stres;

    public static MyFragment newInstance() {
        Bundle args = new Bundle();
        MyFragment fragment = new MyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("my");
        RxBus.get().register(this);
        if (!NetworkConnectionUtils.isNetworkConnected(mContext)) {
            ll_wangluo.setVisibility(View.VISIBLE);
        }
        if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
            btnExit.setText("立即登录");
            tvMyAccount.setText("未登录/注册");
            tv_is_shoper.setVisibility(View.GONE);
            ll_partner_center.setVisibility(View.GONE);
            tvMyData.setText(" ");
            tv_namees.setText(" ");
            tvCardMoney.setText("0");
            tvCardData.setText("0");
        } else {
//            if (isFirst) {
            getInfo();
//            }
            btnExit.setText("退出登录");
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_my;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //        Logger.e("RxBus.get().unRegister(this)");
        RxBus.get().unRegister(this);
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        getInfo();
    }

    @Override
    public void initData() {
        super.initData();
        try {
            checkVersion();//检测版本号
        } catch (Exception e) {
            e.printStackTrace();
        }
        //用来标记是否是第一次进入app
        String TAG = (String) SharedPreferencesHelper.getInstance().getData("show2", "");
        if (XEmptyUtils.isEmpty(TAG)) {
            SharedPreferencesHelper.getInstance().saveData("show2", "1231");
            llMyMoney.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // 加载完成后回调
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                        llMyMoney.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        llMyMoney.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }
                    // TODO 显示高亮布局！
//                    show();//直接显示引导层
                }
            });
        }
    }

    /**
     * RxBus
     */
    @Subscribe(code = RX_BUS_CODE_MY)
    public void rxBusEvent() {
        getInfo();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @OnClick({R.id.ll_yue, R.id.ll_gwj, R.id.al_my_share, R.id.lin_account, R.id.ll_about_wes, R.id.ll_my_mone, R.id.ll_my_kef, R.id.ll_my_discount, R.id.ll_partner_center, R.id.ll_corporate_steward, R.id.ll_my_discounts, R.id.ll_my_money, R.id.ll_my_kefu, R.id.ll_help_center, R.id.ll_call_we, R.id.ll_about_we, R.id.btn_exit, R.id.ll_wangluo})
    public void onViewClicked(View view) {
        if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
            startActivity(new Intent(mContext, LoginActivty.class));
            getInfo();
        } else {
            switch (view.getId()) {
                case R.id.ll_yue:   //余额充值
                    startNewActivity(ReChargePhoneActivty.class);
                    break;
                case R.id.ll_gwj:    //购物余额充值
                    startNewActivity(RechargeCardActivty.class);
                    break;
                case R.id.al_my_share:    //分享，弃用
                    break;
                case R.id.lin_account:       //个人资料
                    if (infoBean != null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("info", infoBean);
                        startNewActivity(PersonalDataActivity.class, bundle);
                    }
                    break;

                case R.id.ll_partner_center:       //商家登录
                    startNewActivity(StewardLoggingActivity.class);
//                    showDialogUps();
                   /* WebViewActivity.skip(mActivity,
                            Api.NEWGOODS,
                            "企业管家");*/
                    break;

//            case R.id.ll_corporate_steward:      //企业管家
//
////                startNewActivity(SteWardActivity.class);
//                break;
                case R.id.ll_my_discounts:        //在线客服
                    String url11 = "mqqwpa://im/chat?chat_type=wpa&uin=1558753783&version=1";
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
                    } catch (Exception e) {
                        // 未安装手Q或安装的版本不支持    showToast("未安装手Q或安装的版本不支持");
                        ToastUtils.showToast("未安装手Q或安装的版本不支持");
                    }
                    break;
                case R.id.ll_my_money:              //在线充值
                    startNewActivity(RechargeZFBActivity.class);
                    break;
                case R.id.ll_my_kefu:            //我的钱包
                   /* if (infoBean != null) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("info", infoBean);

                    }*/
                    startNewActivity(MyMoneyBgActivity.class);
                    break;
                case R.id.ll_my_discount:   //话单查询
                    startNewActivity(OrderInquiryActivity.class);
                    break;
                case R.id.ll_my_mone:     //交易明细
                    startNewActivity(RechargeRecordActivity.class);
                    break;
                case R.id.ll_my_kef:     //商城订单
                    startNewActivity(MyOrderActivity.class);
                    break;
                case R.id.ll_about_wes: //商务反馈
                    startNewActivity(TickingActivity.class);
                    break;
                case R.id.ll_help_center:       //我的收益
                    if (infoBean != null) {
                        startNewActivity(PartnerLoginActivity.class);
                    }
                    break;
                case R.id.ll_call_we:          //联系我们
                    WebViewActivity.skip(mActivity,
                            Api.NEWGOODS +Api.VIP + String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")),
                            "联系我们");
                    break;
                case R.id.btn_exit:                //退出登录
                    showDialogUp();
                    break;
                case R.id.ll_wangluo:          //无网络状态
                    startNewActivity(OrderTishiActivity.class);
                    break;
                case R.id.ll_about_we:      //关于我们
                    startNewActivity(AboutWeActivity.class);
                    break;
            }
        }

    }

    private void showDialogUp() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setMessage("是否退出登录").setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ll_partner_center.setVisibility(View.GONE);
                        btnExit.setText("立即登录");
                        tvMyAccount.setText("未登录/注册");
                        tv_is_shoper.setVisibility(View.GONE);
                        tvMyData.setText(" ");
                        tv_namees.setText(" ");
                        tvCardMoney.setText("0");
                        tvCardData.setText("0");
                        Glide.with(mContext).load("").crossFade(300).placeholder(R
                                .drawable.indivicenter_default_portrait).into(civHead);
                        SharedPreferencesHelper.getInstance().deliteData();
                    }
                }).
                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();

    }

    private void showDialogUps() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setMessage("模块优化中，请在电脑端操作").setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();
    }

    /**
     *
     */
    private void getInfo() {
        Map<String, String> params = new HashMap<>();
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        params.put("ParentId", Contant.PARENTID);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.USERINFO_URL)
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli===" + response);
                        UserInfoBean bean = new Gson().fromJson(response, UserInfoBean.class);
                        if (bean.getErrorCode() == 2000) {
                            ll_wangluo.setVisibility(View.GONE);
                            infoBean = bean;
                            if (bean.getJson() == null) return;
                            try {
                                tvMyAccount.setText(bean.getJson().getInfo().getMobile());
                                if (bean.getJson().getInfo().getUsername() != null) {
                                    tv_namees.setText(bean.getJson().getInfo().getUsername() + " | ");
                                }
                                LogUtils.e("时间差：" + getdata(bean.getJson().getTime()));
                                if (getdata(bean.getJson().getTime()) <= 15) {
                                    tvMyData.setTextColor(getResources().getColor(R.color.red));
                                } else {
                                    tvMyData.setTextColor(getResources().getColor(R.color.ivory));
                                }
                                tvMyData.setText(bean.getJson().getTime() + "到期");
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            float fMoney = Float.valueOf(bean.getJson().getMoney());
                            BigDecimal b2 = new BigDecimal(fMoney);
                            float f2 = b2.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
                            if (f2 == 0) {
                                Contant.profitMoney = 0.00 + "";
                            } else {
                                Contant.profitMoney = f2 + "";
                            }

                            Contant.gwjMoney = bean.getJson().getInfo().getShopBalance() + "";
                            tvCardData.setText(bean.getJson().getInfo().getShopBalance() + "");
                            String str = bean.getJson().getMoney();
                            if (str.indexOf(".") != -1) {
                                //获取小数点的位置
                                int num = 0;
                                //找到小数点在字符串中的位置,找到返回一个int类型的数字,不存在的话返回 -1
                                num = str.indexOf(".");
                                String dianAfter = str.substring(0, num + 1);//输入100.30,dianAfter = 100.
                                String afterData = str.replace(dianAfter, "");//把原字符(rateStr)串包括小数点和小数点前的字符替换成"",最后得到小数点后的字符(不包括小数点)
                                //判断小数点后字符的长度并做不同的操作,得到小数点后两位的字符串
                                if (afterData.length() < 2) {
                                    afterData = afterData + "0";
                                } else {
                                    afterData = afterData;
                                }
                                //返回元字符串开始到小数点的位置 + "." + 小数点后两位字符
//                                return str.substring(0, num) + "." + afterData.substring(0, 2);
                                tvCardMoney.setText(str.substring(0, num) + "." + afterData.substring(0, 2));
                            } else {
                                tvCardMoney.setText(str);
                            }
                            if (bean.getJson().isIsAuth()) {
                                tv_is_shoper.setVisibility(View.VISIBLE);
                                ll_partner_center.setVisibility(View.VISIBLE);
                            } else {
                                tv_is_shoper.setVisibility(View.GONE);
                                ll_partner_center.setVisibility(View.GONE);
                            }
                            String head_url = bean.getJson().getInfo().getPic();
                            if (head_url.indexOf("http") != -1) {
                                Contant.URL_IMAG_ICON = head_url;
                            } else {
                                Contant.URL_IMAG_ICON = Contant.URL_IMAGE + head_url;
                            }
                            Glide.with(mContext).load(Contant.URL_IMAG_ICON).crossFade(300).placeholder(R
                                    .drawable.indivicenter_default_portrait).into(civHead);
                            RxBus.get().send(RX_BUS_CODE_UP_LOGO);

                        } else if (bean.getErrorCode() == 1005) {
                            Map<String, String> parames = new HashMap<>();
                            parames.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
                            parames.put("ParentId", String.valueOf(Contant.PARENTID));
                            OkHttpUtils.post()
                                    .url(Api.NEWGOODS + Api.TOKEN_URL)
                                    .params(parames)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {
                                            ToastUtils.showToast("请检查您的网络");
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                                            if (ceshi.getErrorCode() == 2000) {
                                                SharedPreferencesHelper.getInstance().saveData("Tokens", ceshi.getData());//代理Id
                                                getInfo();
                                            } else {
//                                                ToastUtils.showToast("请检查您的网络");
                                            }
                                        }
                                    });
                        } else {
                            ToastUtils.showToast(bean.getData());
                        }
                    }
                });
//        String paramsstring = StringUtil.mapToJson(params);
//        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.PRSONAlINFORMATION_URLHead;
//        HttpUtils.LogHeadStr = "个人信息:";
//        Contant.IsDebug = true;
      /*  RxManager mRxManager = new RxManager();
        Observable<UserBean> compose = RetrofitCreateHelper.createApi(Api.class, Api.NEWGOODS).getUserInfo(paramsstring)
                .compose(RxHelper.<UserBean>rxSchedulerHelper());
        LogUtils.e("fenglizzzzz"+paramsstring);
        mRxManager.register(compose.subscribe(new Consumer<UserBean>() {
            @Override
            public void accept(UserBean bean) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ll_wangluo.setVisibility(View.VISIBLE);
                ToastUtils.showToast("网络异常，请检查网络~");
            }
        }));*/
    }

    public long getdata(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //跨年的情况会出现问题哦
        //如果时间为：2016-03-18 11:59:59 和 2016-03-19 00:00:01的话差值为 1
        Date fDate = sdf.parse(Util.time());
        Date oDate = sdf.parse(data);
        long daysBetween = (oDate.getTime() - fDate.getTime() + 1000000) / (3600 * 24 * 1000);
//        Calendar aCalendar = Calendar.getInstance();
//        aCalendar.setTime(fDate);
//        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
//        aCalendar.setTime(oDate);
//        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
//        int days = day2-day1;
        return daysBetween;
    }

    //对比本程序的版本号和最新程序的版本号
    public void checkVersion() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("ParentId", Contant.PARENTID);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.VERSIONING_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        AndroidBean bindUserEntity = new Gson().fromJson(response, AndroidBean.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            if (bindUserEntity.getJson().getAndroidversion() != null && bindUserEntity.getJson().getAndroidversion().length() > 0) {
                                try {
                                    LogUtils.e("fengli===" + bindUserEntity.getJson().getAndroidpath());
                                    stres = bindUserEntity.getJson().getAndroidversion();
                                    upatepath = bindUserEntity.getJson().getAndroidpath();
                                    LogUtils.e("fengli---" + upatepath);
                                    checkVersions();//检测更新
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

    //对比本程序的版本号和最新程序的版本号
    public void checkVersions() throws Exception {
        //如果检测本程序的版本号小于服务器的版本号，那么提示用户v更新
//        LogUtils.e(Float.parseFloat(getVersionName()) + "banben" + stres);
        if (stres != null && !getVersionName().equals(stres)) {
            iv_gengxin.setVisibility(View.VISIBLE);
            showDialogUpdate();//弹出提示版本更新的对话框
        } else {
            iv_gengxin.setVisibility(View.GONE);
            //否则吐司，说现在是最新的版本
            // SDToast.showToast("当前已经是最新的版本");
        }
    }

    /*
    * 获取当前程序的版本名
   */
    private String getVersionName() throws Exception {
        //获取packagemanager的实例
        PackageManager packageManager = mContext.getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = packageManager.getPackageInfo(mContext.getPackageName(), 0);
        return packInfo.versionName;
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Toast.makeText(mActivity, "选择确定哦", 0).show();
                        // loadNewVersionProgress();//下载最新的版本程序
                        new DownloadUtil(mActivity, upatepath);
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();
    }


    public void show() {
        int[] loc = new int[2];
        iv.getLocationOnScreen(loc);

        View tipsView = createTipsView();

        if (easyGuide != null && easyGuide.isShowing())
            easyGuide.dismiss();

        easyGuide = new EasyGuide.Builder(mActivity)
                // 增加View高亮区域，可同时显示多个
                .addHightArea(iv, HShape.RECTANGLE)
                // 复杂的提示布局，建议通过此方法，较容易控制
                .addView(tipsView, Constants.CENTER, -(llMyMoney.getHeight() * 2), new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                // 若点击作用在高亮区域，是否执行高亮区域的点击事件loc[1] + tlTabs.getHeight()
                .performViewClick(false)
                .addIndicator(R.drawable.walletguide_1, loc[0] - iv1.getWidth() / 2, loc[1] - 20)
                .addIndicator(R.drawable.walletguide_2, Constants.CENTER, loc[1] + llMyMoney.getHeight())
                .dismissAnyWhere(false)
                .build();
//        easyGuide.show();
    }

    private View createTipsView() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.phone_guide_view2, null);
        ImageView ivIsee = (ImageView) view.findViewById(R.id.iv_guide_yes);
        ivIsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easyGuide != null) {
                    llMyMoney.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            // 加载完成后回调
                            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                                llMyMoney.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            } else {
                                llMyMoney.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            }
                            // TODO 显示高亮布局！
                            show2();//直接显示引导层
                        }
                    });
                    easyGuide.dismiss();
                }
            }
        });

        return view;
    }

    public void show2() {
        int[] loc = new int[2];
        iv3.getLocationOnScreen(loc);

        View tipsView = createTipsView2();

        if (easyGuide != null && easyGuide.isShowing())
            easyGuide.dismiss();

        easyGuide = new EasyGuide.Builder(mActivity)
                // 增加View高亮区域，可同时显示多个
                .addHightArea(iv, HShape.RECTANGLE)
                // 复杂的提示布局，建议通过此方法，较容易控制
                .addView(tipsView, Constants.CENTER, -(ll_gwj.getHeight() * 2), new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                // 若点击作用在高亮区域，是否执行高亮区域的点击事件loc[1] + tlTabs.getHeight()
                .performViewClick(false)
                .addIndicator(R.drawable.gouwuquanguide_1, loc[0] - ll_gwj.getWidth(), loc[1] - ll_gwj.getHeight() / 3)
                .addIndicator(R.drawable.gouwuquanguide_2, Constants.CENTER, Constants.CENTER)
                .dismissAnyWhere(false)
                .build();


//        easyGuide.show();
    }

    private View createTipsView2() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.phone_guide_view2, null);
        ImageView ivIsee = (ImageView) view.findViewById(R.id.iv_guide_yes);
        ivIsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easyGuide != null) {
                    llMyMoney.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        @Override
                        public void onGlobalLayout() {
                            // 加载完成后回调
                            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                                llMyMoney.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            } else {
                                llMyMoney.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            }

                            // TODO 显示高亮布局！
                            show3();//直接显示引导层
                        }
                    });
                    easyGuide.dismiss();
                }
            }
        });

        return view;
    }

    public void show3() {
        int[] loc = new int[2];
        iv2.getLocationOnScreen(loc);

        View tipsView = createTipsView3();

        if (easyGuide != null && easyGuide.isShowing())
            easyGuide.dismiss();

        easyGuide = new EasyGuide.Builder(mActivity)
                // 增加View高亮区域，可同时显示多个
                .addHightArea(iv2, HShape.RECTANGLE)
                // 复杂的提示布局，建议通过此方法，较容易控制
                .addView(tipsView, Constants.CENTER, -(llMyKefu.getHeight() * 2), new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT))
                // 若点击作用在高亮区域，是否执行高亮区域的点击事件loc[1] + tlTabs.getHeight()
                .performViewClick(false)
                .addIndicator(R.drawable.onlineserviceguide_1, loc[0] - iv2.getWidth() / 2 - 10, loc[1] - 20)
                .addIndicator(R.drawable.onlineserviceguide_2, Constants.CENTER, loc[1] + llMyKefu.getHeight())
                .dismissAnyWhere(false)
                .build();

//        easyGuide.show();
    }

    private View createTipsView3() {

        View view = LayoutInflater.from(mActivity).inflate(R.layout.phone_guide_view2, null);

        ImageView ivIsee = (ImageView) view.findViewById(R.id.iv_guide_yes);
        ivIsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easyGuide != null) {
                    easyGuide.dismiss();
                }
            }
        });
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("my");
    }
}
