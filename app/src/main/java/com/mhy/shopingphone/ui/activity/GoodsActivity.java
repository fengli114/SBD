package com.mhy.shopingphone.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mhy.sdk.adapter.FragmentAdapter;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StatusBarUtils;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.GoodsVpAdapter;
import com.mhy.shopingphone.constant.TabFragmentIndex;
import com.mhy.shopingphone.contract.goods.GoodsContract;
import com.mhy.shopingphone.model.bean.goods.GoodsTabBean;
import com.mhy.shopingphone.presenter.goods.GoodsPresenter;
import com.mhy.shopingphone.ui.fragment.ShopingFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class GoodsActivity extends BaseMVPCompatActivity<GoodsContract
        .GoodsPresenter, GoodsContract.IGoodsModel> implements GoodsContract
        .IGoodsView {

    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.tv_search)
    TextView tvSearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.title)
    LinearLayout title;
    private String[] list_title;
    private List<Fragment> fragments;
    private GoodsVpAdapter fAdapter;
    private String category;
    private int type;
    private String typeName;
    private int search_type;
    private String searchName;

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return GoodsPresenter.newInstance();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        Intent intent = getIntent();
        search_type = intent.getIntExtra("search_type", 0);
        searchName = intent.getStringExtra("Name");
//        StatusBarUtils.setColor(this,Color.BLACK);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            type = bundle.getInt("type", 0);
            typeName = bundle.getString("typeName");
        }
        tvTitle.setText(typeName);//商品类型名称
        fragments = new ArrayList<>();
        //请求参数
        Map<String, String> params = new HashMap<String, String>();
        //请求参数
        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id

        String paramsstring = StringUtil.mapToJson(params);
        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.GET_TAB_URLHead;
        mPresenter.loadTabs(paramstr);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods;
    }

    @Override
    public void showTabs(GoodsTabBean bean) {

        list_title = new String[bean.getInfo().size() + 1];
        list_title[0] = "全部";

        for (int i = 0; i < bean.getInfo().size(); i++) {
            list_title[i + 1] = bean.getInfo().get(i).getCategory();
        }

        for (int i = 0; i < list_title.length; i++) {
            ShopingFragment shopingFragment = ShopingFragment.newInstance();
            tlTabs.addTab(tlTabs.newTab().setText(list_title[i]));

            if (i == 0 && search_type == 1) {
                category = searchName;
            } else if (i == 0 && search_type == 2) {
                shopingFragment.setSearchName(searchName);
            } else if (i == 0 && search_type == 0) {
                category = "";
            } else {
                category = bean.getInfo().get(i - 1).getCategory();
            }
            shopingFragment.setCategory(category);
            shopingFragment.setSearchType(type);
            fragments.add(shopingFragment);
        }
//            fAdapter = new GoodsVpAdapter(getSupportFragmentManager(), fragments,list_title );
//            //viewpager加载adapter
//            vpFragment.setAdapter(fAdapter);
//            //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
//            //TabLayout加载viewpager
//            tlTabs.setupWithViewPager(vpFragment);
        vpFragment.setAdapter(new FragmentAdapter(getSupportFragmentManager(), fragments));
        vpFragment.setCurrentItem(TabFragmentIndex.TAB_ZHIHU_INDEX);//要设置到viewpager.setAdapter后才起作用
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(TabFragmentIndex.TAB_ZHIHU_INDEX);
        //tlTabs.setupWithViewPager方法内部会remove所有的tabs，这里重新设置一遍tabs的text，否则tabs的text不显示
        for (int i = 0; i < list_title.length; i++) {
            tlTabs.getTabAt(i).setText(list_title[i]);
        }

    }

    @Override
    public void showNetworkError() {

    }


    @OnClick(R.id.al_back)
    public void onViewClicked() {
        finish();
    }
}
