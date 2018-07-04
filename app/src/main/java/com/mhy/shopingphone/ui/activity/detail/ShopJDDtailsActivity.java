package com.mhy.shopingphone.ui.activity.detail;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.AppUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.ResourcesUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.GooderAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.details.GoodsDtailContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.JDXiangqing;
import com.mhy.shopingphone.model.bean.Xiangqing;
import com.mhy.shopingphone.model.bean.shop.GoodesBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopDetailBean;
import com.mhy.shopingphone.presenter.detail.GoodsDtailPresenter;
import com.mhy.shopingphone.ui.activity.recharge.RechargeCardActivty;
import com.mhy.shopingphone.view.webview.WebViewActivity;
import com.youth.banner.Banner;
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
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import okhttp3.Call;

import static com.mhy.shopingphone.constant.InternKeyConstant.INTENT_KEY_MOVIE_SUBJECTBEAN;

public class ShopJDDtailsActivity extends BaseMVPCompatActivity<GoodsDtailContract
        .GoodsDtailPresenter, GoodsDtailContract.IGoodsDtailModel> implements
        GoodsDtailContract.IGoodsDtailView {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.rv_shop)
    RecyclerView rv_shop;
    @BindView(R.id.fragment_rotate_header_with_text_view_frame)
    PtrClassicFrameLayout mPtrFrame;
    @BindView(R.id.ll_qq)
    LinearLayout llQQ;
    @BindView(R.id.tv_fenxiang)
    TextView tvFenxiang;
    @BindView(R.id.tv_duihuan)
    TextView tvDuihuan;
    @BindView(R.id.al_back)
    RelativeLayout al_back;
    private boolean isRefresh = false; //标记是否是 下拉刷新
    private boolean lazyFlag = true;
    private String strTag;
    private Uri uri;
    private String goods_id;
    private JDXiangqing shopDetailBean;
    private String dContent;
    private GooderAdapter goodsAdapter;
    private List<GoodesBean.JsonBean> lists;
    private GoodesBean.JsonBean infoBean;
    private View headView;
    private int index;
    private Banner mBannerView;   //详情图片
    private TextView tv_gd_tite;      //商品名称
    private TextView tv_gd_price;       //淘宝价格
    private TextView tv_gd_discount;     //可折扣价格
    private TextView tv_gd_xl;            //销量
    private TextView youxiaoqi;           //有效期
    private int isbool;

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        llQQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url11 = "mqqwpa://im/chat?chat_type=wpa&uin=1558753783&version=1";
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url11)));
                } catch (Exception e) {
                    // 未安装手Q或安装的版本不支持    showToast("未安装手Q或安装的版本不支持");
                    ToastUtils.showToast("未安装手Q或安装的版本不支持");
                }
            }
        });
        al_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        if (getIntent() != null) {
            goods_id = getIntent().getStringExtra("goods_id");
        }
        tvFenxiang.setOnClickListener(new View.OnClickListener() {    //分享商品
            @Override
            public void onClick(View view) {
                showDialogUpdates();
            }
        });
        mPtrFrame.setLastUpdateTimeRelateObject(this);    //下拉刷新
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                isRefresh = true;
                mPtrFrame.refreshComplete();
            }
        });

        if (!TextUtils.isEmpty(goods_id)) {
            getDetails();
        } else {
            ToastUtils.showToast("参数错误");
        }
        goodsAdapter = new GooderAdapter(R.layout.item_shop);
        rv_shop.setAdapter(goodsAdapter);
        rv_shop.setLayoutManager(new LinearLayoutManager(mContext));
        lists = (List<GoodesBean.JsonBean>) getIntent().getSerializableExtra("remen");
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_xiangqing;
    }

    /**
     * @param context   activity
     * @param infoBean  bean
     * @param imageView imageView
     */
    public static void start(Activity context, GoodsBean.JsonBean.CommoditiesBean infoBean, ImageView imageView) {
        Intent intent = new Intent(context, ShopJDDtailsActivity.class);
        intent.putExtra(INTENT_KEY_MOVIE_SUBJECTBEAN, infoBean);
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

    private void showDialogUpdates() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setMessage("暂未开放，详情点击反馈咨询。").
                // 设置确定按钮
                        setPositiveButton("确定", null);

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();
    }

    /**
     * 购物兑换的提示
     */
    private void showDialogUpdate() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setMessage(dContent).setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        goNow();
                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();

    }

    /**
     * 购物兑换的提示
     */
    private void showDialogUpdate3() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setMessage(dContent).setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        if (AppUtils.isInstallApp("com.jingdong.app.mall")) {
//                            Intent intent = new Intent();
//                            intent.setAction("Android.intent.action.VIEW");
//                            uri = Uri.parse(shopDetailBean.getJson().getCouponList().replace("https://", "taobao://")); // 商品地址
//                            intent.setClassName("com.jingdong.app.mall", "com.taobao.browser.BrowserActivity");
//                            intent.setData(uri);
//                            startActivity(intent);
//                        } else {
                        WebViewActivity.skip(ShopJDDtailsActivity.this, shopDetailBean.getJson().getCouponList(), "粉丝福利购");
//                        }
                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();

    }

    /**
     * 购物兑换的提示
     */
    private void showDialogUpdate2() {
        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // 设置提示框的标题
        builder.setMessage(dContent).setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("去充券", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(RechargeCardActivty.class);

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        goNow();
                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();

    }

    //抵扣兑换
    private void goNow() {
        Map<String, String> params = new HashMap<>();
        //请求参数/jdshop/shopByBlance
//        params.put("ParentId", String.valueOf(Contant.PARENTID));//代理商id
//        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));//SharedPreferencesHelper.getInstance().saveData("Mobile", jsonObject.getString("Mobile"));//手机号码
        params.put("Prefix", shopDetailBean.getJson().getMateriaUrl());//详情地址
        params.put("Role", shopDetailBean.getJson().getDiscount());//可抵扣金额
        params.put("skuid", shopDetailBean.getJson().getSkuId());

        OkHttpUtils.post()
                .url(Api.NEWGOODS + "jdshop/shopByBlance")
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        ToastUtils.showToast("请检查您的网络设置");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Ceshi list = new Gson().fromJson(response, Ceshi.class);
                        if (list.getErrorCode() == 2000) {
                            isbool = 9527;
                          /*  Contant.gwjMoney = list.getShopMoney();
                            Contant.phoneMoney = list.getMoney();
                            shopDetailBean.getJson().setGoodstype(9527);*/
                            jingdong();
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
                                            ToastUtils.showToast("请检查您的网络");
                                        }

                                        @Override
                                        public void onResponse(String response, int id) {
                                            Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                                            if (ceshi.getErrorCode() == 2000) {
                                                SharedPreferencesHelper.getInstance().saveData("Tokens", ceshi.getData());//代理Id
                                                goNow();
                                            } else {
                                                ToastUtils.showToast("请检查您的网络");
                                            }
                                        }
                                    });
                        } else if (list.getErrorCode() == 1012) {
                            jingdong();
                        } else {
                            ToastUtils.showToast(list.getData());
                        }
                    }
                });
    }

    private void jingdong() {
//        if (AppUtils.isInstallApp("com.jingdong.app.mall")) {
//            Intent intent = new Intent();
//            intent.setAction("Android.intent.action.VIEW");
//            // 商品地址
//            uri = Uri.parse(shopDetailBean.getJson().getCouponList().replace("https://", "taobao://")); // 商品地址
//            XLog.e("URL:" + uri.toString());
//            intent.setClassName("com.jingdong.app.mall", "com.taobao.browser.BrowserActivity");
//            intent.setData(uri);
//            startActivity(intent);
//        } else {
        WebViewActivity.skip(ShopJDDtailsActivity.this, shopDetailBean.getJson().getCouponList(), "粉丝福利购");
//        }
    }

    //请求
    private void getDetails() {
        Map<String, String> params = new HashMap<>();
        //请求参数
        params.put("userId", String.valueOf(SharedPreferencesHelper.getInstance().getData("UserId", "")));//商户id
        params.put("skuid", goods_id);//商品ID

        OkHttpUtils.post()
                .url(Api.NEWGOODS + "jdshop/shopDetail")
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JDXiangqing bean = new Gson().fromJson(response, JDXiangqing.class);
                        goodsAdapter = new GooderAdapter(R.layout.item_shop, lists);
                        goodsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                index = position;
                                infoBean = (GoodesBean.JsonBean) adapter.getItem(position);
                                Bundle bundle = new Bundle();
                                bundle.putString("goods_id", infoBean.getSkuId());
                                startNewActivity(ShopJDDtailsActivity.class, bundle);
                            }
                        });
                        initHeadView();
                        goodsAdapter.addHeaderView(headView);
                        rv_shop.setAdapter(goodsAdapter);
                        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
                        rv_shop.setLayoutManager(new StaggeredGridLayoutManager(2,
                                StaggeredGridLayoutManager.VERTICAL));
                        List<String> strings = new ArrayList<>();
                        if (bean.getErrorCode() == 2000) {
                            shopDetailBean = bean;
                            tv_gd_tite.setText(bean.getJson().getSkuName());           //商品名称
                            tv_gd_price.setText(bean.getJson().getWlPrice());        //淘宝价格
                            tv_gd_discount.setText(bean.getJson().getDiscount());   //可折扣价格
                            tv_gd_xl.setText("销量 " + "0");       //销量

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
                            long lcc_time = Long.valueOf(bean.getJson().getEndTime());
                            String re_StrTime = sdf.format(new Date(lcc_time));
                            //  时间戳转为字符串
//                            youxiaoqi.setText("有效期 " + re_StrTime);
                            youxiaoqi.setVisibility(View.GONE);
                            SharedPreferencesHelper.getInstance().saveData("ok", "");
                            strings.add(bean.getJson().getPicUrl());
                            Util.setBanner(null, strings, mBannerView);//设置轮播图
                            isbool = shopDetailBean.getJson().getGoodstype();
                            tvDuihuan.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    if (isbool == 9527) {
                                        dContent = "该商品已经领取过优惠券，此次操作不扣取任何余额";
                                        showDialogUpdate3();
                                    } else {
                                        if (TextUtils.isEmpty(Contant.gwjMoney)) {
                                            Contant.gwjMoney = "0";
                                        }
                                        float gWj = Float.parseFloat(Contant.gwjMoney);
                                        float zhekou = Float.parseFloat(shopDetailBean.getJson().getDiscount());
                                        if (gWj >= zhekou) {
                                            dContent = "此操作不可撤销，请确认使用购物券余额" + shopDetailBean.getJson().getDiscount() + "元兑换此商品现金优惠券";
                                            showDialogUpdate();
                                        } else if (0 < gWj && gWj < zhekou) {
                                            dContent = "此操作不可撤销，请确认使用余额" + shopDetailBean.getJson().getDiscount() + "元兑换此商品现金优惠券,本次操作将从购物余额中扣除，不足的部分扣除账户余额。";
                                            showDialogUpdate();
                                        } else if (gWj == 0) {
                                            dContent = "此操作不可撤销，请确认使用账户余额" + shopDetailBean.getJson().getDiscount() + "兑换此商品现金优惠券";
                                            showDialogUpdate();
                                        } else {
                                            dContent = "购物券余额不足，不足的部分将从您的话费中扣除";
                                            showDialogUpdate2();
                                        }
                                    }
                                }
                            });
                        } else {
//                            SharedPreferencesHelper.getInstance().saveData("ok", "ok");
//                            ToastUtils.showToast(bean.getData() + "");
//                            finish();
                        }
                    }
                });
    }

    @Override
    public void update(Ceshi list) {

    }

    @Override
    public void showDetails(ShopDetailBean bean) {

    }

    private void initHeadView() {
        if (headView == null) {
            headView = ResourcesUtils.inflate(R.layout.activity_goods_dtail);
        }
        mBannerView = headView.findViewById(R.id.banner_shopping_mall);    //详情图片
        youxiaoqi = headView.findViewById(R.id.youxiaoqi);                     //有效期
        tv_gd_tite = headView.findViewById(R.id.tv_gd_tite);                     //商品内容
        tv_gd_price = headView.findViewById(R.id.tv_gd_price);                  //淘宝价格
        tv_gd_discount = headView.findViewById(R.id.tv_gd_discount);            //可折扣价格
        tv_gd_xl = headView.findViewById(R.id.tv_gd_xl);                          //销量
//        ll_duihuanjuan = headView.findViewById(R.id.ll_duihuanjuan);                //兑换
        headView.findViewById(R.id.rl_gd_detail).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (shopDetailBean == null) {
                    return;
                }
                if (AppUtils.isInstallApp("com.jingdong.app.mall")) { //跳转京东详情界面
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("openApp.jdMobile://virtual?params={\"category\":\"jump\",\"des\":\"productDetail\",\"skuId\":\"" + shopDetailBean.getJson().getSkuId() + "\",\"sourceType\":\"JSHOP_SOURCE_TYPE\",\"sourceValue\":\"JSHOP_SOURCE_VALUE\"}"));
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } else {
                    WebViewActivity.skip(ShopJDDtailsActivity.this, shopDetailBean.getJson().getMateriaUrl(), "商品详情");
                }
            }
        });

        headView.findViewById(R.id.ll_duihuanjuan).setOnClickListener(new View.OnClickListener() {      //兑换按钮
            @Override
            public void onClick(View view) {
                if (shopDetailBean == null) {
                    return;
                }
//                isbool = shopDetailBean.getJson().getGoodstype();
                if (isbool == 9527) {
                    dContent = "该商品已经领取过优惠券，此次操作不扣取任何话费";
                    showDialogUpdate3();
                } else {
                    if (TextUtils.isEmpty(Contant.gwjMoney)) {
                        Contant.gwjMoney = "0";
                    }
                    float gWj = Float.parseFloat(Contant.gwjMoney);
                    float dikou = Float.parseFloat(shopDetailBean.getJson().getDiscount());
                    if (gWj >= dikou) {
                        dContent = "确定用" + shopDetailBean.getJson().getDiscount() + "元话费抵扣" + shopDetailBean.getJson().getDiscount() + "元现金吗，将从您的购物券中扣除";
                        showDialogUpdate();
                    } else if (gWj == 0) {
                        dContent = "确定用" + shopDetailBean.getJson().getDiscount() + "元话费抵扣" + shopDetailBean.getJson().getDiscount() + "元现金吗，将从您的账户余额中扣除";
                        showDialogUpdate();
                    } else {
                        dContent = "购物券余额不足，不足的部分将从您的话费中扣除";
                        showDialogUpdate2();
                    }
                }
            }
        });
    }

    @Override
    public void showNetworkError() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!lazyFlag) {
            strTag = (String) SharedPreferencesHelper.getInstance().getData("ok", "");
            if (strTag.equals("ok")) {
                goodsAdapter.remove(index);
                SharedPreferencesHelper.getInstance().saveData("ok", "");
            }
        }
        lazyFlag = false;
    }
}
