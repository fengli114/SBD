package com.mhy.shopingphone.presenter.shop;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.cache.Cache;
import com.mhy.shopingphone.contract.shop.ShopTypeContract;
import com.mhy.shopingphone.model.bean.shop.ShopTypeBean;
import com.mhy.shopingphone.model.shop.ShopTypeModel;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */
public class ShopTypePresenter extends ShopTypeContract.ShopTypePresenter {
    private boolean isLoading;

    @NonNull
    public static ShopTypePresenter newInstance() {
        return new ShopTypePresenter();
    }

    @Override
    public ShopTypeContract.IShopTypeModel getModel() {
        return ShopTypeModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadShopTypeList(String map) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getShopTypeList(map).subscribe(new Consumer<ShopTypeBean>() {
            @Override
            public void accept(ShopTypeBean goodsBean) throws Exception {
                if (mIView == null)
                    return;
                if (goodsBean.getCode().equals("0")) {
                    mIView.updateContentList(goodsBean.getInfo());
                }else {
                    ToastUtils.showToast(goodsBean.getMess());
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




    @Override
    public void onItemClick(int position, ShopTypeBean.InfoBean item, ImageView imageView) {
//        GoodsDtailActivity.start2(mIView.getBindActivity(), item, imageView);
    }
}
