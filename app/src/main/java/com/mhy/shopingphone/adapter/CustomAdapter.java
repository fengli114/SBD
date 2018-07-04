package com.mhy.shopingphone.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mhy.sdk.contant.Contant;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.PayTypeBean;
import com.mhy.shopingphone.model.bean.shop.ShopTypeBean;

import java.util.List;

/**
 * 作者： "RedRainM" on 2018/2/1 0001.
 * 描述：
 */

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private Context context;
    private List<PayTypeBean.JsonBean> data;
    private OnRecyclerViewItemClickListener mOnItemClickListener;
    private MyViewHolder holder;
    private int layoutPosition;
    public  interface OnRecyclerViewItemClickListener {
        void onItemClick(View view , String data,int position);
    }
    public CustomAdapter(Context context, List<PayTypeBean.JsonBean> data){
        this.context = context;
        this.data = data;
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = View.inflate(context, R.layout.item_my_pay_type, null);
        holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        PayTypeBean.JsonBean infoBean = data.get(position);
        holder.tvRealMoney.setText(infoBean.getAlimoney()+"元");
        holder.TvGiveMoney.setText(infoBean.getMemo());
        holder.itemView.setTag(infoBean.getGrade()+"");
        Glide.with(context).load(Contant.URL_IMAGE + infoBean.getPic()).into(holder.icon);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //获取当前点击的位置
                layoutPosition = holder.getLayoutPosition();
                notifyDataSetChanged();
                mOnItemClickListener.onItemClick(holder.itemView, (String) holder.itemView.getTag(), layoutPosition);
            }
        });

        //更改状态
        if(position == layoutPosition){
            holder.alBg.setBackgroundResource(R.drawable.pay_type_bg01);
//            holder.textView.setTextColor(Color.RED);
        }else{
            holder.alBg.setBackgroundResource(R.drawable.pay_type_bg);
//            holder.textView.setTextColor(Color.BLUE);
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvRealMoney,TvGiveMoney;
        private final ImageView icon;
        private final RelativeLayout alBg;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvRealMoney = (TextView) itemView.findViewById(R.id.tv_real_money);
            TvGiveMoney = (TextView) itemView.findViewById(R.id.tv_give_money);
            icon = (ImageView) itemView.findViewById(R.id.iv_pay_icon);
            alBg = (RelativeLayout) itemView.findViewById(R.id.pay_type_bg);
        }
    }
}
