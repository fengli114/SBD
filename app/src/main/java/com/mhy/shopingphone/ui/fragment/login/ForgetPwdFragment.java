package com.mhy.shopingphone.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.login.RegisterContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.Logining;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.presenter.login.RegisterPresenter;
import com.mhy.shopingphone.ui.MainActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class ForgetPwdFragment extends BaseMVPCompatFragment<RegisterContract
        .RegisterPresenter, RegisterContract.IRegisterModel> implements
        RegisterContract.IRegisterView {
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.lin_login)
    LinearLayout linLogin;
    @BindView(R.id.edit_pwd_sure)
    EditText editPwdSure;
    @BindView(R.id.btn_next)
    Button btnNext;
    Unbinder unbinder;
    private Map<String, String> params;
    private String paramstr;
    private String pwdStr;
    private String mPwd1;
    private LoginActivty loginActivty;

    public static ForgetPwdFragment newInstance() {
        Bundle args = new Bundle();
        ForgetPwdFragment fragment = new ForgetPwdFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return RegisterPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_forget_pwd;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        params = new HashMap<>();
        loginActivty = (LoginActivty) mActivity;
        if (XEmptyUtils.isEmpty(loginActivty.getTypeStr())) {
            btnNext.setText("登录");
        } else {
            btnNext.setText("修改");
        }
    }

    @Override
    public void goRegister(Ceshi bean) {
        LogUtils.e("注册：" + bean.getCode());

    }

    @Override
    public void forgetPwd(Ceshi bean) {
        LogUtils.e("修改密码：" + bean.getCode());

    }


    //LOGIN请求
    private void goLogin() {
        params.clear();
        params.put("Mobile", loginActivty.getPhoneStr());
        params.put("Password", editPwd.getText().toString());
        params.put("ParentId", Contant.PARENTID);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.ENTER_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        hideWaitDialog();
                        Logining bean = new Gson().fromJson(response, Logining.class);
//                        String code = bean.getErrorCode();
//                        String Mess = bean.getMess();
                        if (bean.getErrorCode() == 2000) {
                            SharedPreferencesHelper.getInstance().saveData("Mobile", bean.getJson().getMobile());//手机号码
                            SharedPreferencesHelper.getInstance().saveData("ShopID", bean.getJson().getShopid());//手机号码
                            SharedPreferencesHelper.getInstance().saveData("AgentId", bean.getJson().getParentid());//代理Id
                            SharedPreferencesHelper.getInstance().saveData("UserId", bean.getJson().getId());//商户Id
//            SharedPreferencesUtils.setParam(startActivity, "islogin", true);
                            Intent intent = new Intent(mActivity, MainActivity.class);
                            startActivity(intent);
                            mActivity.finish();
                        } else {
                            ToastUtils.showToast(bean.getData());
                        }
                    }
                });
//        String paramsstring = StringUtil.mapToJson(params);
//        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.LOGIN_URLHead;
//        mPresenter.goLogin(paramstr);

    }

    //Regiter请求   注册
    private void goRegister() {
        params.put("Mobile", loginActivty.getPhoneStr());
        params.put("ParentId", Contant.PARENTID);//代理商id
        params.put("Password", editPwd.getText().toString());
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.LOGIN_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Ceshi bean = new Gson().fromJson(response, Ceshi.class);
                        LogUtils.e("注册：" + bean.getMess());
                        if (bean.getErrorCode() == 2000) {
                            //btn_send_code.setmDisableTime(actModel.getLesstime());
//                            ToastUtils.showToast(bean.getMess());
                            loginActivty.showFragement(1);
                        } else {
                            ToastUtils.showToast(bean.getData());
                        }
                    }
                });
        LogUtils.e("注册：" + params.toString());
    }

    //new PWD请求  修改密码
    private void goForgetPwd() {
        params.put("Mobile", loginActivty.getPhoneStr());
        params.put("ParentId", Contant.PARENTID);//代理商id
        params.put("Theme", pwdStr);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.PASSWORD_URL)
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Ceshi bean = new Gson().fromJson(response, Ceshi.class);
                        LogUtils.e("修改密码：" + bean.getMess());
                        if (bean.getErrorCode() == 2000) {
                            ToastUtils.showToast(bean.getMess());
                            SharedPreferencesHelper.getInstance().saveData("Mobile", loginActivty.getPhoneStr());//手机号码
                            goLogin();//掉登录接口
                        } else if (bean.getErrorCode() == 1005) {
                            Map<String, String> parames = new HashMap<>();
                            parames.put("Mobile", loginActivty.getPhoneStr());
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
                                                goForgetPwd();
                                            } else {
                                                ToastUtils.showToast("请检查您的网络");
                                            }
                                        }
                                    });
                        } else {
                            ToastUtils.showToast(bean.getData());
                        }
                    }
                });
//        String paramsstring = StringUtil.mapToJson(params);
//        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.GET_NEW_PWD_URLHead;
//        LogUtils.e("修改密码：" + params.toString());
    }

    @Override
    public void goLogin(LoginBean bean) {

    }

    @Override
    public void showNetworkError() {

    }


    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if (valiCodeParam()) {
            if (XEmptyUtils.isEmpty(loginActivty.getTypeStr())) {
                goRegister();
//                mPresenter.goRegister(paramstr);
            } else {
                goForgetPwd();
//                mPresenter.forgetPwd(paramstr);
            }
        }
    }

    private boolean valiCodeParam() {
        pwdStr = editPwdSure.getText().toString().trim();
        mPwd1 = editPwd.getText().toString().trim();
        if (TextUtils.isEmpty(pwdStr) && TextUtils.isEmpty(mPwd1)) {
            ToastUtils.showToast("密码或确定密码不能为空~");
            return false;
        }
        if (!mPwd1.equals(pwdStr)) {
            ToastUtils.showToast("密码和确定密码不一致~");
            return false;
        }
        return true;
    }
}
