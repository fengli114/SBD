package com.mhy.shopingphone.model.discover;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.discover.DiscoverContract;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.discover.ShoperBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/25 0025.
 * 描述：
 */

public class DiscoverModel  extends BaseModel implements DiscoverContract.IDiscoverModel  {
    @NonNull
    public static DiscoverModel newInstance() {
        return new DiscoverModel();
    }

//    @Override
//    public Observable<NewsBean> getNewsList() {
//        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getNewsList()
//                .compose(RxHelper.<NewsBean>rxSchedulerHelper());
//    }

    @Override
    public Observable<ShoperBean> getShoperList(String parms) {
//        return RetrofitCreateHelper.createApi(Api.class, Api.NEWGOODS).getShoperList(parms)
//                .compose(RxHelper.<ShoperBean>rxSchedulerHelper());
        return null;
    }
    @Override
    public Observable<DynamicBean> getDynamicList(String map) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getDynamicList(map)
                .compose(RxHelper.<DynamicBean>rxSchedulerHelper());
    }
}
