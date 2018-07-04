package com.mhy.shopingphone.presenter.login;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.goods.GoodsContract;
import com.mhy.shopingphone.contract.login.PhoneLoginContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.goods.GoodsTabBean;
import com.mhy.shopingphone.model.goods.GoodsModel;
import com.mhy.shopingphone.model.login.PhoneLoginModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class PhoneLoginPresenter extends PhoneLoginContract.PhoneLoginPresenter{
    @NonNull
    public static PhoneLoginPresenter newInstance() {
        return new PhoneLoginPresenter();
    }
    @Override
    public PhoneLoginContract.IPhoneLoginModel getModel() {
        return PhoneLoginModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void nowBuy(String parms) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.goNext(parms).subscribe(new Consumer<Ceshi>() {
            @Override
            public void accept(Ceshi bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.next(bean);
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
