package com.mhy.shopingphone.contract.partner;

import android.widget.ImageView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseActivity;
import com.mhy.sdk.base.IBaseFragment;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.shopingphone.model.bean.partner.PartnerShipBean;


import java.util.List;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */

public interface PartnerContract {
    abstract class PartnerPresenter extends BasePresenter<IPartnerModel,
            IPartnerView> {

        /**
         * 加载商品
         */
        public abstract void loadContentList(String string);

//        /**
//         * item点击事件
//         *
//         * @param position  position
//         * @param item      item
//         * @param imageView imageView
//         */
//        public abstract void onItemClick(int position, PartnerShipBean, ImageView imageView);

       /**
        * Header被点击
        */
//     public abstract void onHeaderClick();
    }

    interface IPartnerModel extends IBaseModel {

        /**
         * 获取
         *
         * @return
         */
        Observable<PartnerShipBean> getContentData(String s);

    }

    interface IPartnerView extends IBaseActivity {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentList(PartnerShipBean list);


        /**
         * 显示网络错误
         */
        void showNetworkError();

    }
}

