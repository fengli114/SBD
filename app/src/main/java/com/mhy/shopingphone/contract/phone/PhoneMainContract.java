package com.mhy.shopingphone.contract.phone;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseFragment;
import com.mhy.sdk.base.IBaseModel;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：  电话Contract
 */

public interface PhoneMainContract {
    //主页接口
    abstract class PhoneMainPresenter extends BasePresenter<IPhoneMainModel, IPhoneMainView> {
        public abstract void getTabList();
    }

    interface IPhoneMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IPhoneMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }
}
