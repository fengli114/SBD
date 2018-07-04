package com.mhy.shopingphone.model.phone;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.phone.DialBackContract;
import com.mhy.shopingphone.contract.phone.PhoneMainContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/13 0013.
 * 描述： 电话model
 */

public class DialBackModel extends BaseModel implements DialBackContract.IDialBackModel{

    @NonNull
    public static DialBackModel newInstance() {
        return new DialBackModel();
    }

    @Override
    public Observable<Ceshi> goDialBack(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).goDialBack(parms)
                .compose(RxHelper.<Ceshi>rxSchedulerHelper());
    }
}
