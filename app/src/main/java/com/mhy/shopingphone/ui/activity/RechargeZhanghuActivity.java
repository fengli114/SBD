package com.mhy.shopingphone.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.qiyeguanjia.Logdwenglu;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 商户充值
 */

public class RechargeZhanghuActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.phonenum)    //充值金额
            EditText phonenum;
    @BindView(R.id.moneynum)    //购物金充值
            EditText moneynum;
    @BindView(R.id.querencz)
    Button querencz;
    private String username;

    @Override
    protected int getLayout() {
        return R.layout.activity_recharge_sahnghu;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        Intent intent = getIntent();
        username = intent.getStringExtra("user_name");
        order_back.setOnClickListener(this);
        querencz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            case R.id.querencz:
                yuechongzhi();
                break;

        }
    }

    public void yuechongzhi() {     //空中充值
        String money, moneys;
        if (phonenum.getText().toString().trim() != null) {
            money = phonenum.getText().toString().trim();
        } else {
            money = "";
        }
        if (moneynum.getText().toString() != null) {
            moneys = moneynum.getText().toString();
        } else {
            moneys = "";
        }
        Map<String, String> params = new HashMap<>();
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));
        params.put("username", username);
        params.put("money", money);
        params.put("shopmoney", moneys);
        LogUtils.e("fengli111--" + params);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.SHGLCZ_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli--" + response);
                        Logdwenglu bindUserEntity = new Gson().fromJson(response, Logdwenglu.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            ToastUtils.showToast(bindUserEntity.getData());
                            finish();
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }


}
