package com.mhy.shopingphone.presenter.phone;

import android.support.annotation.NonNull;


import com.mhy.shopingphone.contract.phone.DialBackContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.phone.DialBackModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class DialBackPresenter extends DialBackContract.DialBackPresenter{
    @NonNull
    public static DialBackPresenter newInstance() {
        return new DialBackPresenter();
    }
    @Override
    public DialBackContract.IDialBackModel getModel() {
        return DialBackModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void goDialBack(String parms) {
        if (mIView == null || mIModel == null)
            return;

            mRxManager.register(mIModel.goDialBack(parms).subscribe(new Consumer<Ceshi>() {
            @Override
            public void accept(Ceshi bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.goDialBack(bean);
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
