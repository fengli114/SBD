package com.mhy.sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.mhy.sdk.contant.Contant;
//import com.orhanobut.logger.Logger;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;


/**
 * 作者： "RedRainM" on 2017/12/21 0021.
 * 描述：
 */

public class Util {
    /**
     * 设置轮播图
     * @param name
     * @param urls
     */
    public static void setBanner(List<String> name, List<String> urls, Banner banner) {
        //设置banner样式
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(urls);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.Default);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles(strings1);
        //设置自动轮播，默认为true
        banner.isAutoPlay(true);
        //设置轮播时间
        banner.setDelayTime(2000);

        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
    }

    public static boolean contains(String cotacNmae, String search) {
        if (TextUtils.isEmpty(cotacNmae)) {
            return false;
        }

        boolean flag = false;
        // 简拼匹配,如果输入在字符串长度大于6就不按首字母匹配了
        if (search.length() < 6) {
            String firstLetters = FirstLetterUtil.getFirstLetter(cotacNmae);
            // 不区分大小写
            Pattern firstLetterMatcher = Pattern.compile(search,
                    Pattern.CASE_INSENSITIVE);
            flag = firstLetterMatcher.matcher(firstLetters).find();
        }

        if (!flag) { // 如果简拼已经找到了，就不使用全拼了
            // 全拼匹配
            CharacterParser finder = CharacterParser.getInstance();
            finder.setResource(cotacNmae);
            // 不区分大小写
            Pattern pattern2 = Pattern
                    .compile(search, Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = pattern2.matcher(finder.getSpelling());
            flag = matcher2.find();
        }

        return flag;
    }

    public static void  setStatusBarHeigh(Context mContext, View tou){
        if (Build.VERSION.SDK_INT  > Build.VERSION_CODES.KITKAT) {
            ViewGroup.LayoutParams param = tou.getLayoutParams();
            // 控件的高强制设成56dp+状态栏高度
            param.height = /*DisplayUtils.dp2px(40) + */StatusBarUtils.getStatusBarHeight
                    (mContext);
            tou.setLayoutParams(param);
        }else {
            tou.setVisibility(View.GONE);
        }

    }

    public static void  setMarginsStatusBar(Context mContext, View al_title){

        if (Build.VERSION.SDK_INT >  Build.VERSION_CODES.KITKAT) {
            // 控件的高强制设成56dp+状态栏高度
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) al_title.getLayoutParams();
            lp.setMargins(0,   StatusBarUtils.getStatusBarHeight
                    (mContext), 0, 0);
            al_title.setLayoutParams(lp);
        }
    }

    /**
     * 搜索页面FlexboxLayout中TextView的相关设置方法
     * @param context
     * @param pixel
     * @return
     */
    public static int pixelToDp(Context context, int pixel) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return pixel < 0 ? pixel : Math.round(pixel / displayMetrics.density);
    }
    public static int dpToPixel(Context context, int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return dp < 0 ? dp : Math.round(dp * displayMetrics.density);
    }

    /**
     * EditText获取焦点并显示软键盘
     */
    public static void showSoftInputFromWindow(Activity activity, EditText editText) {
        editText.setFocusable(true);
        editText.setFocusableInTouchMode(true);
        editText.requestFocus();
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }
    public static int[] getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return new int[]{outMetrics.widthPixels, outMetrics.heightPixels};
    }
    /**
     * 获取当前时间
     */
    public static String time() {
        //时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        //String datatime = formatter.format(curDate).replaceFirst("0","");
        String datatime = formatter.format(curDate);
        LogUtils.e("当前时间：" + datatime);
        return datatime;
//        URL url = null;//取得资源对象
//        String datatime = null;
//        try {
//            url = new URL("http://www.baidu.com");
//            URLConnection uc = url.openConnection();//生成连接对象
//            uc.connect(); //发出连接
//            long ld=uc.getDate(); //取得网站日期时间
//            Date date=new Date(ld); //转换为标准时间对象
//            datatime = formatter.format(date);
//            Logger.i(TAG,"ld---->>>>"+datatime);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  datatime;
    }
}
