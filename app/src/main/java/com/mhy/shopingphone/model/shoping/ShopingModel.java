package com.mhy.shopingphone.model.shoping;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.shop.ShopContract;
import com.mhy.shopingphone.contract.shoping.ShopingContract;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;
import com.youth.xframe.utils.log.XLog;

import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class ShopingModel extends BaseModel implements ShopingContract.IShopModel {

    @NonNull
    public static ShopingModel newInstance() {
        return new ShopingModel();
    }


    @Override
    public Observable<GoodsBean> getGoodsList(Map<String, String> map) {

        return RetrofitCreateHelper.createApi(Api.class, Api.NEWGOODS).getGoodsList(map)
                .compose(RxHelper.<GoodsBean>rxSchedulerHelper());
//        return null;
    }

}
