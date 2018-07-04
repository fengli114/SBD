package com.mhy.shopingphone.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.sdk.contant.Contant;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.shop.ShopTypeBean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class ShopTypeAdapter extends BaseCompatAdapter<ShopTypeBean.InfoBean, BaseViewHolder> {
    public ShopTypeAdapter(@LayoutRes int layoutResId, @Nullable List<ShopTypeBean.InfoBean> data) {
        super(layoutResId, data);
        init();
    }

    public ShopTypeAdapter(@Nullable List<ShopTypeBean.InfoBean> data) {
        super(data);
        init();
    }

    public ShopTypeAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShopTypeBean.InfoBean item) {

//        helper.setText(R.id.tv_name,item.getText())
//
////                 .setText(R.id.tv_goods_price, Util.getData(item.getCreate_time(),"MM-dd"))
//        ;
        Glide.with(mContext).load(Contant.URL_IMAGE + item.getPic()).crossFade(300).placeholder(R
                .mipmap.img_default_movie).into((ImageView) helper.getView(R.id.iv_shop_type));
//        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                WebViewActivity.skip(mContext, item.getUrl(), item.getText());
//            }
//        });
    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }


}
