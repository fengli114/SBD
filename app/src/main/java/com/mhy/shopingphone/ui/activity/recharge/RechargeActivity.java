package com.mhy.shopingphone.ui.activity.recharge;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RechargeActivity extends BaseActivity {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.cv_card)
    ImageView cvCard;
    @BindView(R.id.cv_online)
    ImageView cvOnline;

    private Intent intent;
    @Override
    protected int getLayout() {
        return R.layout.activity_recharge_;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext,tou);
        tvTitle.setText("充值");
    }


    @OnClick({R.id.al_back, R.id.tv_right, R.id.cv_card, R.id.cv_online})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.cv_card:
                intent = new Intent(this,ReChargePhoneActivty.class);
                startActivity(intent);
                break;
            case R.id.cv_online:
                intent = new Intent(this,RechargeZFBActivity.class);
                startActivity(intent);
                break;
        }
    }
}
