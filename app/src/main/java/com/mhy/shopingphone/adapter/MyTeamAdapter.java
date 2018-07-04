package com.mhy.shopingphone.adapter;


import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.TimeUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.partner.MyTeamEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * 描述：商品RC适配器
 * Created by F.L on 2017/6/1.
 */

public class MyTeamAdapter extends BaseCompatAdapter<MyTeamEntity.InfoBean, BaseViewHolder> {
    public MyTeamAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public MyTeamAdapter(@LayoutRes int layoutResId, @Nullable List<MyTeamEntity.InfoBean> data) {
        super(layoutResId, data);
    }

    public MyTeamAdapter(@Nullable List<MyTeamEntity.InfoBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, MyTeamEntity.InfoBean item) {
        View raBg = viewHolder.getView(R.id.al_item);

        viewHolder.setText(R.id.tv_team_user, item.getPic());
        float fMoney  = Float.valueOf(item.getCommission());
        BigDecimal b   =   new BigDecimal(fMoney);
        float   f1   =   b.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
        viewHolder.setText(R.id.tv_team_sum, f1 +"");
    }

}
