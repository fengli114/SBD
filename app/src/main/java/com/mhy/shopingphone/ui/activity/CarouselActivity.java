package com.mhy.shopingphone.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.activity.order.SogoManageActivity;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;

import butterknife.BindView;

/**
 * 轮播广告
 */

public class CarouselActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.ll_bohaopan)
    LinearLayout ll_bohaopan;
    @BindView(R.id.ll_faxianye)
    LinearLayout ll_faxianye;
    @Override
    protected int getLayout() {
        return R.layout.activity_carousel;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        ll_bohaopan.setOnClickListener(this);
        ll_faxianye.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            case R.id.ll_bohaopan:     //拨号界面广告图
                Intent intent1 = new Intent(this, ImageUploadActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_faxianye:      //发现界面广告图
                Intent intent2 = new Intent(this, ImageUploadActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
