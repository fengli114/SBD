package com.mhy.shopingphone.ui.activity.tixian;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.alibaba.fastjson.JSONObject;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.partner.BindEntity;
import com.mhy.shopingphone.ui.activity.partner.XDButils;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;


import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class TiXianActivity extends BaseActivity {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tv_tixian_balance)
    TextView tvTixianBalance;
    @BindView(R.id.iv_tixian_zfb)
    ImageView ivTixianZfb;
    @BindView(R.id.al_tixian_zfb)
    LinearLayout alTixianZfb;
    @BindView(R.id.iv_tixian_card)
    ImageView ivTixianCard;
    @BindView(R.id.al_tixian_card)
    LinearLayout alTixianCard;
    @BindView(R.id.edit_tixian_)
    EditText editTixian;
    @BindView(R.id.btn_go_tixian)
    Button btnGoTixian;

    private boolean txType = false;//用于标记提现的方式 false:支付宝 true:银行卡
    private static final String TAG = "TiXianActivity";
    private String moneySum;
    private BindEntity bindEntity;
    private AlertDialog.Builder builder;

    @Override
    protected int getLayout() {
        return R.layout.activity_ti_xian;
    }

    @Override
    protected void onResume() {
        super.onResume();
        bindEntity = XDButils.getBindUserInfo();
        if (bindEntity == null && XEmptyUtils.isEmpty(bindEntity.getBankNo())) {
            builder.create().show();
        }
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext,tou);
        tvTitle.setText("提现");
        tvRight.setText("绑定信息");
        tvTixianBalance.setText(Contant.profitMoney);
        try {
            float a = Float.valueOf(Contant.profitMoney);
            int pMoney = (int) a;
            editTixian.setHint("您可提现的余额为" + pMoney / 100 * 100+"元");
            System.out.println("d: " + pMoney);
        } catch (NumberFormatException e) {
            System.out.println("pMoney is not a number");
        }


        // 创建构建器
        builder = new AlertDialog.Builder(this);
        // 设置参数
        builder.setTitle("提示")
                .setMessage("您还未绑定账户，现在就去绑定！")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {// 积极

                    @Override
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        startActivity(new Intent(TiXianActivity.this, BindActivity.class));
                    }
                }).setNeutralButton("取消", new DialogInterface.OnClickListener() {// 中间级

            @Override
            public void onClick(DialogInterface dialog,
                                int which) {
                builder.create().dismiss();
            }
        });

    }

    //登陆请求
    private void goTiXian(String txType) {
        //时间
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        //String datatime = formatter.format(curDate).replaceFirst("0","");
        String datatime = formatter.format(curDate);
        if (datatime.split(":")[0].length() >= 2 && datatime.startsWith("0")) {
            datatime = datatime.substring(1, datatime.length());
        }
        //请求头
        String headmsg = NSRBase64.encodeToString(MD5Util.MD5Encode(Contant.TIXIAN_MONEY_URLHead + datatime)).trim();

        //请求参数
        Map<String, String> params = new HashMap<String, String>();
//        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        params.put("ID", Contant.PARENTID);//代理商id
        params.put("ProfitMoney", moneySum);//体现金额
        params.put("RegiMoney", txType);//体现类型
        LogUtils.e(TAG + params.toString());
        String paramsstring = StringUtil.mapToJson(params);

        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();

        OkHttpUtils
                .post()
                .url(Contant.URL_TEST + Contant.TIXIAN_MONEY)
                .addHeader("Authorization", headmsg)
                .addParams("A", paramstr)//参数
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //ToastUtils.showToast("失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e(TAG + response);
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        String code = jsonObject.getString("Code");
                        String Mess = jsonObject.getString("Mess");
                        String money = jsonObject.getString("Money");

                        if (code.equals("0")) {
                            float fMoney  = Float.valueOf(money);
                            BigDecimal b2   =   new   BigDecimal(fMoney);
                            float   f2  =   b2.setScale(2,   BigDecimal.ROUND_HALF_UP).floatValue();
                            if (f2 == 0){
                                Contant.profitMoney = 0.00 + "";
                            }else {
                                Contant.profitMoney = f2+"";
                            }

                            LogUtils.e("合伙人"+f2);
                            tvTixianBalance.setText(f2+"");
                            try {
                                float a = Float.valueOf(money);
                                int pMoney = (int) a;
                                editTixian.setHint("您可提现的余额为" + pMoney / 100 * 100+"元");
                                System.out.println("d: " + pMoney);
                            } catch (NumberFormatException e) {
                                System.out.println("pMoney is not a number");
                            }
                            editTixian.setText("");
                            ToastUtils.showToast("提现申请成功~");
                        } else {
                            ToastUtils.showToast(Mess);
                        }
                    }
                });
    }

    private boolean isTX() {
        moneySum = editTixian.getText().toString().trim();
        if (XEmptyUtils.isEmpty(moneySum)) {
            ToastUtils.showToast("请输入提现的金额~");
            return false;
        }
        float a = Float.valueOf(Contant.profitMoney);
        float b = Float.valueOf(moneySum);
        LogUtils.e("niiiiiiiiiiiiiiiiii" + "a:" + a + "b:" + b);
        if (b > a) {
            ToastUtils.showToast("可提现金额不足~");
            return false;
        }
        if ((int) b % 100 != 0) {
            ToastUtils.showToast("金额大于或等于100时方可进行整额提现操作！");
            return false;
        }
        return true;
    }

    @OnClick({R.id.img_back, R.id.tv_right, R.id.al_tixian_zfb, R.id.al_tixian_card, R.id.btn_go_tixian})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right:
                startActivity(new Intent(TiXianActivity.this, BindActivity.class));
                break;
            case R.id.al_tixian_zfb:
                ivTixianCard.setVisibility(View.GONE);
                ivTixianZfb.setVisibility(View.VISIBLE);
                txType = false;
                break;
            case R.id.al_tixian_card:
                ivTixianCard.setVisibility(View.VISIBLE);
                ivTixianZfb.setVisibility(View.GONE);
                txType = true;
                break;
            case R.id.btn_go_tixian:

                if (txType) {
                    if (bindEntity != null && !XEmptyUtils.isEmpty(bindEntity.getBankNo())) {
                        if (!isTX()) {
                            return;
                        }
                        goTiXian("2");
                    } else {
                        builder.create().show();
                    }
                } else {
                    if (bindEntity != null && !XEmptyUtils.isEmpty(bindEntity.getAliAccount())) {
                        if (!isTX()) {
                            return;
                        }
                        goTiXian("1");
                    } else {
                        builder.create().show();
                    }
                }
                break;
        }
    }
}
