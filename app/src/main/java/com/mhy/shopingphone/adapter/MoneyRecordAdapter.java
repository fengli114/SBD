package com.mhy.shopingphone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.activity.tixian.MoneyRecordEntity;
import com.youth.xframe.utils.log.XLog;
import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.utils.AutoUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 描述：商品RC适配器
 * Created by F.L on 2017/6/1.
 */

public class MoneyRecordAdapter extends BaseAdapter {
    private List<MoneyRecordEntity.InfoBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater ;
    public MoneyRecordAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<MoneyRecordEntity.InfoBean> list) {
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
            view = inflater.inflate(R.layout.money_record_item, viewGroup, false);
            AutoUtils.autoSize(view);
        }
        AutoLinearLayout relativeLayout = (AutoLinearLayout) view.findViewById(R.id.al_item);

        if (i % 2 == 0){
            relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }
        TextView tvData = (TextView) view.findViewById(R.id.tv_record_data);
        TextView tvMoney = (TextView) view.findViewById(R.id.tv_record_money);
        String time = list.get(i).getCreateTime().replace("/Date(","").replace(")/","");

//                dataTime = Integer.parseInt(time.trim());
        Date date = new Date(Long.parseLong(time));
        SimpleDateFormat sDateFormat = new SimpleDateFormat(
                "yyyy-MM-dd");
        XLog.e("time:"+time);
//                XLog.e("dataTime:"+dataTime);
        TextView tvName = (TextView) view.findViewById(R.id.tv_record_name);
        tvData.setText(sDateFormat.format(date) );
        tvMoney.setText("+"+list.get(i).getMoney());
        return view;
    }
}
