package com.mhy.shopingphone.view.webview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.mhy.sdk.utils.StatusBarUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 类描述：WebView加载网络资源
 * Created by lzy on 2016/9/22.
 */
public class WebViewActivity extends Activity {
    @BindView(R.id.title_recharge)
    RelativeLayout titleRecharge;
    @BindView(R.id.ll)
    LinearLayout mLinearLayout;
    private TextView webviewTitle;
    private TinyWebView progressWebview;
    private String title;
    private String webViewUrl;
    private RelativeLayout img_backs;
    private AgentWeb mAgentWeb;
    private TextView img_finsh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.web_activity);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >  Build.VERSION_CODES.KITKAT) {
            StatusBarUtils.setBarColor(this, Color.BLACK);
        }
        Util.setMarginsStatusBar(this,titleRecharge);
        getData();
        webviewTitle = (TextView) findViewById(R.id.tv_title);
        img_backs = (RelativeLayout) findViewById(R.id.img_backs);
        img_finsh = (TextView) findViewById(R.id.img_finsh);
        if (!TextUtils.isEmpty(title))
            webviewTitle.setText(title);
        mAgentWeb = AgentWeb.with(this)//传入Activity
                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))//传入AgentWeb 的父控件 ，如果父控件为 RelativeLayout ， 那么第二参数需要传入 RelativeLayout.LayoutParams
                .useDefaultIndicator()// 使用默认进度条
                .defaultProgressBarColor() // 使用默认进度条颜色
                .setReceivedTitleCallback(null) //设置 Web 页面的 title 回调
                .createAgentWeb()//
                .ready()
                .go(webViewUrl);

        img_backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // true表示AgentWeb处理了该事件
                if (!mAgentWeb.back()) {
                 finish();
                }else {
                    mAgentWeb.back();
                }
            }
        });
        img_finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mAgentWeb.clearWebCache();
            }
        });

//        initViews();
//        loadData();
    }

    /**
     * 方法描述：接收数据
     */
    private void getData() {
        webViewUrl = getIntent().getStringExtra("webview_url");
        title = getIntent().getStringExtra("webview_title");
    }

    /**
     * 方法描述：初始化WebView
     */
    private void initViews() {
        progressWebview = (TinyWebView) findViewById(R.id.progress_webview);
        webviewTitle = (TextView) findViewById(R.id.tv_title);
        img_backs = (RelativeLayout) findViewById(R.id.img_backs);
        progressWebview.setWebViewClient(new WebViewClient(){
            @Override
            public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
                super.doUpdateVisitedHistory(view, url, isReload);
                progressWebview.clearHistory();
            }
        });

        img_backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                progressWebview.clearHistory();
                progressWebview.destroy();
            }
        });
        //web资源
        progressWebview.loadUrl(webViewUrl);
    }

    /**
     * 方法描述：加载数据
     */
    private void loadData() {
        if (!TextUtils.isEmpty(title))
            webviewTitle.setText(title);

        if (TextUtils.isEmpty(webViewUrl))
            progressWebview.loadUrl(webViewUrl);
    }

    /**
     * 方法描述：改写物理按键——返回的逻辑
     */
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if (progressWebview.canGoBack()) {
//                progressWebview.goBack();//返回上一页面
//                return true;
//            } else {
//                System.exit(0);//退出程序
//            }
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    /**
     * 方法描述：跳转至本Activity
     *
     * @param activity     发起跳转的Activity
     * @param webviewUrl   WebView的url
     * @param webviewTitle WebView页面的标题
     */
    public static void skip(Context activity, String webviewUrl, String webviewTitle) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("webview_url", webviewUrl);
        intent.putExtra("webview_title", webviewTitle);
        activity.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        progressWebview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//        progressWebview.clearHistory();
//        progressWebview.clearFormData();
//        progressWebview.clearCache(true);
//
//        CookieSyncManager cookieSyncManager =  CookieSyncManager.createInstance(progressWebview.getContext());
//        CookieManager cookieManager = CookieManager.getInstance();
//        cookieManager.setAcceptCookie(true);
//        cookieManager.removeSessionCookie();
//        cookieManager.removeAllCookie();

    }
}
