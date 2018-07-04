package com.mhy.shopingphone.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.activity.phone.DialBackActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * 商铺详情
 */

public class ShopIntroActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.al_my_break)
    LinearLayout order_back;
    @BindView(R.id.tou)
    ImageView tou;

    @BindView(R.id.ll_phonenum)    //拨打电话
            LinearLayout phone;
    @BindView(R.id.tv_maidans)      //买单
            TextView tv_maidans;

    @Override
    protected int getLayout() {
        return R.layout.activity_shop_intro;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        phone.setOnClickListener(this);
        tv_maidans.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.al_my_break:
                finish();
                break;
            case R.id.tv_maidans:
                Intent intent = new Intent(mContext, AboutDiscountActivity.class);
                mContext.startActivity(intent);
                break;
            case R.id.ll_phonenum:
                Pattern t = Pattern.compile("^0(10|2[0-5789]|\\d{3})\\d{7,8}$");
                Matcher s = t.matcher("");
                if (s.matches()) {
                    showNormalDialog("");
                } else {
                    showDialogUpdate(" ");
                }
                break;
            default:
                break;
        }
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
                    if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mContext.startActivity(intent);
                }
            }
        });
        listDialog.show();
    }

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
