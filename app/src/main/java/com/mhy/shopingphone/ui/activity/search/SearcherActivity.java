package com.mhy.shopingphone.ui.activity.search;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.shop.GoodesBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.ui.fragment.shop.child.ShoperActivity;
import com.mhy.shopingphone.widgets.NonSlipViewPager;
import com.youth.xframe.utils.XEmptyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class SearcherActivity extends BaseMVPCompatActivity {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.rl_remove)
    RelativeLayout rlRemove;
    @BindView(R.id.tv_exit)
    TextView tvExit;
    @BindView(R.id.rl_remove_empty)
    LinearLayout rlRemoveEmpty;
    @BindView(R.id.tv_no)
    TextView tvNo;
    @BindView(R.id.fl_history)
    FlexboxLayout flHistory;
    @BindView(R.id.fl_hot)
    FlexboxLayout flHot;
    @BindView(R.id.vp_search)
    NonSlipViewPager vpSearch;
    @BindView(R.id.activity_search)
    LinearLayout activitySearch;
    private List<String> mHistoryKeywords;//记录文本
    public static final String EXTRA_KEY_TYPE = "extra_key_type";
    public static final String EXTRA_KEY_KEYWORD = "extra_key_keyword";
    public static final String KEY_SEARCH_HISTORY_KEYWORD = "key_search_history_keyword";
    private SharedPreferences mPref;//使用SharedPreferences记录搜索历史
    private GoodesBean goodsBean;

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext,tou);
        Util.showSoftInputFromWindow(this,editSearch);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            goodsBean = (GoodesBean) bundle.getSerializable("data");
        }
//        getInfo();
      /*  if (goodsBean.getJson().getCategories() != null && goodsBean.getJson().getCategories().size() > 0) {
            setHot(goodsBean.getJson().getCategories());
        }*/
        flHistory.removeAllViews();
        mPref = getSharedPreferences("input", Activity.MODE_PRIVATE);
        mHistoryKeywords = new ArrayList<>();//记录文本

        String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(history)) {
            List<String> list = new ArrayList<String>();
            for (Object o : history.split(",")) {
                list.add((String) o);
            }
            mHistoryKeywords = list;
        }
        if (mHistoryKeywords.size()>0){
            tvNo.setVisibility(View.GONE);
        }else {
            tvNo.setVisibility(View.VISIBLE);
        }
        //获得搜索历史
//        searchHistory = SerachUtils.getInstance().getSearchList();
        setHistory(mHistoryKeywords);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        flHistory.removeAllViews();
        mPref = getSharedPreferences("input", Activity.MODE_PRIVATE);
        mHistoryKeywords = new ArrayList<>();//记录文本

        String history = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        if (!TextUtils.isEmpty(history)) {
            List<String> list = new ArrayList<String>();
            for (Object o : history.split(",")) {
                list.add((String) o);
            }
            mHistoryKeywords = list;
        }
        if (mHistoryKeywords.size()>0){
            tvNo.setVisibility(View.GONE);
            rlRemoveEmpty.setVisibility(View.VISIBLE);
        }else {
            tvNo.setVisibility(View.VISIBLE);
            rlRemoveEmpty.setVisibility(View.GONE);
        }
        //获得搜索历史
//        searchHistory = SerachUtils.getInstance().getSearchList();
        setHistory(mHistoryKeywords);
    }

