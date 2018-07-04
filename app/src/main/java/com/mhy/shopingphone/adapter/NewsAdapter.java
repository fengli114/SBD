package com.mhy.shopingphone.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.sdk.contant.Contant;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.constant.BundleKeyConstant;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.ui.activity.WebViewLoadActivity;
import com.mhy.shopingphone.view.webview.WebViewActivity;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class NewsAdapter extends BaseCompatAdapter<NewsBean.JsonBean, BaseViewHolder> {
    public NewsAdapter(@LayoutRes int layoutResId, @Nullable List<NewsBean.JsonBean> data) {
        super(layoutResId, data);
        init();
    }

    public NewsAdapter(@Nullable List<NewsBean.JsonBean> data) {
        super(data);
        init();
    }

    public NewsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, final NewsBean.JsonBean item) {
        helper.setText(R.id.tv_title,item.getTitle())
//                .setText(R.id.tv_goods_title,item.getName())
//                .setText(R.id.tv_goods_price,item.getMoney()+"")
//                .setText(R.id.tv_goods_xl,"销售量"+item.getSalesVolume()+"")
//                 .setText(R.id.tv_goods_price, Util.getData(item.getCreate_time(),"MM-dd"))
        ;

        Glide.with(mContext).load(item.getPic()).crossFade(300).placeholder(R
                .mipmap.img_default_movie).into((ImageView) helper.getView(R.id.new_img));

        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebViewActivity.skip(mContext, item.getUrl(), "每日新闻");
            }
        });

    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }


}
