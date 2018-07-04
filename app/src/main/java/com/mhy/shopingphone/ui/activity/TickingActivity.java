package com.mhy.shopingphone.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.rxbus.Subscribe;
import com.mhy.sdk.utils.AppUtils;
import com.mhy.sdk.utils.FileUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.contract.main.PersonalContract;
import com.mhy.shopingphone.model.bean.rxbus.RxEventHeadBean;
import com.mhy.shopingphone.presenter.main.PersonalUpperPresenter;
import com.mhy.shopingphone.ui.activity.main.HeadSettingActivity;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.mhy.shopingphone.widgets.PersonalPopupWindow;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import butterknife.BindView;
import butterknife.OnClick;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_HEAD_IMAGE_URI;

/**
 * 商务反馈
 */

public class TickingActivity extends BaseMVPCompatActivity<PersonalContract
        .PersonalUpperPresenter, PersonalContract.IPersonalUpperModel> implements PersonalContract.IPersonalUpperView {
    @BindView(R.id.al_back)
    RelativeLayout order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.tv_right)    //反馈
            Button tv_right;
    @BindView(R.id.et_fankui)    //反馈内容
            EditText et_fankui;
    @BindView(R.id.iv_one)       //图片1
            ImageView iv_one;
    @BindView(R.id.iv_ones)       //图片1删除
            ImageView iv_ones;
    @BindView(R.id.iv_two)       //图片2
            ImageView iv_two;
    @BindView(R.id.iv_twos)       //图片2删除
            ImageView iv_twos;
    @BindView(R.id.iv_three)       //图片3
            ImageView iv_three;
    @BindView(R.id.iv_threes)       //图片3删除
            ImageView iv_threnes;
    private PersonalPopupWindow popupWindow;
    private File fileLOGO;
    private String flag;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PersonalUpperPresenter.newInstance();
    }

    @OnClick({R.id.al_back, R.id.tv_right, R.id.iv_one, R.id.iv_two, R.id.iv_three, R.id.iv_ones, R.id.iv_twos, R.id.iv_threes})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            //返回
            case R.id.al_back:
                finish();
                break;
            case R.id.tv_right:
                if (et_fankui.getText().toString() != null && et_fankui.getText().length() > 0) {

                }
                break;
            case R.id.iv_one:
                flag = "1";
                mPresenter.btnHeadClicked();
                break;
            case R.id.iv_two:
                flag = "2";
                mPresenter.btnHeadClicked();
                break;
            case R.id.iv_three:
                flag = "3";
                mPresenter.btnHeadClicked();
                break;
            case R.id.iv_ones:
                iv_one.setImageResource(R.drawable.customerservive_addbuttuon);
                iv_ones.setVisibility(View.GONE);
                break;
            case R.id.iv_twos:
                iv_two.setImageResource(R.drawable.customerservive_addbuttuon);
                iv_twos.setVisibility(View.GONE);
                break;
            case R.id.iv_threes:
                iv_three.setImageResource(R.drawable.customerservive_addbuttuon);
                iv_threnes.setVisibility(View.GONE);
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxBus.get().register(this);
        Util.setStatusBarHeigh(mContext, tou);
        initPopupView();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tickling;
    }

    @Override
    public void initPopupView() {                                  //调取弹出窗
        popupWindow = new PersonalPopupWindow(this);
        popupWindow.setOnItemClickListener(new PersonalPopupWindow.OnItemClickListener() {
            @Override
            public void onCaremaClicked() {
                mPresenter.btnCameraClicked();     //相机点击按钮
            }

            @Override
            public void onPhotoClicked() {
                mPresenter.btnPhotoClicked();        //相册按钮点击
            }

            @Override
            public void onCancelClicked() {
                mPresenter.btnCancelClicked();     //取消按钮点击
            }
        });
    }

    @Override
    public void showHead(Bitmap bitmap, Uri uri) {
        try {
            fileLOGO = new File(new URI(uri.toString()));
        } catch (URISyntaxException e) {
            e.printStackTrace();
            LogUtils.e("上传头像：" + e.toString());
        }
    }

    @Override
    public void showPopupView() {
        View parent = LayoutInflater.from(this).inflate(R.layout.fragment_personal, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM | Gravity.LEFT, 0, 0);
    }

    @Override
    public void dismissPopupView() {
        popupWindow.dismiss();
    }

    @Override
    public boolean popupIsShowing() {
        return popupWindow.isShowing();
    }

    @Override
    public void gotoHeadSettingActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(mContext, HeadSettingActivity.class);
        intent.setData(uri);
        startActivity(intent);
    }

    @Override
    public void gotoSystemPhoto(int requestCode) {
        //跳转到调用系统图库
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media
                .EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), requestCode);
    }

    @Override
    public void gotoSystemCamera(File tempFile, int requestCode) {
///跳转到调用系统相机
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            ContentValues contentValues = new ContentValues(1);
            contentValues.put(MediaStore.Images.Media.DATA, tempFile.getAbsolutePath());
            Uri uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        } else {
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    /**
     * RxBus接收图片Uri
     *
     * @param bean RxEventHeadBean
     */
    @Subscribe(code = RX_BUS_CODE_HEAD_IMAGE_URI)
    public void rxBusEvent(RxEventHeadBean bean) {
        Uri uri = bean.getUri();
        if (uri == null) {
            return;
        }
        String cropImagePath = FileUtils.getRealFilePathFromUri(AppUtils.getContext(), uri);
        try {
            fileLOGO = new File(new URI(uri.toString()));
            LogUtils.e("fengli===" + fileLOGO);
//            getfileIformation();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            LogUtils.e("上传头像：" + e.toString());
        }
        Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
        if (bitMap != null)
            if (flag.equals("1")) {
                iv_one.setImageBitmap(bitMap);
                iv_ones.setVisibility(View.VISIBLE);
            } else if (flag.equals("2")) {
                iv_two.setImageBitmap(bitMap);
                iv_twos.setVisibility(View.VISIBLE);
            } else {
                iv_three.setImageBitmap(bitMap);
                iv_threnes.setVisibility(View.VISIBLE);
            }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
