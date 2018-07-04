package com.mhy.shopingphone.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.GroupMemberBean;
import com.mhy.shopingphone.ui.activity.phone.DialBackActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Created by Administrator on 2017/10/2.
 */

public class LvAdapter extends BaseAdapter {

    private List<GroupMemberBean> mDatas;
    private Context context;
    private int type;

    public void setmDatas(List<GroupMemberBean> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    public LvAdapter(Context context) {
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
            convertView = View.inflate(context, R.layout.lv_group_phone_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final GroupMemberBean bean = mDatas.get(position);
        holder.tv_name.setText(bean.getName());
        holder.tv_phone.setText(bean.getPhone().trim());
        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUp();
                } else {
                    Intent intent = new Intent(context, DialBackActivity.class);
                    intent.putExtra("phone_number", bean.getName());
                    intent.putExtra("phone_name", bean.getPhone().trim());
                    context.startActivity(intent);
                }
            }
        });

        return convertView;
    }

    class ViewHolder{
        TextView tv_name;
        TextView tv_phone;
        TextView tv_date;
        ImageView iv_detail;
        LinearLayout content;

        public ViewHolder(View view) {
            tv_name = (TextView) view.findViewById(R.id.title);
            tv_phone = (TextView) view.findViewById(R.id.phone);
            content = (LinearLayout) view.findViewById(R.id.content);

        }
    }
    private void showDialogUp() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置提示框的标题
        builder.setMessage("你还没有登录").setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(context, LoginActivty.class));
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();
    }
}
