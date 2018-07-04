package com.mhy.shopingphone.model.bean.phone;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.phone.top.TOPDialContract;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.youth.xframe.utils.log.XLog;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/21 0021.
 * 描述：
 */

public class TOPDialModel  extends BaseModel implements TOPDialContract.ITOPDialModel{

    @NonNull
    public static TOPDialModel newInstance() {
        return new TOPDialModel();
    }


    @Override
    public Observable<BannerBean> getBanners(String parms) {
//        return RetrofitCreateHelper.createApi(Api.class, Api.NEWGOODS).getBanners(parms)
//                .compose(RxHelper.<BannerBean>rxSchedulerHelper());
        return null;
    }
}
