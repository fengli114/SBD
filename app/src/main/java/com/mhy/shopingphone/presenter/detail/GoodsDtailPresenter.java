package com.mhy.shopingphone.presenter.detail;

import android.support.annotation.NonNull;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.contract.details.GoodsDtailContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.shop.ShopDetailBean;
import com.mhy.shopingphone.model.detail.GoodsDtailModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2018/1/3 0003.
 * 描述：
 */

public class GoodsDtailPresenter extends GoodsDtailContract.GoodsDtailPresenter{
    @NonNull
    public static GoodsDtailPresenter newInstance() {
        return new GoodsDtailPresenter();
    }

    @Override
    public GoodsDtailContract.IGoodsDtailModel getModel() {
        return GoodsDtailModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void nowBuy(String url) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.goNowBuy(url).subscribe(new Consumer<Ceshi>() {
            @Override
            public void accept(Ceshi newsBean) throws Exception {
                if (mIView == null)
                    return;
                LogUtils.e("兑换："+newsBean.getCode());
//                LogUtils.e("兑换："+newsBean.getMess());
                mIView.update(newsBean);
//                Cache.saveHotMovieCache(newsBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null) {
                    mIView.showToast("网络异常，请检查网络~");
//                    if (Cache.getHotMovieCache().size() == 0) {//没有缓存缓存，显示网络错误界面
//                        mIView.showNetworkError();
//                    } else {
//                        mIView.updateContentList(Cache.getHotMovieCache());//加载缓存
//                    }
                }
            }
        }));
    }

    @Override
    public void getDetails(String parms) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getDetails(parms).subscribe(new Consumer<ShopDetailBean>() {
            @Override
            public void accept(ShopDetailBean newsBean) throws Exception {
                if (mIView == null)
                    return;
                LogUtils.e("详情："+newsBean.getCode());
                LogUtils.e("详情："+newsBean.getMess());
                mIView.showDetails(newsBean);
//                Cache.saveHotMovieCache(newsBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null) {
                    mIView.showToast("网络异常，请检查网络~");
//                    if (Cache.getHotMovieCache().size() == 0) {//没有缓存缓存，显示网络错误界面
//                        mIView.showNetworkError();
//                    } else {
//                        mIView.updateContentList(Cache.getHotMovieCache());//加载缓存
//                    }
                }
            }
        }));
    }
}
