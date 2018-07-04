package com.mhy.shopingphone.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.LunboBean;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 企业管家修改密码
 */

public class StewardPawswordActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.et_oldpawe)
    EditText et_oldpawe;
    @BindView(R.id.et_newpaw)
    EditText et_newpaw;
    @BindView(R.id.et_okpaws)
    EditText et_okpaws;
    @BindView(R.id.btn_partner_login)
    Button btn_partner_login;
    private String oldpaw;
    private String newpaws;
    private String pward;

    @Override
    protected int getLayout() {
        return R.layout.activity_qyxiugaimima;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        btn_partner_login.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            case R.id.btn_partner_login:
                if (isPhone()) {
                    loadMyOrderList();
                }
                break;
        }
    }

    private void loadMyOrderList() {
        //请求参数
        Map<String, String> params = new HashMap<>();
        params.put("id", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));
        params.put("oldpassword", oldpaw);
        params.put("password", newpaws);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.XIUGAIMIMA_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli----" + response);
                        LunboBean bindUserEntity = new Gson().fromJson(response, LunboBean.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            ToastUtils.showToast(bindUserEntity.getData());
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }

    private boolean isPhone() {
        oldpaw = et_oldpawe.getText().toString().trim();
        newpaws = et_newpaw.getText().toString().trim();
        pward = et_okpaws.getText().toString().trim();
        if (XEmptyUtils.isEmpty(oldpaw)) {
            ToastUtils.showToast("原密码不能为空");
            return false;
        }
        if (XEmptyUtils.isEmpty(newpaws)) {
            ToastUtils.showToast("新密码不能为空");
            return false;
        }
        if (!newpaws.equals(pward)) {
            ToastUtils.showToast("两次输入新密码不一致");
            return false;
        }
        return true;
    }
}
