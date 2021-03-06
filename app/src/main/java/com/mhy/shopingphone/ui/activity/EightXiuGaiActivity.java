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
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

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
import com.mhy.shopingphone.adapter.ImageUploadAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.main.PersonalContract;
import com.mhy.shopingphone.model.bean.photoBean;
import com.mhy.shopingphone.model.bean.rxbus.RxEventHeadBean;
import com.mhy.shopingphone.model.qiyeguanjia.Modelers;
import com.mhy.shopingphone.presenter.main.PersonalUpperPresenter;
import com.mhy.shopingphone.ui.activity.main.HeadSettingActivity;
import com.mhy.shopingphone.widgets.PersonalPopupWindow;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_HEAD_IMAGE_URI;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_UP_LOGO;

/**
 * 八大模块
 */

public class EightXiuGaiActivity extends BaseMVPCompatActivity<PersonalContract
        .PersonalUpperPresenter, PersonalContract.IPersonalUpperModel> implements PersonalContract.IPersonalUpperView {
    @BindView(R.id.et_wenzi)
    EditText et_wenzi;
    @BindView(R.id.et_youxianji)
    EditText et_youxianji;
    @BindView(R.id.et_urlers)
    EditText et_urlers;
    @BindView(R.id.btn_shangchuan)
    Button btn_shangchuan;
    @BindView(R.id.al_back)
    RelativeLayout al_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.btn_queding)
    Button btn_queding;
    @BindView(R.id.iv_wancheng)
    ImageView iv_wancheng;
    private PersonalPopupWindow popupWindow;
    private File fileLOGO;
    private String pathimg;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PersonalUpperPresenter.newInstance();
    }

    @OnClick({R.id.al_back, R.id.btn_shangchuan, R.id.btn_queding})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            //返回
            case R.id.al_back:
                finish();
                break;
            case R.id.btn_shangchuan:
                mPresenter.btnHeadClicked();
                break;
            case R.id.btn_queding:
                loadMyOrderList();

                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxBus.get().register(this);
        Util.setStatusBarHeigh(mContext, tou);
        pathimg = String.valueOf(getIntent().getSerializableExtra("logo"));
        et_youxianji.setText(String.valueOf(getIntent().getSerializableExtra("grade")));
        et_urlers.setText(String.valueOf(getIntent().getSerializableExtra("url")));
        et_wenzi.setText(String.valueOf(getIntent().getSerializableExtra("text")));
        initPopupView();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_xiugai;
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
                                iv_wancheng.setVisibility(View.VISIBLE);
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
        params.put("text", et_wenzi.getText().toString());
        params.put("id", String.valueOf(getIntent().getSerializableExtra("photos")));
        params.put("agentid", String.valueOf(getIntent().getSerializableExtra("user")));
        params.put("logo", pathimg);
        params.put("url", et_urlers.getText().toString());
        params.put("datastatus", "1");
        params.put("grade", et_youxianji.getText().toString());
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.PHOTOXIUGAI_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Modelers bindUserEntity = new Gson().fromJson(response, Modelers.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            ToastUtils.showToast(bindUserEntity.getData());
                            finish();
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
