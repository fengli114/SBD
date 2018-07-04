package com.mhy.shopingphone.ui.fragment.discover;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mhy.sdk.base.fragment.BaseCompatFragment;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.fragment.addresslist.AddressListFragment;
import com.mhy.shopingphone.ui.fragment.discover.child.DiscoverFragment;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：
 */

public class DiscoverRootFragment extends  BaseCompatFragment {

    public static DiscoverRootFragment newInstance() {
        Bundle args = new Bundle();
        DiscoverRootFragment fragment = new DiscoverRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_discover;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        if (findChildFragment(DiscoverFragment.class) == null) {
            loadRootFragment(R.id.fl_container, DiscoverFragment.newInstance());
        }
    }
}
