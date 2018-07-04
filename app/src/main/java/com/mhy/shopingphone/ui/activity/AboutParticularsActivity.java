package com.mhy.shopingphone.ui.activity;

import android.view.View;
import android.widget.ImageView;

import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;

import butterknife.BindView;

/**
 * 历史版本详情
 */

public class AboutParticularsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;

    @Override
    protected int getLayout() {
        return R.layout.activity_about_particulars;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
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
