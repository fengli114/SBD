package com.mhy.shopingphone.presenter.login;

import android.support.annotation.NonNull;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.contract.login.LoginCodeContract;
import com.mhy.shopingphone.contract.login.LoginContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.model.login.LoginCodeModel;
import com.mhy.shopingphone.model.login.LoginModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class LoginCodePresenter extends LoginCodeContract.LoginCodePresenter{
    @NonNull
    public static LoginCodePresenter newInstance() {
        return new LoginCodePresenter();
    }
    @Override
    public LoginCodeContract.ILoginCodeModel getModel() {
        return LoginCodeModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void getCode(String parms) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.getCode(parms).subscribe(new Consumer<Ceshi>() {
            @Override
            public void accept(Ceshi bean) throws Exception {
                if (mIView == null)
                    return;
                LogUtils.e("获取验证码："+bean.getCode());
//                LogUtils.e("获取验证码："+bean.getMess());

                mIView.getCode(bean);
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
}
