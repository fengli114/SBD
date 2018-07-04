package com.mhy.shopingphone.ui.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.activity.recharge.ScanActivity;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;

import org.xutils.common.util.LogUtil;

import butterknife.BindView;

/**
 * 作者： "RedRainM" on 2018/4/25 0025.
 * 描述：
 */

public class ConsumerActivity extends BaseActivity implements View.OnClickListener {
    private final static int SCANNIN_GREQUEST_CODE = 1;
    @BindView(R.id.tou)
            ImageView tou;
    @BindView(R.id.al_orcode)
    RelativeLayout alorcode;
    @BindView(R.id.et_mobiles)
    EditText etmobiles;
    @BindView(R.id.ll_xiaofei)
    LinearLayout llxiaofei;
    private String echargecard;
    @Override
    protected int getLayout() {
        return R.layout.activity_consumer;
    }
    //请求
    private void goRecharge(String substr) {
        LogUtil.e("fengli==="+substr);
//        showWaitDialog("请稍等...");
//        fragRecharge.setEnabled(false);
//        params.put("Mobile", edphone);
//        params.put("Pwd", substr);
//        params.put("Type", 1+"");
//        params.put("ParentID", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));
//        LogUtils.e("充值："+params.toString());
//        String paramsstring = StringUtil.mapToJson(params);
//        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.RECHARGE_URLHead;
//        mPresenter.goRecharge(paramstr);
    }
    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        alorcode.setOnClickListener(this);
        llxiaofei.setOnClickListener(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case SCANNIN_GREQUEST_CODE:
                if (resultCode == RESULT_FIRST_USER) {
                    String result = data.getStringExtra("result");
                    String substr = result.substring(0, result.length() - 2);
                    etmobiles.setText(substr);
//                    goRecharge(substr);
                }
                break;
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.al_orcode:   //二维码
                Intent intent = new Intent();
                intent.setClass(this, ScanActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivityForResult(intent, SCANNIN_GREQUEST_CODE);
                break;
            case R.id.ll_xiaofei:   //确定按钮
                if (validateParam()) {
                    String card_pwd = etmobiles.getText().toString().trim();
                    goRecharge(card_pwd);
                }
                break;
        }
    }
    /*
      * 非空验证
      * */
    private boolean validateParam() {
        echargecard = etmobiles.getText().toString().trim();
        if (TextUtils.isEmpty(echargecard)) {
            ToastUtils.showToast("消费码不能为空");
            return false;
        }

        return true;
    }

}
