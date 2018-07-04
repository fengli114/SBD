package com.mhy.shopingphone.ui.fragment.phone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mhy.sdk.base.fragment.BaseCompatFragment;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.fragment.phone.child.PhoneFragment;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：  首页-电话界面
 */

public class PhoneRootFragment extends BaseCompatFragment {
    @Override
    public int getLayoutId() {
        return R.layout.frag_phone;
    }
    public static PhoneRootFragment newInstance() {
        Bundle args = new Bundle();
        PhoneRootFragment fragment = new PhoneRootFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(PhoneFragment.class) == null) {
            loadRootFragment(R.id.fl_container, PhoneFragment.newInstance());
        }
    }
}
