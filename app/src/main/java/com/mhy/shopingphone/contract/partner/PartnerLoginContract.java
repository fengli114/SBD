package com.mhy.shopingphone.contract.partner;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.IBaseView;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.model.bean.partner.PartnerLoginBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public interface PartnerLoginContract {

        abstract class LoginPresenter extends BasePresenter<PartnerLoginContract.ILoginModel, PartnerLoginContract.ILoginView> {

        /**
         * 加载商品详情
         *
         * @param parms url
         */
        public abstract void goLogin(String parms);
    }

    interface ILoginModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<PartnerLoginBean> goLogin(String parms);
    }

    interface ILoginView extends IBaseView {
        /**
         *
         *
         * @param partnerLoginBean list
         */
        void goLogin(PartnerLoginBean partnerLoginBean);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
