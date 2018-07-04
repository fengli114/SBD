package com.mhy.shopingphone.presenter.zf;

import android.support.annotation.NonNull;

import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.cache.Cache;
import com.mhy.shopingphone.contract.zf.PayTypeContract;
import com.mhy.shopingphone.model.bean.PayTypeBean;
import com.mhy.shopingphone.model.zf.PayTypeModel;


import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */
public class PayTypePresenter extends PayTypeContract.PayTypePresenter {
    private boolean isLoading;

    @NonNull
    public static PayTypePresenter newInstance() {
        return new PayTypePresenter();
    }

    @Override
    public PayTypeContract.IPayTypeModel getModel() {
        return PayTypeModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadPayTypeList(String map) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getPayTypeList(map).subscribe(new Consumer<PayTypeBean>() {
            @Override
            public void accept(PayTypeBean goodsBean) throws Exception {
                if (mIView == null)
                    return;
                if (goodsBean.getErrorCode()==200) {
                    mIView.updateContentList(goodsBean.getJson());
                }else {
//                    ToastUtils.showToast(goodsBean.getMess());
                }
//                Cache.saveHotMovieCache(goodsBean.getInfo());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                    if (mIView == null)
                        return;
                   mIView.showToast("网络异常，请检查网络~");
                   mIView.showNetworkError();

                    if (Cache.getHotMovieCache().size() == 0) {//没有缓存缓存，显示网络错误界面
                        mIView.showNetworkError();
                    } else {
//                        mIView.updateContentList(Cache.getHotMovieCache());//加载缓存
                    }
                }

        }));
    }

}
