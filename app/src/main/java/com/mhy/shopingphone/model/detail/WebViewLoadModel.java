package com.mhy.shopingphone.model.detail;

import android.support.annotation.NonNull;

import com.mhy.sdk.base.BaseModel;
import com.mhy.shopingphone.contract.detail.WebViewLoadConaract;


/**
 * Created by Horrarndoo on 2017/10/20.
 * <p>
 */

public class WebViewLoadModel extends BaseModel implements
        WebViewLoadConaract.IWebViewLoadModel {

    @NonNull
    public static WebViewLoadModel newInstance() {
        return new WebViewLoadModel();
    }
}
