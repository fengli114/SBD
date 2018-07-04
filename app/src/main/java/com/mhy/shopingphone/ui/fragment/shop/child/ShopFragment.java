package com.mhy.shopingphone.ui.fragment.shop.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseRecycleFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.NetworkConnectionUtils;
import com.mhy.sdk.utils.ResourcesUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.GoodesAdapter;
import com.mhy.shopingphone.adapter.GoodsAdapter;
import com.mhy.shopingphone.adapter.GridViewAdapter;
import com.mhy.shopingphone.adapter.ViewPagerAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.shop.ShopContract;
import com.mhy.shopingphone.model.Model;
import com.mhy.shopingphone.model.bean.greendao.bean.BannerListBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;
import com.mhy.shopingphone.model.bean.shop.ShopTypesBean;
import com.mhy.shopingphone.presenter.shop.ShopPresenter;
import com.mhy.shopingphone.ui.MainActivity;
import com.mhy.shopingphone.ui.activity.GoodsActivity;
import com.mhy.shopingphone.ui.activity.ShopTypeActivity;
import com.mhy.shopingphone.ui.activity.detail.GoodsDtailActivity;
import com.mhy.shopingphone.ui.activity.detail.ShopDtailesActivity;
import com.mhy.shopingphone.ui.activity.detail.ShopDtailsActivity;
import com.mhy.shopingphone.ui.activity.order.MyOrderActivity;
import com.mhy.shopingphone.ui.activity.search.SearchActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.mhy.shopingphone.widgets.ScrollTextView;
import com.umeng.analytics.MobclickAgent;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：  淘商城界面
 */

public class ShopFragment extends BaseRecycleFragment<ShopContract.ShopPresenter,
        ShopContract.IShopModel> implements ShopContract.IShopView, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rv_shop)
    RecyclerView rv_shop;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    Unbinder unbinder;
    @BindView(R.id.ic_loading)
    LinearLayout ic_loading;
    private LayoutInflater inflater;
    private ViewPager mPager;   //分类
    private RelativeLayout rl_fenlei;
    LinearLayout mLlDot;        //圆点
    private int pageCount;//总页数
    private int pageSize = 10;//每一页的个数
    private int curIndex = 0;//当前显示的事第几页
    private List<View> mPagerList;
    private List<Model> mDatas;
    private GoodsAdapter goodsAdapter;
    private Map<String, String> params;
    private Map<String, String> paramsBanner;
    private int mP = 1;
    private View headView;
    private Banner mBannerView;
    private ImageView iv_jingdong;
    private String scollText;
    private ScrollTextView tvADV;
    private RecyclerView rvshops;
    private String paramstrBanner;
    private boolean isRefresh = false; //标记是否是 下拉刷新
    private boolean lazyFlag = true;
    private String strTag;
    private int index;
    private GoodsBean.JsonBean.CommoditiesBean infoBean;
    private int index2;
    private ImageView mShoptype1, mShoptype2, mShoptype3, mShoptype4, mShoptype5, mShoptype6, mShoptype7, mShopTypeMore;
    private List<GoodsBean.JsonBean.CategoriesBean> listInfo;
    private Bundle bundle;
    private GoodsBean mInfoBean;
    private ShopTypesBean typesBean;
    private int pages;
    private int nums;

    public static ShopFragment newInstance() {
        Bundle args = new Bundle();
        ShopFragment fragment = new ShopFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ShopPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shop_;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        tv_advbanner.init(mActivity.getWindowManager());
        loadGoodsLists();
        goodsListes("1", null);
//        mPresenter.loadGoodsList();
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                mPresenter.loadGoodsList();
                goodsListes("1", null);
                isRefresh = true;
                mPtrFrame.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    //全部分类
    private void loadGoodsLists() {
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.COMPLETE_URL)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        typesBean = new Gson().fromJson(response, ShopTypesBean.class);
                        if (typesBean.getErrorCode() == 2000) {
                            mDatas = new ArrayList<Model>();
                            for (int i = 0; i < typesBean.getJson().size(); i++) {
                                if (i==0){
                                    mDatas.add(new Model("京东", ""));
                                }else {
                                    mDatas.add(new Model(typesBean.getJson().get(i-1).getCate(), typesBean.getJson().get(i-1).getPic()));
                                }
                            }
                        }
                    }
                });

    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        paramsBanner = new HashMap<>();
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        goodsAdapter = new GoodsAdapter(R.layout.item_shop);

        rv_shop.setAdapter(goodsAdapter);
