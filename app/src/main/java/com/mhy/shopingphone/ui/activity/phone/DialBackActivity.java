package com.mhy.shopingphone.ui.activity.phone;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.phone.DialBackContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.phone.MailistCallBean;
import com.mhy.shopingphone.presenter.phone.DialBackPresenter;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2018/1/12 0012.
 * 描述：
 */

public class DialBackActivity extends BaseMVPCompatActivity<DialBackContract
        .DialBackPresenter, DialBackContract.IDialBackModel> implements
        DialBackContract.IDialBackView {

    @BindView(R.id.tv_callphone_name)
    TextView tvCallphoneName;
    @BindView(R.id.tv_call_number)
    TextView tvCallNumber;
    @BindView(R.id.tv_calladdress)
    TextView tvCalladdress;
    @BindView(R.id.dial_bg)
    ImageView dialBg;
    @BindView(R.id.auto)
    LinearLayout auto;
    @BindView(R.id.img_dialback)
    ImageView imgDialback;

    private Map<String, String> params;
    private String paramstr;
    private String phone_name;
    private String phone_number;
    private TelephonyManager tManager;
    private MediaPlayer mMediaPlayer;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return DialBackPresenter.newInstance();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        params = new HashMap<>();
        Intent intent = getIntent();
        if (intent.getStringExtra("phone_name") != null && intent.getStringExtra("phone_name").length() > 0) {
            phone_name = intent.getStringExtra("phone_name").replace(" ", "");
        }
        String stre = intent.getStringExtra("phone_number").replace("+86", "");
        phone_number = stre.replace(" ", "");
        tvCallphoneName.setText(phone_name);
        tvCallNumber.setText(phone_number);
        LogUtils.e("idid:" + phone_number + phone_name);
        String phonebg = (String) SharedPreferencesHelper.getInstance().getData("phonebg", "");
//        dialBg.setAlpha(80);
        if (!XEmptyUtils.isEmpty(phonebg)) {
            Glide.with(mContext).load(phonebg).crossFade(300).placeholder(R
                    .mipmap.gray_default).into(dialBg);
        }

        //获取系统的TelephonyManager对象
        tManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //创建一个通话状态监听器
        PhoneStateListener pListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String number) {
                // TODO Auto-generated method stub
                switch (state) {
                    case TelephonyManager.CALL_STATE_IDLE://无任何状态

                        break;

                    case TelephonyManager.CALL_STATE_OFFHOOK://接听来电
                        break;

                    case TelephonyManager.CALL_STATE_RINGING://来电
//                        autoPhone();
                        finish();
                        break;
                    default:
                        break;
                }

            }
        };
        //为tManager添加监听器
        tManager.listen(pListener, PhoneStateListener.LISTEN_CALL_STATE);
        goDialBack();
    }

    private boolean isPhone() {
        if (RegexUtils.isMobileExact(phone_number) || RegexUtils.isTel(phone_number)) {
            return true;
        } else {
            ToastUtils.showToast("请输入正确的号码");
            return false;
        }
    }

    //请求
    private void goDialBack() {
        AddCall();
        if (isPhone()) {
            params.put("Calle164", phone_number);//被叫  phone_number
            params.put("Caller", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));//主叫
            params.put("AgentID", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));
            OkHttpUtils.post()
                    .url(Api.NEWGOODS + Api.CALLPHONE_URl)
                    .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                    .params(params)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            ToastUtils.showToast("网络异常");
                            finish();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                            if (ceshi.getErrorCode() == 2000) {
                                mMediaPlayer = MediaPlayer.create(DialBackActivity.this, R.raw.callcoming);
                                mMediaPlayer.start();
                                mMediaPlayer.setLooping(false);//设置是否循环播放
                                ToastUtils.showToast("正在接通中...");
                            } else if (ceshi.getErrorCode() == 1005) {
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
                                                ToastUtils.showToast("网络异常");
                                                finish();
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                                                if (ceshi.getErrorCode() == 2000) {
                                                    SharedPreferencesHelper.getInstance().saveData("Tokens", ceshi.getData());//代理Id
                                                    goDialBack();
                                                } else {
                                                    ToastUtils.showToast("网络异常");
                                                }
                                            }
                                        });
                            } else {
                                ToastUtils.showToast(ceshi.getData());
                                finish();
                            }
                        }
                    });
        } else {
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dial_back;
    }

    /*
    * 5.0以上接听方法  部分不支持不行
    * */
    private void autoPhone() {
        try {
            if (android.os.Build.VERSION.SDK_INT >= 19) {
                AudioManager am = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
                long eventtime = SystemClock.uptimeMillis() - 1;
                KeyEvent var6 = new KeyEvent(/*eventtime, 1L + eventtime, */0, 79/*, 0, 0, 0, 0, 128*/);
                KeyEvent var7 = new KeyEvent(1, 79);
                am.dispatchMediaKeyEvent(var6);
                am.dispatchMediaKeyEvent(var7);
            } else {
                Runtime.getRuntime().exec("input keyevent " + Integer.toString(KeyEvent.KEYCODE_HEADSETHOOK));
            }

        } catch (IOException e) {
        }
    }

    @Override
    public void goDialBack(Ceshi ceshi) {

    }

    @SuppressLint("MissingPermission")
    private void AddCall() {
        try {
            ContentValues localContentValues = new ContentValues();
            localContentValues.put("name", phone_name);
            localContentValues.put("number", phone_number);
            localContentValues.put("type", Integer.valueOf(2));
            localContentValues.put("date", Long.valueOf(System.currentTimeMillis()));
            localContentValues.put("duration", Integer.valueOf(1));
            getContentResolver().insert(CallLog.Calls.CONTENT_URI, localContentValues);
            return;
        } catch (Exception paramContext) {
            paramContext.printStackTrace();
            LogUtils.e(paramContext.toString() + "插入通话记录");
        }

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.stop();//停止播放
            mMediaPlayer.release();//释放资源
        }
    }

    @OnClick(R.id.img_dialback)
    public void onViewClicked() {
       /* if (mMediaPlayer != null) {
            mMediaPlayer.stop();//停止播放
            mMediaPlayer.release();//释放资源
        }*/
        finish();
    }
}
