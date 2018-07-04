package com.mhy.shopingphone.contract.detail;

import android.widget.ImageView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseActivity;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */

public interface DynamicContract {
    abstract class DynamicPresenter extends BasePresenter<IDynamicModel,
            IDynamicView> {

        /**
         * 加载商品
         */
        public abstract void loadDynamicList(String string);

        /**
         * 加载更多商品
         */
        public abstract void loadMoreDynamicList(String string);

        /**
         * item点击事件
         *
         * @param position  position
         * @param item      item
         * @param imageView imageView
         */
        public abstract void onItemClick(int position, DynamicBean.InfoBean item, ImageView imageView);

       /**
        * Header被点击
        */
//     public abstract void onHeaderClick();
    }

    interface IDynamicModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<DynamicBean> getDynamicList(String map);


    }

    interface IDynamicView extends IBaseActivity {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentList(List<DynamicBean.InfoBean> list);


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

