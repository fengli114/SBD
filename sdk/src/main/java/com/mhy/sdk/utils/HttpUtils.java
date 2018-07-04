package com.mhy.sdk.utils;

import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.WebSettings;

import com.mhy.sdk.contant.Contant;
//import com.orhanobut.logger.Logger;

import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.ContentValues.TAG;

/**
 * Created by Horrarndoo on 2017/9/18.
 * <p>
 * HttpUtils 主要用于获取UserAgent
 */

public class HttpUtils {
    public static String headStr;
    public static String LogHeadStr;

    /**
     * 获取UserAgent
     *
     * @return UserAgent
     */
    @NonNull
    public static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(AppUtils.getContext());
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 获取当前时间
     */
    public static String time() {
        //时间
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
//        URL url = null;//取得资源对象
//        String datatime = null;
//        try {
//            url = new URL("http://www.baidu.com");
//            URLConnection uc = url.openConnection();//生成连接对象
//            uc.connect(); //发出连接
//            long ld=uc.getDate(); //取得网站日期时间
//            Date date=new Date(ld); //转换为标准时间对象
//            datatime = formatter.format(date);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  datatime;
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        //String datatime = formatter.format(curDate).replaceFirst("0","");
        String datatime = formatter.format(curDate);
        if (datatime.split(":")[0].length() >= 2 && datatime.startsWith("0")) {
            datatime = datatime.substring(1, datatime.length());
        }
        return datatime;
    }

    //获取头部
    @NonNull
    public static String getUserAgent2() {
        //请求头
        if (!TextUtils.isEmpty(headStr)) {
            String headmsg = NSRBase64.encodeToString(MD5Util.MD5Encode(headStr + time())).trim();
            return headmsg;
        } else {
            ToastUtils.showToast("头部参数不能为空");
            return null;
        }
    }

    //获取头部
    @NonNull
    public static String getUserAgent3() {
        //请求头
        if (TextUtils.isEmpty(LogHeadStr)) {
            LogHeadStr = "服务器返回数据";
            return LogHeadStr;
        } else {
            return LogHeadStr;
        }
    }


    public static String makeUA() {
        final String ua = Build.BRAND + "/" + Build.MODEL + "/" + Build.VERSION.RELEASE;
        return ua;
    }

    public static String[] returnImageUrlsFromHtml(String html) {
        List<String> imageSrcList = new ArrayList<String>();
        Pattern p = Pattern.compile("<img\\b[^>]*\\bsrc\\b\\s*=\\s*('|\")?([^'\"\n\r\f>]+(\\" +
                ".jpg|\\.bmp|\\.eps|\\.gif|\\.mif|\\.miff|\\.png|\\.tif|\\.tiff|\\.svg|\\.wmf|\\" +
                ".jpe|\\.jpeg|\\.dib|\\.ico|\\.tga|\\.cut|\\.pic|\\b)\\b)[^>]*>", Pattern
                .CASE_INSENSITIVE);
        Matcher m = p.matcher(html);
        String quote = null;
        String src = null;
        while (m.find()) {
            quote = m.group(1);
            src = (quote == null || quote.trim().length() == 0) ? m.group(2).split("//s+")[0] : m
                    .group(2);
            imageSrcList.add(src);
        }
        if (imageSrcList.size() == 0) {
//            Logger.e("资讯中未匹配到图片链接");
            return null;
        }
        return imageSrcList.toArray(new String[imageSrcList.size()]);
    }
}
