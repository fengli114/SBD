package com.mhy.shopingphone.adapter;

import android.app.Activity;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.phone.MyPhoneEntity;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;

import java.util.List;


/**
 * 描述：商品RC适配器
 * Created by F.L on 2017/6/1.
 */

public class MyPhoneAdapter extends BaseCompatAdapter<MyPhoneEntity, BaseViewHolder> {


    public MyPhoneAdapter(int layoutResId, @Nullable List<MyPhoneEntity> data) {
        super(layoutResId, data);
    }

    public MyPhoneAdapter(@Nullable List<MyPhoneEntity> data) {
        super(data);
    }

    public MyPhoneAdapter(int layoutResId) {
        super(layoutResId);
    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyPhoneEntity item) {
        helper.setText(R.id.tv_phonenumber,item.phone)
        ;
    }
}
