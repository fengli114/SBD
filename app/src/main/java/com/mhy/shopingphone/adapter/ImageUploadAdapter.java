package com.mhy.shopingphone.adapter;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.qiyeguanjia.Modelers;
import com.mhy.shopingphone.ui.activity.EightXiuGaiActivity;
import com.mhy.shopingphone.ui.activity.ImageXiugaiActivity;

import java.io.Serializable;
import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class ImageUploadAdapter extends BaseCompatAdapter<Modelers.JsonBean, BaseViewHolder> {
    private CharSequence textctr;
    private EditText et_tbnumber;

    public ImageUploadAdapter(@LayoutRes int layoutResId, @Nullable List<Modelers.JsonBean> data) {
        super(layoutResId, data);
        init();
    }

    public ImageUploadAdapter(@Nullable List<Modelers.JsonBean> data) {
        super(data);
        init();
    }

    public ImageUploadAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, final Modelers.JsonBean item) {
        helper.setText(R.id.tv_titles, item.getText())
                .setText(R.id.tv_urles, item.getUrl())
                .setText(R.id.tv_bumers, item.getGrade() + "");
        Glide.with(mContext).load(item.getLogo()).crossFade(300).placeholder(R
                .mipmap.img_default_movie).into((ImageView) helper.getView(R.id.iv_order_icon));
        helper.getView(R.id.tv_xiugai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("text", item.getText());
                bundle.putString("photos", item.getId());
                bundle.putString("user", item.getAgentid());
                bundle.putString("logo", item.getLogo());
                bundle.putString("url", item.getUrl());
                bundle.putString("grade", item.getGrade() + "");
                Intent intent2 = new Intent(mContext, EightXiuGaiActivity.class);
                intent2.putExtras(bundle);
                startActivity(intent2);
//                startNewActivity(ImageXiugaiActivity.class, bundle);
            }
        });

    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }

}
