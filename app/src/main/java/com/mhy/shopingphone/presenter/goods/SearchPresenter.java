package com.mhy.shopingphone.presenter.goods;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.recharge.RechargeContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.RechargeBean;
import com.mhy.shopingphone.model.recharge.RechargeModel;
import com.youth.xframe.utils.log.XLog;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class SearchPresenter extends RechargeContract.RechargePresenter{
    @NonNull
    public static SearchPresenter newInstance() {
        return new SearchPresenter();
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
                XLog.e("充值："+bean.getCode());
                XLog.e("充值："+bean.getMess());
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
