package com.mhy.shopingphone.adapter;

import android.content.Intent;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import android.text.InputType;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.ui.activity.ShopIntroActivity;
import com.mhy.shopingphone.view.webview.NineGridTestLayout;
import com.youth.xframe.utils.log.XLog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class DynamicAdapter extends BaseCompatAdapter<DynamicBean.InfoBean, BaseViewHolder> {
    private List<String> list;

    public DynamicAdapter(@LayoutRes int layoutResId, @Nullable List<DynamicBean.InfoBean> data) {
        super(layoutResId, data);
        init();
    }

    public DynamicAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, DynamicBean.InfoBean infoBean) {
        list = new ArrayList<>();
        String time = infoBean.getCreateTime().replace("/Date(", "").replace(")/", "");

        Date date = new Date(Long.parseLong(time));
        java.text.SimpleDateFormat sDateFormat = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss");
        XLog.e("time:" + time);
        try {
            helper.setText(R.id.tv_time, sDateFormat.format(date) /*Util.getData(time.trim()+"","yyyy年MM月dd日 HH:mm")*/)
                    .setText(R.id.tv_names, infoBean.getTitle())
                    .setText(R.id.tv_content, infoBean.getContent());
            list.add(Contant.URL_IMAGE + infoBean.getPic());
            NineGridTestLayout layout
                    = helper.getView(R.id.layout_nine_grid);
            layout.setIsShowAll(true);
            layout.setUrlList(list);
            ImageView imageView = helper.getView(R.id.iv_titles);
            imageView.setOnClickListener(new View.OnClickListener() {     //用户头部点击事件
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, ShopIntroActivity.class);
                    mContext.startActivity(intent);
                }
            });
            RelativeLayout rl_zan = helper.getView(R.id.rl_zan);      //点赞的点击事件
            final CheckBox cb_zan = helper.getView(R.id.cb_zan);            //显示的图标
            cb_zan.setOnCheckedChangeListener(onCheckedChangeListener);
            rl_zan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (cb_zan.isChecked()) {
                        cb_zan.setChecked(false);
                    } else {
                        cb_zan.setChecked(true);
                    }
                }
            });

//            Glide.with(mContext).load(Contant.URL_IMAGE + infoBean.getPic()).crossFade(300).placeholder(R
//                    .mipmap.img_default_movie).into((ImageView) helper.getView(R.id.layout_nine_grid));
        } catch (Exception e) {
            e.printStackTrace();
        }
//
    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if (b) {

            } else {

            }
        }
    };
}
