package com.mhy.shopingphone.ui.activity.start;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.mhy.sdk.base.activity.BaseCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.VpAdapter;
import com.mhy.shopingphone.ui.fragment.login.ForgetPwdFragment;
import com.mhy.shopingphone.ui.fragment.login.GetCodeFragment;
import com.mhy.shopingphone.ui.fragment.login.ISPhoneFragment;
import com.mhy.shopingphone.ui.fragment.login.LoginFragment;
import com.mhy.shopingphone.widgets.NonSlipViewPager;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivty extends BaseCompatActivity {

    @BindView(R.id.regis_img)
    ImageView regisImg;
    @BindView(R.id.rl_back)
    RelativeLayout rlBack;
    @BindView(R.id.rl_backs)
    RelativeLayout rl_backs;
    @BindView(R.id.lin_tilte)
    RelativeLayout linTilte;
    @BindView(R.id.vp_start)
    NonSlipViewPager vpStart;

    private List<Fragment> fragments;
    private int index = 0;
    private String phoneStr;
    private String typeStr;//判断是注册还是修改密码

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

    public void setPhoneStr(String phoneStr) {
        this.phoneStr = phoneStr;
    }

    public String getPhoneStr() {
        return phoneStr;
    }

    public String getTypeStr() {
        return typeStr;
    }



    @Override
    protected void initView(Bundle savedInstanceState) {
        Contant.BGMURL = (String) SharedPreferencesHelper.getInstance().getData("BGMURL", "");//
        if (!XEmptyUtils.isEmpty(Contant.BGMURL)) {
            Glide.with(this).load(Contant.URL_IMAGE + Contant.BGMURL).into(regisImg);
        }
        fragments = new ArrayList<>();
        fragments.add(ISPhoneFragment.newInstance());
        fragments.add(LoginFragment.newInstance());
        fragments.add(GetCodeFragment.newInstance());//得到验证码
        fragments.add(ForgetPwdFragment.newInstance());//忘记密码
        VpAdapter adapter = new VpAdapter(getSupportFragmentManager(), fragments);
        vpStart.setAdapter(adapter);
        showFragement(0);
        rl_backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    //切换界面
    public void showFragement(int i) {
        index = i;
        if (i != 0) {
            rlBack.setVisibility(View.VISIBLE);
            rl_backs.setVisibility(View.GONE);
        } else {
            rl_backs.setVisibility(View.VISIBLE);
            rlBack.setVisibility(View.GONE);
        }
        vpStart.setCurrentItem(i, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_activty;
    }


    @OnClick(R.id.rl_back)
    public void onViewClicked() {
        if (index != 0) {
            showFragement(0);
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            if (index != 0) {
                showFragement(0);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }
}
