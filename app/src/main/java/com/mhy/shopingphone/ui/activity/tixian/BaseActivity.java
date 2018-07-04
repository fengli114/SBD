package com.mhy.shopingphone.ui.activity.tixian;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.WindowManager;
import android.widget.Toast;

import com.jaeger.library.StatusBarUtil;
import com.mhy.sdk.utils.StatusBarUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {
    private boolean isExit = false; // 是否退出按钮的转态标记
    protected Context mContext;
    protected final int DEFAULT_STATUS_BAR_ALPHA = 30;

    protected int page = 1;
    protected int count = 20;
    protected boolean canLoadmore = true;
    protected Unbinder mbinder;
    protected Toast toast = null;
    private static final String TAG = "BaseActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        StatusBarUtils.setTransparent(this);
        setContentView(getLayout());
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            StatusBarCompat.setStatusBarColor(this, getResources().getColor(R.color.white));
//          }
setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        mContext = this;
        mbinder = ButterKnife.bind(this);
        initDate();
    }

    /**
     * 设置沉浸式标题栏 图片的
     */
    protected void setStatusBarTranslucent() {
        StatusBarUtil.setTranslucent(this, DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置沉浸式标题栏 标题栏的颜色
     */
    protected void setStatusBarColor(int color) {
        StatusBarUtil.setColor(this, ContextCompat.getColor(mContext, color), DEFAULT_STATUS_BAR_ALPHA);
    }

    /**
     * 设置沉浸式标题栏 里面是Fragment
     */
    protected void setStatusBarColorInFragment() {
        StatusBarUtil.setTranslucentForImageViewInFragment(this, DEFAULT_STATUS_BAR_ALPHA, null);
    }

    //    @Override
//    protected LinearLayout.LayoutParams generateTitleViewLayoutParams()
//    {
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, SDResourcesUtil.getDimensionPixelSize(R.dimen.height_title_bar));
//        return params;
//    }
    protected abstract int getLayout();

    //  protected abstract void initView(Bundle savedInstanceState);

    protected abstract void initDate();

    protected void showToast(String message) {
        if (!TextUtils.isEmpty(message)) {
            if (toast == null) {
                toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
            } else {
                toast.setText(message);
            }
            toast.show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    protected void openActivity(Class<?> pClass) {
        openActivity(pClass, null);
    }

    /**
     * 打开一个新的activity 之前的activity 都清除了
     *
     * @param pClass
     */
    protected void openActivityByNew(Class<?> pClass) {
        Intent i = new Intent(this, pClass);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(i);
    }

    /**
     * 跳转页面
     *
     * @param pClass  跳转的页面
     * @param pBundle 传递过去的参数
     */
    protected void openActivity(Class<?> pClass, Bundle pBundle) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
        }
        startActivity(intent);
        // 动画效果
        // overridePendingTransition(R.anim.zoomin, R.anim.zoomout);
    }

    /**
     * 启动带结果的Activity
     *
     * @param pClass
     * @param pBundle
     * @param requestCode
     */
    protected void openActivityForResult(Class<?> pClass, Bundle pBundle, int requestCode) {
        Intent intent = new Intent(this, pClass);
        if (pBundle != null) {
            intent.putExtras(pBundle);
            startActivityForResult(intent, requestCode);
        } else {
            startActivityForResult(intent, requestCode);
        }
    }

    /**
     * 双击退出程序
     */
    protected void exitBy2click() {
        Timer eExit = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(BaseActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            eExit = new Timer();
            eExit.schedule(new TimerTask() {

                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    public void finish() {
        super.finish();
        // 动画效果
        // overridePendingTransition(R.anim.zoomin, R.anim.zoomin);
    }

    public void defaultFinish() {
        super.finish();
    }


    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mbinder.unbind();
    }

    public static final int PERMISSION_REQUEST_CODE = 0x0001;
    public static PermissionListeren mLsteren = null;
    public static int REQUEST_CODE = 0;

    public static void requestPermission(int requestCode, PermissionListeren listeren, String... permission) {
        REQUEST_CODE = requestCode;
        List<String> permissionList = new ArrayList<>();
        Activity activity = ActivityManagerUtils.getInstance().getTopActivity();
        mLsteren = listeren;
        for (String s : permission) {
            if (ContextCompat.checkSelfPermission(activity, s) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(s);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(activity, permissionList.toArray(new String[permissionList.size()]),
                    PERMISSION_REQUEST_CODE);
        } else {
            listeren.onGranted(REQUEST_CODE);
        }
    }

    /**
     * 请求权限
     */
    protected void requestPermissions(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {
                    List<String> permissionDenieds = new ArrayList<>();
                    List<String> noReminderPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        String permission = permissions[i];
                        if (grantResults[i] == PackageManager.PERMISSION_DENIED) {
                            permissionDenieds.add(permission);
                            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                                noReminderPermissions.add(permission);
                            }
                        }
                    }
                    if (!permissionDenieds.isEmpty()) {
                        mLsteren.onDenied(REQUEST_CODE, permissionDenieds, noReminderPermissions);
                    } else {
                        mLsteren.onGranted(REQUEST_CODE);
                    }
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                break;
        }
    }

    public interface PermissionListeren {

        //全部同意授权
        void onGranted(int requestCode);


        /**
         * 不同意授权
         *
         * @param deniedPermissions     未同意但是没有点不再提醒
         * @param noReminderPermissions 未同意并且点了不再提醒
         */
        void onDenied(int requestCode, List<String> deniedPermissions, List<String> noReminderPermissions);
    }
}