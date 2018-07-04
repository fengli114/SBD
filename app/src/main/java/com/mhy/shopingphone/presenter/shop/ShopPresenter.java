package com.mhy.shopingphone.presenter.shop;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.cache.Cache;
import com.mhy.shopingphone.contract.shop.ShopContract;
import com.mhy.shopingphone.model.bean.UserBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;
import com.mhy.shopingphone.model.shop.ShopModel;
import com.mhy.shopingphone.ui.activity.detail.GoodsDtailActivity;
import com.youth.xframe.utils.log.XLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */
public class ShopPresenter extends ShopContract.ShopPresenter {
    private boolean isLoading;
    private int Page = 1;

    @NonNull
    public static ShopPresenter newInstance() {
        return new ShopPresenter();
    }

    @Override
    public ShopContract.IShopModel getModel() {
        return ShopModel.newInstance();
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
//                        mIView.showNoMoreData();
                    }
                }
            }));
        }
    }


    private Map<String, String> getParamstr() {
        if (mIView.getParams() != null) {
            mIView.getParams().put("Page", Page + "");
        }
        Map<String, String> params = mIView.getParams();
//        String paramsstring = StringUtil.mapToJson(mIView.getParams());
//        LogUtils.e("fengli===="+paramsstring);
////        Map<String,String> paramsstring =mIView.getParams();
//        OkHttpUtils.post()
//                .url(Api.NEWGOODS + "shop/getShopIndex")
//                .addParams("A", paramsstring)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//                        LogUtils.e("hahahah" + e.getMessage());
//                    }
//                    @Override
//                    public void onResponse(String response, int id) {
//                        XLog.e("fengliaaaaaa", response);
//                        LogUtils.e("fengli22222" + response);
//                    }
//                });

        return params;
    }

    @Override
    public void onItemClick(int position, GoodsBean.JsonBean.CommoditiesBean item, ImageView imageView) {
        GoodsDtailActivity.start(mIView.getBindActivity(), item, imageView);
    }
}
