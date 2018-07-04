package com.mhy.shopingphone.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.mhy.sdk.base.activity.BaseCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.MyOrderAdapter;
import com.mhy.shopingphone.adapter.MyTeamAdapter;
import com.mhy.shopingphone.adapter.TeamAdapter;
import com.mhy.shopingphone.model.bean.partner.MyTeamEntity;
import com.mhy.shopingphone.widgets.XCRoundRectImageView;
import com.youth.xframe.utils.log.XLog;
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

public class MyTeamActivity extends BaseCompatActivity {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_tx_icon)
    XCRoundRectImageView ivTxIcon;
    @BindView(R.id.tv_tx_account)
    TextView tvTxAccount;
    @BindView(R.id.rc_team)
    RecyclerView rcTm;
    @BindView(R.id.spr)
    SpringView springView;

    private static final String TAG = "MyTeamActivity";
    private int page = 1;
    private TeamAdapter adapter;
    private List<MyTeamEntity.InfoBean> listEntity;
    private MyTeamAdapter mAdapter;

    @Override
    protected void initView(Bundle savedInstanceState) {
        listEntity = new ArrayList<>();
        tvTitle.setText("我的团队");
        Util.setStatusBarHeigh(mContext, tou);

        Glide.with(this).load(Contant.URL_IMAG_ICON).into(ivTxIcon);
        tvTxAccount.setText(String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));

        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(onFreshListener);
        springView.setHeader(new DefaultHeader(mContext));
        springView.setFooter(new DefaultFooter(mContext));
        mAdapter = new MyTeamAdapter(R.layout.my_team_item);
        rcTm.setLayoutManager(new LinearLayoutManager(this));
        rcTm.setAdapter(mAdapter);
        goHttp(page);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_team;
    }

    /**
     * Spring 的监听
     */
    SpringView.OnFreshListener onFreshListener = new SpringView.OnFreshListener() {
        @Override
        public void onRefresh() {
            if (listEntity != null && listEntity.size() > 0) {
                listEntity.clear();
            }
            page = 1;
            goHttp(page);
        }

        @Override
        public void onLoadmore() {
            page += 1;
            goHttp(page);
        }
    };

    //登陆请求
    private void goHttp(int page) {
        showProgressDialog("加载中");
        //时间
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        //String datatime = formatter.format(curDate).replaceFirst("0","");
        String datatime = formatter.format(curDate);
        if (datatime.split(":")[0].length() >= 2 && datatime.startsWith("0")) {
            datatime = datatime.substring(1, datatime.length());
        }
        //请求头
        String headmsg = NSRBase64.encodeToString(MD5Util.MD5Encode(Contant.MY_TEAM_URLHead + datatime)).trim();

        //请求参数
        Map<String, String> params = new HashMap<String, String>();
//        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        params.put("ParentId", Contant.PARTNER_ID);//id
        params.put("RegiMoney", page + "");//页面
        LogUtils.e(TAG + params.toString());
        String paramsstring = StringUtil.mapToJson(params);

        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();

        OkHttpUtils
                .post()
                .url(Contant.URL_TEST + Contant.MY_TEAM)
                .addHeader("Authorization", headmsg)
                .addParams("A", paramstr)//参数
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //SDToast.showToast("失败");
                        springView.onFinishFreshAndLoad();
                        hideProgressDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        hideProgressDialog();
                        springView.onFinishFreshAndLoad();
                        LogUtils.e(TAG + response);
                        MyTeamEntity teamEntity = new Gson().fromJson(response, MyTeamEntity.class);
                        if (teamEntity.getCode().equals("0")) {
                            if (teamEntity.getInfo() != null && teamEntity.getInfo().size() > 0) {
                                LogUtils.e(TAG + teamEntity.getInfo().size() + "");
                                listEntity.addAll(teamEntity.getInfo());
                                mAdapter.setNewData(listEntity);
                            } else {
                                ToastUtils.showToast("没有数据啦~");
                            }
                        } else if (teamEntity.getCode().equals(-5)){
                            ToastUtils.showToast("没有更多数据~");

                        }else {
                            ToastUtils.showToast(teamEntity.getMess());
                        }
                    }
                });
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
