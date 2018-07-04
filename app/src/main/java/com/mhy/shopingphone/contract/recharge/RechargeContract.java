package com.mhy.shopingphone.contract.recharge;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.IBaseView;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.RechargeBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public interface RechargeContract {

        abstract class RechargePresenter extends BasePresenter<RechargeContract.IRechargeModel, RechargeContract.IRechargeView> {

        /**
         * 加载商品详情
         *
         * @param parms url
         */
        public abstract void goRecharge(String parms);
    }

    interface IRechargeModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<RechargeBean> goRecharge(String parms);
    }

    interface IRechargeView extends IBaseView {
        /**
         *
         *
         * @param ceshi list
         */
        void goRecharge(RechargeBean ceshi);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
