package com.mhy.shopingphone.widgets;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 描述： 不可左右滑动的viewpager
 * 作者：F.L
 * 时间: 2017-05-31 17:00
 */
public class NonSlipViewPager extends ViewPager {
    public NonSlipViewPager(Context context) {
        super(context);
    }

    public NonSlipViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent arg0) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0) {
        return false;
    }
}
