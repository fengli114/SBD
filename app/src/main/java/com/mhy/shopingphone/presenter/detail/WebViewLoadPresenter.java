package com.mhy.shopingphone.presenter.detail;

import android.support.annotation.NonNull;

import com.mhy.shopingphone.contract.detail.WebViewLoadConaract;
import com.mhy.shopingphone.model.detail.WebViewLoadModel;


/**
 * Created by Horrarndoo on 2017/10/20.
 * <p>
 */

public class WebViewLoadPresenter extends WebViewLoadConaract.WebViewLoadPresenter {

    @NonNull
    public static WebViewLoadPresenter newInstance() {
        return new WebViewLoadPresenter();
    }

    @Override
    public void loadUrl(String url) {
        if (mIView == null)
            return;

        try {
            mIView.showUrlDetail(url);
        } catch (Exception e) {
            mIView.showNetworkError();
            e.printStackTrace();
        }
    }

    @Override
    public WebViewLoadConaract.IWebViewLoadModel getModel() {
        return WebViewLoadModel.newInstance();
    }

    @Override
    public void onStart() {
    }
}
