package com.mhy.shopingphone.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.view.View;
import android.view.ViewGroup;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.utils.DisplayUtils;
import com.mhy.sdk.utils.StatusBarUtils;
import com.mhy.shopingphone.constant.BundleKeyConstant;
import com.mhy.shopingphone.contract.detail.WebViewLoadConaract;
import com.mhy.shopingphone.presenter.detail.WebViewLoadPresenter;


/**
 * Created by Horrarndoo on 2017/10/20.
 * <p>
 * Webview加载Url详情页
 */

public class WebViewLoadActivity extends BaseWebViewLoadActivity<WebViewLoadConaract
        .WebViewLoadPresenter, WebViewLoadConaract.IWebViewLoadModel> implements
        WebViewLoadConaract.IWebViewLoadView {

    private String mTitle, mUrl;

    @Override
    protected void initData() {
        super.initData();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_URL);
            mTitle = bundle.getString(BundleKeyConstant.ARG_KEY_WEB_VIEW_LOAD_TITLE);
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        ViewGroup.LayoutParams param = tou.getLayoutParams();
        // 控件的高强制设成56dp+状态栏高度
        param.height = /*DisplayUtils.dp2px(40) + */StatusBarUtils.getStatusBarHeight
                (mContext);
        tou.setLayoutParams(param);
//        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) appBar.getChildAt(0)
//                .getLayoutParams();
//        // 控件的高强制设成56dp+状态栏高度
//        params.height = DisplayUtils.dp2px(56) + StatusBarUtils.getStatusBarHeight
//                (mContext);
    }

    @Override
    public void showUrlDetail(String url) {
        flNetView.setVisibility(View.GONE);
        nswvDetailContent.loadUrl(url);
    }

    @Override
    protected void loadDetail() {
        mPresenter.loadUrl(mUrl);
    }

    @Override
    protected String getToolbarTitle() {
        return mTitle;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return WebViewLoadPresenter.newInstance();
    }
}
