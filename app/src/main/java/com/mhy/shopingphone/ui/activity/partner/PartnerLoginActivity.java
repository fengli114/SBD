package com.mhy.shopingphone.ui.activity.partner;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StatusBarUtils;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.contract.partner.PartnerContract;
import com.mhy.shopingphone.contract.partner.PartnerLoginContract;
import com.mhy.shopingphone.model.bean.partner.PartnerLoginBean;
import com.mhy.shopingphone.presenter.partner.PartnerLoginPresenter;
import com.mhy.shopingphone.presenter.partner.PartnerPresenter;
import com.youth.xframe.utils.XEmptyUtils;

import android.widget.RelativeLayout.LayoutParams;

import com.youth.xframe.widget.XToast;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PartnerLoginActivity extends BaseMVPCompatActivity<PartnerLoginContract
        .LoginPresenter, PartnerLoginContract.ILoginModel> implements PartnerLoginContract
        .ILoginView {


    @BindView(R.id.img_login)
    ImageView imgLogin;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.al_title)
    RelativeLayout al_title;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.edit_partner_phone)
    EditText editPartnerPhone;
    @BindView(R.id.edit_partner_pwd)
    EditText editPartnerPwd;
    @BindView(R.id.cb_partner_remember)
    CheckBox cbPartnerRemember;
    @BindView(R.id.btn_partner_login)
    Button btnPartnerLogin;
    private String accountStr;
    private String pwdStr;
    private Map<String, String> params;
    private String paramstr;
    private static final String TAG = "PartnerLoginActivity";

    @Override
    protected void initView(Bundle savedInstanceState) {

        Util.setMarginsStatusBar(mContext, al_title);

        params = new HashMap<>();
        accountStr = (String) SharedPreferencesHelper.getInstance().getData("A", "");
        pwdStr = (String) SharedPreferencesHelper.getInstance().getData("P", "");

        LogUtils.e(TAG + accountStr + pwdStr);
        editPartnerPhone.setText(accountStr);
        editPartnerPwd.setText(pwdStr);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_partner_login;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
            return PartnerLoginPresenter.newInstance();
    }

    //判断 是都符合登录得条件
    private boolean isTure() {
        accountStr = editPartnerPhone.getText().toString().trim();
        pwdStr = editPartnerPwd.getText().toString().trim();
        if (XEmptyUtils.isEmpty(accountStr)) {
//            XToast.info("邮箱或用户不能为空~");
            ToastUtils.showToast("邮箱或用户不能为空~");
            return false;
        }
        if (XEmptyUtils.isEmpty(pwdStr)) {
//            XToast.info("密码不能为空~");
            ToastUtils.showToast("密码不能为空~");
            return false;
        }
        return true;
    }

    @OnClick({R.id.al_back, R.id.btn_partner_login, R.id.al_partner_remember})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.btn_partner_login:
                if (isTure()) {
                    goPartnerLogin();
                }
                break;
            case R.id.al_partner_remember:
                if (cbPartnerRemember.isChecked()) {
                    cbPartnerRemember.setChecked(false);
                } else {
                    cbPartnerRemember.setChecked(true);
                }
                break;
        }
    }

    //请求
    private void goPartnerLogin() {
        params.put("UserName", accountStr);//代理商id
        params.put("Password",pwdStr);

        String paramsstring = StringUtil.mapToJson(params);
        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.PARTNER_LOGIN_URLHead;
        mPresenter.goLogin(paramstr);
        HttpUtils.LogHeadStr = "合伙人收益:";
        Contant.IsDebug = true;
    }

    @Override
    public void goLogin(PartnerLoginBean partnerLoginBean) {
        if (partnerLoginBean.getCode().equals("0")) {
            initData(partnerLoginBean);
        }else {
            ToastUtils.showToast("账号或密码错误，请重新输入");
//            ToastUtils.showToast(partnerLoginBean.getMess());
        }
    }
    //登陆成功后操作
    private void initData(PartnerLoginBean partnerEntity) {
        Contant.PARTNER_ID = partnerEntity.getInfo().getID();
        float fMoney  = (float) partnerEntity.getInfo().getProfitMoney();
        BigDecimal b2   =   new   BigDecimal(fMoney);
        float   f2  =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
         LogUtils.e("合伙人yue"+f2);
         if (f2 == 0){
             Contant.profitMoney = 0.00 + "";
         }else {
             Contant.profitMoney = f2 + "";
         }
        //保存密码操作
        if (cbPartnerRemember.isChecked()){
            LogUtils.e(TAG+accountStr+pwdStr);
            SharedPreferencesHelper.getInstance().saveData("A",accountStr);//手机号码
            SharedPreferencesHelper.getInstance().saveData("P",pwdStr);//手机号码
        }else {
            SharedPreferencesHelper.getInstance().saveData("A","");//手机号码
            SharedPreferencesHelper.getInstance().saveData("P","");//手机号码
        }
        XDButils.saveBindUserInfo(partnerEntity.getPay());
        startActivity(PartnerShipActivity.class);
        finish();
    }
    @Override
    public void showNetworkError() {

    }
}
