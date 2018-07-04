package com.mhy.shopingphone.ui.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.ImageUploadAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.qiyeguanjia.Modelers;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.umeng.commonsdk.debug.E;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 图片修改
 */

public class ImageXiugaiActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    RelativeLayout order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.btn_shangchuan)    //上传图片
            Button btn_shangchuan;
    @BindView(R.id.btn_queding)
    Button btn_queding;
    @BindView(R.id.et_youxianji)
    EditText et_youxianji;
    @BindView(R.id.et_urlers)
    EditText et_urlers;
    @BindView(R.id.et_wenzi)
    EditText et_wenzi;

    @Override
    protected int getLayout() {
        return R.layout.activity_image_xiugai;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        btn_shangchuan.setOnClickListener(this);
        btn_queding.setOnClickListener(this);
        et_youxianji.setText(String.valueOf(getIntent().getSerializableExtra("grade")));
        et_urlers.setText(String.valueOf(getIntent().getSerializableExtra("url")));
        et_wenzi.setText(String.valueOf(getIntent().getSerializableExtra("text")));
    }

    private void loadMyOrderList() {
        //请求参数
        Map<String, String> params = new HashMap<>();
        params.put("text", et_wenzi.getText().toString());
        params.put("id", String.valueOf(getIntent().getSerializableExtra("photos")));
        params.put("agentid", String.valueOf(getIntent().getSerializableExtra("user")));
        params.put("logo", String.valueOf(getIntent().getSerializableExtra("logo")));
        params.put("url", et_urlers.getText().toString());
        params.put("datastatus", "1");
        params.put("grade", et_youxianji.getText().toString());
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.PHOTOXIUGAI_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Modelers bindUserEntity = new Gson().fromJson(response, Modelers.class);
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
            case R.id.btn_shangchuan:   //上传图片

                break;
            case R.id.btn_queding:      //确认修改
                loadMyOrderList();
                break;
        }
    }
}
