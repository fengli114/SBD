package com.mhy.shopingphone.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.ImageUploadAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.AdvertisingBean;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 滚动文字
 */

public class MarqueeTextActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.edit_phone)
    EditText edit_phone;
    @BindView(R.id.et_shopId)
    EditText et_shopId;
    @BindView(R.id.btn_next)
    Button btn_next;
    @BindView(R.id.btn_shopadd)
    Button btn_shopadd;

    @Override
    protected int getLayout() {
        return R.layout.activity_marquee_text;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        loadMyOrder();
        order_back.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        btn_shopadd.setOnClickListener(this);
    }

    private void loadMyOrder() {
        //请求参数
        Map<String, String> params = new HashMap<>();
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));//用户id
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.GUNDONG_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        AdvertisingBean bindUserEntity = new Gson().fromJson(response, AdvertisingBean.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            if (bindUserEntity.getJson() != null) {
                                if (bindUserEntity.getJson().getShopinfoes() != null) {
                                    edit_phone.setText(bindUserEntity.getJson().getShopinfoes().get(0).getText().toString());
                                }
                                if (bindUserEntity.getJson().getShopTexts() != null) {
                                    et_shopId.setText(bindUserEntity.getJson().getShopTexts().get(0).getText().toString());
                                }
                            }

                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }

                    }
                });
    }

    private void loadfaxian() {
        //请求参数
        String strs;
        if (edit_phone.getText() != null) {
            strs = edit_phone.getText().toString();
        } else {
            strs = "";
        }
        Map<String, String> params = new HashMap<>();
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));//用户id
        params.put("text", strs);//用户id
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.GUNDONGFX_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        AdvertisingBean bindUserEntity = new Gson().fromJson(response, AdvertisingBean.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            ToastUtils.showToast(bindUserEntity.getData());
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }

                    }
                });
    }

    private void loadshop() {
        //请求参数
        String stres;
        if (et_shopId.getText() != null) {
            stres = et_shopId.getText().toString();
        } else {
            stres = "";
        }
        Map<String, String> params = new HashMap<>();
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));//用户id
        params.put("text", stres);//用户id
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.GUNDONGSP_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        AdvertisingBean bindUserEntity = new Gson().fromJson(response, AdvertisingBean.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            ToastUtils.showToast(bindUserEntity.getData());
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            case R.id.btn_next:
                loadfaxian();
                break;
            case R.id.btn_shopadd:
                loadshop();
                break;
        }
    }
}
