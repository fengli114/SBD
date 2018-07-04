package com.mhy.shopingphone.ui.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.rxbus.Subscribe;
import com.mhy.sdk.utils.AppUtils;
import com.mhy.sdk.utils.FileUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.main.PersonalContract;
import com.mhy.shopingphone.model.bean.LunboBean;
import com.mhy.shopingphone.model.bean.photoBean;
import com.mhy.shopingphone.model.bean.rxbus.RxEventHeadBean;
import com.mhy.shopingphone.model.qiyeguanjia.Modelers;
import com.mhy.shopingphone.presenter.main.PersonalUpperPresenter;
import com.mhy.shopingphone.ui.activity.main.HeadSettingActivity;
import com.mhy.shopingphone.widgets.PersonalPopupWindow;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_HEAD_IMAGE_URI;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_UP_LOGO;

/**
 * 轮播广告
 */

public class CarouseActivityActivity extends BaseMVPCompatActivity<PersonalContract
        .PersonalUpperPresenter, PersonalContract.IPersonalUpperModel> implements PersonalContract.IPersonalUpperView {
    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.ll_bohaopan)
    LinearLayout ll_bohaopan;
    @BindView(R.id.ll_faxianye)
    LinearLayout ll_faxianye;
    @BindView(R.id.iv_startphoto)
    ImageView iv_startphoto;
    @BindView(R.id.iv_huafeiphoyo)
    ImageView iv_huafeiphoyo;
    @BindView(R.id.gouwuphoto)
    ImageView gouwuphoto;
    @BindView(R.id.iv_huibophoto)
    ImageView iv_huibophoto;
    @BindView(R.id.iv_tongxunluphoto)
    ImageView iv_tongxunluphoto;
    @BindView(R.id.iv_zhucephoto)
    ImageView iv_zhucephoto;
    private PersonalPopupWindow popupWindow;
    private File fileLOGO;
    private String pathimg;
    private String adtype;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PersonalUpperPresenter.newInstance();
    }

    @OnClick({R.id.order_back, R.id.ll_bohaopan, R.id.ll_faxianye, R.id.iv_startphoto, R.id.iv_huafeiphoyo, R.id.gouwuphoto, R.id.iv_huibophoto, R.id.iv_tongxunluphoto, R.id.iv_zhucephoto})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            case R.id.ll_bohaopan:
                Intent intent1 = new Intent(this, ImageUploadActivity.class);
                startActivity(intent1);
                break;
            case R.id.ll_faxianye:
                Intent intent2 = new Intent(this, ImageUploadActivity.class);
                startActivity(intent2);
                break;
            case R.id.iv_startphoto:
                adtype = "1";
                mPresenter.btnHeadClicked();
                break;
            case R.id.iv_huafeiphoyo:
                adtype = "6";
                mPresenter.btnHeadClicked();
                break;
            case R.id.gouwuphoto:
                adtype = "8";
                mPresenter.btnHeadClicked();
                break;
            case R.id.iv_huibophoto:
                adtype = "2";
                mPresenter.btnHeadClicked();
                break;
            case R.id.iv_tongxunluphoto:
                adtype = "7";
                mPresenter.btnHeadClicked();
                break;
            case R.id.iv_zhucephoto:
                adtype = "5";
                mPresenter.btnHeadClicked();
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxBus.get().register(this);
        Util.setStatusBarHeigh(mContext, tou);
        loadMyOrderList();
        initPopupView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_carousel;
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
            getfileIformation();
        } catch (URISyntaxException e) {
            e.printStackTrace();
            LogUtils.e("上传头像：" + e.toString());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mPresenter.onActivityResult(requestCode, resultCode, data);
    }

    /*
    * 上传文件接口
    * */
    public void getfileIformation() {
        OkHttpUtils
                .post()
                .url(Api.NEWGOODS + Api.PHOTO_URL)
//                .params(params)
                .addFile("file", fileLOGO.getName(), fileLOGO)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("fengli====" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            LogUtils.e("fengli===" + response);
                            photoBean baseBean = new Gson().fromJson(response, photoBean.class);
                            if (baseBean.getErrorCode() == 2000) {
                                if (baseBean.getJson().indexOf("http") != -1) {
                                    Contant.URL_IMAG_ICON = baseBean.getJson();
                                } else {
                                    Contant.URL_IMAG_ICON = Api.NEWGOODS + baseBean.getJson();
                                }
                                pathimg = baseBean.getJson();
                                loadMyOrderUpdata();
                                RxBus.get().send(RX_BUS_CODE_UP_LOGO);
                            } else {
                                ToastUtils.showToast("上传失败");
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void loadMyOrderList() {
        //请求参数
        Map<String, String> params = new HashMap<>();
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));

        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.PHOTOLUNBO_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli----" + response);
                        LunboBean bindUserEntity = new Gson().fromJson(response, LunboBean.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            for (int i = 0; i < bindUserEntity.getJson().size(); i++) {
                                if (bindUserEntity.getJson().get(i).getAdtype() == 1) {    //启动图
                                    Glide.with(mContext).load(bindUserEntity.getJson().get(i).getPath()).crossFade(300).placeholder(R
                                            .mipmap.img_default_movie).into(iv_startphoto);
                                }
                                if (bindUserEntity.getJson().get(i).getAdtype() == 6) {
                                    Glide.with(mContext).load(bindUserEntity.getJson().get(i).getPath()).crossFade(300).placeholder(R
                                            .mipmap.img_default_movie).into(iv_huafeiphoyo);
                                }
                                if (bindUserEntity.getJson().get(i).getAdtype() == 8) {
                                    Glide.with(mContext).load(bindUserEntity.getJson().get(i).getPath()).crossFade(300).placeholder(R
                                            .mipmap.img_default_movie).into(gouwuphoto);
                                }
                                if (bindUserEntity.getJson().get(i).getAdtype() == 2) {
                                    Glide.with(mContext).load(bindUserEntity.getJson().get(i).getPath()).crossFade(300).placeholder(R
                                            .mipmap.img_default_movie).into(iv_huibophoto);
                                }
                                if (bindUserEntity.getJson().get(i).getAdtype() == 7) {
                                    Glide.with(mContext).load(bindUserEntity.getJson().get(i).getPath()).crossFade(300).placeholder(R
                                            .mipmap.img_default_movie).into(iv_tongxunluphoto);
                                }
                                if (bindUserEntity.getJson().get(i).getAdtype() == 5) {
                                    Glide.with(mContext).load(bindUserEntity.getJson().get(i).getPath()).crossFade(300).placeholder(R
                                            .mipmap.img_default_movie).into(iv_zhucephoto);
                                }
                            }

                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }

    private void loadMyOrderUpdata() {
        Map<String, String> params = new HashMap<>();
        params.put("parentid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));
        params.put("path", pathimg);
        params.put("url", "");
        params.put("adtype", adtype);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.ADDPHOTOLUNBO_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli==-" + response);
                        LunboBean bindUserEntity = new Gson().fromJson(response, LunboBean.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            loadMyOrderList();
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
