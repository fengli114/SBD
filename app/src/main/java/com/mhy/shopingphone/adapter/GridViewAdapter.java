package com.mhy.shopingphone.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.Model;

import java.util.List;

/**
 * Created by YH on 2016/10/17.
 */

public class GridViewAdapter extends BaseAdapter {
    private List<Model> mDatas;
    private LayoutInflater inflater;
    /**
     * 页数下标,从0开始(当前是第几页)
     */
    private int curIndex;
    /**
     * 每一页显示的个数
     */
    private int pageSize;
    protected Context mContext;

    public GridViewAdapter(Context context, List<Model> mDatas, int curIndex, int pageSize) {
        this.mDatas = mDatas;
        this.pageSize = pageSize;
        this.curIndex = curIndex;
        mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size() > (curIndex + 1) * pageSize ? pageSize : (mDatas.size() - curIndex * pageSize);
    }

    @Override
    public Object getItem(int position) {
        return mDatas.get(position + curIndex * pageSize);
    }

    @Override
    public long getItemId(int position) {
        return position + curIndex * pageSize;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_shopers, null);
            vh = new ViewHolder();
            vh.iv = (ImageView) convertView.findViewById(R.id.img);
            vh.tv = (TextView) convertView.findViewById(R.id.tv_name);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }
        //计算一下位置
        int pos = position + curIndex * pageSize;
        if (pos==0){
            Glide.with(mContext).load("").crossFade(300).placeholder(R.drawable.jdicon).into((vh.iv));
            vh.tv.setText(mDatas.get(pos).getName());
        }else {
            Glide.with(mContext).load(mDatas.get(pos).getIconRes()).crossFade(300).placeholder(R
                    .mipmap.gray_default).into((vh.iv));
            vh.tv.setText(mDatas.get(pos).getName());
        }
        return convertView;
    }

    class ViewHolder {
        public TextView tv;
        public ImageView iv;
    }
}
