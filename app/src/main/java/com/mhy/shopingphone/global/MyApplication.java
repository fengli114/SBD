package com.mhy.shopingphone.global;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

import com.blankj.utilcode.util.Utils;
import com.mhy.sdk.global.GlobalApplication;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.shopingphone.model.bean.greendao.DaoMaster;
import com.mhy.shopingphone.model.bean.greendao.DaoSession;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;


import org.xutils.x;

import java.util.HashSet;
import java.util.Set;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Horrarndoo on 2017/9/6.
 * <p>
 */

public class MyApplication extends  GlobalApplication{
    //这个key是自己在聚合数据申请的key，需要自己去聚合数据申请
    public static final String JU_HE_APP_KEY = "799b785ba7b97223be80534651dd0d63";
    public static int SCREEN_WIDTH = -1;
    public static int SCREEN_HEIGHT = -1;
    public static float DIMEN_RATE = -1.0F;
    public static int DIMEN_DPI = -1;
    public static MyApplication app;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        x.Ext.init(this);                    //xutils初始化
        //配置数据库
        setupDatabase();
//        UMConfigure.init(app, 0, null);
        //初始化屏幕宽高
        getScreenSize();
        Utils.init(this);                   //工具类 初始化
        UMConfigure.init(this,UMConfigure.DEVICE_TYPE_PHONE, "1fe6a20054bcef865eeb0991ee84525b");
//        MobclickAgent.setSecret(Context context, String secretkey)
        CrashReport.initCrashReport(getApplicationContext(), "c73ecc54da", false);

        SharedPreferencesHelper.init(getApplicationContext());

        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
        //极光推送初始化sdk
        JPushInterface.setDebugMode(true);//正式版的时候设置false，关闭调试
        JPushInterface.init(this);
        //建议添加tag标签，发送消息的之后就可以指定tag标签来发送了
        Set<String> set = new HashSet<>();
        set.add("andfixdemo");//名字任意，可多添加几个
        JPushInterface.setTags(this, set, null);//设置标签
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    /**
     * 配置数据库
     */
    private void setupDatabase() {
        //创建数据库shop.db"
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "shop.db", null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        DaoMaster daoMaster = new DaoMaster(db);
        //获取Dao对象管理者
        daoSession = daoMaster.newSession();
    }

    public static DaoSession getDaoInstant() {
        return daoSession;
    }
    public void getScreenSize() {
        WindowManager windowManager = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        Display display = windowManager.getDefaultDisplay();
        display.getMetrics(dm);
        DIMEN_RATE = dm.density / 1.0F;
        DIMEN_DPI = dm.densityDpi;
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.heightPixels;
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int t = SCREEN_HEIGHT;
            SCREEN_HEIGHT = SCREEN_WIDTH;
            SCREEN_WIDTH = t;
        }
    }
}
