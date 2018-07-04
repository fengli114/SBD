package com.mhy.shopingphone.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mhy.sdk.base.activity.BaseCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.greendao.DaoSession;
import com.mhy.shopingphone.ui.MainActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import okhttp3.Call;

public class FlashActivity extends BaseCompatActivity {
    @BindView(R.id.ll_skip)
    LinearLayout llSkip;
    @BindView(R.id.tv_count_down)
    TextView tvCountDown;
    @BindView(R.id.iv_welcome_bg)
    ImageView ivWelcomeBg;
    private static final String TAG = "RxPermission";
    private boolean mIsCancle;
    private int mTime = 3;
    Map<String, String> params = new HashMap<>();
    private String paramstr;
    private DaoSession daoInstant;

    @Override
    protected void initView(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //注：魅族pro6s-7.0-flyme6权限没有像类似6.0以上手机一样正常的提示dialog获取运行时权限，而是直接默认给了权限
        llSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIsCancle = true;
                startActivity(new Intent(FlashActivity.this, MainActivity.class));
                finish();
            }
        });
        requestPermissions();
        Contant.Moblie = (String) SharedPreferencesHelper.getInstance().getData("Mobile", "");
        getencryption();
        getInfo();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_flash;
    }

    private void requestPermissions() {
        RxPermissions rxPermission = new RxPermissions(FlashActivity.this);
        //请求权限全部结果
        rxPermission.request(
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR,
                Manifest.permission.READ_CALL_LOG,
                Manifest.permission.WRITE_CALL_LOG,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_SMS,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.CAMERA,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.SEND_SMS)
                .subscribe(new Consumer<Boolean>() {
                    @Override
                    public void accept(Boolean granted) throws Exception {
                        if (!granted) {
//                            ToastUtils.showToast("App未能获取全部需要的相关权限，部分功能可能不能正常使用.");
                        }
                        //不管是否获取全部权限，进入主页面
                        initCountDown();
                    }
                });
        //分别请求权限
//                rxPermission.requestEach(Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                        Manifest.permission.READ_CALENDAR,
//                        Manifest.permission.READ_CALL_LOG,
//                        Manifest.permission.READ_CONTACTS,
//                        Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.READ_SMS,
//                        Manifest.permission.RECORD_AUDIO,
//                        Manifest.permission.CAMERA,
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.SEND_SMS)
        //注：魅族pro6s-7.0-flyme6权限没有像类似6.0以上手机一样正常的提示dialog获取运行时权限，而是直接默认给了权限。魅族pro6s动态获取权限不会回调下面的方法
        //        rxPermission.requestEach(
        //                Manifest.permission.CAMERA,
        //                Manifest.permission.READ_PHONE_STATE,
        //                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        //                Manifest.permission.READ_EXTERNAL_STORAGE,
        //                Manifest.permission.ACCESS_COARSE_LOCATION)
        //                .subscribe(new Consumer<Permission>() {
        //                    @Override
        //                    public void accept(Permission permission) throws Exception {
        //                        if (permission.granted) {
        //                            // 用户已经同意该权限
        //                            Log.d(TAG, permission.name + " is granted.");
        //                        } else if (permission.shouldShowRequestPermissionRationale) {
        //                            // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
        //                            Log.d(TAG, permission.name + " is denied. More info should be provided.");
        //                        } else {
        //                            // 用户拒绝了该权限，并且选中『不再询问』
        //                            Log.d(TAG, permission.name + " is denied.");
        //                        }
        //                    }
        //                });
    }

    private void initCountDown() {
        Observable.interval(1, TimeUnit.SECONDS)
                .take(3)//计时次数
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mTime - aLong;// 3-0 3-2 3-1
                    }
                })
                .compose(RxHelper.<Long>rxSchedulerHelper())
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(Long value) {
                        //                        Logger.e("value = " + value);
                        tvCountDown.setText(String.valueOf(value));
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {
                        if (!mIsCancle) {
//                            if (XEmptyUtils.isEmpty(Contant.Moblie)) {
//                            startActivity(new Intent(FlashActivity.this, LoginActivty.class));
//                            } else {
                            startActivity(new Intent(FlashActivity.this, MainActivity.class));
//                            }
                            finish();
                        }
                    }
                });
    }

    private void getencryption() {
        Map<String, String> parames = new HashMap<>();
        parames.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        parames.put("ParentId", String.valueOf(Contant.PARENTID));
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.TOKEN_URL)
                .params(parames)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                        if (ceshi.getErrorCode() == 2000) {
                            SharedPreferencesHelper.getInstance().saveData("Tokens", ceshi.getData());//代理Id
                        }
                    }
                });
    }

    /**
     * 启动图接口
     */
    private void getInfo() {
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        params.put("ParentId", String.valueOf(Contant.PARENTID));
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.INITIATE_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                        if (ceshi.getErrorCode() == 2000) {
                            Glide.with(mContext).load(ceshi.getJson().getPath()).crossFade(300).placeholder(R
                                    .mipmap.img_default_movie).into(ivWelcomeBg);
                        }
                    }
                });
    }
}
