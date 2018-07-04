package com.mhy.shopingphone.contract.phone.tabs;

import com.mhy.shopingphone.model.bean.phone.PhoneItemBean;
import com.mhy.shopingphone.model.bean.recentcalls.RecentCallsItemBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：
 */

public interface RecentCallsContract {
    abstract class  RecentCallsPresenter extends BaseTabsContract.BaseTabsPresenter<IRecentCallsModel,
            IRecentCallsView, PhoneItemBean> {
    }

    interface IRecentCallsModel extends BaseTabsContract.IBaseTabsModel {
        /**
         * 根据日期获取日报list --> 20170910
         *
         * @param date 日期
         * @return Observable
         */
//        Observable<RecentCallsItemBean> getDailyList(String date);

        /**
         * 获取日报list
         *
         * @return Observable
         */
        Observable<RecentCallsItemBean> getDailyList();
    }

    interface IRecentCallsView extends BaseTabsContract.IBaseTabsView<RecentCallsItemBean> {
    }
}
