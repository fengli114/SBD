package com.mhy.shopingphone.ui.fragment.addresslist.child;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mhy.sdk.base.fragment.BaseCompatFragment;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.fragment.addresslist.AddressListFragment;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：  通讯录界面
 */

public class AddressListRootFragment extends BaseCompatFragment {

    public static AddressListRootFragment newInstance() {
        Bundle args = new Bundle();
        AddressListRootFragment fragment = new AddressListRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_phone;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(AddressListFragment.class) == null) {
            loadRootFragment(R.id.fl_container, AddressListFragment.newInstance());
        }
    }
}
