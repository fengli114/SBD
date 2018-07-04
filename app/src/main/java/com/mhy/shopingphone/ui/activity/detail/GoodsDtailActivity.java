package com.mhy.shopingphone.ui.activity.detail;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.blankj.utilcode.util.AppUtils;
import com.bumptech.glide.Glide;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.DisplayUtils;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.ResourcesUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.constant.BundleKeyConstant;
import com.mhy.shopingphone.contract.details.GoodsDtailContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.MyOrderBean;
import com.mhy.shopingphone.model.bean.shop.ShopDetailBean;
import com.mhy.shopingphone.presenter.detail.GoodsDtailPresenter;
import com.mhy.shopingphone.ui.activity.WebViewLoadActivity;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.mhy.shopingphone.widgets.dialog.TipDialog;
import com.mhy.shopingphone.widgets.dialog.TipDialog2;
import com.youth.xframe.utils.log.XLog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.mhy.sdk.utils.StatusBarUtils.getStatusBarHeight;
import static com.mhy.shopingphone.constant.InternKeyConstant.INTENT_KEY_MOVIE_SUBJECTBEAN;

public class GoodsDtailActivity extends BaseMVPCompatActivity<GoodsDtailContract
        .GoodsDtailPresenter, GoodsDtailContract.IGoodsDtailModel> implements
        GoodsDtailContract.IGoodsDtailView  {

    @BindView(R.id.iv_gd_icon)
    ImageView ivGdIcon;
    @BindView(R.id.iv_gd_back)
    ImageView ivGdBack;
    @BindView(R.id.tv_gd_tite)
    TextView tvGdTite;
    @BindView(R.id.al_gd_share)
    LinearLayout alGdShare;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.tv_gd_price)
    TextView tvGdPrice;
    @BindView(R.id.tv_gd_discount)
    TextView tvGdDiscount;
    @BindView(R.id.tv_content)
    TextView tvContent;
    @BindView(R.id.tv_gd_xl)
    TextView tvGdXl;
    @BindView(R.id.rl_gd_detail)
    LinearLayout rlGdDetail;
    @BindView(R.id.rl_now_buy)
    RelativeLayout rlNowBuy;
    @BindView(R.id.al_back)
    RelativeLayout al_back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private GoodsBean.JsonBean.CommoditiesBean infoBean;
    private TipDialog tipDialog;
    private TipDialog2 tipDialog2;
    private Uri uri;
    private MyOrderBean.JsonBean infoBean2;
    private int type;
    private int position;

    @Override
    protected void initData() {
        super.initData();
        if (getIntent() != null) {
            type = getIntent().getIntExtra("type", 0);
            position = getIntent().getIntExtra("position", 0);
            if (type == 1){
                infoBean2 = (MyOrderBean.JsonBean) getIntent().getSerializableExtra("data");
                Glide.with(this).load(infoBean2.getPic())
//                .placeholder(R.drawable.home_default_rectangle)
//                .error(R.drawable.home_default_rectangle)
                        .into(ivGdIcon);

                tvGdDiscount.setText("可折扣" + infoBean2.getDiscount() + "元");
                tvGdTite.setText(infoBean2.getName()+"");
                tvGdPrice.setText(infoBean2.getMoney() + "");
                tvGdXl.setText("销量" + infoBean2.getSalesVolume());
//                if (infoBean2.getDiscount() > infoBean2.getMoney()){
//                    tvContent.setText("当前订单"+ infoBean2.getYhStart());
//                    tvContent.setTextColor(Color.RED);
//                    tvContent.setTextSize(18);
//                }
            }else {
                infoBean = (GoodsBean.JsonBean.CommoditiesBean) getIntent().getSerializableExtra
                        (INTENT_KEY_MOVIE_SUBJECTBEAN);
                initViews();
            }
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {

    }

    private void initViews() {

        Util.setMarginsStatusBar(mContext,al_back);
        Glide.with(this).load(infoBean.getPic()).asBitmap().into(ivGdIcon);
        tvGdDiscount.setText("可优惠" + infoBean.getDiscount() + "元");
        tvGdTite.setText(infoBean.getName());
        tvGdPrice.setText(infoBean.getMoney() + "");
        tvGdXl.setText("销量" + infoBean.getSalesvolume());
        if (infoBean.getDiscount() > infoBean.getMoney()){
            tvContent.setText("当前订单"+infoBean.getYhstart());
            tvContent.setTextColor(Color.RED);
            tvContent.setTextSize(18);
        }
//        DisplayUtils.displayBlurImg(this, infoBean.getPic(), ivGdIcon);
//        DisplayUtils.displayBlurImg(this, infoBean.getPic(), toolbar);
//        int headerBgHeight = toolbar.getLayoutParams().height + getStatusBarHeight(this);
        // 使背景图向上移动到图片的最低端，保留（toolbar+状态栏）的高度
        // 实际上此时ivToolbarBg高度还是330dp，只是除了toolbar外，剩下部分是透明状态
//        ViewGroup.MarginLayoutParams ivTitleHeadBgParams = (ViewGroup.MarginLayoutParams)
//                ivGdIcon.getLayoutParams();
//        int marginTop = ivGdIcon.getLayoutParams().height - headerBgHeight;
//        ivTitleHeadBgParams.setMargins(0, -marginTop, 0, 0);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_goods_dtail;
    }

    /**
     * @param context   activity
     * @param infoBean  bean
     * @param imageView imageView
     */
    public static void start(Activity context, GoodsBean.JsonBean.CommoditiesBean infoBean, ImageView imageView) {
        Intent intent = new Intent(context, GoodsDtailActivity.class);
        intent.putExtra(INTENT_KEY_MOVIE_SUBJECTBEAN, infoBean);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                (context, imageView, ResourcesUtils.getString(R.string.transition_movie_img));
        //与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
    /**
     * @param context   activity
     * @param infoBean  bean
     * @param imageView imageView
     */
    public static void start2(Activity context, MyOrderBean.JsonBean infoBean, ImageView imageView) {
        Intent intent = new Intent(context, GoodsDtailActivity.class);
//        intent.putExtra(INTENT_KEY_MOVIE_SUBJECTBEAN, infoBean);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation
                (context, imageView, ResourcesUtils.getString(R.string.transition_movie_img));
        //与xml文件对应
        ActivityCompat.startActivity(context, intent, options.toBundle());
    }
    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return GoodsDtailPresenter.newInstance();
    }

    @OnClick({R.id.al_back, R.id.rl_gd_detail, R.id.rl_now_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.rl_gd_detail:
                if (AppUtils.isInstallApp("com.taobao.taobao")) {
                    Intent intent = new Intent();
                    intent.setAction("Android.intent.action.VIEW");
                    if (type == 0) {
                        // 商品地址
                        uri = Uri.parse(infoBean.getDetailurl());
                    } else {
                        Uri uri = Uri.parse(infoBean2.getDetailUrl()+""); // 商品地址
                    }
                    intent.setData(uri);
                    intent.setClassName("com.taobao.taobao", "com.taobao.tao.detail.activity.DetailActivity");
                    startActivity(intent);
                } else {
                    if (type == 0) {
                        WebViewActivity.skip(GoodsDtailActivity.this, infoBean.getDetailurl(), "物品详情");
                    } else {
                        WebViewActivity.skip(GoodsDtailActivity.this, infoBean2.getDetailUrl()+"", "物品详情");
                    }
                }
                break;
            case R.id.rl_now_buy:
                LogUtils.e("maoamo"+infoBean.getDetailurl());
                if (!infoBean.isDatastatus()) {
                    tipDialog = new TipDialog(this, R.style.MillionDialogStyle, infoBean.getDiscount() + "", onClickListener);
                    tipDialog.show();
                }else {
                    tipDialog2 = new TipDialog2(this, R.style.MillionDialogStyle, infoBean.getDiscount() + "", onClickListener2);
                    tipDialog2.show();
                }
                break;
        }
    }
    View.OnClickListener onClickListener2 = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            if (AppUtils.isInstallApp("com.taobao.taobao")) {
                Intent intent = new Intent();
                intent.setAction("Android.intent.action.VIEW");
                if (type == 0) {
                    // 商品地址
                    uri = Uri.parse(infoBean.getTgurl()+"".replace("https://", "taobao://")); // 商品地址

                } else {
                    uri = Uri.parse(infoBean2.getTgUrl()+"".replace("https://", "taobao://")); // 商品地址
                }
                intent.setClassName("com.taobao.taobao", "com.taobao.browser.BrowserActivity");
                intent.setData(uri);
                startActivity(intent);
            } else {
                if (type == 0) {
                    WebViewActivity.skip(GoodsDtailActivity.this, infoBean.getTgurl()+"", "粉丝福利购");
                } else {
                    WebViewActivity.skip(GoodsDtailActivity.this, infoBean2.getTgUrl()+"", "粉丝福利购");
                }

            }
            tipDialog2.dismiss();
        }
    };
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            goNow();
            tipDialog.dismiss();
        }
    };
    //请求
    private void goNow() {
        Map<String,String>params = new HashMap<>();
        //请求参数
        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));//SharedPreferencesHelper.getInstance().saveData("Mobile", jsonObject.getString("Mobile"));//手机号码
        if (type == 1) {
            params.put("Prefix", infoBean2.getDetailUrl()+"");//详情地址
            params.put("Role", infoBean2.getDiscount() + "");//可抵扣金额
            params.put("ID", infoBean2.getCid());
        } else {
            params.put("Prefix", infoBean.getDetailurl());//详情地址
            params.put("Role", infoBean.getDiscount() + "");//可抵扣金额
            params.put("ID", infoBean.getId());
        }
        LogUtils.e("兑换参数："+params.toString());
        String paramsstring = StringUtil.mapToJson(params);
        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.TAKE_URLHead;
        mPresenter.nowBuy(paramstr);
    }

    @Override
    public void update(Ceshi list) {
        if (list.getCode().equals("0")) {
            infoBean.setDatastatus(true);
            if (AppUtils.isInstallApp("com.taobao.taobao")) {
                Intent intent = new Intent();
                intent.setAction("Android.intent.action.VIEW");
                if (type == 0) {
                    // 商品地址
                    uri = Uri.parse(infoBean.getTgurl()+"".replace("https://", "taobao://")); // 商品地址
                    XLog.e("URL2:" + uri.toString());
                } else {
                    uri = Uri.parse(infoBean2.getTgUrl()+"".replace("https://", "taobao://")); // 商品地址
                    XLog.e("URL2:" + uri.toString());
                }
                XLog.e("URL:" + uri.toString());
                intent.setClassName("com.taobao.taobao", "com.taobao.browser.BrowserActivity");
                intent.setData(uri);
                startActivity(intent);
            } else {
                if (type == 0) {
                    WebViewActivity.skip(GoodsDtailActivity.this, infoBean.getTgurl()+"", "粉丝福利购");
                } else {
                    WebViewActivity.skip(GoodsDtailActivity.this, infoBean2.getTgUrl()+"", "粉丝福利购");
                }

            }
        } else if (list.getCode().equals("-5")){
            ToastUtils.showToast("话费不足");
        }else {
            ToastUtils.showToast(list.getMess());
        }
    }

    @Override
    public void showDetails(ShopDetailBean list) {

    }


    @Override
    public void showNetworkError() {

    }
}
