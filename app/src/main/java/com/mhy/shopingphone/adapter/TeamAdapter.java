package com.mhy.shopingphone.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.partner.MyTeamEntity;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * 描述：商品RC适配器
 * Created by F.L on 2017/6/1.
 */

public class TeamAdapter extends BaseAdapter {
    private List<MyTeamEntity.InfoBean> list = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater ;
    public TeamAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public void setList(List<MyTeamEntity.InfoBean> list) {
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
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;

        if(null == convertView){

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.my_team_item,null);

            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_team_user);
            viewHolder.tvNum= (TextView) convertView.findViewById(R.id.tv_team_sum);
            viewHolder.relativeLayout = (LinearLayout) convertView.findViewById(R.id.al_item);
            convertView.setTag(viewHolder);

        }else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        //set data
        if (i % 2 == 0){
            viewHolder.relativeLayout.setBackgroundColor(context.getResources().getColor(R.color.white));
        }

        viewHolder.tvName.setText(list.get(i).getPic());
        float fMoney  = Float.valueOf(list.get(i).getCommission());
        BigDecimal b   =   new BigDecimal(fMoney);
        float   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
        viewHolder.tvNum.setText(f1 +"");
        return convertView;

    }
    class ViewHolder{
        private TextView tvName;
        private TextView tvNum;
        private LinearLayout relativeLayout;
    }
}