//        rv_shop.setLayoutManager(new GridLayoutManager(mActivity,2));
        //getItemCount()返回值<=0,要设置LinearLayoutManager，否则后面数据更新RecycleView也不执行onBindViewHolder;
        rv_shop.setLayoutManager(new LinearLayoutManager(mActivity));
    }


    @Override
    public void updateContentList(GoodsBean infoBean) {

    }

    //初始化 头部控件
    private void initHeadViews(GoodsBean infoBean) {
        final List<GoodsBean.JsonBean.CommoditiesBean> lists = infoBean.getJson().getCommodities();
        final List<GoodsBean.JsonBean.HotsBean> listes = infoBean.getJson().getHots();
        if (mP == 1) {
            mInfoBean = infoBean;
            List<String> strings = new ArrayList<>();
            final List<String> stringes = new ArrayList<>();
            for (GoodsBean.JsonBean.ShopTextsBean bean : infoBean.getJson().getShopTexts()) {
                if (bean.getType() == 2) {
                    scollText = bean.getText();
                } else {
                    strings.add(bean.getPic());
                    stringes.add(bean.getUrl());
                }
            }
            tvADV.setText(scollText);
            Util.setBanner(null, strings, mBannerView);//设置轮播图
            mBannerView.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int position) {
                    if (stringes.get(position) != null && stringes.get(position).length() > 0) {
                        WebViewActivity.skip(mActivity, stringes.get(position), null);
                    }
                }
            });
            GoodesAdapter sAdapter;    //横向滑动的适配器
            sAdapter = new GoodesAdapter(R.layout.item_shops, listes);
            sAdapter.setOnLoadMoreListener(this, rvshops);
            sAdapter.loadMoreEnd();
            sAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {    //热门推荐的点击事件
                    if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {       //判断是否登录
                        showDialogUpdate();
                    } else {
                        index = position;
                        Bundle bundle = new Bundle();
                        bundle.putString("goods_id", listes.get(position).getId());
                        bundle.putSerializable("remen", (Serializable) listes);
                        startNewActivity(ShopDtailesActivity.class, bundle);
                    }
                }
            });
            rvshops.setAdapter(sAdapter);
            //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            rvshops.setLayoutManager(linearLayoutManager);
