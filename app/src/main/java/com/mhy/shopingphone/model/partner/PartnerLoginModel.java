package com.mhy.shopingphone.model.partner;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.login.LoginContract;
import com.mhy.shopingphone.contract.partner.PartnerContract;
import com.mhy.shopingphone.contract.partner.PartnerLoginContract;
import com.mhy.shopingphone.model.bean.login.LoginBean;
import com.mhy.shopingphone.model.bean.partner.PartnerLoginBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class PartnerLoginModel extends BaseModel implements PartnerLoginContract.ILoginModel {

    @NonNull
    public static PartnerLoginModel newInstance() {
        return new PartnerLoginModel();
    }


    @Override
    public Observable<PartnerLoginBean> goLogin(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).goPartnerLogin(parms)
                .compose(RxHelper.<PartnerLoginBean>rxSchedulerHelper());
    }
}
