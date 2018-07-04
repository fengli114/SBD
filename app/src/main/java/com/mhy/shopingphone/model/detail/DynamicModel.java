package com.mhy.shopingphone.model.detail;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.detail.DynamicContract;
import com.mhy.shopingphone.contract.order.MyOrderContract;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.youth.xframe.utils.log.XLog;

import io.reactivex.Observable;

/**
 * Created by Horrarndoo on 2017/10/16.
 * <p>
 */

public class DynamicModel extends BaseModel implements DynamicContract.IDynamicModel {

    @NonNull
    public static DynamicModel newInstance() {
        return new DynamicModel();
    }


    @Override
    public Observable<DynamicBean> getDynamicList(String map) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getDynamicList(map)
                .compose(RxHelper.<DynamicBean>rxSchedulerHelper());
    }
}
