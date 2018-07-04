package com.mhy.shopingphone.ui.activity;

import android.view.View;
import android.widget.AdapterView;
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
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 空中充值
 */

public class RechargeCentreActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.querencz)
    Button querencz;
    @BindView(R.id.querenca)
    Button querenca;
    @BindView(R.id.phonenum)
    EditText phonenum;
    @BindView(R.id.moneynum)
    EditText moneynum;
    @BindView(R.id.spinner1)
    Spinner spinner1;
    @BindView(R.id.phonenums)
    EditText phonenums;
    @BindView(R.id.moneynums)
    EditText moneynums;
    @BindView(R.id.spinner2)
    Spinner spinner2;
    private String spinner;

    @Override
    protected int getLayout() {
        return R.layout.activity_recharge_centre;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        querencz.setOnClickListener(this);
        querenca.setOnClickListener(this);
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
            case R.id.querenca:
                gouwuyue();
                break;
        }
    }

    public void yuechongzhi() {     //空中充值
        spinner = spinner1.getSelectedItem().toString();
        Map<String, String> params = new HashMap<>();
        params.put("parentid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));
        params.put("money", moneynum.getText().toString());
        params.put("mobile", phonenum.getText().toString().trim());
        params.put("pushDate", spinner);
        params.put("type", "2");
        LogUtils.e("fengli111--" + params);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.CHONGZHI_URL)
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
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }

    public void gouwuyue() {      //购物充值
        spinner = spinner2.getSelectedItem().toString();
        Map<String, String> params = new HashMap<>();
        params.put("parentid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));
        params.put("shopMoney", moneynums.getText().toString());
        params.put("mobile", phonenums.getText().toString().trim());
        params.put("pushDate", spinner);
        params.put("type", "2");
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.CHONGZHI_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Logdwenglu bindUserEntity = new Gson().fromJson(response, Logdwenglu.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            ToastUtils.showToast(bindUserEntity.getData());
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }
}
