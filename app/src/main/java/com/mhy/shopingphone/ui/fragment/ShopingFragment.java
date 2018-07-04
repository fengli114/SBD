package com.mhy.shopingphone.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseRecycleFragment;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.ResourcesUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.GoodsAdapter;
import com.mhy.shopingphone.contract.shoping.ShopingContract;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.presenter.shoping.ShopingPresenter;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;

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

/**
 * 作者： "RedRainM" on 2017/12/29 0029.
 * 描述：
 */

public class ShopingFragment extends BaseRecycleFragment<ShopingContract.ShopPresenter,
        ShopingContract.IShopModel> implements ShopingContract.IShopView, BaseQuickAdapter
        .RequestLoadMoreListener {
    @BindView(R.id.rv_shoping)
    RecyclerView rvShoping;
    Unbinder unbinder;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.iv_discount)
    ImageView ivDiscount;
    @BindView(R.id.rl_discount)
    LinearLayout rlDiscount;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.iv_price)
    ImageView ivPrice;
    @BindView(R.id.rl_price)
    LinearLayout rlPrice;
    @BindView(R.id.tv_xl)
    TextView tvXl;
    @BindView(R.id.iv_xl)
    ImageView ivXl;
    @BindView(R.id.layout_xl)
    LinearLayout layoutXl;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    Unbinder unbinder1;
    private Map<String, String> params;
    private String paramstr;
    private int mP = 1;
    private GoodsAdapter goodsAdapter;
    private View headView;
    //1折扣正序  2折扣倒序  3价格正序 4价格倒序  5销量正序  6销量倒序
    private boolean flag1, flag2, flag3;

    private boolean lazyFlag = true;
    private String orderBy = "6";
    private String category = "";
    private String searchName = "";
    private int SearchType;

    private boolean isRefresh = false; //标记是否是 下拉刷新
    private String strTag;
    private int index;
    private GoodsBean.JsonBean.CommoditiesBean infoBean;
    private int index2;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSearchType(int SearchType) {
        this.SearchType = SearchType;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public static ShopingFragment newInstance() {
        Bundle args = new Bundle();
        ShopingFragment fragment = new ShopingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ShopingPresenter.newInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_shoping;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!lazyFlag) {
            strTag = (String) SharedPreferencesHelper.getInstance().getData("ok", "");
            if (strTag.equals("ok")) {
                index2 = index;
                SharedPreferencesHelper.getInstance().saveData("ok", "");
            }
        }
        lazyFlag = false;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        params = new HashMap<>();
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        goodsAdapter = new GoodsAdapter(R.layout.item_shop);
        rvShoping.setAdapter(goodsAdapter);
//        rv_shop.setLayoutManager(new GridLayoutManager(mActivity,2));
        //getItemCount()返回值<=0,要设置LinearLayoutManager，否则后面数据更新RecycleView也不执行onBindViewHolder;
        rvShoping.setLayoutManager(new LinearLayoutManager(mActivity));
        loadGoodsList(1);
        mPresenter.loadGoodsList();

        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadGoodsList(1);
                mPresenter.loadGoodsList();
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
    private void loadGoodsList(int page) {
        //请求参数
        params.put("Agentid", String.valueOf(SharedPreferencesHelper.getInstance().getData("ShopID", "")));//代理商id
        params.put("UserID", String.valueOf(SharedPreferencesHelper.getInstance().getData("UserId", "")));//代理商id
        params.put("OrderBy", orderBy);//排序
        if (!XEmptyUtils.isEmpty(category)) {
            params.put("Category", category);//
        } else {
            params.put("Category", "");//
        }
        params.put("SearchType", SearchType + "");//分类类型
        params.put("Page", page + "");
        if (!XEmptyUtils.isEmpty(searchName)) {
            params.put("Name", searchName);
        } else {
            params.put("Name", "");
        }
        LogUtils.e("SHANGPIN:" + params.toString());
        String paramsstring = StringUtil.mapToJson(params);
        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.GOODS_URLHead;
        HttpUtils.LogHeadStr = "ShopingFragment商品:";
        Contant.IsDebug = true;
    }

    @Override
    public void onLoadMoreRequested() {
        mP += 1;
        goodsAdapter.loadMoreComplete();
        loadGoodsList(mP);
        mPresenter.loadMoreShopList();
    }

    @Override
    public void updateContentList(GoodsBean list) {
        if (isRefresh) {
            isRefresh = false;
            goodsAdapter.setNewData(list.getJson().getCommodities());
        } else {
            if (goodsAdapter.getData().size() == 0) {
                initRecycleView(list.getJson().getCommodities());
            } else {
                goodsAdapter.addData(list.getJson().getCommodities());
            }
        }
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }


    @Override
    public void showNoMoreData() {
        goodsAdapter.loadMoreEnd(true);
    }

    @Override
    public void showLoadMoreError() {
        goodsAdapter.loadMoreFail();
    }

    @Override
    public void showNetworkError() {
        goodsAdapter.setEmptyView(errorView);
    }

    private void initRecycleView(List<GoodsBean.JsonBean.CommoditiesBean> list) {
        goodsAdapter = new GoodsAdapter(R.layout.item_shop, list);
        goodsAdapter.setOnLoadMoreListener(this, rvShoping);
        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                index = position;
                infoBean = (GoodsBean.JsonBean.CommoditiesBean) adapter.getItem(position);
                if (index2 == position) {
                    infoBean.setDatastatus(true);
                }
                mPresenter.onItemClick(position + 1, infoBean,
                        (ImageView) view.findViewById(R.id.iv_goods_icon));
//
            }
        });
