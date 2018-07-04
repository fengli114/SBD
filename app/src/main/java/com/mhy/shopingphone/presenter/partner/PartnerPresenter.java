package com.mhy.shopingphone.presenter.partner;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.cache.Cache;
import com.mhy.shopingphone.contract.partner.PartnerContract;
import com.mhy.shopingphone.contract.shop.ShopContract;
import com.mhy.shopingphone.model.bean.partner.PartnerShipBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;
import com.mhy.shopingphone.model.partner.PartnerModel;
import com.mhy.shopingphone.model.shop.ShopModel;
import com.mhy.shopingphone.ui.activity.detail.GoodsDtailActivity;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */
public class PartnerPresenter extends PartnerContract.PartnerPresenter {
    private boolean isLoading;

    @NonNull
    public static PartnerPresenter newInstance() {
        return new PartnerPresenter();
    }

    @Override
    public PartnerContract.IPartnerModel getModel() {
        return PartnerModel.newInstance();
    }

    @Override
    public void onStart() {

    }


    @Override
    public void loadContentList(String string) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getContentData(string).subscribe(new Consumer<PartnerShipBean>() {
            @Override
            public void accept(PartnerShipBean partnerShipBean) throws Exception {
                if (mIView == null)
                    return;
                mIView.updateContentList(partnerShipBean);
//                Cache.saveHotMovieCache(partnerShipBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    mIView.showToast("网络异常，请检查网络~");

                if (Cache.getHotMovieCache().size() == 0) {//没有缓存缓存，显示网络错误界面
                    mIView.showNetworkError();
                } else {
//                        mIView.updateContentList(Cache.getHotMovieCache());//加载缓存
                }

            }
        }));
    }
}
