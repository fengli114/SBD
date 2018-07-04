package com.mhy.shopingphone.ui.activity.tixian;

import android.app.Activity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhy.shopingphone.R;
import com.youth.xframe.utils.XEmptyUtils;


/**
 * 描述：商品RC适配器
 * Created by F.L on 2017/6/1.
 */

public class OrderDetailsAdapter extends BaseQuickAdapter<OrderDetailsEntity.InfoBean, ViewHolder> {

    public OrderDetailsAdapter(Activity activity) {
        super(R.layout.item_tx_order_rc, null);
    }

    @Override
    protected void convert(ViewHolder viewHolder, OrderDetailsEntity.InfoBean item) {
//
        String commission;
        if (XEmptyUtils.isEmpty(item.getCommission())){
            commission = "¥0.00";
        }else {
            commission = "¥"+item.getCommission();
        }
        viewHolder.setText(R.id.tv_order_title,item.getName()+"")
                .setText(R.id.tv_order_discount,commission);
        Glide.with(mContext).load(item.getPic()).crossFade().into((ImageView) viewHolder.getView(R.id.iv_order_icon));
    }
}
