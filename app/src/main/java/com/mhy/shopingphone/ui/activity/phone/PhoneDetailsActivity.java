package com.mhy.shopingphone.ui.activity.phone;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StatusBarUtils;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.MyPhoneAdapter;
import com.mhy.shopingphone.model.bean.cy.CYPhoneEntity;
import com.mhy.shopingphone.model.bean.phone.MyPhoneEntity;
import com.mhy.shopingphone.ui.activity.partner.XDButils;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.mhy.shopingphone.widgets.NewSpacesItemDecoration;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhoneDetailsActivity extends BaseMVPCompatActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.tv_phonename)
    TextView tvPhonename;
    @BindView(R.id.lin_short_message)
    LinearLayout linShortMessage;
    @BindView(R.id.lin_call)
    LinearLayout linCall;
    @BindView(R.id.lin_callusual)
    LinearLayout linCallusual;
    @BindView(R.id.datail_phone)
    RecyclerView mRc;
    @BindView(R.id.tv_phonenumber)
    TextView tvPhonenumber;
    @BindView(R.id.call_img)
    RelativeLayout callImg;
    @BindView(R.id.cb_cy)
    CheckBox checkBox;
    private CYPhoneEntity entity;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_details;
    }


    String phone_name, phone_number;
    private String id;
    private int type;
    private List<MyPhoneEntity> listStr;


    @Override
    protected void initView(Bundle savedInstanceState) {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
            StatusBarUtils.setBarColor(this, Color.BLACK);
            LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) title.getLayoutParams();
            lp.setMargins(0, StatusBarUtils.getStatusBarHeight
                    (mContext), 0, 0);
            title.setLayoutParams(lp);
        }
        // 控件的高强制设成56dp+状态栏高度

        tvTitle.setText("电话详情");
        tvRight.setText("编辑");
        Intent intent = getIntent();
        phone_name = intent.getStringExtra("phone_name");
        phone_number = intent.getStringExtra("phone_number");
        type = intent.getIntExtra("type", 0);
        id = intent.getStringExtra("contact_id");
        CYPhoneEntity cyPhoneEntity = XDButils.getCYPhoneInfo2(id + "");
        if (XEmptyUtils.isEmpty(cyPhoneEntity)) {
            checkBox.setChecked(false);
        } else {
            checkBox.setChecked(true);
        }
        entity = new CYPhoneEntity();
        entity.setName(phone_name);
        entity.setId(id);
        entity.setPhone(phone_number);

        listStr = new ArrayList<>();
        printContacts(id);
        checkBox.setEnabled(false);
        if (type == 1) {
            tvRight.setVisibility(View.GONE);
        } else {
            tvRight.setVisibility(View.VISIBLE);
        }


        tvPhonename.setText(phone_name);
        tvPhonenumber.setText(phone_number);

        mRc.addItemDecoration(new NewSpacesItemDecoration(15));
        mRc.setLayoutManager(new LinearLayoutManager(this));
        MyPhoneAdapter adapter = new MyPhoneAdapter(R.layout.my_phone_item);
        mRc.setAdapter(adapter);
//        adapter.setNewData(list);
        adapter.setOnItemClickListener(onItemClickListener);
        adapter.addData(listStr);
    }

    /**
     * Rc item的点击事件
     */
    BaseQuickAdapter.OnItemClickListener onItemClickListener = new BaseQuickAdapter.OnItemClickListener() {

        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                showDialogUp();
            } else {
                MyPhoneEntity infoBean = listStr.get(position);
                Intent intent = new Intent(mContext, DialBackActivity.class);
                intent.putExtra("phone_name", phone_name);
                intent.putExtra("phone_number", infoBean.phone);
                startActivity(intent);
            }
        }
    };

    /*
       * 自定义显示Contacts提供的联系人的方法
       */
    public void printContacts(String mId) {
        //生成ContentResolver对象
        ContentResolver contentResolver = getContentResolver();

        // 获得所有的联系人
        /*Cursor cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
         */
        //这段代码和上面代码是等价的，使用两种方式获得联系人的Uri
        Cursor cursor = contentResolver.query(Uri.parse("content://com.android.contacts/contacts"), null, null, null, null);

        // 循环遍历
        if (cursor.moveToFirst()) {

            // 查看联系人有多少个号码，如果没有号码，返回0
            int phoneCount = cursor
                    .getInt(cursor
                            .getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));

            if (phoneCount > 0) {
                // 获得联系人的电话号码列表
                Cursor phoneCursor = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                        null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                                + "=" + mId, null, null);
                if (phoneCursor.moveToFirst()) {
                    do {
                        //遍历所有的联系人下面所有的电话号码
                        String phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        LogUtils.e("号码:" + phoneNumber);
                        //使用Toast技术显示获得的号码
                        listStr.add(new MyPhoneEntity(phoneNumber.trim()));

                    } while (phoneCursor.moveToNext());
                }
            }


        }

    }


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @OnClick({R.id.al_back, R.id.tv_right, R.id.lin_short_message, R.id.lin_call, R.id.lin_callusual, R.id.call_img})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.tv_right:
                //编辑联系人
                Intent editIntent = new Intent(Intent.ACTION_EDIT, Uri.parse("content://com.android.contacts/contacts/" + id));
                startActivity(editIntent);
                break;
            case R.id.lin_short_message:
                Uri smsToUri = Uri.parse("smsto:" + phone_number);
                Intent intent2 = new Intent(Intent.ACTION_SENDTO, smsToUri);
                //短信内容
                startActivity(intent2);
                break;
            case R.id.lin_call:
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUp();
                } else {
                    Intent intent = new Intent(PhoneDetailsActivity.this, DialBackActivity.class);
                    intent.putExtra("phone_name", phone_name);
                    intent.putExtra("phone_number", phone_number);
                    startActivity(intent);
                }

                break;
            case R.id.call_img:
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUp();
                } else {
                    Intent intent1 = new Intent(PhoneDetailsActivity.this, DialBackActivity.class);
                    intent1.putExtra("phone_name", phone_name);
                    intent1.putExtra("phone_number", phone_number);
                    startActivity(intent1);
                }
                break;

            case R.id.lin_callusual:
                if (checkBox.isChecked()) {
                    checkBox.setChecked(false);
                    XDButils.dlCYPhoneInfo(phone_name);
                } else {
                    checkBox.setChecked(true);
                    List<CYPhoneEntity> list = XDButils.getCYPhoneInfo();
                    if (list != null && list.size() == 5) {
                        XDButils.dlCYPhoneInfo(list.get(0).getName());
                        XDButils.saveCYUserInfo(entity);
                    } else {
                        XDButils.saveCYUserInfo(entity);
                    }
                    //保存

                }
                break;
        }
    }

    private void showDialogUp() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(PhoneDetailsActivity.this);
        // 设置提示框的标题
        builder.setMessage("你还没有登录").setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PhoneDetailsActivity.this, LoginActivty.class);
                        startActivity(intent);
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
