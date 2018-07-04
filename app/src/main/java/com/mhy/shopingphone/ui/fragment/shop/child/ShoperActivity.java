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

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.GooderAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.shoping.ShopingContract;
import com.mhy.shopingphone.model.bean.shop.GoodesBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.presenter.shoping.ShopingPresenter;
import com.mhy.shopingphone.ui.activity.detail.ShopJDDtailsActivity;
import com.mhy.shopingphone.ui.activity.search.SearchActivity;
import com.mhy.shopingphone.ui.activity.search.SearcherActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Call;

public class ShoperActivity extends BaseMVPCompatActivity<ShopingContract.ShopPresenter,
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
    @BindView(R.id.tv_zonghe)
    TextView tv_zonghe;
    @BindView(R.id.iv_zonghe)
    ImageView iv_zonghe;
    @BindView(R.id.iv_discount)
    ImageView ivDiscount;
    @BindView(R.id.rl_discount)
    LinearLayout rlDiscount;
    @BindView(R.id.rl_zonghe)
    LinearLayout rl_zonghe;
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
    private GooderAdapter goodsAdapter;
    //OrderBy //0 综合 1券后价 2券金额 3原价 4销量
    //1折扣正序  2折扣倒序  3价格正序 4价格倒序  5销量正序  6销量倒序
    private boolean flag0, flag1, flag2, flag3;

    private boolean lazyFlag = true;
    private String orderBy = "0";
    private String category = "";
    private String searchName = "";

    private boolean isRefresh = false; //标记是否是 下拉刷新
    private String strTag;
    private int index;
    private GoodesBean.JsonBean infoBean;
    private int index2;
    private String typeName = "";
    private View errorView, loadingView;
    private int type;
    private boolean loading;
    private GoodesBean mInfoBean;
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
            typeName = bundle.getString("typeName");
            searchName = bundle.getString("Name");
            category = bundle.getString("category");

        }
        if (type == 1) {
            alRight.setVisibility(View.GONE);
        }
        LogUtils.e("typeName:" + typeName + "category:" + category + "SearchType:" + "type:" + type);
        if (bundle.getString("titlt") != null && bundle.getString("title").length() > 0) {
            tvTitle.setText(bundle.getString("title"));
        } else if (category != null && category.length() > 0) {
            tvTitle.setText(category);//商品类型名称
        }
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        goodsAdapter = new GooderAdapter(R.layout.item_shop);
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
        return R.layout.activity_shopes;
    }

    //请求
    private void loadGoodsList(final String page, GooderAdapter adapter) {
        mWaitPorgressDialog.show();
        params = new HashMap<>();
        //请求参数
        params.put("Agentid", String.valueOf(SharedPreferencesHelper.getInstance().getData("ShopID", "")));//代理商id
        params.put("OrderBy", orderBy);//排序
        if (!XEmptyUtils.isEmpty(category)) {
            params.put("Category", "");//
        } else {
            params.put("Category", "");//
        }
        if (!XEmptyUtils.isEmpty(searchName)) {
            params.put("Name", searchName);
        } else {
            params.put("Name", "");
        }
        params.put("Page", page);
        LogUtils.e("fengli----" + params);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.SHOPPINGES_URL)
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
                        LogUtils.e("fengli==--" + response);
                        try {
                            hideProgressDialog();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        GoodesBean goodsBean = new Gson().fromJson(response, GoodesBean.class);
                        if (goodsBean.getErrorCode() == 2000 && goodsBean.getJson() != null) {
                            nums = goodsBean.getJson().size();
                            loading = false;
                            if (isRefresh) {
                                isRefresh = false;
                                goodsAdapter.setNewData(goodsBean.getJson());
                            } else {
                                if (goodsAdapter.getData().size() == 0) {
                                    initRecycleView(goodsBean.getJson());
                                } else {
                                    goodsAdapter.addData(goodsBean.getJson());
                                }
                            }
                            if (mP == 1) {
                                mInfoBean = goodsBean;
                            }

                            if (goodsAdapter != null) {
                                goodsAdapter.loadMoreComplete();
                            }
                        } else {
                            try {
                                ic_loading.setVisibility(View.VISIBLE);
                                goodsAdapter.loadMoreComplete();
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }

    @Override
    public void onLoadMoreRequested() {
        mP += 1;
        pages += 1;
//        mPresenter.loadMoreShopList();
        if (nums < 20) {
            goodsAdapter.loadMoreEnd(false);   //没有更多数据了
        } else {
            loadGoodsList(pages + "", goodsAdapter);
        }
    }

    @Override
    public void updateContentList(GoodsBean goodsBean) {
    }

    private void initRecycleView(final List<GoodesBean.JsonBean> list) {
        if (list.size() == 0) {
            ic_loading.setVisibility(View.VISIBLE);
        } else {
            ic_loading.setVisibility(View.GONE);
        }
        goodsAdapter = new GooderAdapter(R.layout.item_shop, list);
        goodsAdapter.setOnLoadMoreListener(this, rvShoping);
        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUpdate();
                } else {
                    index = position;
                    infoBean = (GoodesBean.JsonBean) adapter.getItem(position);
                    Bundle bundle = new Bundle();
                    bundle.putString("goods_id", infoBean.getSkuId());
                    bundle.putSerializable("remen", (Serializable) list);
                    startNewActivity(ShopJDDtailsActivity.class, bundle);
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


    @OnClick({R.id.al_back, R.id.al_right, R.id.rl_discount, R.id.rl_price, R.id.layout_xl, R.id.rl_zonghe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.al_right:
                if (mInfoBean != null && mInfoBean.getJson().size() > 0) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", mInfoBean);
                    startNewActivity(SearcherActivity.class, bundle);
                    finish();
                }
                break;
            case R.id.rl_zonghe:
                if (!flag0) {
                    flag0 = true;
                    flag1 = false;
                    flag2 = false;
                    flag3 = false;
                    tv_zonghe.setTextColor(getResources().getColor(R.color.orangered));
                    iv_zonghe.setImageResource(R.drawable.taoshop_category_filter02);
                    ivDiscount.setImageResource(R.drawable.taoshop_category_filter);
                    tvDiscount.setTextColor(getResources().getColor(R.color.goods));
                    ivPrice.setImageResource(R.drawable.taoshop_category_filter);
                    tvPrice.setTextColor(getResources().getColor(R.color.goods));
                    ivXl.setImageResource(R.drawable.taoshop_category_filter);
                    tvXl.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "0";
                } else {
                    flag0 = false;
                    iv_zonghe.setImageResource(R.drawable.taoshop_category_filter);
                    tv_zonghe.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "0";
                }
                break;
            case R.id.rl_discount://折扣
//                mWaitPorgressDialog.show();
                if (!flag1) {
                    flag0 = false;
                    flag1 = true;
                    flag2 = false;
                    flag3 = false;
                    iv_zonghe.setImageResource(R.drawable.taoshop_category_filter);
                    tv_zonghe.setTextColor(getResources().getColor(R.color.goods));
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
                    flag0 = false;
                    flag1 = false;
                    flag2 = true;
                    flag3 = false;
                    iv_zonghe.setImageResource(R.drawable.taoshop_category_filter);
                    tv_zonghe.setTextColor(getResources().getColor(R.color.goods));
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
                    orderBy = "3";
                }

                break;
            case R.id.layout_xl://销量
//                mWaitPorgressDialog.show();
                if (!flag3) {
                    flag0 = false;
                    flag1 = false;
                    flag2 = false;
                    flag3 = true;
                    iv_zonghe.setImageResource(R.drawable.taoshop_category_filter);
                    tv_zonghe.setTextColor(getResources().getColor(R.color.goods));
                    ivXl.setImageResource(R.drawable.taoshop_category_filter02);
                    tvXl.setTextColor(getResources().getColor(R.color.orangered));
                    ivPrice.setImageResource(R.drawable.taoshop_category_filter);
                    tvPrice.setTextColor(getResources().getColor(R.color.goods));
                    ivDiscount.setImageResource(R.drawable.taoshop_category_filter);
                    tvDiscount.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "4";
                } else {
                    flag3 = false;
                    ivXl.setImageResource(R.drawable.taoshop_category_filter);
                    tvXl.setTextColor(getResources().getColor(R.color.goods));
                    orderBy = "4";
                }
                break;
        }
        goodsAdapter.removeAllHeaderView();
        loadGoodsList("1", null);
        isRefresh = true;
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
