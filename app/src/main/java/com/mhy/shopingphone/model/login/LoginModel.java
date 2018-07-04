package com.mhy.shopingphone.model.login;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.login.LoginContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.login.LoginBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class LoginModel extends BaseModel implements LoginContract
        .ILoginModel  {

    @NonNull
    public static LoginModel newInstance() {
        return new LoginModel();
    }


    @Override
    public Observable<LoginBean> goLogin(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).goLogin(parms)
                .compose(RxHelper.<LoginBean>rxSchedulerHelper());
    }
}
