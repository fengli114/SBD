package com.mhy.shopingphone.model.phone.tabs;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.shopingphone.contract.phone.tabs.RecentCallsContract;
import com.mhy.shopingphone.model.bean.recentcalls.RecentCallsItemBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/13 0013.
 * 描述：
 */

public class RecentCallsModel extends BaseModel implements RecentCallsContract.IRecentCallsModel {
    @NonNull
    public static RecentCallsModel newInstance() {
        return new RecentCallsModel();
    }
    @Override
    public Observable<RecentCallsItemBean> getDailyList() {
        return null;
    }

    @Override
    public Observable<Boolean> recordItemIsRead(String key) {
        return null;
    }
}
