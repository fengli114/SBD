package com.mhy.shopingphone.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.sdk.contant.Contant;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.PayTypeBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class PayTypeAdapter extends BaseCompatAdapter<PayTypeBean.JsonBean, BaseViewHolder> {
    public PayTypeAdapter(@LayoutRes int layoutResId, @Nullable List<PayTypeBean.JsonBean> data) {
        super(layoutResId, data);
        init();
    }

    public PayTypeAdapter(@Nullable List<PayTypeBean.JsonBean> data) {
        super(data);
        init();
    }

    public PayTypeAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, PayTypeBean.JsonBean item) {
        helper.setText(R.id.tv_real_money, item.getAlimoney() + " 元")
                .setText(R.id.tv_give_money, item.getMemo());
        Glide.with(mContext).load(Contant.URL_IMAGE + item.getPic()).into((ImageView) helper.getView(R.id.iv_pay_icon));
//        helper.setText(R.id.tv_movie_title, item.getTitle());
//        helper.setText(R.id.tv_movie_directors, item.getDirectorsString());
//        helper.setText(R.id.tv_movie_actors, item.getActorsString());
//        helper.setText(R.id.tv_movie_genres, item.getGenresString());
//        helper.setText(R.id.tv_movie_rating_rate, String.valueOf(item.getRating().getAverage()));
//        Glide.with(mContext).load(item.getImages().getLarge()).crossFade(300).placeholder(R
//                .mipmap.img_default_movie).into((ImageView) helper.getView(R.id.iv_moive_photo));
    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }


}
