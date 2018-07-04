package com.mhy.shopingphone.contract.discover;

import android.widget.ImageView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseFragment;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.shopingphone.contract.shop.ShopContract;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.discover.ShoperBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/25 0025.
 * 描述：
 */

public interface DiscoverContract {
    abstract class DiscoverPresenter extends BasePresenter<DiscoverContract.IDiscoverModel,
            DiscoverContract.IDiscoverView> {

//        /**
//         * 加载news
//         */
//        public abstract void loadGoodsList();
        /**
         * 加载商户
         */
        public abstract void loadShoperList(String string);
        /**
         * 加载商品
         */
        public abstract void loadDynamicList(String string);

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

    interface IDiscoverModel extends IBaseModel {
//        /**
//         * 获取新闻
//         *
//         * @return 新闻
//         */
//        Observable<NewsBean> getNewsList();
        /**
         * 获取商户
         *
         * @return 商户
         */
        Observable<ShoperBean> getShoperList(String parms);
        /**
         *
         * @param map
         * @return
         */
        Observable<DynamicBean> getDynamicList(String map);   //动态
    }

    interface IDiscoverView extends IBaseFragment {
//        /**
//         * 更新界面list
//         *
//         * @param list list
//         */
//        void updateContentList(NewsBean list);
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateShoperList(List<ShoperBean.JsonBean> list);
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentsList(List<DynamicBean.InfoBean> list);
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