//        initHeadView();
//        goodsAdapter.addHeaderView(headView);
        rvShoping.setAdapter(goodsAdapter);
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        rvShoping.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));

    }

    private void initHeadView() {
        if (headView == null) {
            headView = ResourcesUtils.inflate(R.layout.include_shoping_layout);
        }
//        mBannerView = headView.findViewById(R.id.banner_shopping_mall);
//        tvADV = headView.findViewById(R.id.tv_adv);
//        headView.findViewById(R.id.rl_shopping_all).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startNewActivity(GoodsActivity.class);
//            }
//        });
    }

    @Override
    protected void onErrorViewClick(View view) {
        //设置头部
        loadGoodsList(1);
        mPresenter.loadGoodsList();
    }

    @Override
    protected void showLoading() {
        goodsAdapter.setEmptyView(loadingView);
    }

    @OnClick({R.id.rl_discount, R.id.rl_price, R.id.layout_xl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rl_discount://折扣
                if (!flag1) {
                    flag1 = true;
                    ivDiscount.setImageResource(R.drawable.ascend);
                    tvDiscount.setTextColor(getResources().getColor(R.color.orangered));
                    ivPrice.setImageResource(R.drawable.descend_1);
                    tvPrice.setTextColor(getResources().getColor(R.color.goods));
                    ivXl.setImageResource(R.drawable.descend_1);
                    tvXl.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "1";
                } else {
                    flag1 = false;
                    ivDiscount.setImageResource(R.drawable.descend_1);
                    tvDiscount.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "2";
                }
                break;
            case R.id.rl_price://价格
                if (!flag2) {
                    flag2 = true;
                    ivPrice.setImageResource(R.drawable.ascend);
                    tvPrice.setTextColor(getResources().getColor(R.color.orangered));
                    ivDiscount.setImageResource(R.drawable.descend_1);
                    tvDiscount.setTextColor(getResources().getColor(R.color.goods));
                    ivXl.setImageResource(R.drawable.descend_1);
                    tvXl.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "3";
                } else {
                    flag2 = false;
                    ivPrice.setImageResource(R.drawable.descend_1);
                    tvPrice.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "4";

                }

                break;
            case R.id.layout_xl://销量
                if (!flag3) {
                    flag3 = true;
                    ivXl.setImageResource(R.drawable.ascend);
                    tvXl.setTextColor(getResources().getColor(R.color.orangered));
                    ivPrice.setImageResource(R.drawable.descend_1);
                    tvPrice.setTextColor(getResources().getColor(R.color.goods));
                    ivDiscount.setImageResource(R.drawable.descend_1);
                    tvDiscount.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "5";
                } else {
                    flag3 = false;
                    ivXl.setImageResource(R.drawable.descend_1);
                    tvXl.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "6";
                }
                break;
        }
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        goodsAdapter = new GoodsAdapter(R.layout.item_shop);
        rvShoping.setAdapter(goodsAdapter);
//        rv_shop.setLayoutManager(new GridLayoutManager(mActivity,2));
        //getItemCount()返回值<=0,要设置LinearLayoutManager，否则后面数据更新RecycleView也不执行onBindViewHolder;
        rvShoping.setLayoutManager(new LinearLayoutManager(mActivity));
        loadGoodsList(1);
        mPresenter.loadGoodsList();
        mP = 1;
//        goodsAdapter.notifyDataSetChanged();
    }
}
