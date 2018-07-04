package com.mhy.shopingphone.ui;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.text.TextUtils;

import com.blankj.utilcode.util.AppUtils;

import java.io.File;

import cn.trinea.android.common.util.PreferencesUtils;

/**
 * 功能描述：监听apk下载后安装的广播
 * <p>
 * 创 建 人：文啓金
 * 创建时间：2017-09-29
 * 版 本 号：V1.0
 * 修 改 人：文啓金（2017-09-29）
 */
public class UpdataBroadcastReceiver extends BroadcastReceiver {

    @SuppressLint("NewApi")
    public void onReceive(Context context, Intent intent) {
        long downLoadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
        long cacheDownLoadId = PreferencesUtils.getLong(context, "download_id");
        if (cacheDownLoadId == downLoadId) {
            install(context);
        }
    }

    private void install(Context context) {
//        Intent install = new Intent(Intent.ACTION_VIEW);

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //先获取是否有安装未知来源应用的权限
            boolean haveInstallPermission = getPackageManager().canRequestPackageInstalls();
            if (!haveInstallPermission) {
                //跳转设置开启允许安装
                Uri packageURI = Uri.parse("package:"+context.getPackageName());
                Intent intent =new Intent(Settings.ACTION_MANAGE_UNKNOWN_APP_SOURCES,packageURI);
                startActivityForResult(intent,1000);
                return;
            }
        }*/
        File apkFile = queryDownloadedApk(context);
//        File apkFile = queryDownloadedApk(context);
        AppUtils.installApp(apkFile, "com.mhy.shopingphone.fileprovider");
//        install.setDataAndType(Uri.fromFile(apkFile), "application/vnd.android.package-archive");
//        install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        context.startActivity(install);
    }

    //通过downLoadId查询下载的apk，解决6.0以后安装的问题
    public static File queryDownloadedApk(Context context) {
        File targetApkFile = null;
        DownloadManager downloader = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        long downloadId = PreferencesUtils.getLong(context, "download_id");
        if (downloadId != -1) {
            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(downloadId);
            query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
            Cursor cur = downloader.query(query);
            if (cur != null) {
                if (cur.moveToFirst()) {
                    String uriString = cur.getString(cur.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                    if (!TextUtils.isEmpty(uriString)) {
                        targetApkFile = new File(Uri.parse(uriString).getPath());
                    }
                }
                cur.close();
            }
        }
        return targetApkFile;
    }
}