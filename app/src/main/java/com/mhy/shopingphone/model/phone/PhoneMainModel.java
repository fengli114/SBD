package com.mhy.shopingphone.model.phone;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.shopingphone.contract.phone.PhoneMainContract;

/**
 * 作者： "RedRainM" on 2017/12/13 0013.
 * 描述： 电话model
 */

public class PhoneMainModel extends BaseModel implements PhoneMainContract.IPhoneMainModel{

    @NonNull
    public static PhoneMainModel newInstance() {
        return new PhoneMainModel();
    }
    @Override
    public String[] getTabs() {
        return new String[]{"拨号盘", "通话记录"};
    }

}
