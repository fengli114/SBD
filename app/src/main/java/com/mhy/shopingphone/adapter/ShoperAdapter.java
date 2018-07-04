package com.mhy.shopingphone.adapter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.RegexUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.animation.ScaleInAnimation;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.constant.BundleKeyConstant;
import com.mhy.shopingphone.model.bean.discover.ShoperBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.ui.activity.WebViewLoadActivity;
import com.mhy.shopingphone.ui.activity.phone.DialBackActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.sdu.didi.openapi.DiDiWebActivity;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ClipData.newIntent;
import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class ShoperAdapter extends BaseCompatAdapter<ShoperBean.JsonBean, BaseViewHolder> {
    HashMap<String, String> sres;

    public ShoperAdapter(@LayoutRes int layoutResId, @Nullable List<ShoperBean.JsonBean> data) {
        super(layoutResId, data);
        init();
    }

    public ShoperAdapter(@Nullable List<ShoperBean.JsonBean> data) {
        super(data);
        init();
    }

    public ShoperAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
        init();
    }

    @Override
    protected void convert(BaseViewHolder helper, final ShoperBean.JsonBean item) {

        helper.setText(R.id.tv_name, item.getText());
//                 .setText(R.id.tv_goods_price, Util.getData(item.getCreate_time(),"MM-dd"))
        Glide.with(mContext).load(item.getLogo()).crossFade(300).placeholder(R
                .mipmap.gray_default).into((ImageView) helper.getView(R.id.img));
        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isNumericzidai(item.getUrl())) {
//                    Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
                    Pattern t = Pattern.compile("^0(10|2[0-5789]|\\d{3})\\d{7,8}$");
//                    Matcher m = p.matcher(item.getUrl());   //此处参数为String的字符串
                    Matcher s = t.matcher(item.getUrl());
                    if (s.matches()) {
                        showNormalDialog(item.getUrl());
                    } else {
                        showDialogUpdate(item.getUrl());
                    }
                } else {
                    if (item.getText().equals("滴滴出行")) {
                        sres = new HashMap<>();
                        sres.put("finish", "home_page");
                        DiDiWebActivity.showDDPage(mContext, sres);
                    } else {
                        WebViewActivity.skip(mContext, item.getUrl(), item.getText());
                    }
                }
            }
        });
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate(final String s) {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        // 设置提示框的标题
        builder.setMessage(s).
                // 设置确定按钮
                        setPositiveButton("呼叫", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                            showDialogUp();
                        } else {
                            Intent intent = new Intent(mContext, DialBackActivity.class);
                            intent.putExtra("phone_number", s);
                            mContext.startActivity(intent);
                        }

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();

    }

    public static boolean isNumericzidai(String str) {
        Pattern pattern = Pattern.compile("-?[0-9]+.?[0-9]+");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    private void init() {
        openLoadAnimation(new ScaleInAnimation(0.8f));
        isFirstOnly(false);
    }

    private void showNormalDialog(final String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final String[] items = {"随便打拨打", "本机拨打", "取消"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(mContext);
        listDialog.setTitle("是否呼叫" + str);
        listDialog.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (items[which].equals("随便打拨打")) {
                    if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                        showDialogUp();
                    } else {
                        Intent intent = new Intent(mContext, DialBackActivity.class);
                        intent.putExtra("phone_number", str);
                        mContext.startActivity(intent);
                    }
                } else if (items[which].equals("本机拨打")) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    Uri data = Uri.parse("tel:" + str);
                    intent.setData(data);
                    startActivity(intent);
                } else {

                }
            }
        });
        listDialog.show();
    }

    private void showDialogUp() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        // 设置提示框的标题
        builder.setMessage("你还没有登录").setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(mContext, LoginActivty.class));
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();
    }
}
