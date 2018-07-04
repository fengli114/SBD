package com.mhy.shopingphone.ui.activity.recharge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseCompatActivity;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.RecordAdapter;
import com.mhy.shopingphone.adapter.RecordEntity;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.mhy.shopingphone.widgets.NewSpacesItemDecoration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class RechargeRecordActivity extends BaseMVPCompatActivity {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_recharge_record)
    RecyclerView rvRechargeRecord;
    @BindView(R.id.spring)
    SpringView springView;
    private String paramsstring;
    private String paramstr;
    private int mPage = 1;
    private RecordAdapter recordAdapter;
    private List<RecordEntity.JsonBean> recordList;


    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_recharge_record;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        recordList = new ArrayList<>();
        tvTitle.setText("充值记录");
        recordAdapter = new RecordAdapter(R.layout.recharge_record_iteam);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);

        rvRechargeRecord.setLayoutManager(layoutManager);
        rvRechargeRecord.setHasFixedSize(true);
        rvRechargeRecord.setNestedScrollingEnabled(false);
        rvRechargeRecord.addItemDecoration(new NewSpacesItemDecoration(15));
//            rcShoppingMall.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rvRechargeRecord.setAdapter(recordAdapter);
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(onFreshListener);
        springView.setHeader(new DefaultHeader(this));
        springView.setFooter(new DefaultFooter(this));
        initHttps(mPage);
    }

    /**
     * Spring 的监听
     */
    SpringView.OnFreshListener onFreshListener = new SpringView.OnFreshListener() {
        @Override
        public void onRefresh() {
            if (recordList != null && recordList.size() > 0) {
                recordList.clear();
            }
            mPage = 1;
            initHttps(1);
        }

        @Override
        public void onLoadmore() {
            mPage += 1;
            initHttps(mPage);
        }
    };


    private void initHttps(final int page) {
        final int pages = page;
        showWaitDialog("加载中...");
        //请求参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("AgentID", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));//代理商id
        params.put("Page", page + "");//代理商id
        OkHttpUtils
                .post()
                .url(Api.NEWGOODS + Api.DETAIL_URL)
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        hideWaitDialog();
                        springView.onFinishFreshAndLoad();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        springView.onFinishFreshAndLoad();
                        hideWaitDialog();
                        RecordEntity recordEntity = new Gson().fromJson(response, RecordEntity.class);
                        if (recordEntity.getErrorCode() == 2000) {
                            if (recordEntity.getJson() != null && recordEntity.getJson().size() > 0) {
                                recordList.addAll(recordEntity.getJson());
                                recordAdapter.setNewData(recordList);
                            }
                        } else if (recordEntity.getErrorCode() == 1005) {
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
                                                initHttps(pages);
                                            } else {
                                                ToastUtils.showToast("网络异常");
                                            }
                                        }
                                    });
                        } else if (recordEntity.getErrorCode() == 9001) {
                            ToastUtils.showToast("没有更多记录了");
                        } else {
                            ToastUtils.showToast(recordEntity.getData());
                        }
                    }
                });

    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }


}
