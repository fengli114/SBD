package com.mhy.shopingphone.widgets.adresslist.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.GroupMemberBean;
import com.mhy.shopingphone.ui.activity.phone.PhoneDetailsActivity;
import com.mhy.shopingphone.widgets.adresslist.bean.Friend;

import java.util.List;

/**
 * Created by LinJinFeng on 2016/9/29.
 */

public class ContactAdapter extends BaseRecyclerAdapter<GroupMemberBean,BaseHolder> {
    List<GroupMemberBean> dataList;
    Context context;
    public ContactAdapter(Context context) {
        super(context);
    }

    public ContactAdapter(Context context, List<GroupMemberBean> dataList) {
        super(context, dataList);
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public BaseHolder createCustomViewHolder(ViewGroup parent, int viewType) {
        return new BaseHolder(parent, R.layout.contact_item);
    }

    @Override
    public void bindCustomViewHolder(BaseHolder holder, final int position) {
        final GroupMemberBean friend=getItem(position);
        if (dataList.size() == 0){
            holder.getView(R.id.stick_container).setVisibility(View.GONE);
        }
        ((TextView)holder.getView(R.id.name)).setText(friend.getAccount());
        ((TextView)holder.getView(R.id.phone)).setText(friend.getPhone());
        if (position==0){
            holder.getView(R.id.stick_container).setVisibility(View.VISIBLE);
            ((TextView)holder.getView(R.id.header)).setText(friend.getFirstPinyin());
        }else {
            if (!TextUtils.equals(friend.getFirstPinyin(),getItem(position-1).getFirstPinyin())){
                holder.getView(R.id.stick_container).setVisibility(View.VISIBLE);
                ((TextView)holder.getView(R.id.header)).setText(friend.getFirstPinyin());
            }else {
                holder.getView(R.id.stick_container).setVisibility(View.GONE);
            }
        }
        holder.itemView.setContentDescription(friend.getFirstPinyin());
        holder.getView(R.id.content).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, PhoneDetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("phone_name", friend.getName());
                intent.putExtra("phone_number", friend.getPhone());
                intent.putExtra("contact_id", friend.getContact_id());
                LogUtils.e("ph："+friend.getName()+friend.getPhone()+friend.getContact_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    protected int getCustomViewType(int position) {
        return 0;
    }
}
