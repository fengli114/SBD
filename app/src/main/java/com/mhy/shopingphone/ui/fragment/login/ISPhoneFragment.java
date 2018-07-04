package com.mhy.shopingphone.ui.fragment.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.blankj.utilcode.util.RegexUtils;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.login.PhoneLoginContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.presenter.login.PhoneLoginPresenter;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class ISPhoneFragment extends BaseMVPCompatFragment<PhoneLoginContract
        .PhoneLoginPresenter, PhoneLoginContract.IPhoneLoginModel> implements
        PhoneLoginContract.IPhoneLoginView {
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.lin_login)
    LinearLayout linLogin;
    @BindView(R.id.btn_next)
    Button btnNext;
    Unbinder unbinder;
    private Map<String, String> params;
    private String paramstr;
    private String Mess;
    private LoginActivty loginActivty;

    @Override
    public int getLayoutId() {
        return R.layout.frag_login_phone;
    }

    public static ISPhoneFragment newInstance() {
        Bundle args = new Bundle();
        ISPhoneFragment fragment = new ISPhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        params = new HashMap<>();
        loginActivty = (LoginActivty) mActivity;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PhoneLoginPresenter.newInstance();
    }

    @Override
    public void showWaitDialog(String waitMsg) {
        mWaitPorgressDialog.show();
    }

    @Override
    public void hideWaitDialog() {
        mWaitPorgressDialog.dismiss();
    }

    //请求
    private void goNext() {
        params.put("Mobile", editPhone.getText().toString().trim());
        params.put("Password"," ");
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
                        loginActivty.setPhoneStr(editPhone.getText().toString().trim());
                        if (bean.getErrorCode() == 1004) {
//                            loginActivty.setPhoneStr(editPhone.getText().toString().trim());
                            loginActivty.showFragement(1);
                            loginActivty.setTypeStr("1");
                           /* Mess = bean.getData();
                            if (Mess.equals("true")) {
                                loginActivty.showFragement(1);
                                loginActivty.setTypeStr("1");
                            } else {
                                loginActivty.showFragement(2);
                                loginActivty.setTypeStr("");
                            }*/
                        } else if (bean.getErrorCode() == 1000) {
                            loginActivty.showFragement(2);
                            loginActivty.setTypeStr("");
                        }else {
                            ToastUtils.showToast(bean.getData());
                        }
                    }
                });
//        String paramsstring = StringUtil.mapToJson(params);
//        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.IS_USER_URLHead;

    }

    /*
     请求完成操作
   */
    @Override
    public void next(Ceshi bean) {

    }

    @Override
    public void showNetworkError() {
        mWaitPorgressDialog.dismiss();
    }


    @OnClick(R.id.btn_next)
    public void onViewClicked() {
        if (isPhone()) {
          /*  loginActivty.showFragement(1);
            loginActivty.setTypeStr("1");*/
          goNext();
//            mPresenter.nowBuy(paramstr);
        }
    }

    private boolean isPhone() {
        String phone = editPhone.getText().toString().trim();
        if (XEmptyUtils.isEmpty(phone)) {
            ToastUtils.showToast("手机号不能为空");
            return false;
        }
        if (!RegexUtils.isMobileExact(phone)) {
            ToastUtils.showToast("请输入正确的手机号");
            return false;
        }
        return true;
    }
}
