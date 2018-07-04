package com.mhy.shopingphone.ui.fragment.shop.child;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mhy.sdk.RxManager;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.GoodsAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.shoping.ShopingContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.presenter.shoping.ShopingPresenter;
import com.mhy.shopingphone.ui.activity.detail.ShopDtailsActivity;
import com.mhy.shopingphone.ui.activity.search.SearchActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;

public class ShopActivity extends BaseMVPCompatActivity<ShopingContract.ShopPresenter,
        ShopingContract.IShopModel> implements ShopingContract.IShopView, BaseQuickAdapter
        .RequestLoadMoreListener {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.al_right)
    RelativeLayout alRight;
    @BindView(R.id.title)
    LinearLayout title;
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
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.layout_xl)
    LinearLayout layoutXl;
    @BindView(R.id.rv_shoping)
    RecyclerView rvShoping;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @BindView(R.id.ic_loading)
    LinearLayout ic_loading;
    private Map<String, String> params;
    private String paramstr;
    private int mP = 1;
    private GoodsAdapter goodsAdapter;
    //1折扣正序  2折扣倒序  3价格正序 4价格倒序  5销量正序  6销量倒序
    private boolean flag1, flag2, flag3;

    private boolean lazyFlag = true;
    private String orderBy = "6";
    private String category = "";
    private String searchName = "";

    private boolean isRefresh = false; //标记是否是 下拉刷新
    private String strTag;
    private int index;
    private GoodsBean.JsonBean.CommoditiesBean infoBean;
    private int index2;
    private int search_type;
    private String typeName = "";
    private View errorView, loadingView;
    private int type;
    private boolean loading;
    private GoodsBean mInfoBean;
    private int pages;
    private int nums;

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
        pages = 1;
        lazyFlag = false;
    }

    public Map<String, String> getParams() {
        return params;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type", 0);
            search_type = bundle.getInt("SearchType", 0);
//            search_type = bundle.getString("SearchType");
            typeName = bundle.getString("typeName");
            searchName = bundle.getString("Name");
            category = bundle.getString("category");

        }
        if (type == 1) {
            alRight.setVisibility(View.GONE);
        }
        LogUtils.e("typeName:" + typeName + "category:" + category + "SearchType:" + search_type + "type:" + type);
        if (bundle.getString("titlt") != null && bundle.getString("title").length() > 0) {
            tvTitle.setText(bundle.getString("title"));
        } else if (category != null && category.length() > 0) {
            tvTitle.setText(category);//商品类型名称
        }
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        goodsAdapter = new GoodsAdapter(R.layout.item_shop);
        rvShoping.setLayoutManager(new LinearLayoutManager(mContext));
        rvShoping.setAdapter(goodsAdapter);
        loadingView = getLayoutInflater().inflate(R.layout.view_loading, rvShoping, false);
        goodsAdapter.setEmptyView(loadingView);

        errorView = getLayoutInflater().inflate(R.layout.view_network_error, rvShoping, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                mPresenter.loadGoodsList();
                loadGoodsList("1", null);
            }
        });
        loadGoodsList("1", null);
//        rv_shop.setLayoutManager(new GridLayoutManager(mActivity,2));
        //getItemCount()返回值<=0,要设置LinearLayoutManager，否则后面数据更新RecycleView也不执行onBindViewHolder;

//        mPresenter.loadGoodsList();
        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mP = 1;
