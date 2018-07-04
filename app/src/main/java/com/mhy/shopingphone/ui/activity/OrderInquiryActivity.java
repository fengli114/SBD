package com.mhy.shopingphone.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.SingleModel;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.mhy.shopingphone.ui.huadan.CommonRecyclerViewAdapter;
import com.mhy.shopingphone.ui.huadan.CommonRecyclerViewHolder;
import com.mhy.shopingphone.ui.huadan.CustomDatePicker;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

/**
 * Created by Administrator on 2017/9/23.话单查询
 */

public class OrderInquiryActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.order_recyview)
    RecyclerView order_recyview;

    @BindView(R.id.order_back)
    ImageView order_back;
    //起始时间
    @BindView(R.id.tv_starttimes)
    TextView tv_starttime;
    //截止时间
    @BindView(R.id.tv_endttime)
    TextView tv_endttime;

    @BindView(R.id.lin_start)
    LinearLayout lin_start;

    @BindView(R.id.lin_endtime)
    RelativeLayout lin_endtime;
    //被叫号码
    @BindView(R.id.ed_called)
    EditText ed_called;
    //查询
    @BindView(R.id.btn_query)
    Button btn_query;

    //查询
    @BindView(R.id.tou)
    ImageView tou;
    private String paramsstring, paramstr;


    private CustomDatePicker customDatePicker1, customDatePicker2;
    //测试用
    private String called;//被叫号码

    private SingleModel list;
    private List<SingleModel.JsonBean> infoList;
    private CommonRecyclerViewAdapter<SingleModel.JsonBean> adapter;
    private String now;
    private String now_time;

    @Override
    protected int getLayout() {
        return R.layout.frag_order_inquiry;
    }

    /*
  * 话单查询
  * */
    public void getSigndata() {
        //请求参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("Caller", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        params.put("ParentID", Contant.PARENTID);//代理id
        params.put("SysNum", tv_starttime.getText().toString().trim());//开始时间
        params.put("Status", tv_endttime.getText().toString().trim());//结束时间
        params.put("Calle164", ed_called.getText().toString());//被叫号码

      /*  paramsstring = StringUtil.mapToJson(params);
        String paramstr = paramsstring.replaceAll("\n", "").trim();
        HttpUtils.headStr = Contant.PRSONAlINFORMATION_URLHead;
        HttpUtils.LogHeadStr = "话单查询:";
        Contant.IsDebug = true;*/
        OkHttpUtils
                .post()
                .url(Api.NEWGOODS + Api.WORDlISTQUERY_URL)
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli====" + response);
                        list = new Gson().fromJson(response, SingleModel.class);
                        if (list.getErrorCode() == 2000) {
                            infoList.clear();
                            for (SingleModel.JsonBean bean : list.getJson()) {
                                infoList.add(bean);
                            }
                            initRecView();
                        } else if (list.getErrorCode() == 1005) {
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
                                                getSigndata();
                                            } else {
                                                ToastUtils.showToast("网络异常");
                                            }
                                        }
                                    });
                        } else {
                            ToastUtils.showToast(list.getData());
                        }
                    }
                });
    }


    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        order_back.setOnClickListener(this);
        btn_query.setOnClickListener(this);
        lin_start.setOnClickListener(this);
        lin_endtime.setOnClickListener(this);
        infoList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());
        now_time = now.split(" ")[0];
        initDatePicker();
        initDatePicker2();

    }

    private void initRecView() {
        //线性布局
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        order_recyview.setLayoutManager(mLinearLayoutManager);
        adapter = new CommonRecyclerViewAdapter<SingleModel.JsonBean>(getBaseContext(), infoList) {
            @Override
            public void convert(CommonRecyclerViewHolder h, SingleModel.JsonBean sInfoBean, int position) {
                h.setText(R.id.tv_phone, sInfoBean.getCalle164());
//                long calltime = Long.parseLong(sInfoBean.getCalltime()+"".substring((int)sInfoBean.getCalltime(), (int)sInfoBean.getCalltime()));
                // h.setText(R.id.tv_data, DateUtils.timedates(calltime));
                h.setText(R.id.tv_data, getDateToString(sInfoBean.getCalltime()));
                h.setText(R.id.tv_price, String.valueOf("￥" + sInfoBean.getMoney() + "元"));
            }

            //返回item布局的id
            @Override
            public int getLayoutViewId(int viewType) {
                return R.layout.order_phone_item;
            }

        };
        //设置适配器
        order_recyview.setAdapter(adapter);
        adapter.setOnRecyclerViewItemClickListener(new CommonRecyclerViewAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
//                Toast.makeText(getBaseContext(), "你点击了第" + position + "个item", Toast.LENGTH_SHORT).show();
            }
        });
        adapter.notifyDataSetChanged();
    }

    private void initDatePicker() {

        customDatePicker1 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_starttime.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker1.showSpecificTime(false); // 不显示时和分
        customDatePicker1.setIsLoop(false); // 不允许循环滚动

    }

    /*时间戳转换成字符窜*/
    public String getDateToString(long time) {
        Date d = new Date(time);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(d);
    }

    private void initDatePicker2() {

        customDatePicker2 = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tv_endttime.setText(time.split(" ")[0]);
            }
        }, "2010-01-01 00:00", now); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker2.showSpecificTime(false); // 不显示时和分
        customDatePicker2.setIsLoop(false); // 不允许循环滚动

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //返回
            case R.id.order_back:
                finish();
                break;
            //开始时间
            case R.id.lin_start:
                // 日期格式为yyyy-MM-dd
                customDatePicker1.show(now_time);
                break;
            //截止时间
            case R.id.lin_endtime:
                customDatePicker2.show(now_time);
                break;
            //查询
            case R.id.btn_query:
                getSigndata();
//                if (valiCodeParam()) {
//
//                }
                break;
        }
    }

    private boolean valiCodeParam() {
        called = ed_called.getText().toString().trim();
        if (TextUtils.isEmpty(called)) {
            ToastUtils.showToast("被叫号码不能为空");
            return false;
        }

        return true;
    }

}
