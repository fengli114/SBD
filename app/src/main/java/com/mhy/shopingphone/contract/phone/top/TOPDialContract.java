package com.mhy.shopingphone.contract.phone.top;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseFragment;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.shopingphone.model.bean.banner.BannerBean;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/21 0021.
 * 描述：
 */

public interface TOPDialContract {
    abstract class TOPDialPresenter extends BasePresenter<ITOPDialModel,ITOPDialView>{
        /**
         * 加载商品
         */
        public abstract void loadBannerData(String string);
        /**
         * 点击banner
         */
        public abstract void btnBannerClicked(BannerBean bean);

    }


    interface ITOPDialModel extends IBaseModel{
        /**
         * 获所有Banner信息
         *
         * @return Banner信息
         */
        Observable<BannerBean> getBanners(String parms);
    }
    interface ITOPDialView extends IBaseFragment{
        /**
         * 显示书籍详情
         *
         * @param bean 书籍详情bean
         */
        void showBannerDetail(BannerBean bean);

        /**
         * 显示网络错误
         */
        void showNetworkError();
    }
}