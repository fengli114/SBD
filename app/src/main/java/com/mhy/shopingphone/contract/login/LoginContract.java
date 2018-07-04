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

public interface LoginContract {
    
        abstract class LoginPresenter extends BasePresenter<LoginContract.ILoginModel, LoginContract.ILoginView> {

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
        Observable<LoginBean> goLogin(String parms);
    }

    interface ILoginView extends IBaseView {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void goLogin(LoginBean list);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
