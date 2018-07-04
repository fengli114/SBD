package com.mhy.shopingphone.ui.activity.order;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StatusBarUtils;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.MyOrderAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.order.MyOrderContract;
import com.mhy.shopingphone.model.bean.AndroidBean;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.mhy.shopingphone.presenter.order.MyOrderPresenter;
import com.mhy.shopingphone.ui.activity.detail.GoodsDtailActivity;
import com.mhy.shopingphone.ui.activity.detail.ShopDtailsActivity;
import com.youth.xframe.utils.log.XLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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
import me.yokeyword.fragmentation.SupportFragment;
import okhttp3.Call;

public class MyOrderActivity extends BaseMVPCompatActivity<MyOrderContract.MyOrderPresenter,
        MyOrderContract.IMyOrderModel> implements MyOrderContract.IMyOrderView, BaseQuickAdapter
        .RequestLoadMoreListener {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.iv_kefu)
    ImageView ivKefu;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_my_order)
    RecyclerView rvMyOrder;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrClassicFrameLayout mPtrFrame;

    private Map<String, String> params;
    private boolean isRefresh = false; //标记是否是 下拉刷新;
    private String paramsstring;
    private MyOrderAdapter mAdapter;
    private int mP = 1;
    private View errorView, loadingView;
    private int pages;
    private int nums;
    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        mAdapter = new MyOrderAdapter(R.layout.item_my_order_);
        rvMyOrder.setLayoutManager(new LinearLayoutManager(this));
        rvMyOrder.setAdapter(mAdapter);

        loadingView = getLayoutInflater().inflate(R.layout.view_loading, rvMyOrder, false);
        params = new HashMap<>();
        mAdapter.setEmptyView(loadingView);
        loadMyOrderList(1, null);
//        mPresenter.loadOrderList(paramsstring);

        errorView = getLayoutInflater().inflate(R.layout.view_network_error, rvMyOrder, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMyOrderList(1, null);
//                mPresenter.loadOrderList(paramsstring);
            }
        });


        mPtrFrame.setLastUpdateTimeRelateObject(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                loadMyOrderList(1, null);
//                mPresenter.loadOrderList(paramsstring);
                isRefresh = true;
                mPtrFrame.refreshComplete();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });

    }

    private void loadMyOrderList(int page, MyOrderAdapter adapyer) {
        //请求参数
        params.put("ParentId", Contant.PARENTID);//代理商id
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));//SharedPreferencesHelper.getInstance().saveData("Mobile", jsonObject.getString("Mobile"));//手机号码
        params.put("Page", page + "");

        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.SHOPINDENT_URL)
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (mAdapter != null) {
                            mAdapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        MyOrderBean bindUserEntity = new Gson().fromJson(response, MyOrderBean.class);
                          nums = bindUserEntity.getJson().size();
                        if (bindUserEntity.getErrorCode() == 2000) {
                            List<MyOrderBean.JsonBean> list = bindUserEntity.getJson();
                            if (isRefresh) {
                                isRefresh = false;
                                mP = 1;
                                mAdapter.setNewData(list);
                            } else {
                                if (mAdapter.getData().size() == 0) {
                                    initRecycleView(list);
                                } else {
                                    mAdapter.addData(list);
                                }
                            }
                            if (mAdapter != null) {
                                mAdapter.loadMoreComplete();
                            }
                        } else if (bindUserEntity.getErrorCode() == 1005) {
                            Map<String, String> parames = new HashMap<>();
                            parames.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
                            parames.put("ParentId", String.valueOf(Contant.PARENTID));
                            OkHttpUtils.post()
                                    .url(Api.NEWGOODS + Api.TOKEN_URL)
                                    .params(parames)
                                    .build()
                                    .execute(new StringCallback() {
                                        @Override
                                        public void onError(Call call, Exception e, int id) {

                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                                            if (ceshi.getErrorCode() == 2000) {
                                                SharedPreferencesHelper.getInstance().saveData("Tokens", ceshi.getData());//代理Id
                                                loadMyOrderList(1, null);
                                            } else {
                                                ToastUtils.showToast("网络异常");
                                            }
                                        }
                                    });
                        } else {
                            mAdapter.loadMoreComplete();
                        }

                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_order;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return MyOrderPresenter.newInstance();
    }


    @OnClick(R.id.al_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onLoadMoreRequested() {
//        mAdapter.loadMoreComplete();
        mP += 1;
//        mAdapter.loadMoreComplete();
        pages += 1;
        if (nums < 10) {
            mAdapter.loadMoreEnd(false);   //没有更多数据了
        } else {
            loadMyOrderList(pages, mAdapter);
        }
//        loadMyOrderList(pages, mAdapter);
//        mPresenter.loadMoreMyOrderList(paramsstring);
    }


    @Override
    public void updateContentList(List<MyOrderBean.JsonBean> list) {
        //        Logger.e(list.toString());
    }

    private void initRecycleView(final List<MyOrderBean.JsonBean> list) {
        mAdapter = new MyOrderAdapter(R.layout.item_my_order_, list);
        mAdapter.setOnLoadMoreListener(this, rvMyOrder);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                mPresenter.onItemClick(position, (MyOrderBean.UserBean) adapter.getItem(position));
               /* MyOrderBean.JsonBean infoBean = (MyOrderBean.JsonBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("goods_id", infoBean.getCid());
                startNewActivity(ShopDtailsActivity.class, bundle);*/
            }
        });
        rvMyOrder.setAdapter(mAdapter);
    }

    @Override
    public void showLoadMoreError() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd(false);
    }

    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
    }

}
