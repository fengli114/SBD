package com.mhy.shopingphone.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.GroupMemberBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/2.
 */

public class LvAdapter_pay extends BaseAdapter {

    private List<GroupMemberBean> mDatas;
    private Context context;
    private int type;

    public void setmDatas(List<GroupMemberBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public LvAdapter_pay(Context context) {
        this.context = context;
    }

    public void setType(int type) {
        this.type = type;

    }

    @Override
    public int getCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null) {
            convertView = View.inflate(context,R.layout.lv_pay_phone_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GroupMemberBean bean = mDatas.get(position);
        holder.tv_name.setText(bean.getName());
        holder.tv_phone.setText(bean.getPhone().trim());


        return convertView;
    }

    class ViewHolder{
        TextView tv_name;
        TextView tv_phone;
        TextView tv_date;
        ImageView iv_detail;

        public ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.title);
            tv_phone = (TextView) view.findViewById(R.id.phone);

        }
    }
}
