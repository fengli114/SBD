package com.mhy.shopingphone.contract.goods;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.IBaseView;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.RechargeBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public interface SearchContract {

        abstract class SearchPresenter extends BasePresenter<SearchContract.ISearchModel, SearchContract.ISearchView> {

        /**
         * 加载商品详情
         *
         * @param parms url
         */
        public abstract void goSearch(String parms);
    }

    interface ISearchModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<RechargeBean> goSearch(String parms);
    }

    interface ISearchView extends IBaseView {
        /**
         *
         *
         * @param ceshi list
         */
        void goSearch(Ceshi ceshi);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
