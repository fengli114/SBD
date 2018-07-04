package com.mhy.shopingphone.adapter;


import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.shopingphone.model.qiyeguanjia.UserInfoers;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class RechargeableCardAdapter extends BaseCompatAdapter<UserInfoers.JsonBean.UsersBean, BaseViewHolder> {
    private CharSequence textctr;
    private EditText et_tbnumber;

    public RechargeableCardAdapter(@LayoutRes int layoutResId, @Nullable List<UserInfoers.JsonBean.UsersBean> data) {
        super(layoutResId, data);
        init();
    }

    public RechargeableCardAdapter(@Nullable List<UserInfoers.JsonBean.UsersBean> data) {
        super(data);
        init();
    }

    public RechargeableCardAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, UserInfoers.JsonBean.UsersBean item) {
           /* helper.setText(R.id.tv_names, item.getUsername())
                    .setText(R.id.tv_pwasd, item.getPassword())
                    .setText(R.id.tv_shangji, item.getParentid())
                    .setText(R.id.zhanghuyue, item.getBlance()+"")
                    .setText(R.id.gouwuyue, item.getShopblance()+"");
            Glide.with(mContext).load(item.getPic()).crossFade(300).placeholder(R
                    .drawable.housekeeper_shanghuguanli_touxiang).into((ImageView) helper.getView(R.id.iv_order_icon));*/


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
