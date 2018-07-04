package com.mhy.shopingphone.ui;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mhy.sdk.RxManager;
import com.mhy.sdk.base.activity.BaseCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.greendao.bean.BannerListBean;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

public class VerificationActivity extends BaseCompatActivity {

    @BindView(R.id.tou)
    ImageView tou;
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
    @BindView(R.id.edit_shoper_account)
    EditText editShoperAccount;
    @BindView(R.id.edit_shoper_pwd)
    EditText editShoperPwd;
    @BindView(R.id.btn_partner_login)
    Button btnPartnerLogin;
    @BindView(R.id.cb_look)
    CheckBox cbLook;
    @BindView(R.id.rl_cb_look)
    RelativeLayout rlCbLook;
    private final static int Verification_CODE = 4;
    @Override
    protected void initView(Bundle savedInstanceState) {
         Util.setStatusBarHeigh(mContext,tou);
         tvTitle.setText("商家认证");
         cbLook.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_verification;
    }


    @OnClick({R.id.al_back, R.id.btn_partner_login, R.id.rl_cb_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.btn_partner_login:
                if (isOk()){
                    goVer();
                }
                break;
            case R.id.rl_cb_look:
                if (cbLook.isChecked()) {
                    cbLook.setChecked(false);
                } else {
                    cbLook.setChecked(true);
                }
                break;
        }
    }
    private boolean isOk() {
        String account = editShoperAccount.getText().toString();
        String pwdStr = editShoperPwd.getText().toString();
        if (TextUtils.isEmpty(pwdStr)) {
            ToastUtils.showToast("密码不能为空");
            return false;
        }
        if (TextUtils.isEmpty(account)) {
            ToastUtils.showToast("用户名不能为空");
            return false;
        }
        return true;
    }

    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            if (b) {
                editShoperPwd.setInputType(EditorInfo.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD); //明文密码
            } else {
                editShoperPwd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            }
        }
    };
    private void goVer() {
        Map<String, String> params = new HashMap<>();
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        params.put("UserName", editShoperAccount.getText().toString());
        params.put("Password", editShoperPwd.getText().toString());
        String paramsstring = StringUtil.mapToJson(params);
        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = "Vm9pcEFwaQ/AuthAgent/";
        RxManager mRxManager = new RxManager();
        Observable<Ceshi> compose = RetrofitCreateHelper.createApi(Api.class, Api.GOODS).goVer(paramstr)
                .compose(RxHelper.<Ceshi>rxSchedulerHelper());
        mRxManager.register(compose.subscribe(new Consumer<Ceshi>() {
            @Override
            public void accept(Ceshi bean) throws Exception {
                if (bean.getCode().equals("0")) {
                    ToastUtils.showToast("认证成功");
                    Intent mIntent = new Intent();
                    setResult(Verification_CODE, mIntent);
                    finish();
                    finish();
                } else {
                    ToastUtils.showToast(bean.getMess());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ToastUtils.showToast("网络异常，请检查网络~");
            }
        }));
    }
}
