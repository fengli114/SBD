package com.mhy.shopingphone.presenter.shoping;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.cache.Cache;
import com.mhy.shopingphone.contract.shop.ShopContract;
import com.mhy.shopingphone.contract.shoping.ShopingContract;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;
import com.mhy.shopingphone.model.shop.ShopModel;
import com.mhy.shopingphone.model.shoping.ShopingModel;
import com.mhy.shopingphone.ui.activity.detail.GoodsDtailActivity;

import java.util.Map;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */
public class ShopingPresenter extends ShopingContract.ShopPresenter {
    private boolean isLoading;
    private int Page = 1;

    @NonNull
    public static ShopingPresenter newInstance() {
        return new ShopingPresenter();
    }

    @Override
    public ShopingContract.IShopModel getModel() {
        return ShopingModel.newInstance();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadGoodsList() {
        Page = 1;
        if (mIModel == null || mIView == null)
            return;
        mRxManager.register(mIModel.getGoodsList(getParamstr()).subscribe(new Consumer<GoodsBean>() {
            @Override
            public void accept(GoodsBean goodsBean) throws Exception {
                if (mIView == null)
                    return;
                Page += 1;
                mIView.updateContentList(goodsBean);
//                Cache.saveHotMovieCache(goodsBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;

                mIView.showToast("网络异常，请检查网络~");
                mIView.showNetworkError();

            }
//            }
        }));
    }

    @Override
    public void loadMoreShopList() {
        if (!isLoading) {
            isLoading = true;
            mRxManager.register(mIModel.getGoodsList(getParamstr()).subscribe(new Consumer<GoodsBean>() {
                @Override
                public void accept(GoodsBean hotMovieBean) throws
                        Exception {
                    isLoading = false;
                    if (mIView == null)
                        return;
                    if (hotMovieBean != null && hotMovieBean.getJson().getCommodities() != null &&
                            hotMovieBean.getJson().getCommodities().size() > 0) {
                        Page += 1;
                        mIView.updateContentList(hotMovieBean);
                    } else {
                        mIView.showNoMoreData();
                    }
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Exception {
                    isLoading = false;
                    if (mIView != null) {
//                        mIView.showLoadMoreError();
                    }
                }
            }));
        }
    }

    private Map<String, String> getParamstr() {
        if (mIView.getParams() != null) {
            mIView.getParams().put("Page", Page + "");
        }
       /* String paramsstring = StringUtil.mapToJson(mIView.getParams());
        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();

        //设置头部
        HttpUtils.headStr = Contant.GOODS_URLHead;*/
        LogUtils.e("商品：" + mIView.getParams().toString());
//        HttpUtils.LogHeadStr = "ShopFragment商品:";
        Contant.IsDebug = true;

        return mIView.getParams();

    }


    @Override
    public void onItemClick(int position, GoodsBean.JsonBean.CommoditiesBean item, ImageView imageView) {
//        GoodsDtailActivity.start(mIView., item, imageView);
    }
}
