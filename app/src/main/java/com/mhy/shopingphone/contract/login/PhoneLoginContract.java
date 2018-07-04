package com.mhy.shopingphone.contract.login;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.IBaseView;
import com.mhy.shopingphone.model.bean.Ceshi;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public interface PhoneLoginContract {
    
        abstract class PhoneLoginPresenter extends BasePresenter<PhoneLoginContract.IPhoneLoginModel, PhoneLoginContract.IPhoneLoginView> {

        /**
         * 加载商品详情
         *
         * @param parms url
         */
        public abstract void nowBuy(String parms);
    }

    interface IPhoneLoginModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<Ceshi> goNext(String parms);
    }

    interface IPhoneLoginView extends IBaseView {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void next(Ceshi list);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