//                mPresenter.loadGoodsList();
                loadGoodsList("1", null);
                isRefresh = true;
                mPtrFrame.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ShopingPresenter.newInstance();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop;
    }

    //请求
    private void loadGoodsList(final String page, GoodsAdapter adapter) {
        mWaitPorgressDialog.show();
        params = new HashMap<>();
        //请求参数
        params.put("Agentid", String.valueOf(SharedPreferencesHelper.getInstance().getData("ShopID", "")));//代理商id
//        params.put("UserID", String.valueOf(SharedPreferencesHelper.getInstance().getData("UserId", "")));//代理商id
        params.put("OrderBy", orderBy);//排序
        if (!XEmptyUtils.isEmpty(category)) {
            params.put("Category", category);//
        } else {
            params.put("Category", "");//
        }
        params.put("SearchType", search_type + "");//分类类型
//        params.put("Page", page + "");
        if (!XEmptyUtils.isEmpty(searchName)) {
            params.put("Name", searchName);
        } else {
            params.put("Name", "");
        }
        params.put("Page", page);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.SHOPPING_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        try {
                            hideProgressDialog();
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                        ic_loading.setVisibility(View.VISIBLE);
                        if (goodsAdapter != null) {
                            goodsAdapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli-----" + response);
                        try {
                            hideProgressDialog();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        GoodsBean goodsBean = new Gson().fromJson(response, GoodsBean.class);
                        nums = goodsBean.getJson().getCommodities().size();
                        if (goodsBean.getErrorCode() != 2000) {
                            ic_loading.setVisibility(View.VISIBLE);
                            goodsAdapter.loadMoreComplete();
                            ToastUtils.showToast(goodsBean.getData());
                            return;
                        }

                        loading = false;
                        if (isRefresh) {
                            isRefresh = false;
                            goodsAdapter.setNewData(goodsBean.getJson().getCommodities());
                        } else {
                            if (goodsAdapter.getData().size() == 0) {
                                initRecycleView(goodsBean.getJson().getCommodities());
                            } else {
                                goodsAdapter.addData(goodsBean.getJson().getCommodities());
                            }
                        }
                        if (mP == 1) {
                            mInfoBean = goodsBean;
                        }

                        if (goodsAdapter != null) {
                            goodsAdapter.loadMoreComplete();
                        }
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        mP += 1;
        pages += 1;
//        mPresenter.loadMoreShopList();
        if (nums < 10) {
            goodsAdapter.loadMoreEnd(false);   //没有更多数据了
        } else {
            loadGoodsList(pages + "", goodsAdapter);
        }
    }

    @Override
    public void updateContentList(GoodsBean goodsBean) {
    }

    private void initRecycleView(final List<GoodsBean.JsonBean.CommoditiesBean> list) {
        if (list.size() == 0) {
            ic_loading.setVisibility(View.VISIBLE);
        } else {
            ic_loading.setVisibility(View.GONE);
        }
        goodsAdapter = new GoodsAdapter(R.layout.item_shop, list);
        goodsAdapter.setOnLoadMoreListener(this, rvShoping);
        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUpdate();
                } else {
                    index = position;
                    infoBean = (GoodsBean.JsonBean.CommoditiesBean) adapter.getItem(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("goods_id", infoBean.getId());
                    bundle.putSerializable("remen", (Serializable) list);
                    startNewActivity(ShopDtailsActivity.class, bundle);
                }
            }
        });
        rvShoping.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
        rvShoping.setAdapter(goodsAdapter);
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流

    }

    @Override
    public void showNoMoreData() {
        goodsAdapter.loadMoreEnd(false);
    }

    @Override
    public void showLoadMoreError() {
        goodsAdapter.loadMoreFail();    //加载失败
    }

    @Override
    public void showNetworkError() {
        goodsAdapter.setEmptyView(errorView);
    }


    @OnClick({R.id.al_back, R.id.al_right, R.id.rl_discount, R.id.rl_price, R.id.layout_xl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.al_right:
                if (mInfoBean != null && mInfoBean.getJson().getCommodities().size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", mInfoBean);
                    startNewActivity(SearchActivity.class, bundle);
                    finish();
                }
                break;
            case R.id.rl_discount://折扣
//                mWaitPorgressDialog.show();
                if (!flag1) {
                    flag1 = true;
                    flag2 = false;
                    flag3 = false;
                    ivDiscount.setImageResource(R.drawable.taoshop_category_filter02);
                    tvDiscount.setTextColor(getResources().getColor(R.color.orangered));
                    ivPrice.setImageResource(R.drawable.taoshop_category_filter);
                    tvPrice.setTextColor(getResources().getColor(R.color.goods));
                    ivXl.setImageResource(R.drawable.taoshop_category_filter);
                    tvXl.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "1";
                } else {
                    flag1 = false;
                    ivDiscount.setImageResource(R.drawable.taoshop_category_filter);
                    tvDiscount.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "2";
                }
                break;
            case R.id.rl_price://价格
//                mWaitPorgressDialog.show();
                if (!flag2) {
                    flag1 = false;
                    flag2 = true;
                    flag3 = false;
                    ivPrice.setImageResource(R.drawable.taoshop_category_filter02);
                    tvPrice.setTextColor(getResources().getColor(R.color.orangered));
                    ivDiscount.setImageResource(R.drawable.taoshop_category_filter);
                    tvDiscount.setTextColor(getResources().getColor(R.color.goods));
                    ivXl.setImageResource(R.drawable.taoshop_category_filter);
                    tvXl.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "3";
                } else {
                    flag2 = false;
                    ivPrice.setImageResource(R.drawable.taoshop_category_filter);
                    tvPrice.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "4";
                }

                break;
            case R.id.layout_xl://销量
//                mWaitPorgressDialog.show();
                if (!flag3) {
                    flag1 = false;
                    flag2 = false;
                    flag3 = true;
                    ivXl.setImageResource(R.drawable.taoshop_category_filter02);
                    tvXl.setTextColor(getResources().getColor(R.color.orangered));
                    ivPrice.setImageResource(R.drawable.taoshop_category_filter);
                    tvPrice.setTextColor(getResources().getColor(R.color.goods));
                    ivDiscount.setImageResource(R.drawable.taoshop_category_filter);
                    tvDiscount.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "5";
                } else {
                    flag3 = false;
                    ivXl.setImageResource(R.drawable.taoshop_category_filter);
                    tvXl.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "6";
                }
                break;
        }
        goodsAdapter.removeAllHeaderView();
        loadGoodsList("1", null);
        isRefresh = true;
//        mPresenter.loadGoodsList();
        ;
    }

    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
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

}
