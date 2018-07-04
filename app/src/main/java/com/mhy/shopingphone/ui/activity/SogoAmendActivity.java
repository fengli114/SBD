package com.mhy.shopingphone.ui.activity;

import android.view.View;
import android.widget.ArrayAdapter;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 商户添加删除
 */

public class SogoAmendActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.sp_feilv)   //费率
            Spinner sp_feilv;
    @BindView(R.id.sp_yinqing)    //通话引擎
            Spinner sp_yinqing;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_sogo_amend;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        adddata();
        //数据
        data_list = new ArrayList<String>();
        data_list.add("随便打");
        data_list.add("蓝树谷");
        data_list.add("够划算");
        data_list.add("互惠");
        data_list.add("随便打");
        data_list.add("蓝树谷");
        data_list.add("够划算");
        data_list.add("互惠");
        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp_feilv.setAdapter(arr_adapter);
        sp_yinqing.setAdapter(arr_adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;

        }
    }
    public void adddata() {
        Map<String, String> params = new HashMap<>();
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.GJSHOUYE_URL)
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
                            if (bindUserEntity.getJson().getUsers() != null) {

                            }
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }
}
