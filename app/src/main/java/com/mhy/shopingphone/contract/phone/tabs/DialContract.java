package com.mhy.shopingphone.contract.phone.tabs;

import com.mhy.shopingphone.model.bean.phone.PhoneItemBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：
 */

public interface DialContract {
    abstract class  DialPresenter extends BaseTabsContract.BaseTabsPresenter<IDialModel,
            IDialView, PhoneItemBean> {
    }

    interface IDialModel extends BaseTabsContract.IBaseTabsModel {
        /**
         * 根据日期获取日报list --> 20170910
         *
         * @param date 日期
         * @return Observable
         */
//        Observable<DialDailyListBean> getDailyList(String date);

        /**
         * 获取日报list
         *
         * @return Observable
         */
//        Observable<DialDailyListBean> getDailyList();
    }

    interface IDialView extends BaseTabsContract.IBaseTabsView<PhoneItemBean> {
    }
}
