package com.mhy.shopingphone.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.NineGridTestModel;
import com.mhy.shopingphone.view.webview.NineGridTestLayout;

import java.util.List;

/**
 * 作者： "RedRainM" on 2018/4/27 0027.
 * 描述：
 */

public class DynamicsAdapter extends RecyclerView.Adapter<DynamicsAdapter.ViewHolder> {
    private Context mContext;
    private List<NineGridTestModel> mList;
    protected LayoutInflater inflater;

    public DynamicsAdapter(Context context) {
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<NineGridTestModel> list) {
        mList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View converView = inflater.inflate(R.layout.item_bbs_nine_grid,parent,false);
        ViewHolder viewHolder = new ViewHolder(converView);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.layout.setIsShowAll(mList.get(position).isShowAll);
        holder.layout.setUrlList(mList.get(position).urlList);
    }

    @Override
    public int getItemCount() {
        return getListSize(mList);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        NineGridTestLayout layout;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (NineGridTestLayout) itemView.findViewById(R.id.layout_nine_grid);

        }
    }

    private int getListSize(List<NineGridTestModel> list) {
        if (list == null || list.size() == 0) {
            return 0;
        }
        return list.size();
    }
}
