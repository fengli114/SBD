package com.mhy.shopingphone.presenter.partner;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.login.LoginContract;
import com.mhy.shopingphone.contract.partner.PartnerLoginContract;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.model.bean.partner.PartnerLoginBean;
import com.mhy.shopingphone.model.login.LoginModel;
import com.mhy.shopingphone.model.partner.PartnerLoginModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class PartnerLoginPresenter extends PartnerLoginContract.LoginPresenter{
    @NonNull
    public static PartnerLoginPresenter newInstance() {
        return new PartnerLoginPresenter();
    }
    @Override
    public PartnerLoginContract.ILoginModel getModel() {
        return PartnerLoginModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void goLogin(String parms) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.goLogin(parms).subscribe(new Consumer<PartnerLoginBean>() {
            @Override
            public void accept(PartnerLoginBean bean) throws Exception {
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
}
