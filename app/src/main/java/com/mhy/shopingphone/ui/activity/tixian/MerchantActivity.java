package com.mhy.shopingphone.ui.activity.tixian;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

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
import com.mhy.shopingphone.model.bean.photoBean;
import com.mhy.shopingphone.model.bean.rxbus.RxEventHeadBean;
import com.mhy.shopingphone.model.qiyeguanjia.Logdwenglu;
import com.mhy.shopingphone.model.qiyeguanjia.Logginges;
import com.mhy.shopingphone.presenter.main.PersonalUpperPresenter;
import com.mhy.shopingphone.ui.activity.main.HeadSettingActivity;
import com.mhy.shopingphone.widgets.PersonalPopupWindow;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_HEAD_IMAGE_URI;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_UP_LOGO;

/**
 * 商户添加
 */

public class MerchantActivity extends BaseMVPCompatActivity<PersonalContract
        .PersonalUpperPresenter, PersonalContract.IPersonalUpperModel> implements PersonalContract.IPersonalUpperView {
    @BindView(R.id.al_back)
    RelativeLayout order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.btn_photo)       //图片
            Button btn_photo;
    @BindView(R.id.sp_feilv)   //费率
            Spinner sp_feilv;
    @BindView(R.id.sp_yinqing)    //通话引擎
            Spinner sp_yinqing;
    @BindView(R.id.btn_add)
    Button btn_add;
    @BindView(R.id.ed_names)     //用户名称
            EditText ed_names;
    @BindView(R.id.et_userpward)
    EditText et_userpward;
    @BindView(R.id.et_adid)
    EditText et_adid;
    @BindView(R.id.et_pid)
    EditText et_pid;
    @BindView(R.id.et_fenrun)
    EditText et_fenrun;
    @BindView(R.id.Spinnerzhifu)
    Spinner Spinnerzhifu;
    @BindView(R.id.Spinnerfenrun)
    Spinner Spinnerfenrun;
    @BindView(R.id.Spinnerxiaji)
    Spinner Spinnerxiaji;
    @BindView(R.id.et_fenrunbl)
    EditText et_fenrunbl;
    @BindView(R.id.Spinnerzhifufenrun)
    Spinner Spinnerzhifufenrun;
    @BindView(R.id.tv_touxiangwenjian)
    TextView tv_touxiangwenjian;
    @BindView(R.id.et_fuzeren)
    EditText et_fuzeren;
    private List<String> data_list;
    private ArrayAdapter<String> arr_adapter;
    private PersonalPopupWindow popupWindow;
    private File fileLOGO;
    private String pathimg;
    private Logginges userBean;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PersonalUpperPresenter.newInstance();
    }

    @OnClick({R.id.al_back, R.id.btn_photo, R.id.btn_add})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            //返回
            case R.id.al_back:
                finish();
                break;
            case R.id.btn_photo:
                mPresenter.btnHeadClicked();
                break;
            case R.id.btn_add:
                if (isPhone()) {
                    adddata();
                }
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        RxBus.get().register(this);
        Util.setStatusBarHeigh(mContext, tou);
        pathimg = "";
        initPopupView();
        userBean = (Logginges) getIntent().getSerializableExtra("something");
        data_list = new ArrayList<String>();
        for (int i = 0; i < userBean.getJson().getFreeroutes().size(); i++) {
            data_list.add(userBean.getJson().getFreeroutes().get(i).getName());
        }
        //适配器
        arr_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data_list);
        //设置样式
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        sp_feilv.setAdapter(arr_adapter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_sogo_amend;
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
                                tv_touxiangwenjian.setText("已选择");
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

    public void adddata() {
        String zaixianzhifu = "true";
        String xiaji = "true";
        String xuyaofenrun = "true";
        String zhifufenrun = "true";
        String fenrun = "";
        String zhifu = "";
        String emals = "";
        if (Spinnerzhifu.getSelectedItem().toString().equals("允许")) {
            zaixianzhifu = "true";
        } else {
            zaixianzhifu = "flase";
        }
        if (Spinnerxiaji.getSelectedItem().toString().equals("允许")) {
            xiaji = "true";
        } else {
            xiaji = "flase";
        }
        if (Spinnerzhifufenrun.getSelectedItem().toString().equals("允许")) {
            zhifufenrun = "true";
        } else {
            zhifufenrun = "flase";
        }
        if (Spinnerfenrun.getSelectedItem().toString().equals("允许")) {
            xuyaofenrun = "true";
        } else {
            xuyaofenrun = "flase";
        }
        if (et_fenrun.getText().toString() != null) {
            fenrun = et_fenrun.getText().toString();
        }
        if (et_fenrunbl.getText().toString() != null) {
            zhifu = et_fenrunbl.getText().toString();
        }
        if (et_fuzeren.getText().toString()!=null){
            emals = et_fuzeren.getText().toString();
        }
        String yinqing ;
        if (sp_yinqing.getSelectedItem().toString().equals("随便打JH引擎")){
            yinqing = "1";
        }else {
            yinqing = "2";
        }
        Map<String, String> params = new HashMap<>();
        params.put("parentid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));
        params.put("username", ed_names.getText().toString());
        params.put("password", et_userpward.getText().toString());
        params.put("pic", pathimg);   //图片路径
        params.put("adid", et_adid.getText().toString());
        params.put("pid", et_pid.getText().toString());
        params.put("dkbl", fenrun);
        params.put("freeroute", sp_feilv.getSelectedItem().toString());    //费率
        params.put("calltype", yinqing);   //通话引擎
        params.put("isalipay", zaixianzhifu);
        params.put("nextagent", xiaji);
        params.put("email", emals);
        params.put("role", "2");
        params.put("isprofit", xuyaofenrun);
        params.put("isaliprofit", zhifufenrun);
        params.put("aliprofitratio", zhifu);
        LogUtils.e("fengli---" + params.toString());
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.ADDDATA_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("fengli====" + e.toString());
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Logdwenglu bindUserEntity = new Gson().fromJson(response, Logdwenglu.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            ToastUtils.showToast(bindUserEntity.getData());
                            finish();
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }

    private boolean isPhone() {
        String str1 = ed_names.getText().toString();
        String str2 = et_userpward.getText().toString();
        if (XEmptyUtils.isEmpty(str1)) {
            ToastUtils.showToast("用户名称不能为空");
            return false;
        }
        if (XEmptyUtils.isEmpty(str2)) {
            ToastUtils.showToast("密码不能为空");
            return false;
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
