package com.mhy.shopingphone.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.DownloadUtil;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.AndroidBean;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2018/4/23 0023.
 * 描述：
 */

public class AboutWeActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.order_back)
    ImageView order_back;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.rr_introduction)
    RelativeLayout rr_introduction;
    @BindView(R.id.rr_score)
    RelativeLayout rr_score;
    @BindView(R.id.tv_version)
    TextView tv_version;
    @BindView(R.id.rr_issue)
    RelativeLayout rrissue;
    @BindView(R.id.privates)
    TextView privates;
    @BindView(R.id.iv_gengxin)
    ImageView gengxin;
    @BindView(R.id.tv_banben)
    TextView tv_banben;
    private String shanghuId;
    private String stres;
    private String upatepath;

    @Override
    protected int getLayout() {
        return R.layout.activity_about_we;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        tv_version.setText("随便打 " + getVersionName());
        order_back.setOnClickListener(this);
        rr_introduction.setOnClickListener(this);
        rr_score.setOnClickListener(this);
        rrissue.setOnClickListener(this);
        privates.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkVersion();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            case R.id.rr_introduction:     //检查更新
                if (stres != null && stres.length() > 0) {
                    checkVersions();
                }
                break;
            case R.id.rr_score:       //历史版本
                Intent intent = new Intent();
                intent.setClass(mContext, HistoryVersionsActivity.class);
                startActivity(intent);
                break;
            case R.id.rr_issue:      //常见问题
                WebViewActivity.skip(mContext,
                        Api.GOODS + Contant.URL_HELP + String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")),
                        "常见问题");
                break;
            case R.id.privates:      //隐私协议
                WebViewActivity.skip(mContext, "http://tcs.sbdznkj.com:2018/file/AboutUs/about.html", "条款和隐私协议");
                break;
        }
    }

    //对比本程序的版本号和最新程序的版本号
    public void checkVersion() {
        Map<String, String> params = new HashMap<>();
        params.put("ParentId", Contant.PARENTID);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.VERSIONING_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        AndroidBean bindUserEntity = new Gson().fromJson(response, AndroidBean.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            if (bindUserEntity.getJson().getAndroidversion() != null && bindUserEntity.getJson().getAndroidversion().length() > 0) {
                                try {
                                    stres = bindUserEntity.getJson().getAndroidversion();
                                    upatepath = bindUserEntity.getJson().getAndroidpath();
                                    if (stres != null && !getVersionName().equals(stres)) {   //检测更新
                                        tv_banben.setText(stres);
                                        tv_banben.setVisibility(View.VISIBLE);
                                        gengxin.setVisibility(View.VISIBLE);
                                    } else {
                                        tv_banben.setVisibility(View.GONE);
                                        gengxin.setVisibility(View.GONE);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                });
    }

    //对比本程序的版本号和最新程序的版本号
    public void checkVersions() {
        //如果检测本程序的版本号小于服务器的版本号，那么提示用户v更新
        try {
//            LogUtils.e(Float.parseFloat(getVersionName()) + "banben" + stres);
            if (!getVersionName().equals(stres)) {
                showDialogUpdate();//弹出提示版本更新的对话框
            } else {
                //否则吐司，说现在是最新的版本
                ToastUtils.showToast("当前已经是最新的版本");
                // SDToast.showToast("当前已经是最新的版本");
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    /*
* 获取当前程序的版本名
*/
    private String getVersionName() {
        //获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        //getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo = null;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packInfo.versionName;
    }

    /**
     * 提示版本更新的对话框
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setMessage("发现新版本！请及时更新").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DownloadUtil(AboutWeActivity.this, upatepath);
                    }
                }).
                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", null);
        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();
        alertDialog.getButton(alertDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
        alertDialog.getButton(alertDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);
    }
}
