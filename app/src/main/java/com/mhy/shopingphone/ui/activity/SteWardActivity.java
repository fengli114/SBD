package com.mhy.shopingphone.ui.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.qiyeguanjia.Logdwenglu;
import com.mhy.shopingphone.model.qiyeguanjia.Logginges;
import com.mhy.shopingphone.ui.activity.order.SogoManageActivity;
import com.mhy.shopingphone.ui.activity.order.SogoRechargeActivity;
import com.mhy.shopingphone.ui.activity.order.UserManageActivity;
import com.mhy.shopingphone.ui.activity.phone.DialBackActivity;
import com.mhy.shopingphone.ui.activity.tixian.BaseActivity;
import com.mhy.shopingphone.ui.activity.tixian.MerchantActivity;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;


public class SteWardActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.tv_yue)  //余额
            TextView tvyue;
    @BindView(R.id.gouwuyue)   //购物余额
            TextView gouwuyue;
    @BindView(R.id.tv_yhnum)
    TextView tvyhnum;
    @BindView(R.id.tv_todaycz)   //今日充值
            TextView tv_todaycz;
    @BindView(R.id.jinrihy)
    TextView jinrihy;
    @BindView(R.id.zonghys)
    TextView zonghys;
    @BindView(R.id.iv_back)    //返回
            ImageView order_back;
    @BindView(R.id.ll_shanghu_add)    //添加商户
            LinearLayout ll_shanghu_add;
    @BindView(R.id.ll_shanghu_guanli)    //商户管理
            LinearLayout ll_shanghu_guanli;
    @BindView(R.id.ll_guanggao_photo)    //八大模块
            LinearLayout ll_guanggao_photo;
    @BindView(R.id.ll_kongzhong_chongzhi)    //空中充值
            LinearLayout ll_kongzhong_chongzhi;
    @BindView(R.id.ll_shanghuchongzhi)      //商户充值
            LinearLayout ll_shanghuchongzhi;
    @BindView(R.id.yonghuguanli)          //用户管理
            LinearLayout yonghuguanli;
    @BindView(R.id.ll_gundongwenzi)       //滚动文字
            LinearLayout ll_gundongwenzi;
    @BindView(R.id.ll_qiyedongtai)       //企业动态
            LinearLayout ll_qiyedongtai;
    @BindView(R.id.chongkaguanli)       //充值卡管理
            LinearLayout chongkaguanli;
    @BindView(R.id.ll_kahaohb)         //卡号划拨
            LinearLayout ll_kahaohb;
    @BindView(R.id.ll_qitagg)         //其他广告
            LinearLayout ll_qitagg;
    @BindView(R.id.ll_xiugaikmima)    //修改密码
            LinearLayout ll_xiugaikmima;
    @BindView(R.id.ll_lunbo)
    LinearLayout ll_lunbo;
    @BindView(R.id.tv_names)
    TextView tv_names;
    private Logginges userBean;

    @Override

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_shanghu_add:   //添加商户
                userBean = (Logginges) getIntent().getSerializableExtra("info");
                Intent intent = new Intent();
                intent.setClass(this, MerchantActivity.class);   //描述起点和目标
                Bundle bundle = new Bundle();                           //创建Bundle对象
                bundle.putSerializable("something", userBean);     //装入数据
                intent.putExtras(bundle);                                //把Bundle塞入Intent里面
                startActivity(intent);
                break;
            case R.id.ll_shanghu_guanli:    //商户管理
                userBean = (Logginges) getIntent().getSerializableExtra("info");
                Intent intent2 = new Intent();
                intent2.setClass(this, SogoManageActivity.class);   //描述起点和目标
                Bundle bundle1 = new Bundle();                           //创建Bundle对象
                bundle1.putSerializable("something", userBean);     //装入数据
                intent2.putExtras(bundle1);                                //把Bundle塞入Intent里面
                startActivity(intent2);
              /*  Intent intent2 = new Intent(this, SogoManageActivity.class);
                startActivity(intent2);*/
                break;
            case R.id.ll_guanggao_photo:   //八大模块
                Intent intent3 = new Intent(this, EightUploadActivity.class);
                startActivity(intent3);
               /* Intent intent3 = new Intent(this, CarouselActivity.class);
                startActivity(intent3);*/
                break;
            case R.id.ll_kongzhong_chongzhi:    //空中充值
                Intent intent4 = new Intent(this, RechargeCentreActivity.class);
                startActivity(intent4);
                break;
            case R.id.ll_lunbo:    //轮播广告
                Intent intent5 = new Intent(this, CarouseActivityActivity.class);
                startActivity(intent5);
                break;
            case R.id.ll_shanghuchongzhi:
                Intent intent6 = new Intent(this, SogoRechargeActivity.class);
                startActivity(intent6);
                break;
            case R.id.yonghuguanli:
                Intent intent7 = new Intent(this, UserManageActivity.class);   //用户管理
                startActivity(intent7);
                break;
            case R.id.ll_gundongwenzi:
                Intent intent8 = new Intent(this, MarqueeTextActivity.class);
                startActivity(intent8);
                break;
            case R.id.ll_qiyedongtai:
                showNormalDialog();
                break;
            case R.id.chongkaguanli:
                Intent intent10 = new Intent(this, RechargeableCardActivity.class);
                startActivity(intent10);
                break;
            case R.id.ll_kahaohb:
                Intent intent11 = new Intent(this, CardidTransferActivity.class);
                startActivity(intent11);
                break;
            case R.id.ll_qitagg:
                Intent intent12 = new Intent(this, EightqitaActivity.class);
                startActivity(intent12);
                break;
            case R.id.ll_xiugaikmima:
                    Intent intent13 = new Intent(this, StewardPawswordActivity.class);
                    startActivity(intent13);

                break;
        }

    }

    private void showNormalDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        final String[] items = {"功能优化中，敬请期待"};
        AlertDialog.Builder listDialog =
                new AlertDialog.Builder(mContext);
        listDialog.setTitle("温馨提示");
        listDialog.setItems(items, null);
        listDialog.show();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_steward;
    }

    @Override
    protected void onResume() {
        super.onResume();
        dologo();
    }

    @Override
    protected void initDate() {
        userBean = (Logginges) getIntent().getSerializableExtra("info");
        LogUtils.e("fengli----" + userBean.getJson().getFreeroutes().get(0).getName());
        tvyue.setOnClickListener(this);
        order_back.setOnClickListener(this);
        ll_shanghu_add.setOnClickListener(this);
        ll_shanghu_guanli.setOnClickListener(this);
        ll_guanggao_photo.setOnClickListener(this);
        ll_kongzhong_chongzhi.setOnClickListener(this);
        ll_lunbo.setOnClickListener(this);
        ll_shanghuchongzhi.setOnClickListener(this);
        yonghuguanli.setOnClickListener(this);
        ll_gundongwenzi.setOnClickListener(this);
        ll_qiyedongtai.setOnClickListener(this);
        chongkaguanli.setOnClickListener(this);
        ll_kahaohb.setOnClickListener(this);
        ll_qitagg.setOnClickListener(this);
        ll_xiugaikmima.setOnClickListener(this);
    }

    public void dologo() {
        Map<String, String> params = new HashMap<>();
        params.put("userid", (String) SharedPreferencesHelper.getInstance().getData("adminUserId", ""));
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.GJSHOUYE_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli--" + response);

                        Logdwenglu bindUserEntity = new Gson().fromJson(response, Logdwenglu.class);
                        if (bindUserEntity.getErrorCode() == 2000) {
                            if (bindUserEntity.getJson().getUsers() != null) {
                                tv_names.setText(bindUserEntity.getJson().getUsers().getUsername());
                                tvyue.setText(bindUserEntity.getJson().getUsers().getBlance() + "");
                                gouwuyue.setText(bindUserEntity.getJson().getUsers().getShopblance() + "");
                                tvyhnum.setText(bindUserEntity.getJson().getCounttotle() + "");
                                tv_todaycz.setText(bindUserEntity.getJson().getCounttodayrechard() + "");
                                jinrihy.setText(bindUserEntity.getJson().getCounttoday() + "");
                                zonghys.setText(bindUserEntity.getJson().getCountnew() + "");

                            }
                        } else {
                            ToastUtils.showToast(bindUserEntity.getData());
                        }
                    }
                });
    }
}
