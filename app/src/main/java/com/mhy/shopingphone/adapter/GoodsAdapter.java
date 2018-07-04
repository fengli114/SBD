package com.mhy.shopingphone.adapter;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;


import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class GoodsAdapter extends BaseCompatAdapter<GoodsBean.JsonBean.CommoditiesBean, BaseViewHolder> {
    public GoodsAdapter(@LayoutRes int layoutResId, @Nullable List<GoodsBean.JsonBean.CommoditiesBean> data) {
        super(layoutResId, data);
        init();
    }

    public GoodsAdapter(@Nullable List<GoodsBean.JsonBean.CommoditiesBean> data) {
        super(data);
        init();
    }

    public GoodsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, GoodsBean.JsonBean.CommoditiesBean item) {
        helper.setText(R.id.tv_goods_discount, "可折扣 " + item.getDiscount() + " 元")
                .setText(R.id.tv_goods_title, item.getName())
                .setText(R.id.tv_goods_price, "￥" + item.getMoney())
                .setText(R.id.tv_goods_xl, "可折扣" + item.getDiscount() + "元");
//                 .setText(R.id.tv_goods_price, Util.getData(item.getCreate_time(),"MM-dd"))

        Glide.with(mContext).load(item.getPic()).crossFade(300).placeholder(R
                .mipmap.gray_default).into((ImageView) helper.getView(R.id.iv_goods_icon));
    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }


}
