package com.mhy.shopingphone.ui.activity.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.DynamicAdapter;
import com.mhy.shopingphone.adapter.MyOrderAdapter;
import com.mhy.shopingphone.contract.detail.DynamicContract;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.mhy.shopingphone.presenter.detail.DynamicPresenter;
import com.mhy.shopingphone.view.webview.WebViewActivity;

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

public class DynamicActivity extends BaseMVPCompatActivity<DynamicContract
        .DynamicPresenter, DynamicContract.IDynamicModel> implements
        DynamicContract.IDynamicView {
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.rv_dynamic)
    RecyclerView rvDynamic;



//    PtrClassicFrameLayout mPtrFrame;

    private Map<String, String> params;
    private boolean isRefresh = false; //标记是否是 下拉刷新;
    private String paramstr;
    private DynamicAdapter mAdapter;
    private int mP;
    private View errorView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        params = new HashMap<>();
        loadDynamicList(1);
        mPresenter.loadDynamicList(paramstr);
        mAdapter = new DynamicAdapter(R.layout.item_dynamic_);
        rvDynamic.setLayoutManager(new LinearLayoutManager(this));
        rvDynamic.setAdapter(mAdapter);

        errorView = getLayoutInflater().inflate(R.layout.view_network_error, rvDynamic, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDynamicList(1);
                mPresenter.loadDynamicList(paramstr);
            }
        });
//        mPtrFrame.setLastUpdateTimeRelateObject(this);
//        mPtrFrame.setPtrHandler(new PtrHandler() {
//            @Override
//            public void onRefreshBegin(PtrFrameLayout frame) {
//                loadDynamicList(1);
//                mPresenter.loadDynamicList(paramstr);
//                isRefresh = true;
//                mPtrFrame.refreshComplete();
//            }
//
//            @Override
//            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
//                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
//            }
//        });
    }

    private void loadDynamicList(int page) {
        //请求参数
        params.put("Prefix", "");
        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));

        String paramsstring = StringUtil.mapToJson(params);
        paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.DYNAMIC_URLHead;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dynamic;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return DynamicPresenter.newInstance();
    }

    @Override
    public void updateContentList(List<DynamicBean.InfoBean> list) {
        if (mAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mAdapter.addData(list);
        }
    }

    private void initRecycleView(List<DynamicBean.InfoBean> list) {
        mAdapter = new DynamicAdapter(R.layout.item_dynamic_, list);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DynamicBean.InfoBean infoBean = (DynamicBean.InfoBean) adapter.getItem(position);
                WebViewActivity.skip(DynamicActivity.this, infoBean.getUrl(), infoBean.getTitle());
            }
        });
        rvDynamic.setAdapter(mAdapter);
    }

    @Override
    public void showNoMoreData() {

    }

    @Override
    public void showLoadMoreError() {
        mAdapter.loadMoreFail();
    }

    @Override
    public void showNetworkError() {
        mAdapter.setEmptyView(errorView);
    }


    @OnClick(R.id.al_back)
    public void onViewClicked() {
        finish();
    }
}
