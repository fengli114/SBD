package com.mhy.shopingphone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.mhy.shopingphone.ui.fragment.ShopingFragment;

import java.util.List;

/**
 * Created by LeBron on 2017/6/2.
 */

public class GoodsVpAdapter extends FragmentPagerAdapter {
    private List<ShopingFragment> views;
    private String[] titles;

    public GoodsVpAdapter(FragmentManager fm, List<ShopingFragment> views, String[]titles) {
        super(fm);
        this.views = views;
        this.titles = titles;
    }


    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }
    //重写这个方法，将设置每个Tab的标题
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

}
