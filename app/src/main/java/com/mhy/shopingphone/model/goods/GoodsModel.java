package com.mhy.shopingphone.model.goods;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.goods.GoodsContract;
import com.mhy.shopingphone.model.bean.goods.GoodsTabBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class GoodsModel extends BaseModel implements GoodsContract.IGoodsModel{
    @NonNull
    public static GoodsModel newInstance() {
        return new GoodsModel();
    }

    @Override
    public Observable<GoodsTabBean> getTabs(String parms) {
        return RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getGoodsTabsData(parms)
                .compose(RxHelper.<GoodsTabBean>rxSchedulerHelper());
    }

}
