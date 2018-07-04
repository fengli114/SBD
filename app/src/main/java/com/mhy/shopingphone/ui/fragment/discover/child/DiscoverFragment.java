package com.mhy.shopingphone.ui.fragment.discover.child;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mhy.sdk.RxManager;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.rxbus.Subscribe;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.ResourcesUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.DynamicAdapter;
import com.mhy.shopingphone.adapter.GoodsAdapter;
import com.mhy.shopingphone.adapter.NewsAdapter;
import com.mhy.shopingphone.adapter.ShoperAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.discover.DiscoverContract;
import com.mhy.shopingphone.global.MyApplication;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.discover.ShoperBean;
import com.mhy.shopingphone.model.bean.greendao.bean.BannerListBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;
import com.mhy.shopingphone.presenter.discover.DiscoverPresenter;
import com.mhy.shopingphone.ui.activity.detail.DynamicActivity;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.mhy.shopingphone.widgets.ScrollTextView;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_BANNER;
import static com.mhy.shopingphone.constant.RxBusCode.RX_BUS_CODE_UP_LOGO;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：
 */

public class DiscoverFragment extends BaseMVPCompatFragment<DiscoverContract.DiscoverPresenter,
        DiscoverContract.IDiscoverModel> implements DiscoverContract.IDiscoverView {

    @BindView(R.id.iv_kefu)
    ImageView ivKefu;
    @BindView(R.id.img_history)
    RelativeLayout imgHistory;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_news)
    RecyclerView rvNews;
    @BindView(R.id.rv_shoper)
    RecyclerView rvShoper;
    @BindView(R.id.rv_dynamic)    //动态
            RecyclerView rvDynamic;
    @BindView(R.id.message_container)
    LinearLayout messageContainer;
    Unbinder unbinder;
    @BindView(R.id.banner_discover)
    Banner bannerDiscover;
    @BindView(R.id.tv_adv)
    ScrollTextView tvAdv;

    Unbinder unbinder1;
    private DynamicAdapter msAdapter;
    private NewsAdapter mAdapter;
    private Map<String, String> parames;
    private String paramstr;
    private View headView;
    private static List<String> strings;
    private static List<String> strUrl;
    private HashMap<String, String> shoperParams;
    private ShoperAdapter shoperAdapter;
    private String shoperParamstr;
    private List<BannerListBean> bannerListBeans;

    public static DiscoverFragment newInstance() {
        Bundle args = new Bundle();
        DiscoverFragment fragment = new DiscoverFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return DiscoverPresenter.newInstance();
    }


    @Override
    public int getLayoutId() {
        return R.layout.fragment_discover_;
    }

    @Override
    public void onResume() {
        super.onResume();

        MobclickAgent.onPageStart("faxian");
        RxBus.get().register(this);
        parames = new HashMap<>();
        shoperParams = new HashMap<>();
        shoperAdapter = new ShoperAdapter(R.layout.item_shoper_);
        rvShoper.setAdapter(shoperAdapter);
        rvShoper.setLayoutManager(new LinearLayoutManager(mActivity));
        loadShoperData();
        msAdapter = new DynamicAdapter(R.layout.item_bbs_nine_grid);      //加载动态数据
        rvDynamic.setAdapter(msAdapter);
        rvDynamic.setLayoutManager(new LinearLayoutManager(mActivity));
//        loadDynamicList(1);
        mAdapter = new NewsAdapter(R.layout.item_news);
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        rvNews.setAdapter(mAdapter);
        //getItemCount()返回值<=0,要设置LinearLayoutManager，否则后面数据更新RecycleView也不执行onBindViewHolder;
        rvNews.setLayoutManager(new LinearLayoutManager(mActivity));
        loadNewsData();
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        String nums = String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", ""));
        bannerListBeans = MyApplication.getDaoInstant().loadAll(BannerListBean.class);
        strings = new ArrayList<>();
        strUrl = new ArrayList<>();
        for (BannerListBean bean : bannerListBeans) {
            strings.add(bean.getBannerUrl());
            strUrl.add(bean.getStrUrl());
        }
        if (strings.size() == 0) {

        } else {
            Util.setBanner(null, strings, bannerDiscover);//设置轮播图
        }
        bannerDiscover.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                if (!TextUtils.isEmpty(strUrl.get(position))) {
                    WebViewActivity.skip(getActivity(), strUrl.get(position), "详情");
                }
                LogUtils.e("地址：" + strUrl.get(position));
            }
        });

    }

    private void loadNewsData() {
        Map<String, String> paramea = new HashMap<>();
        paramea.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        paramea.put("Mobile", (String) SharedPreferencesHelper.getInstance().getData("Mobile", ""));
        paramea.put("curPage", "1");
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.NES_URL)
                .params(paramea)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli111" + response);
                        try {
                            NewsBean newsBean = new Gson().fromJson(response, NewsBean.class);
                            if (newsBean.getErrorCode() == 2000) {
                                List<NewsBean.JsonBean> data = newsBean.getJson();
                                mAdapter.addData(data);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    //请求
    private void loadShoperData() {
        //请求参数
        shoperParams.put("ParentId", Contant.PARENTID);//代理商id
        shoperParams.put("Mobile", (String) SharedPreferencesHelper.getInstance().getData("Mobile", ""));
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.PLATE_URL)
                .params(shoperParams)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }
                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            ShoperBean list = new Gson().fromJson(response, ShoperBean.class);
                            List<ShoperBean.JsonBean> listData = new ArrayList<>();
                            for (ShoperBean.JsonBean bean : list.getJson()) {
                                if (bean.getType() == 1) {
                                    listData.add(bean);
                                } else if (bean.getType() == 2) {
                                    tvAdv.setText(bean.getText());
                                }
                            }
                            if (shoperAdapter.getData().size() == 0) {
                                initRecycleView(listData);
                            } else {
//                                shoperAdapter.addData(listData);
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override    //八大板块
    public void updateShoperList(List<ShoperBean.JsonBean> list) {

    }

    private void loadDynamicList(int page) {
        //请求参数
        parames.put("Prefix", "");
        parames.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));
        parames.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
//        String paramsstring = StringUtil.mapToJson(parames);
//        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.DYNAMIC_URLHead;
//        mPresenter.loadDynamicList(paramstr);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.NES_URL)
                .params(parames)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        DynamicBean bean = new Gson().fromJson(response, DynamicBean.class);
                        List<DynamicBean.InfoBean> list = bean.getInfo();
                        if (msAdapter.getData().size() == 0) {
                            initRecyclesView(list);
                        } else {
                            msAdapter.addData(list);
                        }
                    }
                });
    }

    @Override
    public void updateContentsList(List<DynamicBean.InfoBean> list) {

    }

    private void initRecyclesView(List<DynamicBean.InfoBean> list) {
        msAdapter = new DynamicAdapter(R.layout.item_bbs_nine_grid, list);

       /* msAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DynamicBean.InfoBean infoBean = (DynamicBean.InfoBean) adapter.getItem(position);
                WebViewActivity.skip(mActivity, infoBean.getUrl(), infoBean.getTitle());
            }
        });*/
        rvDynamic.setAdapter(msAdapter);

    }

    private void initRecycleView(List<ShoperBean.JsonBean> list) {
        shoperAdapter = new ShoperAdapter(R.layout.item_shoper_, list);
        shoperAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            }
        });
        rvShoper.setAdapter(shoperAdapter);
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        rvShoper.setLayoutManager(new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL));

    }

    @Override
    public void showNoMoreData() {
        msAdapter.loadMoreEnd(true);
    }

    @Override
    public void showLoadMoreError() {
        msAdapter.loadMoreFail();
    }

    @Override
    public void showNetworkError() {
        //设置头部
        mPresenter.loadDynamicList(paramstr);
        mPresenter.loadShoperList(shoperParamstr);
    }

    @OnClick({R.id.iv_kefu, R.id.img_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_kefu:
                String url11 = "mqqwpa://im/chat?chat_type=wpa&uin=1558753783&version=1";
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
                } catch (Exception e) {
                    // 未安装手Q或安装的版本不支持    showToast("未安装手Q或安装的版本不支持");
                    ToastUtils.showToast("未安装手Q或安装的版本不支持");
                }
                break;
            case R.id.img_history:
                WebViewActivity.skip(mActivity,
                        Api.NEWGOODS,
                        "企业管家");
//                startNewActivity(DynamicActivity.class);
                break;
        }
    }

    /**
     * RxBus接收图片Uri
     */
    @Subscribe(code = RX_BUS_CODE_BANNER)
    public void rxBusEvent() {
        bannerListBeans = MyApplication.getDaoInstant().loadAll(BannerListBean.class);
        strings = new ArrayList<>();
        strUrl = new ArrayList<>();
        for (BannerListBean bean : bannerListBeans) {
            strings.add(bean.getBannerUrl());
            strUrl.add(bean.getStrUrl());
        }
        if (strings.size() == 0) {

        } else {
            Util.setBanner(null, strings, bannerDiscover);//设置轮播图
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        MobclickAgent.onPageEnd("faxian");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
