package com.mhy.shopingphone.ui.activity.recharge;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.recharge.RechargeContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.RechargeBean;
import com.mhy.shopingphone.presenter.recharge.RechargePresenter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;

public class ReChargePhoneActivty extends BaseMVPCompatActivity<RechargeContract.RechargePresenter
        , RechargeContract.IRechargeModel> implements
        RechargeContract.IRechargeView {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.img_orcode)
    ImageView imgOrcode;
    @BindView(R.id.al_orcode)
    RelativeLayout alOrcode;
    @BindView(R.id.title_recharge)
    RelativeLayout titleRecharge;
    @BindView(R.id.vis_rech)
    View visRech;
    @BindView(R.id.img_picture)
    ImageView imgPicture;
    @BindView(R.id.lin_card)
    LinearLayout linCard;
    @BindView(R.id.et_mobiles)
    EditText etMobiles;
    @BindView(R.id.et_cardpwd)
    EditText etCardpwd;
    @BindView(R.id.et_cardnum)
    EditText etCardid;
    @BindView(R.id.lin_edit)
    LinearLayout linEdit;
    @BindView(R.id.regiter_check)
    CheckBox regiterCheck;
    @BindView(R.id.lin_regis)
    LinearLayout linRegis;
    @BindView(R.id.frag_recharge)
    Button fragRecharge;
    @BindView(R.id.lin_confirm)
    LinearLayout linConfirm;

    private final static int SCANNIN_GREQUEST_CODE = 1;
    private final static int RECHARGE_CODE = 2;

    private Map<String, String> params;
    private String paramstr;
    private String edphone;
    private String echargecard;
    private String echargeid;

    @Override
    protected void onResume() {
        super.onResume();
        etCardpwd.setText(Contant.SCAN_QR_CODE);
        fragRecharge.setEnabled(true);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        ImageView tou = (ImageView) findViewById(R.id.tou);
        Util.setStatusBarHeigh(this, tou);
        params = new HashMap<>();
        etMobiles.setText((String) SharedPreferencesHelper.getInstance().getData("Mobile", ""));
        //mListModel.add(bean);
        Glide.with(getBaseContext()).load((String) SharedPreferencesHelper.getInstance().getData("Recharge", "")).into(imgPicture);
    }

    //请求
    private void goRecharge(String substr) {
        showWaitDialog("请稍等...");
        final String substres = substr;
        fragRecharge.setEnabled(false);
        params.put("Mobile", edphone);
        params.put("Pwd", substr);
        params.put("Cardno", echargeid);
        params.put("ParentID", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));
//        LogUtils.e("充值："+params.toString());
//        String paramsstring = StringUtil.mapToJson(params);
//        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.RECHARGE_URLHead;
//        mPresenter.goRecharge(paramstr);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.CARDPAY_URL)
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli===="+response);
                        RechargeBean ceshi = new Gson().fromJson(response, RechargeBean.class);
                        hideWaitDialog();
                        fragRecharge.setEnabled(true);
                        if (ceshi.getErrorCode() == 2000) {
                            ToastUtils.showToast("充值成功");
//                            SharedPreferencesHelper.getInstance().saveData("ShopID", ceshi.getShopID());//手机号码
//                            SharedPreferencesHelper.getInstance().saveData("UserId", ceshi.getID());//代理Id
                            finish();
                        } else if (ceshi.getErrorCode() == 1005) {
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
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                                            if (ceshi.getErrorCode() == 2000) {
                                                SharedPreferencesHelper.getInstance().saveData("Tokens", ceshi.getData());//代理Id
                                                goRecharge(substres);
                                            } else {
                                                ToastUtils.showToast("网络异常");
                                            }
                                        }
                                    });
                        } else {
                            ToastUtils.showToast(ceshi.getData());
                        }

                    }
                });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_re_charge_activty;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return RechargePresenter.newInstance();
    }

    @OnClick({R.id.al_back, R.id.al_orcode, R.id.frag_recharge})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.al_orcode:
                Intent intent = new Intent();
                intent.setClass(this, ScanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                break;
            case R.id.frag_recharge:
                if (validateParam()) {
                    //String mobile = et_mobiles.getText().toString().trim();
                    String card_pwd = etCardpwd.getText().toString().trim();
                    echargeid = etCardid.getText().toString().trim();
                    goRecharge(card_pwd);
                }
                break;
        }
    }

    /*
     * 非空验证
     * */
    private boolean validateParam() {
        edphone = etMobiles.getText().toString().trim();
        if (TextUtils.isEmpty(edphone)) {
            ToastUtils.showToast("手机号码不能为空");
            return false;
        }
        if (!RegexUtils.isMobileExact(edphone)) {
            ToastUtils.showToast("请输入正确的手机号");
            return false;
        }
        echargecard = etCardpwd.getText().toString().trim();
        if (TextUtils.isEmpty(echargecard)) {
            ToastUtils.showToast("充值卡密码不能为空");
            return false;
        }
        if (!regiterCheck.isChecked()) {
            ToastUtils.showToast("请选择注册协议");
            return false;
        }
        echargeid = etCardid.getText().toString().trim();
        if (TextUtils.isEmpty(echargeid)) {
            ToastUtils.showToast("充值卡卡号不能为空");
            return false;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_FIRST_USER) {
                    String result = data.getStringExtra("result");
                    String substr = result.substring(0, result.length() - 2);
                    echargeid = etCardid.getText().toString().trim();
                    goRecharge(substr);
                }
                break;
        }
    }

    @Override
    public void goRecharge(RechargeBean ceshi) {

    }

    @Override
    public void showNetworkError() {

    }
}
