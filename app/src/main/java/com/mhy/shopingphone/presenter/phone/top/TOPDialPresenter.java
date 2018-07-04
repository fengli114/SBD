package com.mhy.shopingphone.presenter.phone.top;

import android.support.annotation.NonNull;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.contract.phone.top.TOPDialContract;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.phone.TOPDialModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/21 0021.
 * 描述：
 */

public class TOPDialPresenter extends TOPDialContract.TOPDialPresenter {
    @NonNull
    public static TOPDialPresenter newInstance() {
        return new TOPDialPresenter();
    }


    @Override
    public TOPDialContract.ITOPDialModel getModel() {
        return TOPDialModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadBannerData(String string) {
        if (mIView == null || mIModel == null)
            return;

        mRxManager.register(mIModel.getBanners(string).subscribe(new Consumer<BannerBean>() {
            @Override
            public void accept(BannerBean bean) throws Exception {
                if (mIView == null)
                    return;
                mIView.showBannerDetail(bean);
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
    public void btnBannerClicked(BannerBean bean) {

    }
}
