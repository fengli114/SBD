package com.mhy.shopingphone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.cy.CYPhoneEntity;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * 描述：商品RC适配器
 * Created by F.L on 2017/6/1.
 */

public class CYAdapter extends BaseAdapter {
    private List<CYPhoneEntity> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater ;
    public CYAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<CYPhoneEntity> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = inflater.inflate(R.layout.my_cy_item, viewGroup, false);
            AutoUtils.autoSize(view);
        }

        TextView tvName = (TextView) view.findViewById(R.id.tv_team_user);

        tvName.setText(list.get(i).getName());

        return view;
    }
}
