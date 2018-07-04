package com.mhy.shopingphone.ui.fragment.my;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.mhy.sdk.base.fragment.BaseCompatFragment;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.ui.fragment.addresslist.AddressListFragment;
import com.mhy.shopingphone.ui.fragment.my.child.MyFragment;
import com.mhy.shopingphone.ui.fragment.shop.child.ShopFragment;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：
 */

public class MyRootFragment extends BaseCompatFragment {

    public static MyRootFragment newInstance() {
        Bundle args = new Bundle();
        MyRootFragment fragment = new MyRootFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        //Logger.e("onLazyInitView");
        //加载子fragment
        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, MyFragment.newInstance());
        } else {  // 这里可能会出现该Fragment没被初始化时,就被强杀导致的没有load子Fragment
            if (findChildFragment(MyFragment.class) == null) {
                loadRootFragment(R.id.fl_container, MyFragment.newInstance());
            }
        }
    }
}
