package com.mhy.shopingphone.presenter.phone;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.phone.PhoneMainContract;
import com.mhy.shopingphone.model.phone.PhoneMainModel;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：
 */

public class PhoneMainPresenter extends PhoneMainContract.PhoneMainPresenter {
    @NonNull
    public static PhoneMainPresenter newInstance() {
        return new PhoneMainPresenter();
    }

    @Override
    public void getTabList() {
        if (mIView == null || mIModel == null)
            return;

        mIView.showTabList(mIModel.getTabs());
    }

    @Override
    public PhoneMainContract.IPhoneMainModel getModel() {
        return PhoneMainModel.newInstance();
    }

    @Override
    public void onStart() {

    }
}
