package com.mhy.sdk.utils;

import android.app.DownloadManager;
import android.content.Context;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;


import com.afollestad.materialdialogs.MaterialDialog;
import com.mhy.sdk.R;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import cn.trinea.android.common.util.PreferencesUtils;

import static android.content.Context.DOWNLOAD_SERVICE;


/**
 * DownloadUtil downloadUtil = new DownloadUtil(activity, downloadUrl);
 * //下载显示名字，不能是中文
 * downloadUtil.setDownloadFileName("apkName" + System.currentTimeMillis() + ".apk");
 * downloadUtil.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
 * downloadUtil.start();
 * Created by Administrator
 * on 2016/5/4.
 */
@SuppressWarnings("unused")
public class DownloadUtil {
    private Context mContext;
    public static final String DOWNLOAD_ID = "download_id";
    private DownloadChangeObserver downloadObserver;
    private long lastDownloadId = 0;
    //"content://downloads/my_downloads"必须这样写不可更改
    public static final Uri CONTENT_URI = Uri.parse("content://downloads/my_downloads");
    private MaterialDialog materialDialog;
    private String NetUrl;

    public DownloadUtil(Context context, String downloadUrl) {
        this.mContext = context;
        this.NetUrl = downloadUrl;
        initView();
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new DownLoadTask());
    }

    public class DownLoadTask implements Runnable {

        @Override
        public void run() {
            initDownLoad();
        }
    }

    private void initView() {
        if (materialDialog == null) {
            materialDialog = new MaterialDialog.Builder(mContext)
                    .title("版本更新")
                    .content("正在下载安装包，请稍候...")
                    .progress(false, 100, false)
                    .cancelable(false)
                    .show();
        }
    }

    private void initDownLoad() {
        //1.得到下载对象
        DownloadManager dowanloadmanager = (DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE);
        //2.创建下载请求对象，并且把下载的地址放进去
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(NetUrl));
        //3.给下载的文件指定路径
        request.setDestinationInExternalFilesDir(mContext, Environment.DIRECTORY_DOWNLOADS, "hotelscan.apk");
        //4.设置显示在文件下载Notification（通知栏）中显示的文字。6.0的手机Description不显示
        request.setTitle(mContext.getString(R.string.app_name));
        request.setDescription(mContext.getString(R.string.update_version));
        //5更改服务器返回的minetype为android包类型
        request.setMimeType("application/vnd.android.package-archive");
        //6.设置在什么连接状态下执行下载操作
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE | DownloadManager.Request.NETWORK_WIFI);
        //7. 设置为可被媒体扫描器找到
        request.allowScanningByMediaScanner();
        //8. 设置为可见和可管理
        request.setVisibleInDownloadsUi(true);
        /**
         * 设置notification显示状态
         * Request.VISIBILITY_VISIBLE：在下载进行的过程中，通知栏中会一直显示该下载的Notification，当下载完成时，该Notification会被移除，这是默认的参数值。
         * Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED：在下载过程中通知栏会一直显示该下载的Notification，在下载完成后该Notification会继续显示，直到用户点击该
         * Notification或者消除该Notification。
         * Request.VISIBILITY_VISIBLE_NOTIFY_ONLY_COMPLETION：只有在下载完成后该Notification才会被显示。
         * Request.VISIBILITY_HIDDEN：不显示该下载请求的Notification。如果要使用这个参数，需要在应用的清单文件中加上android.permission.DOWNLOAD_WITHOUT_NOTIFICATION
         */
//        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        lastDownloadId = dowanloadmanager.enqueue(request);
        //9.保存id到缓存
        PreferencesUtils.putLong(mContext, DOWNLOAD_ID, lastDownloadId);
        //10.采用内容观察者模式实现进度
        downloadObserver = new DownloadChangeObserver(null);
        mContext.getContentResolver().registerContentObserver(CONTENT_URI, true, downloadObserver);
    }

    //用于显示下载进度
    class DownloadChangeObserver extends ContentObserver {

        public DownloadChangeObserver(Handler handler) {
            super(handler);
        }

        @Override
        public void onChange(boolean selfChange) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(lastDownloadId);
            DownloadManager dManager = (DownloadManager) mContext.getSystemService(DOWNLOAD_SERVICE);
            final Cursor cursor = dManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                final int totalColumn = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                final int currentColumn = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                int totalSize = cursor.getInt(totalColumn);
                int currentSize = cursor.getInt(currentColumn);
                float percent = (float) currentSize / (float) totalSize;
                int progress = Math.round(percent * 100);
                materialDialog.setProgress(progress);
                if (progress == 100) {
                    materialDialog.dismiss();
                    mHandler.sendEmptyMessageDelayed(0, 1000);
                }
            }
        }
    }

    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            int what = msg.what;
            switch (what) {
                case 0:
                    onDestroy();
                    break;
            }
        }
    };

    public void onDestroy() {
        mContext.getContentResolver().unregisterContentObserver(downloadObserver);
    }
}
