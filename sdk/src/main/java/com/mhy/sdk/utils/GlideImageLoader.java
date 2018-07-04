package com.mhy.sdk.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.mhy.sdk.R;
import com.youth.banner.loader.ImageLoader;

/**
 * 描述：轮播图  图片加载器
 * 时间: 2017-05-19 10:44
 */
public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //Glide 加载图片简单用法
        Glide.with(context).load(path)
                .placeholder(R.mipmap.gray_default)
                .error(R.mipmap.gray_default)
                .into(imageView);
    }
}
