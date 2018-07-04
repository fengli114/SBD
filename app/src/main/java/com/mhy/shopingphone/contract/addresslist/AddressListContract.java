package com.mhy.shopingphone.contract.addresslist;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseFragment;
import com.mhy.sdk.base.IBaseModel;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：
 */

public interface AddressListContract {
    //主页接口
    abstract class AddressListPresenter extends BasePresenter<IAddressListMainModel, IAddressListMainView> {
        public abstract void getTabList();
    }

    interface IAddressListMainModel extends IBaseModel {
        String[] getTabs();
    }

    interface IAddressListMainView extends IBaseFragment {
        void showTabList(String[] tabs);
    }
}
