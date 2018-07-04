package com.mhy.shopingphone.model.order;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.order.MyOrderContract;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;


import io.reactivex.Observable;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class MyOrderModel extends BaseModel implements MyOrderContract.IMyOrderModel {

    @NonNull
    public static MyOrderModel newInstance() {
        return new MyOrderModel();
    }

    @Override
    public Observable<MyOrderBean> getOrderList(String map) {
        return RetrofitCreateHelper.createApi(Api.class, Api.NEWGOODS).getMyOrder(map)
                .compose(RxHelper.<MyOrderBean>rxSchedulerHelper());
    }
}
