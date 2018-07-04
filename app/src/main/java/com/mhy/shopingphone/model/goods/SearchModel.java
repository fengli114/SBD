package com.mhy.shopingphone.model.goods;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.goods.SearchContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.RechargeBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class SearchModel extends BaseModel implements SearchContract.ISearchModel {

    @NonNull
    public static SearchModel newInstance() {
        return new SearchModel();
    }


    @Override
    public Observable<RechargeBean> goSearch(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).goRecharge(parms)
                .compose(RxHelper.<RechargeBean>rxSchedulerHelper());
    }
}
