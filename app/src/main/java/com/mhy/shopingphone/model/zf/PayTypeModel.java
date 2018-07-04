package com.mhy.shopingphone.model.zf;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.shop.ShopTypeContract;
import com.mhy.shopingphone.contract.zf.PayTypeContract;
import com.mhy.shopingphone.model.bean.PayTypeBean;
import com.mhy.shopingphone.model.bean.shop.ShopTypeBean;

import io.reactivex.Observable;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class PayTypeModel extends BaseModel implements PayTypeContract.IPayTypeModel {

    @NonNull
    public static PayTypeModel newInstance() {
        return new PayTypeModel();
    }


    @Override
    public Observable<PayTypeBean> getPayTypeList(String map) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getPayType(map)
                .compose(RxHelper.<PayTypeBean>rxSchedulerHelper());
    }
}
