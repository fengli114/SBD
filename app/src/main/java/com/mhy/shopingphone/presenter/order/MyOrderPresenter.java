package com.mhy.shopingphone.presenter.order;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.cache.Cache;
import com.mhy.shopingphone.contract.order.MyOrderContract;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.mhy.shopingphone.model.order.MyOrderModel;
import com.mhy.shopingphone.ui.activity.detail.GoodsDtailActivity;


import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */
public class MyOrderPresenter extends MyOrderContract.MyOrderPresenter {
    private boolean isLoading;

    @NonNull
    public static MyOrderPresenter newInstance() {
        return new MyOrderPresenter();
    }

    @Override
    public MyOrderContract.IMyOrderModel getModel() {
        return MyOrderModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadOrderList(String map) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getOrderList(map).subscribe(new Consumer<MyOrderBean>() {
            @Override
            public void accept(MyOrderBean goodsBean) throws Exception {
                if (mIView == null)
                    return;
//                LogUtils.e("订单："+goodsBean.getCode());
//                LogUtils.e("订单："+goodsBean.getMess());
                mIView.updateContentList(goodsBean.getJson());
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
    public void loadMoreMyOrderList(String s) {
        if (!isLoading) {
            isLoading = true;
            mRxManager.register(mIModel.getOrderList(s).subscribe(new Consumer<MyOrderBean>() {
                @Override
                public void accept(MyOrderBean hotMovieBean) throws
                        Exception {
                    isLoading = false;
                    if (mIView == null)
                        return;

                    if (hotMovieBean != null && hotMovieBean.getJson() != null &&
                            hotMovieBean.getJson().size() > 0) {
                        mIView.updateContentList(hotMovieBean.getJson());
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
    public void onItemClick(int position, MyOrderBean.JsonBean item, ImageView imageView) {
//        GoodsDtailActivity.start2(mIView.getBindActivity(), item, imageView);
    }
}
