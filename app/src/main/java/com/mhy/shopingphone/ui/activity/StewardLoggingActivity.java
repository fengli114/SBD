package com.mhy.shopingphone.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.google.gson.Gson;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.DownloadUtil;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.AndroidBean;
import com.mhy.shopingphone.model.qiyeguanjia.Logginges;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2018/4/23 0023.
 * 描述：
 */

public class StewardLoggingActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.iv_back)
    ImageView order_back;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.edit_phone)
    EditText editPhone;
    @BindView(R.id.edit_pwd)
    EditText editPwd;
    @BindView(R.id.regiter_check)
    CheckBox regiter_check;
    @BindView(R.id.tv_forget_pwd)
    TextView tv_forget_pwd;
    @BindView(R.id.cb_look)
    CheckBox cbLook;
    @BindView(R.id.rl_cb_look)
    RelativeLayout rlCbLook;
    @Override
    protected int getLayout() {
        return R.layout.activity_steward_logging;
    }

    @Override
    protected void initDate() {
        String user = (String) SharedPreferencesHelper.getInstance().getData("useracc", "");
        String pwd = (String) SharedPreferencesHelper.getInstance().getData("userpwd", "");
        if (user != null && pwd != null) {
            editPhone.setText(user);
            editPwd.setText(pwd);
        }
        order_back.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        tv_forget_pwd.setOnClickListener(this);
        cbLook.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_next:
                if (isPhone()) {
                    dologo();
                }
                break;
            case R.id.tv_forget_pwd:
                ToastUtils.showToast("请联系您的客服");
                break;
        }
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
    //登录接口
    public void dologo() {
        Map<String, String> params = new HashMap<>();
        params.put("useracc", editPhone.getText().toString());
        params.put("userpwd", editPwd.getText().toString());
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.GJDENGLU_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli--" + response);
                        if (regiter_check.isChecked()) {
                            SharedPreferencesHelper.getInstance().saveData("useracc", editPhone.getText().toString());//代理Id
                            SharedPreferencesHelper.getInstance().saveData("userpwd", editPwd.getText().toString());//代理Id
                        }else {
                            SharedPreferencesHelper.getInstance().saveData("useracc","");//代理Id
                            SharedPreferencesHelper.getInstance().saveData("userpwd", "");//代理Id
                        }
                        Logginges bindUserEntity = new Gson().fromJson(response, Logginges.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            SharedPreferencesHelper.getInstance().saveData("adminUserId", bindUserEntity.getJson().getAdminUserId());//代理Id
                            Intent intent = new Intent(mContext, SteWardActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("info", bindUserEntity);
                            intent.putExtras(bundle);
                            startActivity(intent);
                            finish();
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }

    private boolean isPhone() {
        String phone = editPhone.getText().toString().trim();
        String pward = editPwd.getText().toString().trim();
        if (XEmptyUtils.isEmpty(phone)) {
            ToastUtils.showToast("手机号不能为空");
            return false;
        }
        if (XEmptyUtils.isEmpty(pward)) {
            ToastUtils.showToast("密码不能为空");
            return false;
        }
        return true;
    }
}
