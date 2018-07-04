package com.mhy.shopingphone.ui.fragment.login;

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

import com.alibaba.fastjson.JSONObject;
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
import com.mhy.shopingphone.contract.login.LoginCodeContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.presenter.login.LoginCodePresenter;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.mhy.shopingphone.widgets.customerview.SendValidateButton;
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

public class GetCodeFragment extends BaseMVPCompatFragment<LoginCodeContract
        .LoginCodePresenter, LoginCodeContract.ILoginCodeModel> implements
        LoginCodeContract.ILoginCodeView {
    @BindView(R.id.edit_code)
    EditText editCode;
    @BindView(R.id.btn_send_code)
    SendValidateButton btnSendCode;
    @BindView(R.id.lin_login)
    LinearLayout linLogin;
    @BindView(R.id.btn_next)
    Button btnNext;
    Unbinder unbinder;
    private String mRcode;
    private Map<String, String> params;
    private String paramstr;
    private String Num;
    private LoginActivty loginActivty;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return LoginCodePresenter.newInstance();
    }

    public static GetCodeFragment newInstance() {
        Bundle args = new Bundle();
        GetCodeFragment fragment = new GetCodeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_get_code;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        params = new HashMap<>();
        loginActivty = (LoginActivty) mActivity;
    }

    @Override
    public void getCode(Ceshi bean) {

    }

    @Override
    public void showNetworkError() {

    }

    @OnClick({R.id.btn_send_code, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send_code:
                mWaitPorgressDialog.show();
                goNext();
//                mPresenter.getCode(paramstr);
                break;
            case R.id.btn_next:
                if (validateParam()) {
                    loginActivty.showFragement(3);
                }
                break;
        }
    }

    //请求
    private void goNext() {
        btnSendCode.startTickWork();
        params.put("Mobile", loginActivty.getPhoneStr());
        params.put("ParentId", Contant.PARENTID);//代理商id
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.VERIFICATION_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli=====" + response);
                        hideProgressDialog();
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        String code = jsonObject.getString("errorCode");
                        if (code.equals("2000")) {
//                            ToastUtils.showToast(jsonObject.getString("data"));
                            Num = jsonObject.getString("json");
                        } else {
                            ToastUtils.showToast(jsonObject.getString("data"));
                        }
                    }
                });
//        String paramsstring = StringUtil.mapToJson(params);
//        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.CODE_URLHead;
//        LogUtils.e("获取验证码：" + params.toString());
    }

    /*
    *  非空验证
   * */
    private boolean validateParam() {
        mRcode = editCode.getText().toString();
        if (TextUtils.isEmpty(mRcode)) {
            ToastUtils.showToast("验证码不能为空");
            return false;
        }
        if (!mRcode.equals(Num)) {
            ToastUtils.showToast("验证码输入不正确");
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        editCode.setText("");
        if (btnSendCode != null) {
            btnSendCode.stopTickWork();
        }
    }

    @Override
    public void onDestroy() {
        if (btnSendCode != null) {
            btnSendCode.stopTickWork();
        }
        super.onDestroy();
    }
}
