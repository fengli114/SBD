package com.mhy.shopingphone.ui.activity.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseRecycleFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.ResourcesUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.GoodsAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.details.GoodsDtailContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopDetailBean;
import com.mhy.shopingphone.presenter.shop.ShopPresenter;
import com.mhy.shopingphone.ui.fragment.shop.child.ShopFragment;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述： 商品详情
 */

public class ShopxiangqingActivity extends BaseRecycleFragment<GoodsDtailContract
        .GoodsDtailPresenter, GoodsDtailContract.IGoodsDtailModel> implements GoodsDtailContract.IGoodsDtailView , BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.rv_shop)
    RecyclerView rv_shop;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrClassicFrameLayout mPtrFrame;

    private GoodsAdapter goodsAdapter;
    private Map<String, String> params;
    private int mP = 1;
    private View headView;
    private Banner mBannerView;
    private String scollText;
    private boolean isRefresh = false; //标记是否是 下拉刷新
    private boolean lazyFlag = true;
    private String strTag;
    private int index;
    private GoodsBean.JsonBean.CommoditiesBean infoBean;
    private List<GoodsBean.JsonBean.CategoriesBean> listInfo;
    private GoodsBean mInfoBean;
    private Map<String, String> paramsBanner;
    private String paramstr;
    private RelativeLayout al_back;   //返回按钮
    private TextView tv_gd_tite;      //商品名称
    private TextView tv_gd_price;       //淘宝价格
    private TextView tv_gd_discount;     //可折扣价格
    private TextView tv_gd_xl;            //销量
    private LinearLayout rl_gd_detail;     //商品详情
    private LinearLayout rl_now_buy;     //购买按钮

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
        return R.layout.activity_xiangqing;
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        tv_advbanner.init(mActivity.getWindowManager());

        loadGoodsList();
//        mPresenter.loadGoodsList();
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                mPresenter.loadGoodsList();
                isRefresh = true;
                mPtrFrame.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    //请求
    private void loadGoodsList() {
        params = new HashMap<>();
        //请求参数
        params.put("Agentid", String.valueOf(SharedPreferencesHelper.getInstance().getData("ShopID", "")));//代理商id
        params.put("UserID", String.valueOf(SharedPreferencesHelper.getInstance().getData("UserId", "")));//代理商id
        params.put("OrderBy", "5");//排序
        params.put("Category", "");//
        params.put("SearchType", "4");//分类类型
        params.put("Name", "");

        String paramsstring = StringUtil.mapToJson(params);
        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.GOODS_URLHead;
        HttpUtils.LogHeadStr = "ShopFragment商品:";
        Contant.IsDebug = true;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        Util.setMarginsStatusBar(mContext, tou);
        paramsBanner = new HashMap<>();
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        goodsAdapter = new GoodsAdapter(R.layout.item_shop);
        rv_shop.setAdapter(goodsAdapter);
//        rv_shop.setLayoutManager(new GridLayoutManager(mActivity,2));
        //getItemCount()返回值<=0,要设置LinearLayoutManager，否则后面数据更新RecycleView也不执行onBindViewHolder;
        rv_shop.setLayoutManager(new LinearLayoutManager(mActivity));

    }


  /*  @Override
    public void updateContentList(GoodsBean infoBean) {

        if (!infoBean.getCode().equals("0")) {
            ToastUtils.showToast(infoBean.getMess());
            return;
        }
        if (isRefresh) {
            isRefresh = false;
            goodsAdapter.setNewData(infoBean.getCInfo());
        } else {
            if (goodsAdapter.getData().size() == 0) {
                initRecycleView(infoBean.getCInfo());
            } else {
                goodsAdapter.addData(infoBean.getCInfo());
            }
        }
        initHeadViews(infoBean);
    }*/

    //初始化 头部控件
    private void initHeadViews(GoodsBean infoBean) {
        if (mP == 1) {
            mInfoBean = infoBean;
            List<String> strings = new ArrayList<>();
            for (GoodsBean.JsonBean.ShopTextsBean bean : infoBean.getJson().getShopTexts()) {
                if (bean.getType() == 2) {
                    scollText = bean.getText();
                } else {
                    strings.add(Api.GOODS + bean.getPic());
                }
            }
            Util.setBanner(null, strings, mBannerView);//设置轮播图

            listInfo = infoBean.getJson().getCategories();
        }
    }


    @Override
    public void onLoadMoreRequested() {
        goodsAdapter.loadMoreComplete();
        mP += 1;
//        mPresenter.loadMoreShopList();
    }

 /*   @Override
    public void showNoMoreData() {
        goodsAdapter.loadMoreEnd(false);
    }
*/
/*
    @Override
    public void showLoadMoreError() {
        goodsAdapter.loadMoreFail();
    }
*/

    @Override
    public void update(Ceshi list) {

    }

    @Override
    public void showDetails(ShopDetailBean list) {

    }

    @Override
    public void showNetworkError() {
        goodsAdapter.setEmptyView(errorView);
    }

    @Override
    protected void onErrorViewClick(View view) {
        //设置头部
//        mPresenter.loadGoodsList();
    }

    @Override
    protected void showLoading() {
        goodsAdapter.setEmptyView(loadingView);
    }

    private void initRecycleView(List<GoodsBean.JsonBean.CommoditiesBean> list) {
        goodsAdapter = new GoodsAdapter(R.layout.item_shop, list);
        goodsAdapter.setOnLoadMoreListener(this, rv_shop);
        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                index = position;
                infoBean = (GoodsBean.JsonBean.CommoditiesBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("goods_id", infoBean.getId());
                startNewActivity(ShopDtailsActivity.class, bundle);
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
            headView = ResourcesUtils.inflate(R.layout.activity_goods_dtail);
        }
        mBannerView = headView.findViewById(R.id.banner_shopping_mall);    //详情图片
        al_back = headView.findViewById(R.id.al_back);                         //返回按钮
        tv_gd_tite = headView.findViewById(R.id.tv_gd_tite);                     //商品内容
        tv_gd_price = headView.findViewById(R.id.tv_gd_price);                  //淘宝价格
        tv_gd_discount = headView.findViewById(R.id.tv_gd_discount);            //可折扣价格
        tv_gd_xl = headView.findViewById(R.id.tv_gd_xl);                          //销量
        rl_gd_detail = headView.findViewById(R.id.rl_gd_detail);                   //商品详情
        rl_now_buy = headView.findViewById(R.id.rl_now_buy);                      //购买按钮

    }

    @Override
    public void onResume() {
        super.onResume();
        if (!lazyFlag) {
            strTag = (String) SharedPreferencesHelper.getInstance().getData("ok", "");
            if (strTag.equals("ok")) {
                goodsAdapter.remove(index);
                SharedPreferencesHelper.getInstance().saveData("ok", "");
            }
        }
        lazyFlag = false;
    }
}
