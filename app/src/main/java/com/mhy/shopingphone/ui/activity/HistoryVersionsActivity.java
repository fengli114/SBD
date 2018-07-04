package com.mhy.shopingphone.ui.activity;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;

import butterknife.BindView;

/**
 * 历史版本
 */

public class HistoryVersionsActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.recycler)
    RecyclerView order_recyview;
    @BindView(R.id.ll_history)
    LinearLayout ll_history;
//    private static List<String> mList;

    @Override
    protected int getLayout() {
        return R.layout.activity_history;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        ll_history.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            case R.id.ll_history:
                Intent intent = new Intent();
                intent.setClass(mContext, AboutParticularsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
