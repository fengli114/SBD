package com.mhy.shopingphone.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by LeBron on 2017/6/2.
 */

public class VpAdapter extends FragmentPagerAdapter {
    private List<Fragment> views;


    public VpAdapter(FragmentManager fm, List<Fragment> views) {
        super(fm);
        this.views = views;

    }


    @Override
    public Fragment getItem(int position) {
        return views.get(position);
    }

    @Override
    public int getCount() {
        return views.size();
    }

}