//    private void getInfo() {
//        Map<String, String> params = new HashMap<>();
//        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
//        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));
//        String paramsstring = StringUtil.mapToJson(params);
//        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
//        //设置头部
//        HttpUtils.headStr = Contant.SHOPPING_BANNERHead;
//        RxManager mRxManager = new RxManager();
//        Observable<ShopBannerBean> compose = RetrofitCreateHelper.createApi(Api.class, Api.GOODS).getShopBannerData(paramstr)
//                .compose(RxHelper.<ShopBannerBean>rxSchedulerHelper());
//        mRxManager.register(compose.subscribe(new Consumer<ShopBannerBean>() {
//            @Override
//            public void accept(ShopBannerBean bean) throws Exception {
//                if (bean.getCode().equals("0")) {
//
//                    if (bean.getCInfo() != null && bean.getCInfo().size() > 0) {
//                        setHot(bean.getCInfo());
//                    } else {
//                        ToastUtils.showToast(bean.getMess());
//                    }
//                } else {
//                    ToastUtils.showToast(bean.getMess());
//                }
//            }
//        }, new Consumer<Throwable>() {
//            @Override
//            public void accept(Throwable throwable) throws Exception {
//                ToastUtils.showToast("Network error.");
//            }
//        }));
//    }

  /*  private void setHot(List<GoodesBean.JsonBean> listHot) {
        //测试效果模拟数据

        if (listHot != null && listHot.size() > 0) {
            for (int i = 0; i < listHot.size(); i++) {
                flHot.addView(SetHotText(listHot.get(i).getCate(), i));
            }
        }
    }*/

    /**
     * 动态创建热门推荐TextView
     * (APP做好后，String text可替换为相应的java bean)
     *
     * @param text
     * @param id
     * @return
     */
 /*   private TextView SetHotText(final String text, final int id) {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setTextSize(15);
        textView.setTextColor(Color.parseColor("#333333"));
//        textView.setBackground(getResources().getDrawable(R.drawable.textview_btn));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {   //文字条目点击事件
                Bundle bundle = new Bundle();
                bundle.putString("typeName", text);
                bundle.putString("category", text);
                bundle.putInt("SearchType", 0);
                bundle.putInt("type", 1);
                startNewActivity(ShoperActivity.class,bundle);//文字条目点击事件
//                flHistory.addView(SetHistoryText(text));
                Log.e("搜索热门推荐点击了", text + ",id是" + id);

            }
        });
        int padding = Util.dpToPixel(this, 5);
        int paddingLeftAndRight = Util.dpToPixel(this, 10);
        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        textView.setLayoutParams(layoutParams);
        return textView;
    }*/
    /**
     * 储存搜索历史
     */
    public void save(String text) {
        String oldText = mPref.getString(KEY_SEARCH_HISTORY_KEYWORD, "");
        Log.e("tag", "" + oldText);
        Log.e("Tag", "" + text);
        Log.e("Tag", "" + oldText.contains(text));
        if (!TextUtils.isEmpty(text) && !(oldText.contains(text))) {
            if (mHistoryKeywords.size() > 5) {
                //最多保存条数
                return;
            }
            SharedPreferences.Editor editor = mPref.edit();
            editor.putString(KEY_SEARCH_HISTORY_KEYWORD, text + "," + oldText);
            editor.commit();
            mHistoryKeywords.add(0, text);
        }
    }
    private void setHistory(List<String> listHot) {
        //测试效果模拟数据
        if (listHot != null && listHot.size() > 0) {
            for (int i = 0; i < listHot.size(); i++) {
                flHistory.addView(SetHistoryText(listHot.get(listHot.size() - i - 1)));
            }
        }
    }

    /**
     * 动态添加历史搜索TextView
     *
     * @param text
     * @return
     */
    private TextView SetHistoryText(final String text) {
        TextView textView = new TextView(this);
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setTextSize(15);
        textView.setTextColor(Color.parseColor("#333333"));
//        textView.setBackground(getResources().getDrawable(R.drawable.textview_btn));
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("Name", text);
                bundle.putString("typeName", "搜索结果");
                bundle.putInt("SearchType", 0);
                bundle.putInt("type", 1);
                startNewActivity(ShoperActivity.class,bundle);//文字条目点击事件
            }
        });
        int padding = Util.dpToPixel(this, 5);
        int paddingLeftAndRight = Util.dpToPixel(this, 10);
        ViewCompat.setPaddingRelative(textView, paddingLeftAndRight, padding, paddingLeftAndRight, padding);
        FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10, 10, 10, 10);
        textView.setLayoutParams(layoutParams);
        return textView;
    }



    @OnClick({R.id.iv_back, R.id.tv_exit ,R.id.rl_remove, R.id.rl_remove_empty})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_remove:
                editSearch.setText("");
                break;
            case R.id.rl_remove_empty:
                showDialogUpdate();
                break;
            case R.id.tv_exit:
                if (!XEmptyUtils.isEmpty(editSearch.getText().toString())) {
//                    flHistory.addView(SetHistoryText(etShuRu.getText().toString()));
                    save(editSearch.getText().toString());
                    Bundle bundle = new Bundle();
                    bundle.putInt("type", 1);
                    bundle.putString("Name", editSearch.getText().toString());
                    bundle.putString("typeName", "搜索结果");
                    bundle.putInt("SearchType", 0);
                    startNewActivity(ShoperActivity.class,bundle);//文字条目点击事件
                } else {
                    ToastUtils.showToast("请输入搜索内容~");
                }
                break;
        }
    }


    private void showDialogUpdate() {
            // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // 设置提示框的标题
            builder.setMessage("确定删除全部搜索历史记录？").
                    // 设置确定按钮
                            setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(mActivity, "选择确定哦", 0).show();
                            // loadNewVersionProgress();//下载最新的版本程序
                            cleanHistory();
                        }
                    }).

                    // 设置取消按钮,null是什么都不做，并关闭对话框
                            setNegativeButton("取消", null);

            // 生产对话框
            AlertDialog alertDialog = builder.create();
            alertDialog.setCanceledOnTouchOutside(false);
            // 显示对话框
            alertDialog.show();

    }
    /**
     * 清除历史纪录
     */
    public void cleanHistory() {
        mPref = getSharedPreferences("input", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();
        editor.remove(KEY_SEARCH_HISTORY_KEYWORD).commit();
        mHistoryKeywords.clear();
        flHistory.removeAllViews();
        tvNo.setVisibility(View.VISIBLE);
        rlRemoveEmpty.setVisibility(View.GONE);
        ToastUtils.showToast( "清除搜索历史成功~");
    }
}
