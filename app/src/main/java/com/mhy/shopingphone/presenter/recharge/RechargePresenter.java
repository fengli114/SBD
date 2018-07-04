package com.mhy.shopingphone.presenter.recharge;

import android.support.annotation.NonNull;


import com.mhy.shopingphone.contract.recharge.RechargeContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.RechargeBean;
import com.mhy.shopingphone.model.recharge.RechargeModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class RechargePresenter extends RechargeContract.RechargePresenter{
    @NonNull
    public static RechargePresenter newInstance() {
        return new RechargePresenter();
    }
    @Override
    public RechargeContract.IRechargeModel getModel() {
        return RechargeModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void goRecharge(String parms) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.goRecharge(parms).subscribe(new Consumer<RechargeBean>() {
            @Override
            public void accept(RechargeBean bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.goRecharge(bean);
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
