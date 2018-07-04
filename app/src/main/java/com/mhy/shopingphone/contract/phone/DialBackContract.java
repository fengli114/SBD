package com.mhy.shopingphone.contract.phone;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.IBaseView;
import com.mhy.shopingphone.model.bean.Ceshi;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public interface DialBackContract {
    
        abstract class DialBackPresenter extends BasePresenter<DialBackContract.IDialBackModel, DialBackContract.IDialBackView> {

        /**
         * 加载商品详情
         *
         * @param parms url
         */
        public abstract void goDialBack(String parms);
    }

    interface IDialBackModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<Ceshi> goDialBack(String parms);
    }

    interface IDialBackView extends IBaseView {
        /**
         * 更新界面list
         *
         * @param ceshi list
         */
        void goDialBack(Ceshi ceshi);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
