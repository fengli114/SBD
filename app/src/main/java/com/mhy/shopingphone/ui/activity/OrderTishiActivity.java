package com.mhy.shopingphone.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.SingleModel;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.mhy.shopingphone.ui.huadan.CommonRecyclerViewAdapter;
import com.mhy.shopingphone.ui.huadan.CommonRecyclerViewHolder;
import com.mhy.shopingphone.ui.huadan.CustomDatePicker;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 无网络提示
 */

public class OrderTishiActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.order_back)  //返回按钮
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;

    @Override
    protected int getLayout() {
        return R.layout.activity_tishi;
    }
    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext,tou);
        order_back.setOnClickListener(this);
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
}
