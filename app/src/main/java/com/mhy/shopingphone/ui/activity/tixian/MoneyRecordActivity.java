package com.mhy.shopingphone.ui.activity.tixian;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.MoneyRecordAdapter;
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

public class MoneyRecordActivity extends BaseActivity {
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.lv_money_record)
    ListView lvMoneyRecord;
    @BindView(R.id.spr)
    SpringView springView;
    private MoneyRecordAdapter adapter;
    private int page = 1;
    private static final String TAG = "MoneyRecordActivity";
    private List<MoneyRecordEntity.InfoBean> listEntity;

    @Override
    protected int getLayout() {
        return R.layout.activity_money_record;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext,tou);
        tvTitle.setText("资金记录");
        listEntity = new ArrayList<>();
        springView.setType(SpringView.Type.FOLLOW);
        springView.setListener(onFreshListener);
        springView.setHeader(new DefaultHeader(mContext));
        springView.setFooter(new DefaultFooter(mContext));
        adapter = new MoneyRecordAdapter(this);
        lvMoneyRecord.setAdapter(adapter);
        goHttp(page);
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
        //时间
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        //String datatime = formatter.format(curDate).replaceFirst("0","");
        String datatime = formatter.format(curDate);
        if (datatime.split(":")[0].length() >= 2 && datatime.startsWith("0")) {
            datatime = datatime.substring(1, datatime.length());
        }
        //请求头
        String headmsg = NSRBase64.encodeToString(MD5Util.MD5Encode(Contant.TIXIAN_RECORD_URLHead + datatime)).trim();
        //请求参数
        Map<String, String> params = new HashMap<String, String>();
//        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        params.put("ID", Contant.PARTNER_ID);//id
        params.put("RegiMoney", page + "");//页面
        LogUtils.e(TAG + params.toString());
        String paramsstring = StringUtil.mapToJson(params);
        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();

        OkHttpUtils
                .post()
                .url(Contant.URL_TEST + Contant.TIXIAN_RECORD)
                .addHeader("Authorization", headmsg)
                .addParams("A", paramstr)//参数
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //SDToast.showToast("失败");
                        springView.onFinishFreshAndLoad();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        springView.onFinishFreshAndLoad();
                        LogUtils.e(TAG + response);
                        MoneyRecordEntity recordEntity = new Gson().fromJson(response, MoneyRecordEntity.class);
                        if (recordEntity.getCode().equals("0")) {
                            if (recordEntity.getInfo() != null && recordEntity.getInfo().size() > 0) {
                                LogUtils.e(TAG + recordEntity.getInfo().size() + "");
                                listEntity.addAll(recordEntity.getInfo());
                                adapter.setList(listEntity);
                            } else {
                                ToastUtils.showToast("没有更多数据~");
                            }
                        } else  if (recordEntity.getCode().equals("-5")){
                            ToastUtils.showToast("没有更多数据~");
                        }else {
                            ToastUtils.showToast(recordEntity.getMess());
                        }
                    }
                });
    }

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        finish();
    }
}
