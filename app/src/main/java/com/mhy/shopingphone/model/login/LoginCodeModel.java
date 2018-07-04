package com.mhy.shopingphone.model.login;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.login.LoginCodeContract;
import com.mhy.shopingphone.contract.login.LoginContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.login.LoginBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class LoginCodeModel extends BaseModel implements LoginCodeContract
        .ILoginCodeModel  {

    @NonNull
    public static LoginCodeModel newInstance() {
        return new LoginCodeModel();
    }


    @Override
    public Observable<Ceshi> getCode(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getCode(parms)
                .compose(RxHelper.<Ceshi>rxSchedulerHelper());
    }
}
