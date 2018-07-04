package com.mhy.shopingphone.ui.activity.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mhy.sdk.RxManager;
import com.mhy.sdk.base.activity.BaseCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.UserBean;
import com.mhy.shopingphone.model.bean.UserInfoBean;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_UP_LOGO;

public class MyMoneyBgActivity extends BaseCompatActivity {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.tv_rc_data)
    TextView tvRcData;
    @BindView(R.id.tv_rc_blance)
    TextView tvRcBlance;
    @BindView(R.id.tv_gc_data)
    TextView tvGcData;
    @BindView(R.id.tv_gc_blance)
    TextView tvGcBlance;

    private final static int RECHARGE_CODE = 2;
    private final static int RECHARGE_CARD_CODE = 3;
    private Intent intent;
    private String result;

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        tvTitle.setText("我的钱包");
        tvRight.setText("交易明细");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_money_bg;
    }


    @OnClick({R.id.al_back, R.id.tv_right, R.id.pay_card, R.id.pay_phone, R.id.btn_rc_recharge, R.id.btn_gc_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.tv_right:
                startActivity(RechargeRecordActivity.class);
                break;
            case R.id.btn_rc_recharge:
//                Intent intent = new Intent();
//                intent.setClass(this, RechargeActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivityForResult(intent, RECHARGE_CODE);
                startActivity(RechargeActivity.class);
                break;
            case R.id.pay_phone:
//                intent = new Intent();
//                intent.setClass(this, RechargeCardActivty.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivityForResult(intent, RECHARGE_CARD_CODE);
                startActivity(RechargeActivity.class);
                break;
            case R.id.btn_gc_recharge:
//                intent = new Intent();
//                intent.setClass(this, RechargeCardActivty.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivityForResult(intent, RECHARGE_CARD_CODE);
                startActivity(RechargeCardActivty.class);
                break;
            case R.id.pay_card:
//                intent = new Intent();
//                intent.setClass(this, RechargeCardActivty.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivityForResult(intent, RECHARGE_CARD_CODE);
                startActivity(RechargeCardActivty.class);
                break;
        }
    }

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
                        ToastUtils.showToast("请检查您的网络");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli===" + response);
                        UserInfoBean bean = new Gson().fromJson(response, UserInfoBean.class);
                        if (bean.getErrorCode() == 2000) {
                            try {
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
                                    tvRcBlance.setText(str.substring(0, num) + "." + afterData.substring(0, 2));
                                } else {
                                    tvRcBlance.setText(str);  //话费余额
                                }
                                tvRcData.setText("有效期期至 " + bean.getJson().getTime());      //话费有效期
                                tvGcBlance.setText(bean.getJson().getInfo().getShopBalance() + "");          //购物余额
                                tvGcData.setText("有效期期至 " + bean.getJson().getInfo().getShopEnddate());  //购物有效期
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

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
    }
}
