package com.mhy.shopingphone.presenter.goods;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.goods.GoodsContract;
import com.mhy.shopingphone.model.bean.goods.GoodsTabBean;
import com.mhy.shopingphone.model.goods.GoodsModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class GoodsPresenter extends GoodsContract.GoodsPresenter{
    @NonNull
    public static GoodsPresenter newInstance() {
        return new GoodsPresenter();
    }
    @Override
    public GoodsContract.IGoodsModel getModel() {
        return GoodsModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadTabs(String parms) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.getTabs(parms).subscribe(new Consumer<GoodsTabBean>() {
            @Override
            public void accept(GoodsTabBean bean) throws Exception {
                if (mIView == null)
                    return;

                mIView.showTabs(bean);
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
