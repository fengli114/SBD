package com.mhy.shopingphone.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.GroupMemberBean;
import com.mhy.shopingphone.ui.activity.phone.PhoneDetailsActivity;
import com.mhy.shopingphone.widgets.ColorGenerator;
import com.mhy.shopingphone.widgets.TextDrawable;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RecAdressListAdapter extends RecyclerView.Adapter<RecAdressListAdapter.Holder> {

    private List<GroupMemberBean> mList;

    private Activity mActivity;
    private ColorGenerator mColorGenerator = ColorGenerator.MATERIAL;
    private TextDrawable.IBuilder mDrawableBuilder = TextDrawable.builder().round();
    public RecAdressListAdapter(Activity activity) {
        mList = new ArrayList<>();
        mActivity = activity;
    }

    public void addDatas(List<GroupMemberBean> data) {
        this.mList.clear();
        this.mList.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address_list, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        final GroupMemberBean bean = mList.get(position);

//        holder.phone.setText(mDatas.get(position).getPhone());
        holder.tv_name.setText(bean.getName());
        TextDrawable drawable = mDrawableBuilder.build(String.valueOf(bean.getName().charAt(0)), mColorGenerator.getColor(bean.getName()));
        holder.iv_logo.setImageDrawable(drawable);
        holder.content.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, PhoneDetailsActivity.class);
                intent.putExtra("phone_name", bean.getName());
                intent.putExtra("phone_number", bean.getPhone());
                intent.putExtra("contact_id", bean.getContact_id());
                mActivity.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.title)
        TextView tv_name;

        @BindView(R.id.iv_img)
        ImageView iv_logo;

        @BindView(R.id.content)
        LinearLayout content;

        Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

//        @OnClick(R.id.item_content)
//        void tvClick() {
//            Toast.makeText(itemView.getContext(), "你点击到了" + tv_name.getText(), Toast.LENGTH_SHORT).show();
//        }
    }

}
