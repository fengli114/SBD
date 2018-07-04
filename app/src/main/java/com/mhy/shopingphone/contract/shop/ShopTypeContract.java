package com.mhy.shopingphone.contract.shop;

import android.widget.ImageView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseActivity;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.shopingphone.model.bean.shop.ShopTypeBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */

public interface ShopTypeContract {
    abstract class ShopTypePresenter extends BasePresenter<IShopTypeModel,
            IShopTypeView> {

        /**
         * 加载商品
         */
        public abstract void loadShopTypeList(String string);


        /**
         * item点击事件
         *
         * @param position  position
         * @param item      item
         * @param imageView imageView
         */
        public abstract void onItemClick(int position, ShopTypeBean.InfoBean item, ImageView imageView);

       /**
        * Header被点击
        */
//     public abstract void onHeaderClick();
    }

    interface IShopTypeModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<ShopTypeBean> getShopTypeList(String map);


    }

    interface IShopTypeView extends IBaseActivity {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentList(List<ShopTypeBean.InfoBean> list);


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

