package com.mhy.sdk.helper.okhttp;

import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.AppUtils;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NetworkConnectionUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.logging.Level;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

import static com.mhy.sdk.utils.HttpUtils.getUserAgent;
import static com.mhy.sdk.utils.HttpUtils.getUserAgent2;
import static com.mhy.sdk.utils.HttpUtils.getUserAgent3;
import static com.squareup.okhttp.internal.Internal.logger;


/**
 * Created by Horrarndoo on 2017/9/12.
 * <p>
 * CacheInterceptor
 */
public class CacheInterceptor implements Interceptor {

    private Response response;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (NetworkConnectionUtils.isNetworkConnected(AppUtils.getContext())) {
            // 有网络时, 缓存1小时
            int maxAge = 60 * 60;
            request = request.newBuilder()
                    .removeHeader("User-Agent")
                    .header("User-Agent", getUserAgent())
                    .header("Authorization", getUserAgent2())
                    .build();

            response = chain.proceed(request);
            input();
            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
        } else {
            // 无网络时，缓存为4周
            int maxStale = 60 * 60 * 24 * 28;
            request = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_CACHE)
                    .removeHeader("User-Agent")
                    .header("User-Agent", getUserAgent())
                    .header("Authorization", getUserAgent2())
                    .build();

            response = chain.proceed(request);
            input();

            return response.newBuilder()
                    .removeHeader("Pragma")
                    .removeHeader("Cache-Control")
                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                    .build();
        }

        //        Request request = chain.request();
        //        if (!NetworkConnectionUtils.isConnected(AppUtils.getContext())) {
        //            request = request.newBuilder()
        //                    .cacheControl(CacheControl.FORCE_CACHE)
        //                    .build();
        //        }
        //        Response response = chain.proceed(request);
        //        if (NetworkConnectionUtils.isConnected(AppUtils.getContext())) {
        //            int maxAge = 0;
        //            // 有网络时, 不缓存, 最大保存时长为0
        //            response.newBuilder()
        //                    .header("Cache-Control", "public, max-age=" + maxAge)
        //                    .removeHeader("Pragma")
        //                    .build();
        //        } else {
        //            // 无网络时，设置超时为4周
        //            int maxStale = 60 * 60 * 24 * 28;
        //            response.newBuilder()
        //                    .header("Cache-Control", "public, only-if-cached, max-stale=" +
        // maxStale)
        //                    .removeHeader("Pragma")
        //                    .build();
        //        }
        //        return response;
    }

    private void input() {

        if (Contant.IsDebug) {
//            LogUtils.e("response返回参数:" + response.toString());

            //添加打印服务器返回的数据
            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();
            BufferedSource source = responseBody.source();
            try {
                source.request(Integer.MAX_VALUE); // Buffer the entire body.
            } catch (IOException e) {
                e.printStackTrace();
            }
            Buffer buffer = source.buffer();


            if (contentLength != 0) {
                LogUtils.e(getUserAgent3() + buffer.clone().readString(Charset.forName("UTF-8")));
                HttpUtils.LogHeadStr = "";
                Contant.IsDebug = false;
            }
        }
    }

}