//            rvshops.setLayoutManager(new StaggeredGridLayoutManager(2,
//                    StaggeredGridLayoutManager.VERTICAL));

            listInfo = infoBean.getJson().getCategories();
            mShoptype1.setOnClickListener(onClickListener);
            mShoptype2.setOnClickListener(onClickListener);
            mShoptype3.setOnClickListener(onClickListener);
            mShoptype4.setOnClickListener(onClickListener);
            mShoptype5.setOnClickListener(onClickListener);
            mShoptype6.setOnClickListener(onClickListener);
            mShoptype7.setOnClickListener(onClickListener);
            Glide.with(mContext).load(Contant.URL_IMAGE + listInfo.get(0).getIndexpic()).crossFade(300).placeholder(R
                    .mipmap.img_default_movie).into(mShoptype1);
            Glide.with(mContext).load(Contant.URL_IMAGE + listInfo.get(1).getIndexpic()).crossFade(300).placeholder(R
                    .mipmap.img_default_movie).into(mShoptype2);
            Glide.with(mContext).load(Contant.URL_IMAGE + listInfo.get(2).getIndexpic()).crossFade(300).placeholder(R
                    .mipmap.img_default_movie).into(mShoptype3);
            Glide.with(mContext).load(Contant.URL_IMAGE + listInfo.get(3).getDpic()).crossFade(300).placeholder(R
                    .mipmap.img_default_movie).into(mShoptype4);
            Glide.with(mContext).load(Contant.URL_IMAGE + listInfo.get(4).getDpic()).crossFade(300).placeholder(R
                    .mipmap.img_default_movie).into(mShoptype5);

        }
    }


    @Override
    public void onLoadMoreRequested() {
//        goodsAdapter.loadMoreComplete();
        mP += 1;
        pages += 1;
        if (nums < 10) {
            goodsAdapter.loadMoreEnd(false);   //没有更多数据了
        } else {
            goodsListes(pages + "", goodsAdapter);
        }
//        goodsListes(pages + "", goodsAdapter);
//        mPresenter.loadMoreShopList();
    }

    @Override
    public void showNoMoreData() {
        goodsAdapter.loadMoreEnd(false);
    }

    @Override
    public void showLoadMoreError() {
        goodsAdapter.loadMoreFail();
    }

    @Override
    public void showNetworkError() {
        goodsAdapter.setEmptyView(errorView);
    }

    @Override
    protected void onErrorViewClick(View view) {
        //设置头部
//        mPresenter.loadGoodsList();
        goodsListes("1", null);
    }

    @Override
    protected void showLoading() {
        goodsAdapter.setEmptyView(loadingView);
    }

    private void initRecycleView(final List<GoodsBean.JsonBean.CommoditiesBean> list) {
        goodsAdapter = new GoodsAdapter(R.layout.item_shop, list);
        goodsAdapter.setOnLoadMoreListener(this, rv_shop);
        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {       //判断是否登录
                    showDialogUpdate();
                } else {
                    //由于有headview click position需要+1 adapter.getItem返回的是数据list的position，所以不用+1
                    index = position;
                    infoBean = (GoodsBean.JsonBean.CommoditiesBean) adapter.getItem(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("goods_id", infoBean.getId());
                    bundle.putSerializable("remen", (Serializable) list);
                    startNewActivity(ShopDtailsActivity.class, bundle);
                }
            }
        });
        initHeadView();
        goodsAdapter.addHeaderView(headView);
        rv_shop.setAdapter(goodsAdapter);
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        rv_shop.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
    }

    private void initHeadView() {
        if (headView == null) {
            headView = ResourcesUtils.inflate(R.layout.include_shopmall_layout);
        }
        rl_fenlei = headView.findViewById(R.id.rl_fenlei);
        mPager = headView.findViewById(R.id.viewpagers);
        mLlDot = headView.findViewById(R.id.ll_dot);
        inflater = LayoutInflater.from(mContext);
        mBannerView = headView.findViewById(R.id.banner_shopping_mall);   //banner图片
        iv_jingdong = headView.findViewById(R.id.iv_jingdong);
        tvADV = headView.findViewById(R.id.tv_adv);
        rvshops = headView.findViewById(R.id.rv_shops);     //横向滑动
        mShoptype1 = headView.findViewById(R.id.iv_shop_type1);
        mShoptype2 = headView.findViewById(R.id.iv_shop_type2);
        mShoptype3 = headView.findViewById(R.id.iv_shop_type3);
        mShoptype4 = headView.findViewById(R.id.iv_shop_type4);
        mShoptype5 = headView.findViewById(R.id.iv_shop_type5);
        mShoptype6 = headView.findViewById(R.id.iv_shop_type6);
        mShoptype7 = headView.findViewById(R.id.iv_shop_type7);
        iv_jingdong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("category", "京东");
                bundle.putString("typeName", "");
                startNewActivity(ShoperActivity.class, bundle);
            }
        });
        headView.findViewById(R.id.iv_more_type).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity(ShopTypeActivity.class);
            }
        });
        if (mDatas != null && mDatas.size() > 0) {
            rl_fenlei.setVisibility(View.VISIBLE);
            pageCount = (int) Math.ceil(mDatas.size() * 1.0 / pageSize);
            mPagerList = new ArrayList<>();
            for (int i = 0; i < pageCount; i++) {
                GridView gridView = (GridView) inflater.inflate(R.layout.shopping_mall_type, null);
                gridView.setAdapter(new GridViewAdapter(mContext, mDatas, i, pageSize));
                mPagerList.add(gridView);
                gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        int pos = i + curIndex * pageSize;
                        if (mDatas.get(pos).getName().indexOf("京东") != -1) {
                            Bundle bundle = new Bundle();
                            bundle.putString("category", mDatas.get(pos).getName());
                            bundle.putString("typeName", "");
                            startNewActivity(ShoperActivity.class, bundle);
                        } else {
                            Bundle bundle = new Bundle();
                            bundle.putString("category", mDatas.get(pos).getName());
                            bundle.putString("typeName", "");
                            startNewActivity(ShopActivity.class, bundle);
                        }
//                            ShopTypesBean.JsonBean listInfo = (ShopTypesBean.JsonBean) adapterView.getItemAtPosition(pos);
                    }
                });
            }
            mPager.setAdapter(new ViewPagerAdapter(mPagerList));
            //设置小圆点
            setOvalLayout();
        } else {
            rl_fenlei.setVisibility(View.GONE);
        }
