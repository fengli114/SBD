package com.mhy.shopingphone.ui.fragment.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mhy.sdk.RxManager;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.SharedPreferencesUtils;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.login.LoginContract;
import com.mhy.shopingphone.global.MyApplication;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.greendao.DaoSession;
import com.mhy.shopingphone.model.bean.greendao.bean.BannerListBean;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.presenter.login.LoginPresenter;
import com.mhy.shopingphone.ui.MainActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class LoginFragment extends BaseMVPCompatFragment<LoginContract
        .LoginPresenter, LoginContract.ILoginModel> implements
        LoginContract.ILoginView {
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.cb_look)
    CheckBox cbLook;
    @BindView(R.id.rl_cb_look)
    RelativeLayout rlCbLook;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.regiter_check)
    CheckBox regiterCheck;
    Unbinder unbinder;
    private Map<String, String> params;
    private String paramstr;
    private LoginActivty loginActivty;
    private DaoSession daoInstant;


    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return LoginPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_login_;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        loginActivty = (LoginActivty) mActivity;
        daoInstant = MyApplication.getDaoInstant();
        daoInstant.deleteAll(BannerListBean.class);
        cbLook.setOnCheckedChangeListener(onCheckedChangeListener);
        params = new HashMap<>();
    }


    @OnClick({R.id.rl_cb_look, R.id.btn_next, R.id.tv_forget_pwd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_cb_look:
                if (cbLook.isChecked()) {
                    cbLook.setChecked(false);
                } else {
                    cbLook.setChecked(true);
                }
                break;
            case R.id.btn_next:
                if (isOk()) {
                    showWaitDialog("请稍等...");
                    goLogin();
//                    mPresenter.goLogin(paramstr);
                }
                break;
            case R.id.tv_forget_pwd:
                loginActivty.showFragement(2);
                break;
        }
    }

    //请求
    private void goLogin() {
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
                        LoginBean bean = new Gson().fromJson(response, LoginBean.class);
                        hideWaitDialog();
//                        String code = bean.getErrorCode()+"";
//                        String Mess = bean.getMess();
                        if (bean.getErrorCode() == 2000) {
                            SharedPreferencesHelper.getInstance().saveData("Mobile", bean.getJson().getMobile());//手机号码
                            SharedPreferencesHelper.getInstance().saveData("ShopID", bean.getJson().getShopid());//商户id
                            SharedPreferencesHelper.getInstance().saveData("AgentId", bean.getJson().getParentid());//代理Id
                            SharedPreferencesHelper.getInstance().saveData("UserId", bean.getJson().getId());//代理Id
//            SharedPreferencesUtils.setParam(startActivity, "islogin", true);
                         /*   Intent intent = new Intent(mActivity, MainActivity.class);
                            startActivity(intent);*/
                            mActivity.finish();
                        } else {
                            ToastUtils.showToast(bean.getData());
                        }
                    }
                });
       /* String paramsstring = StringUtil.mapToJson(params);
        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.LOGIN_URLHead;*/

    }

    @Override
    public void goLogin(LoginBean bean) {

    }

    @Override
    public void showNetworkError() {
        hideWaitDialog();
    }

    @Override
    public void showWaitDialog(String msg) {
        super.showWaitDialog(msg);
    }

    @Override
    public void hideWaitDialog() {
        super.hideWaitDialog();
    }

    private boolean isOk() {
        String pwdStr = editPwd.getText().toString();
        if (XEmptyUtils.isEmpty(pwdStr)) {
            ToastUtils.showToast("密码不能为空");
            return false;
        }
        if (!regiterCheck.isChecked()) {
            ToastUtils.showToast("请选择注册协议");
            return false;
        }
        return true;
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if (b) {
                editPwd.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD); //明文密码
            } else {
                editPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        }
    };

    private void getBanner() {
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));

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
                        if (bean2.getErrorCode() == 200) {
                            List<BannerListBean> listBeans = new ArrayList<>();
                            for (BannerBean.JsonBean bean : bean2.getJson()) {
                                if (bean.getAdtype() == 2) {
                                    SharedPreferencesHelper.getInstance().saveData("phonebg", Contant.URL_IMAGE + bean.getPath());//
                                }
                                if (bean.getAdtype() == 3) {
//                            strings.add(Api.GOODS + bean.getPath());
                                }
                                if (bean.getAdtype() == 7) {
                                    SharedPreferencesHelper.getInstance().saveData("AdressList", Api.NEWGOODS + bean.getPath());//
                                }
                                if (bean.getAdtype() == 4) {
                                    BannerListBean bannerListBean = new BannerListBean(null, Api.NEWGOODS + bean.getPath(), bean.getUrl());
                                    daoInstant.insert(bannerListBean);
                                }
                            }
                        }
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
        Util.showSoftInputFromWindow(mActivity, editPwd);
    }
}
