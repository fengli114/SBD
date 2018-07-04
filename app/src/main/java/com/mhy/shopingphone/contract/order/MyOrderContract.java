package com.mhy.shopingphone.contract.order;

import android.widget.ImageView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseActivity;
import com.mhy.sdk.base.IBaseFragment;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;


import java.util.List;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */

public interface MyOrderContract {
    abstract class MyOrderPresenter extends BasePresenter<IMyOrderModel,
            IMyOrderView> {

        /**
         * 加载商品
         */
        public abstract void loadOrderList(String string);

        /**
         * 加载更多商品
         */
        public abstract void loadMoreMyOrderList(String string);

        /**
         * item点击事件
         *
         * @param position  position
         * @param item      item
         * @param imageView imageView
         */
        public abstract void onItemClick(int position, MyOrderBean.JsonBean item, ImageView imageView);

       /**
        * Header被点击
        */
//     public abstract void onHeaderClick();
    }

    interface IMyOrderModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<MyOrderBean> getOrderList(String map);


    }

    interface IMyOrderView extends IBaseActivity {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentList(List<MyOrderBean.JsonBean> list);


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

