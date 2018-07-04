package com.mhy.shopingphone.contract.zf;

import android.widget.ImageView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseActivity;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.shopingphone.model.bean.PayTypeBean;

import java.util.List;

import io.reactivex.Observable;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */

public interface PayTypeContract {
    abstract class PayTypePresenter extends BasePresenter<IPayTypeModel,
            IPayTypeView> {

        /**
         * 加载付款类型
         */
        public abstract void loadPayTypeList(String string);


    }

    interface IPayTypeModel extends IBaseModel {
        /**
         * 获取最热电影
         *
         * @return 最热电影
         */
        Observable<PayTypeBean> getPayTypeList(String map);


    }

    interface IPayTypeView extends IBaseActivity {
        /**
         * 更新界面list
         *
         * @param list list
         */
        void updateContentList(List<PayTypeBean.JsonBean> list);


        /**
         * 显示网络错误
         */
        void showNetworkError();

    }
}

