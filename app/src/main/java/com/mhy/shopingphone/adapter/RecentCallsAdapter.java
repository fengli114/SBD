package com.mhy.shopingphone.adapter;


import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseViewHolder;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.recentcalls.RecentCallsItemBean;
import com.mhy.shopingphone.ui.activity.phone.DialBackActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.mhy.shopingphone.ui.fragment.addresslist.AddressListFragment;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.List;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;

/**
 * 作者： "RedRainM" on 2017/12/13 0013.
 * 描述： 通话记录的适配器
 */

public class RecentCallsAdapter extends BaseCompatAdapter<RecentCallsItemBean, BaseViewHolder> {
    public RecentCallsAdapter(@LayoutRes int layoutResId) {
        super(layoutResId);
    }

    public RecentCallsAdapter(@LayoutRes int layoutResId, @Nullable List<RecentCallsItemBean> data) {
        super(layoutResId, data);
    }

    public RecentCallsAdapter(@Nullable List<RecentCallsItemBean> data) {
        super(data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final RecentCallsItemBean item) {
        helper.setText(R.id.tv_name, item.getName());
        helper.setText(R.id.tv_number, item.getPhone().trim());
        helper.setText(R.id.tv_date, item.getData());

        helper.getView(R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUp();
                } else {
                    Intent intent = new Intent(mContext, DialBackActivity.class);
                    intent.putExtra("phone_name", item.getName());
                    intent.putExtra("phone_number", item.getPhone());
                    mContext.startActivity(intent);
                }

            }
        });
    /*    helper.getView(R.id.contente).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentResolver resolver = mContext.getContentResolver();
                if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, new String[]{"_id"}, "number=? and (type=1 or type=2 or type=3)", new String[]{item.getPhone()},
                        "_id desc limit 1");
                if (cursor.moveToFirst()) {
                    int id = cursor.getInt(0);
                    resolver.delete(CallLog.Calls.CONTENT_URI, "_id=?", new String[]{id + ""});
                }
                if (canclePhone != null) {
                    canclePhone.cancleItem();
                }
                notifyDataSetChanged();
            }
        });*/
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
  /*  public interface canclePhone {
        void cancleItem();
    }
    private canclePhone canclePhone;
    public void setCanclePhone(canclePhone canclePhone) {
        this.canclePhone = canclePhone;
    }*/
}
