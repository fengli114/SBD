package com.mhy.shopingphone.model.main;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.main.PersonalContract;


/**
 * Created by Horrarndoo on 2017/9/26.
 * <p>
 */

public class PersonalUpperModel implements PersonalContract.IPersonalUpperModel {

    @NonNull
    public static PersonalUpperModel newInstance() {
        return new PersonalUpperModel();
    }
}
