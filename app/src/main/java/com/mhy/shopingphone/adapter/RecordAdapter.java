package com.mhy.shopingphone.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.chad.library.adapter.base.BaseViewHolder;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.TimeUtils;
import com.mhy.shopingphone.R;

import com.mhy.shopingphone.widgets.adresslist.adapter.BaseHolder;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 描述：商品RC适配器
 * Created by F.L on 2017/6/1.
 */

public class RecordAdapter extends BaseCompatAdapter<RecordEntity.JsonBean, BaseViewHolder> {
    public RecordAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public RecordAdapter(@LayoutRes int layoutResId, @Nullable List<RecordEntity.JsonBean> data) {
        super(layoutResId, data);
    }

    public RecordAdapter(@Nullable List<RecordEntity.JsonBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, RecordEntity.JsonBean item) {
        viewHolder.setText(R.id.tv_record_price, item.getMoney() + "元");
//                .setText(R.id.tv_goods_title,item.getName())
//                .setText(R.id.tv_goods_price,item.getMoney()+"")
//                .setText(R.id.tv_goods_xl,"销售量"+item.getSalesVolume()+"")
//                 .setText(R.id.tv_goods_price, Util.getData(item.getCreate_time(),"MM-dd"))
        ImageView view = (ImageView) viewHolder.getView(R.id.tv_record_icon);
        TextView view2 = (TextView) viewHolder.getView(R.id.tv_record_type);
        TextView view3 = (TextView) viewHolder.getView(R.id.tv_record_data);
        TextView view4 = (TextView) viewHolder.getView(R.id.tv_record_state);
        TextView view5 = (TextView) viewHolder.getView(R.id.tv_record_price);
        if (item.getType() == 1) {
            ////1 话费 2购物

            if (item.isStatus()) {
                if (item.getCardtype() == 1) {
                    view2.setText("充值卡充值");
                    view4.setText("充值成功");
                    view.setImageResource(R.drawable.onlinepay_record_card);
                    view4.setTextColor(mContext.getResources().getColor(R.color.pay_state));
                    view5.setTextColor(mContext.getResources().getColor(R.color.pay_state4));
                    view4.setBackgroundResource(R.drawable.zf_state_bg);
                } else {
                    view2.setText("购物卡充值");
                    view4.setText("充值成功");
                    view.setImageResource(R.drawable.onlinepay_record_gouwuka);
                    view4.setTextColor(mContext.getResources().getColor(R.color.pay_state));
                    view5.setTextColor(mContext.getResources().getColor(R.color.pay_state4));
                    view4.setBackgroundResource(R.drawable.zf_state_bg);
                }

            } else {
                if (item.getCardtype() == 1) {
                    view2.setText("充值卡充值");
                    view4.setText("充值失败");
                    view.setImageResource(R.drawable.onlinepay_record_nogouwuka);
                    view4.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
                    view5.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
                    view4.setBackgroundResource(R.drawable.zf_state_bg2);
                } else {
                    view2.setText("购物卡充值");
                    view4.setText("充值失败");
                    view.setImageResource(R.drawable.onlinepay_record_card01);
                    view4.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
                    view5.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
                    view4.setBackgroundResource(R.drawable.zf_state_bg2);
                }

            }


        } else if (item.getType() == 2) {
            view2.setText("空中充值");
            if (item.isStatus()) {
                view4.setText("充值成功");
                view.setImageResource(R.drawable.onlinepay_record_airrecharge);
                view4.setTextColor(mContext.getResources().getColor(R.color.pay_state));
                view5.setTextColor(mContext.getResources().getColor(R.color.pay_state4));
                view4.setBackgroundResource(R.drawable.zf_state_bg);
            } else {
                view4.setText("充值失败");
                view.setImageResource(R.drawable.onlinepay_record_airrecharge01);
                view4.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
                view5.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
                view4.setBackgroundResource(R.drawable.zf_state_bg2);
            }
        } else {
            view2.setText("在线充值");
            if (item.getAlistatus().equals("1")) {
                view4.setText("充值成功");
                view.setImageResource(R.drawable.onlinepay_record_phone);
                view4.setTextColor(mContext.getResources().getColor(R.color.pay_state));
                view5.setTextColor(mContext.getResources().getColor(R.color.pay_state4));
                view4.setBackgroundResource(R.drawable.zf_state_bg);
            } else /*if (item.getAliStatus().equals("2"))*/ {
                view4.setText("充值失败");
                view.setImageResource(R.drawable.onlinepay_record_phone01);
                view4.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
                view5.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
                view4.setBackgroundResource(R.drawable.zf_state_bg2);
            }
//            else {
//                view4.setText("充值失败");
//                view.setImageResource(R.drawable.onlinepay_record_phone01);
//                view4.setTextColor(mContext.getResources().getColor(R.color.pay_state2));
//                view4.setBackgroundResource(R.drawable.zf_state_bg2);
//            }
        }
        String time = item.getPaytime()+"".replace("/Date(", "").replace(")/", "");
        Date date = new Date(Long.parseLong(time));
        java.text.SimpleDateFormat sDateFormat = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
//                dataTime = Integer.parseInt(time.trim());
        try {
            if (TimeUtils.IsToday(sDateFormat.format(date))) {
                java.text.SimpleDateFormat sDateFormat1 = new java.text.SimpleDateFormat(
                        "HH:mm");
                view3.setText("今天" + sDateFormat1.format(date));
            } else if (TimeUtils.IsYesterday(sDateFormat.format(date))) {
                java.text.SimpleDateFormat sDateFormat2 = new java.text.SimpleDateFormat(
                        "HH:mm");
                view3.setText("昨天" + sDateFormat2.format(date));
            } else {
                view3.setText(sDateFormat.format(date));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        LogUtils.e("time:" + time);
//                LogUtils.e("dataTime:"+dataTime);


    }

}
