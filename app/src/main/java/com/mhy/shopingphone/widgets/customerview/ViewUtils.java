package com.mhy.shopingphone.widgets.customerview;

import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ViewUtils {
    public static void setBackgroundDrawable(View view, Drawable drawable)
    {
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        view.setBackgroundDrawable(drawable);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
    public static void setBackgroundResource(View view, int resId)
    {
        int paddingTop = view.getPaddingTop();
        int paddingBottom = view.getPaddingBottom();
        int paddingLeft = view.getPaddingLeft();
        int paddingRight = view.getPaddingRight();
        view.setBackgroundResource(resId);
        view.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }
    public static int dp2px(float dp)
    {
        final float scale = getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
    public static DisplayMetrics getDisplayMetrics()
    {
        return SDLibrary.getInstance().getApplication().getResources().getDisplayMetrics();
    }
}
