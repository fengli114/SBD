package com.mhy.shopingphone.contract.details;

import com.mhy.sdk.base.BaseModel;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseActivity;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.IBaseView;
import com.mhy.shopingphone.contract.detail.BaseWebViewLoadContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopDetailBean;
import com.mhy.shopingphone.presenter.detail.BaseWebViewLoadPresenter;


import java.util.List;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/3 0003.
 * 描述：
 */

public interface GoodsDtailContract {
    abstract class GoodsDtailPresenter extends BasePresenter<IGoodsDtailModel, IGoodsDtailView> {

        /**
         * 加载商品详情
         *
         * @param parms url
         */
        public abstract void nowBuy(String parms);
        public abstract void getDetails(String parms);
    }

    interface IGoodsDtailModel extends IBaseModel{
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<Ceshi> goNowBuy(String parms);
        Observable<ShopDetailBean> getDetails(String parms);
    }

    interface IGoodsDtailView extends IBaseView {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void update(Ceshi list);
        void showDetails(ShopDetailBean list);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
