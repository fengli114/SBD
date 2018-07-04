package com.mhy.shopingphone.model.shop;

import android.support.annotation.NonNull;


import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.shop.ShopContract;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class ShopModel extends BaseModel implements ShopContract.IShopModel {

    @NonNull
    public static ShopModel newInstance() {
        return new ShopModel();
    }


    @Override
    public Observable<GoodsBean> getGoodsList(Map<String,String> map) {
       // return null;
        return RetrofitCreateHelper.createApi(Api.class, Api.NEWGOODS).getGoodsList(map)
                .compose(RxHelper.<GoodsBean>rxSchedulerHelper());
    }
    @Override
    public Observable<ShopBannerBean> getBannerList(String s) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getShopBannerData(s)
                .compose(RxHelper.<ShopBannerBean>rxSchedulerHelper());
    }
}
