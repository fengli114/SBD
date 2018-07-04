package com.mhy.shopingphone.presenter.detail;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.cache.Cache;
import com.mhy.shopingphone.contract.detail.DynamicContract;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.model.detail.DynamicModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */
public class DynamicPresenter extends DynamicContract.DynamicPresenter {
    private boolean isLoading;

    @NonNull
    public static DynamicPresenter newInstance() {
        return new DynamicPresenter();
    }


    @Override
    public DynamicContract.IDynamicModel getModel() {
        return DynamicModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadDynamicList(String map) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getDynamicList(map).subscribe(new Consumer<DynamicBean>() {
            @Override
            public void accept(DynamicBean goodsBean) throws Exception {
                if (mIView == null)
                    return;
                LogUtils.e("订单："+goodsBean.getCode());
                mIView.updateContentList(goodsBean.getInfo());
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


    @Override
    public void loadMoreDynamicList(String s) {
        if (!isLoading) {
            isLoading = true;
            mRxManager.register(mIModel.getDynamicList(s).subscribe(new Consumer<DynamicBean>() {
                @Override
                public void accept(DynamicBean hotMovieBean) throws
                        Exception {
                    isLoading = false;
                    if (mIView == null)
                        return;

                    if (hotMovieBean != null && hotMovieBean.getInfo() != null &&
                            hotMovieBean.getInfo().size() > 0) {
                        mIView.updateContentList(hotMovieBean.getInfo());
                    } else {
                        mIView.showNoMoreData();
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    isLoading = false;
                    if (mIView != null) {
                        mIView.showLoadMoreError();
                    }
                }
            }));
        }
    }


    @Override
    public void onItemClick(int position, DynamicBean.InfoBean item, ImageView imageView) {
//        GoodsDtailActivity.start2(mIView.getBindActivity(), item, imageView);
    }
}
