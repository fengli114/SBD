package com.mhy.shopingphone.presenter.login;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.login.LoginContract;
import com.mhy.shopingphone.contract.login.RegisterContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.model.login.LoginModel;
import com.mhy.shopingphone.model.login.RegisterModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class RegisterPresenter extends RegisterContract.RegisterPresenter{
    @NonNull
    public static RegisterPresenter newInstance() {
        return new RegisterPresenter();
    }
    @Override
    public RegisterContract.IRegisterModel getModel() {
        return RegisterModel.newInstance();
    }

    @Override
    public void onStart() {

    }

  /*  @Override
    public void goLogin(String parms) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.goLogin(parms).subscribe(new Consumer<LoginBean>() {
            @Override
            public void accept(LoginBean bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.goLogin(bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;
                mIView.showToast("网络异常，请检查网络~");
                mIView.showNetworkError();
            }
        }));
    }

    @Override
    public void goRegister(String parms) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.goRegister(parms).subscribe(new Consumer<Ceshi>() {
            @Override
            public void accept(Ceshi bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.goRegister(bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;
                mIView.showToast("网络异常，请检查网络~");
                mIView.showNetworkError();
            }
        }));
    }

    @Override
    public void forgetPwd(String parms) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.forgetPwd(parms).subscribe(new Consumer<Ceshi>() {
            @Override
            public void accept(Ceshi bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.forgetPwd(bean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;
                mIView.showToast("网络异常，请检查网络~");
                mIView.showNetworkError();
            }
        }));
    }*/
}
