package com.mhy.shopingphone.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.ImageUploadAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.order.MyOrderContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.mhy.shopingphone.model.qiyeguanjia.Modelers;
import com.mhy.shopingphone.presenter.order.MyOrderPresenter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Call;


/**
 * 上传图片
 */

public class ImageUploadActivity extends BaseMVPCompatActivity<MyOrderContract.MyOrderPresenter,
        MyOrderContract.IMyOrderModel> implements MyOrderContract.IMyOrderView, BaseQuickAdapter
        .RequestLoadMoreListener, View.OnClickListener {
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.order_back)
    RelativeLayout order_back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_my_order)
    RecyclerView rvMyOrder;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @BindView(R.id.btn_shangchuan)
    Button btn_shangchuan;
    @BindView(R.id.btn_queding)
    Button btn_queding;
    @BindView(R.id.et_wenzititle)
    EditText et_wenzititle;
    @BindView(R.id.et_urlterset)
    EditText et_urlterset;
    private ImageView iv_delite;
    private Map<String, String> params;
    private boolean isRefresh = false; //标记是否是 下拉刷新;
    private String paramsstring;
    private ImageUploadAdapter mAdapter;
    private int mP = 1;
    private View errorView, loadingView;
    private int pages;
    private int nums;

    @Override
    protected void initView(Bundle savedInstanceState) {
        mAdapter = new ImageUploadAdapter(R.layout.item_image_upload);

        rvMyOrder.setLayoutManager(new LinearLayoutManager(this));
        rvMyOrder.setAdapter(mAdapter);

        loadingView = getLayoutInflater().inflate(R.layout.view_loading, rvMyOrder, false);
        params = new HashMap<>();
        mAdapter.setEmptyView(loadingView);
        loadMyOrderList(1, null);

        errorView = getLayoutInflater().inflate(R.layout.view_network_error, rvMyOrder, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMyOrderList(1, null);
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

    @Override
    protected void initData() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        btn_shangchuan.setOnClickListener(this);
        btn_queding.setOnClickListener(this);
    }

    private void loadMyOrderList(int page, ImageUploadAdapter adapyer) {
        //请求参数
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));//用户id
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.SEIGHTMODEL_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
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
                        Modelers bindUserEntity = new Gson().fromJson(response, Modelers.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            if (bindUserEntity.getJson() != null && bindUserEntity.getJson().size() > 0) {
                                nums = bindUserEntity.getJson().size();
                                List<Modelers.JsonBean> list = bindUserEntity.getJson();
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
                            mAdapter.loadMoreComplete();
                        }

                    }
                });
    }

    private void initRecycleView(final List<Modelers.JsonBean> list) {
        mAdapter = new ImageUploadAdapter(R.layout.item_image_upload, list);
        mAdapter.setOnLoadMoreListener(this, rvMyOrder);
      /*  mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {

                Modelers.JsonBean infoBean = (Modelers.JsonBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("infodata", (Serializable) infoBean);
                startNewActivity(ImageXiugaiActivity.class, bundle);
//                startNewActivity(ImageXiugaiActivity.class);
            }
        });*/

        rvMyOrder.setAdapter(mAdapter);

    }

    private void goadddres() {   //上传八大板块
        //请求参数
        params.put("agentid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));//用户id
        params.put("logo", "");//用户id
        params.put("url", et_urlterset.getText().toString());//用户id
        params.put("text", et_wenzititle.getText().toString());//用户id
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.ADDDATA_URL)
//                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Modelers bindUserEntity = new Gson().fromJson(response, Modelers.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                           ToastUtils.showToast(bindUserEntity.getData());
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }

                    }
                });
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

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return MyOrderPresenter.newInstance();
    }

    @Override
    public void updateContentList(List<MyOrderBean.JsonBean> list) {

    }

    @Override
    public void showNoMoreData() {
        mAdapter.loadMoreEnd(false);
    }

    @Override
    public void showLoadMoreError() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_image_upload;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            case R.id.btn_shangchuan:

                break;
            case R.id.btn_queding:
                goadddres();
                break;

        }
    }
}
