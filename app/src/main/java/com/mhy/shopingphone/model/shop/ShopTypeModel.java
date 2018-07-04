package com.mhy.shopingphone.model.shop;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.shop.ShopTypeContract;
import com.mhy.shopingphone.model.bean.shop.ShopTypeBean;

import io.reactivex.Observable;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class ShopTypeModel extends BaseModel implements ShopTypeContract.IShopTypeModel {

    @NonNull
    public static ShopTypeModel newInstance() {
        return new ShopTypeModel();
    }

    @Override
    public Observable<ShopTypeBean> getShopTypeList(String map) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getShopType(map)
                .compose(RxHelper.<ShopTypeBean>rxSchedulerHelper());
    }
}
