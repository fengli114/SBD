package com.mhy.shopingphone.contract.goods;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseActivity;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.shopingphone.model.bean.goods.GoodsTabBean;

import io.reactivex.Observable;


/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public interface GoodsContract {
    abstract class GoodsPresenter extends BasePresenter<IGoodsModel, IGoodsView> {
        /**
         * 加载书籍详情
         */
        public abstract void loadTabs(String parms);


    }

    interface IGoodsModel extends IBaseModel {
        /**
         * 获取商品类型
         */
        Observable<GoodsTabBean> getTabs(String parms);
    }

    interface IGoodsView extends IBaseActivity {
        /**
         * 显示商品类型
         *
         * @param bean 显示商品类型bean
         */
        void showTabs(GoodsTabBean bean);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}
