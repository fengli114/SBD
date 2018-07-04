package com.mhy.shopingphone.adapter;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.UserGuanliBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class UserManageAdapter extends BaseCompatAdapter<UserGuanliBean.JsonBean.UsersBean, BaseViewHolder> {
    private CharSequence textctr;
    private EditText et_tbnumber;

    public UserManageAdapter(@LayoutRes int layoutResId, @Nullable List<UserGuanliBean.JsonBean.UsersBean> data) {
        super(layoutResId, data);
        init();
    }

    public UserManageAdapter(@Nullable List<UserGuanliBean.JsonBean.UsersBean> data) {
        super(data);
        init();
    }

    public UserManageAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, UserGuanliBean.JsonBean.UsersBean item) {
        /*    helper.setText(R.id.tv_names, item.getUsername())
                    .setText(R.id.tv_pwasd, item.getPassword())
                    .setText(R.id.tv_shangji, item.getParentid())
                    .setText(R.id.zhanghuyue, item.getBlance()+"")
                    .setText(R.id.gouwuyue, item.getShopblance()+"");*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(item.getEndtime());
        String re_StrTime = sdf.format(new Date(lcc_time));
        helper.setText(R.id.tv_zhanghu, item.getMobile())
                .setText(R.id.tv_pwasd, item.getAliaccount() + "")
                .setText(R.id.tv_gouwu, item.getShopBalance() + "")
                .setText(R.id.tv_shangji, re_StrTime);
    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }

    private void initcata() {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);

        // 获取剪贴板的剪贴数据集
        ClipData clipData = clipboard.getPrimaryClip();

        if (clipData != null && clipData.getItemCount() > 0)

        {

            //从数据集中获取（粘贴）第一条文本数据
            ClipData.Item item = clipData.getItemAt(0);
            textctr = item.getText().toString();
        }

    }
}
