package com.mhy.shopingphone.model.detail;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.details.GoodsDtailContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.shop.ShopDetailBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/3 0003.
 * 描述：
 */

public class GoodsDtailModel extends BaseModel implements GoodsDtailContract
        .IGoodsDtailModel  {

    @NonNull
    public static GoodsDtailModel newInstance() {
        return new GoodsDtailModel();
    }

    @Override
    public Observable<Ceshi> goNowBuy(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).goNowBuy(parms)
                .compose(RxHelper.<Ceshi>rxSchedulerHelper());
    }

    @Override
    public Observable<ShopDetailBean> getDetails(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getShopDetails(parms)
                .compose(RxHelper.<ShopDetailBean>rxSchedulerHelper());
    }
}
