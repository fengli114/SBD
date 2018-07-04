package com.mhy.shopingphone.model.partner;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.partner.PartnerContract;
import com.mhy.shopingphone.contract.shop.ShopContract;
import com.mhy.shopingphone.model.bean.partner.PartnerShipBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;
import com.mhy.shopingphone.model.shop.ShopModel;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/11 0011.
 * 描述：
 */

public class PartnerModel extends BaseModel implements PartnerContract.IPartnerModel {

    @NonNull
    public static PartnerModel newInstance() {
        return new PartnerModel();
    }


    @Override
    public Observable<PartnerShipBean> getContentData(String s) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getPartner(s)
                .compose(RxHelper.<PartnerShipBean>rxSchedulerHelper());
    }
}
