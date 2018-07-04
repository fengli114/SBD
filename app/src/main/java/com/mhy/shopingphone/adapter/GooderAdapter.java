package com.mhy.shopingphone.adapter;

import android.opengl.Visibility;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.shop.GoodesBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class GooderAdapter extends BaseCompatAdapter<GoodesBean.JsonBean, BaseViewHolder> {
    public GooderAdapter(@LayoutRes int layoutResId, @Nullable List<GoodesBean.JsonBean> data) {
        super(layoutResId, data);
        init();
    }

    public GooderAdapter(@Nullable List<GoodesBean.JsonBean> data) {
        super(data);
        init();
    }

    public GooderAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodesBean.JsonBean item) {
        helper.setText(R.id.tv_goods_discount, "可折扣 " + item.getDiscount() + " 元")
                .setText(R.id.tv_goods_title, item.getSkuName())
                .setText(R.id.tv_goods_price, "￥" + item.getWlPrice())
                .setText(R.id.tv_goods_xl, "可折扣" + item.getDiscount() + "元");
//                 .setText(R.id.tv_goods_price, Util.getData(item.getCreate_time(),"MM-dd"))
           helper.setVisible(R.id.iv_jingdong, true);
        Glide.with(mContext).load(item.getPicUrl()).crossFade(300).placeholder(R
                .mipmap.gray_default).into((ImageView) helper.getView(R.id.iv_goods_icon));
    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }


}
