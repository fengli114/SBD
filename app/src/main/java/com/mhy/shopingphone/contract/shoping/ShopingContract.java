package com.mhy.shopingphone.contract.shoping;

import android.widget.ImageView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseFragment;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.IBaseView;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */

public interface ShopingContract {
    abstract class ShopPresenter extends BasePresenter<IShopModel,
            IShopView> {

        /**
         * 加载商品
         */
        public abstract void loadGoodsList();

        /**
         * 加载更多商品
         */
        public abstract void loadMoreShopList();

        /**
         * item点击事件
         *
         * @param position  position
         * @param item      item
         * @param imageView imageView
         */
        public abstract void onItemClick(int position, GoodsBean.JsonBean.CommoditiesBean item, ImageView imageView);

       /**
        * Header被点击
        */
//     public abstract void onHeaderClick();
    }

    interface IShopModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<GoodsBean> getGoodsList(Map<String, String> map);


    }

    interface IShopView extends IBaseView {
        /**
         * 更新界面list
         *
         * @param goodsBean list
         */
        void updateContentList(GoodsBean goodsBean);

        Map<String, String> getParams();

        /**
         * 没有更多数据
         */
        void showNoMoreData();

        /**
         * 显示加载更多失败
         */
        void showLoadMoreError();
        /**
         * 显示网络错误
         */
        void showNetworkError();

    }
}

