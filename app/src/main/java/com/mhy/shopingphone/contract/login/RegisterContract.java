package com.mhy.shopingphone.contract.login;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.IBaseView;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.login.LoginBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public interface RegisterContract {

        abstract class RegisterPresenter extends BasePresenter<RegisterContract.IRegisterModel, RegisterContract.IRegisterView> {

        /**
         * 加载商品详情
         *
         * @param parms url
         */
//        public abstract void goRegister(String parms);
//
//        public abstract void forgetPwd(String parms);
//
//        public abstract void goLogin(String parms);
    }

    interface IRegisterModel extends IBaseModel {

        Observable<Ceshi> goRegister(String parms);

        Observable<Ceshi> forgetPwd(String parms);

        Observable<LoginBean> goLogin(String parms);
    }

    interface IRegisterView extends IBaseView {
        /**
         * 更新界面list
         *
         * @param bean list
         */
        void goRegister(Ceshi bean);

        void forgetPwd(Ceshi bean);

        void goLogin(LoginBean bean);
        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
