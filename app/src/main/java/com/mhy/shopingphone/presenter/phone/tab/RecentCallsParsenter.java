package com.mhy.shopingphone.presenter.phone.tab;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.phone.tabs.RecentCallsContract;
import com.mhy.shopingphone.model.bean.phone.PhoneItemBean;
import com.mhy.shopingphone.model.phone.tabs.RecentCallsModel;

/**
 * 作者： "RedRainM" on 2017/12/13 0013.
 * 描述：
 */

public class RecentCallsParsenter extends RecentCallsContract.RecentCallsPresenter{

    @NonNull
    public static RecentCallsParsenter newInstance() {
        return new RecentCallsParsenter();
    }

    @Override
    public RecentCallsContract.IRecentCallsModel getModel() {
        return RecentCallsModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadLatestList() {

    }

    @Override
    public void loadMoreList() {

    }

    @Override
    public void onItemClick(int position, PhoneItemBean item) {

    }
}
