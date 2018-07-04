package com.mhy.shopingphone.adapter;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class MyOrderAdapter extends BaseCompatAdapter<MyOrderBean.JsonBean, BaseViewHolder> {
    private CharSequence textctr;
    private EditText et_tbnumber;

    public MyOrderAdapter(@LayoutRes int layoutResId, @Nullable List<MyOrderBean.JsonBean> data) {
        super(layoutResId, data);
        init();
    }

    public MyOrderAdapter(@Nullable List<MyOrderBean.JsonBean> data) {
        super(data);
        init();
    }

    public MyOrderAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, MyOrderBean.JsonBean item) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(item.getCreateTime());
        String re_StrTime = sdf.format(new Date(lcc_time));
        //  时间戳转为字符串
//        youxiaoqi.setText("有效期 " + re_StrTime);
        helper.setText(R.id.tv_order_title, item.getName()+"")
//                .setText(R.id.tv_order_price,item.getMoney()+"")
                .setText(R.id.tv_tbmoney, item.getMoney())                //淘宝价
                .setText(R.id.tv_order_price, item.getDiscount() + "元")   //折扣价
                .setText(R.id.tv_sfmoney, item.getMoney())      //实付价
                .setText(R.id.tv_order_data, re_StrTime);
        TextView button = (TextView) helper.getView(R.id.btn_dh);      //申请兑换
        RelativeLayout rl_delete = helper.getView(R.id.rl_delete);    //删除按钮
        et_tbnumber = helper.getView(R.id.et_tbnumber);               //订单编号
        TextView btn_zt = helper.getView(R.id.btn_zt);                //粘贴按钮
        TextView btn_ydh = helper.getView(R.id.btn_ydh);              //已经兑换按钮
        if (item.getStatus()==0) {
            btn_ydh.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
        } else {
            btn_ydh.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
        }
        btn_zt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_tbnumber.getText().clear();
                initcata();
                if (TextUtils.isEmpty(textctr)) {
                    ToastUtils.showToast("请选择要粘贴的内容");
                    return;
                }
                et_tbnumber.setText(textctr);
                et_tbnumber.setSelection(textctr.length());
            }
        });
        rl_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
//        if (item.getStatus().equals("0")) {
//            button.setText("申请兑换");
//            button.setTextColor(mContext.getResources().getColor(R.color.pay_state5));
//            button.setBackgroundResource(R.drawable.order_state_bg);
//        } else {
//            button.setText("查看详情");
//            button.setTextColor(mContext.getResources().getColor(R.color.pay_state4));
//            button.setBackgroundResource(R.drawable.order_state_bg2);
//        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                if (item.getStatus().equals("0")) {
//                    Intent intent = new Intent(mContext, JFOrderActivity.class);
//                    intent.putExtra("pic",item.getPic());
//                    intent.putExtra("title",item.getName());
//                    intent.putExtra("position",viewHolder.getAdapterPosition());
//                    mContext.startActivity(intent);
//                } else if(item.getStatus().equals("1")){
//                    Intent intent = new Intent(mContext, GoodsDetailsActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putSerializable("data", item);
//                    intent.putExtras(bundle);
//                    intent.putExtra("type", 1);
//                    mContext.startActivity(intent);
//                }
            }
        });
//        textView.setText(item.getBlance());
//        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        Glide.with(mContext).load(item.getPic()).crossFade(300).placeholder(R
                .mipmap.img_default_movie).into((ImageView) helper.getView(R.id.iv_order_icon));
//

    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }

    private void initcata() {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);

        // 获取剪贴板的剪贴数据集
        ClipData clipData = clipboard.getPrimaryClip();

        if (clipData != null && clipData.getItemCount() > 0)

        {

            //从数据集中获取（粘贴）第一条文本数据
            ClipData.Item item = clipData.getItemAt(0);
            textctr = item.getText().toString();
        }

    }
}
