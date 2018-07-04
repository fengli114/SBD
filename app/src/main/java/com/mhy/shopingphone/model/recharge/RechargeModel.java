package com.mhy.shopingphone.model.recharge;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.partner.PartnerLoginContract;
import com.mhy.shopingphone.contract.recharge.RechargeContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.RechargeBean;
import com.mhy.shopingphone.model.bean.partner.PartnerLoginBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class RechargeModel extends BaseModel implements RechargeContract.IRechargeModel {

    @NonNull
    public static RechargeModel newInstance() {
        return new RechargeModel();
    }

    @Override
    public Observable<RechargeBean> goRecharge(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).goRecharge(parms)
                .compose(RxHelper.<RechargeBean>rxSchedulerHelper());
    }
}
