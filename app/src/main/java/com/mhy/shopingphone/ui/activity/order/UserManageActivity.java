package com.mhy.shopingphone.ui.activity.order;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.UserManageAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.order.MyOrderContract;
import com.mhy.shopingphone.model.bean.UserGuanliBean;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.mhy.shopingphone.model.qiyeguanjia.Logginges;
import com.mhy.shopingphone.presenter.order.MyOrderPresenter;
import com.mhy.shopingphone.ui.activity.SogoAmenddeletiActivity;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

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

/**
 * 用户管理
 */
public class UserManageActivity extends BaseMVPCompatActivity<MyOrderContract.MyOrderPresenter,
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
    @BindView(R.id.tv_shuaxin)
    TextView tv_shuaxin;
    @BindView(R.id.rl_search_shopping)
    LinearLayout rl_search_shopping;
    @BindView(R.id.tv_exit)
    TextView tv_exit;
    @BindView(R.id.edit_search)
    EditText edit_search;
    @BindView(R.id.tv_numbers)
    TextView tv_numbers;
    private boolean isRefresh = false; //标记是否是 下拉刷新;
    private String paramsstring;
    private UserManageAdapter mAdapter;
    private int mP = 1;
    private View errorView, loadingView;
    private int pages;
    private int nums;
    private Logginges userBean;
    private String sres;

    @Override

    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        sres = "";
        userBean = (Logginges) getIntent().getSerializableExtra("something");
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

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter = new UserManageAdapter(R.layout.item_sogo_manage);
        rvMyOrder.setLayoutManager(new LinearLayoutManager(this));
        rvMyOrder.setAdapter(mAdapter);

        loadingView = getLayoutInflater().inflate(R.layout.view_loading, rvMyOrder, false);

        mAdapter.setEmptyView(loadingView);
        loadMyOrderList(1, null);

        errorView = getLayoutInflater().inflate(R.layout.view_network_error, rvMyOrder, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMyOrderList(1, null);
            }
        });
    }

    private void loadMyOrderList(int page, UserManageAdapter adapyer) {
        mWaitPorgressDialog.show();
        //请求参数
        Map<String, String> params = new HashMap<>();
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));//用户id
        params.put("curPage", page + "");
        params.put("username", sres);
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.YHGUANLI_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        LogUtils.e("fengli---123" + e.toString());
                        hideProgressDialog();
                        if (mAdapter != null) {
                            mAdapter.loadMoreComplete();
                        }
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("femgli--" + response);
                        hideProgressDialog();
                        try {
                            UserGuanliBean bindUserEntity = new Gson().fromJson(response, UserGuanliBean.class);
                            if (bindUserEntity.getErrorCode() == 2000) {
                                if (bindUserEntity.getJson() != null) {
                                    if (bindUserEntity.getJson().getUsers() != null && bindUserEntity.getJson().getUsers().size() > 0) {
                                        List<UserGuanliBean.JsonBean.UsersBean> list = bindUserEntity.getJson().getUsers();
                                        nums = list.size();
                                        tv_numbers.setText(bindUserEntity.getJson().getTotlenum()+"条");
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
                                    }
                                } else {
                                    mAdapter.loadMoreEnd(false);   //没有更多数据了
                                }
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_manage;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return MyOrderPresenter.newInstance();
    }

    @OnClick({R.id.al_back, R.id.tv_shuaxin, R.id.tv_exit})
    public void onViewClicked(View v) {
        switch (v.getId()) {
            //返回
            case R.id.al_back:
                finish();
                break;
            case R.id.tv_shuaxin:
                mAdapter = new UserManageAdapter(R.layout.item_sogo_manage);
                rvMyOrder.setLayoutManager(new LinearLayoutManager(this));
                rvMyOrder.setAdapter(mAdapter);

             /*   loadingView = getLayoutInflater().inflate(R.layout.view_loading, rvMyOrder, false);

                mAdapter.setEmptyView(loadingView);*/
                loadMyOrderList(1, null);
                break;
            case R.id.tv_exit:
                mAdapter = new UserManageAdapter(R.layout.item_user_icards);
                rvMyOrder.setLayoutManager(new LinearLayoutManager(this));
                rvMyOrder.setAdapter(mAdapter);

                if (edit_search.getText().toString() != null) {
                    sres = edit_search.getText().toString();
                } else {
                    sres = "";
                }
                loadMyOrderList(1, null);
                break;
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mP += 1;
        pages += 1;
        if (nums < 10) {
            mAdapter.loadMoreEnd(false);   //没有更多数据了
        } else {
            loadMyOrderList(pages, mAdapter);
        }
    }


    @Override
    public void updateContentList(List<MyOrderBean.JsonBean> list) {
        //        Logger.e(list.toString());
    }

    private void initRecycleView(final List<UserGuanliBean.JsonBean.UsersBean> list) {
        mAdapter = new UserManageAdapter(R.layout.item_user_icards, list);
        mAdapter.setOnLoadMoreListener(this, rvMyOrder);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            /*    Intent intent = new Intent(UserManageActivity.this, SogoAmenddeletiActivity.class);
                intent.putExtra("person", userBean);
                startActivity(intent);*/

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
