package com.mhy.shopingphone.model.login;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.details.GoodsDtailContract;
import com.mhy.shopingphone.contract.login.PhoneLoginContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.detail.GoodsDtailModel;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class PhoneLoginModel extends BaseModel implements PhoneLoginContract
        .IPhoneLoginModel  {

    @NonNull
    public static PhoneLoginModel newInstance() {
        return new PhoneLoginModel();
    }


    @Override
    public Observable<Ceshi> goNext(String parms) {

        return RetrofitCreateHelper.createApi(Api.class, Api.NEWGOODS).goNext(parms)
                .compose(RxHelper.<Ceshi>rxSchedulerHelper());
    }
}