//        headView.findViewById(R.id.rl_shopping_all).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("SearchType", 0);
//                bundle.putString("typeName", "全部分类");
//                startNewActivity(ShopActivity.class, bundle);
//            }
//        });
//        headView.findViewById(R.id.rl_shopping_choiceness).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//                bundle.putInt("SearchType", 1);
//                bundle.putString("typeName", "小编精选");
//                startNewActivity(ShopActivity.class, bundle);
//            }
//        });
//        headView.findViewById(R.id.rl_shopping_moods).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle2 = new Bundle();
//                bundle2.putInt("SearchType", 2);
//                bundle2.putString("typeName", "超高人气");
//                startNewActivity(ShopActivity.class, bundle2);
//            }
//        });
//        headView.findViewById(R.id.rl_shopping_nine).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle3 = new Bundle();
//                bundle3.putInt("SearchType", 3);
//                bundle3.putString("typeName", "9.9包邮");
//                startNewActivity(ShopActivity.class, bundle3);
//            }
//        });
//        headView.findViewById(R.id.rl_shopping_new).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Bundle bundle4 = new Bundle();
//                bundle4.putInt("SearchType", 4);
//                bundle4.putString("typeName", "今日上新");
//                startNewActivity(ShopActivity.class, bundle4);
//            }
//        });
    }

    private void goodsListes(final String page, GoodsAdapter adapter) {
        params = new HashMap<>();
        //请求参数
        params.put("Agentid", Contant.PARENTID);//代理商id
        params.put("OrderBy", "5");//排序
        params.put("Category", "");//
        params.put("SearchType", "1");//分类类型
        params.put("Name", "");
        params.put("Page", page);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.SHOPPING_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ic_loading.setVisibility(View.VISIBLE);
                        if (goodsAdapter != null) {
                            goodsAdapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli==" + response);
                        GoodsBean infoBean = new Gson().fromJson(response, GoodsBean.class);
                        nums = infoBean.getJson().getCommodities().size();
                        if (infoBean.getErrorCode() != 2000) {
                            goodsAdapter.loadMoreComplete();
                            ToastUtils.showToast(infoBean.getData());
                            return;
                        }
                        if (isRefresh) {
                            isRefresh = false;
                            goodsAdapter.setNewData(infoBean.getJson().getCommodities());
                        } else {
                            if (goodsAdapter.getData().size() == 0) {
                                initRecycleView(infoBean.getJson().getCommodities());
                            } else {
                                goodsAdapter.addData(infoBean.getJson().getCommodities());
                            }
                        }
                        if (goodsAdapter != null) {
                            goodsAdapter.loadMoreComplete();
                        }
                        if (page.equals("1")) {
                            initHeadViews(infoBean);
                        }
                    }
                });

    }

    private void setOvalLayout() {
        for (int i = 0; i < pageCount; i++) {
            mLlDot.addView(inflater.inflate(R.layout.dot, null));
        }
        //默认显示第一页
        mLlDot.getChildAt(0).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //取消选中
                mLlDot.getChildAt(curIndex).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_normal);
                //选中
                mLlDot.getChildAt(position).findViewById(R.id.v_dot).setBackgroundResource(R.drawable.dot_selected);

                curIndex = position;

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (listInfo.size() == 0) {
                return;
            }
            bundle = new Bundle();
            if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                showDialogUpdate();
            } else {
                switch (view.getId()) {
                    case R.id.iv_shop_type1:
                        bundle.putString("category", listInfo.get(0).getCate());
                        bundle.putString("typeName", listInfo.get(0).getCate());
                        startNewActivity(ShopActivity.class, bundle);
                        break;
                    case R.id.iv_shop_type2:
                        bundle.putString("category", listInfo.get(1).getCate());
                        bundle.putString("typeName", listInfo.get(1).getCate());
                        startNewActivity(ShopActivity.class, bundle);
                        break;
                    case R.id.iv_shop_type3:
                        bundle.putString("category", listInfo.get(2).getCate());
                        bundle.putString("typeName", listInfo.get(2).getCate());
                        startNewActivity(ShopActivity.class, bundle);
                        break;
                    case R.id.iv_shop_type4:
                        bundle.putString("category", listInfo.get(3).getCate());
                        bundle.putString("typeName", listInfo.get(3).getCate());
                        startNewActivity(ShopActivity.class, bundle);
                        break;
                    case R.id.iv_shop_type5:
                        bundle.putString("category", listInfo.get(4).getCate());
                        bundle.putString("typeName", listInfo.get(4).getCate());
                        startNewActivity(ShopActivity.class, bundle);
                        break;
                    case R.id.iv_shop_type6:     //团购优惠
                        bundle.putInt("SearchType", 4);
                        bundle.putString("typeName", "");
                        bundle.putString("title", "团购优惠");
                        startNewActivity(ShopActivity.class, bundle);
                        break;
                    case R.id.iv_shop_type7:      //限时抢购
                        bundle.putInt("SearchType", 5);
                        bundle.putString("typeName", "");
                        bundle.putString("title", "限时抢购");
                        startNewActivity(ShopActivity.class, bundle);
                        break;
                }
            }
        }
    };

    @OnClick({R.id.rl_search_shopping, R.id.al_shoppping_order})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_search_shopping:
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUpdate();
                    break;
                } else {
                    if (mInfoBean != null && mInfoBean.getJson().getCommodities().size() > 0) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("data", mInfoBean);
                        startNewActivity(SearchActivity.class, bundle);
                    }
                    break;
                }
//                Intent intent = new Intent(mContext, SearchActivity.class);
//                break;
            case R.id.al_shoppping_order:
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUpdate();
                    break;
                } else {
                    startNewActivity(MyOrderActivity.class);
                    break;
                }

        }
    }

    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // 设置提示框的标题
        builder.setMessage("你还没有登录").setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("登陆", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(mContext, LoginActivty.class));
                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("shop");
        if (!NetworkConnectionUtils.isNetworkConnected(mContext)) {
            ic_loading.setVisibility(View.VISIBLE);
        } else {
            ic_loading.setVisibility(View.GONE);
            RxBus.get().register(this);
            if (!lazyFlag) {
                strTag = (String) SharedPreferencesHelper.getInstance().getData("ok", "");
                if (strTag.equals("ok")) {
                    goodsAdapter.remove(index);
                    SharedPreferencesHelper.getInstance().saveData("ok", "");
                }
            }
            lazyFlag = false;
            pages = 1;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("shop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
