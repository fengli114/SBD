package com.mhy.shopingphone.ui.activity;

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
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.ShopTypeAdapter;
import com.mhy.shopingphone.contract.shop.ShopTypeContract;
import com.mhy.shopingphone.model.bean.shop.ShopTypeBean;
import com.mhy.shopingphone.presenter.shop.ShopTypePresenter;
import com.mhy.shopingphone.ui.fragment.shop.child.ShopActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class ShopTypeActivity extends BaseMVPCompatActivity<ShopTypeContract.ShopTypePresenter,
        ShopTypeContract.IShopTypeModel> implements ShopTypeContract.IShopTypeView {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.rv_shop_type)
    RecyclerView rvShopType;
    private ShopTypeAdapter mAdapter;
    private View errorView, loadingView;

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        tvTitle.setText("全部分类");
        mAdapter = new ShopTypeAdapter(R.layout.item_my_shop_type);
        rvShopType.setLayoutManager(new LinearLayoutManager(this));
        rvShopType.setAdapter(mAdapter);

        loadingView = getLayoutInflater().inflate(R.layout.view_loading, rvShopType, false);

        mAdapter.setEmptyView(loadingView);
        getShopType();

        errorView = getLayoutInflater().inflate(R.layout.view_network_error, rvShopType, false);
        errorView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getShopType();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_shop_type;
    }


    @OnClick(R.id.al_back)
    public void onViewClicked() {
        finish();
    }

    private void getShopType() {
        Map<String, String> params = new HashMap<>();
        String paramsstring = StringUtil.mapToJson(params);
        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.SHOP_TYPE_URLHead;
        mPresenter.loadShopTypeList(paramstr);
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return ShopTypePresenter.newInstance();
    }

    @Override
    public void updateContentList(List<ShopTypeBean.InfoBean> list) {
        if (mAdapter.getData().size() == 0) {
            initRecycleView(list);
        } else {
            mAdapter.addData(list);
        }
    }

    private void initRecycleView(final List<ShopTypeBean.InfoBean> list) {
        mAdapter = new ShopTypeAdapter(R.layout.item_my_shop_type, list);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ShopTypeBean.InfoBean listInfo = (ShopTypeBean.InfoBean) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putString("category", listInfo.getCate());
                bundle.putString("typeName", listInfo.getCate());
                startNewActivity(ShopActivity.class,bundle);
            }
        });
        rvShopType.setAdapter(mAdapter);
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        rvShopType.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
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
}
