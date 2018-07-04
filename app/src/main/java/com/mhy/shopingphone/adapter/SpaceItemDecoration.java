package com.mhy.shopingphone.adapter;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * 作者： "RedRainM" on 2018/2/1 0001.
 * 描述：
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    private int horizontalspace;
    private int verticalspace;
    public SpaceItemDecoration(int horizontalspace,int verticalspace) {
        this.horizontalspace = horizontalspace;
        this.verticalspace = verticalspace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        //不是第一个的格子都设一个左边和底部的间距
        outRect.left = horizontalspace;
        outRect.bottom = verticalspace;
//        //由于每行都只有3个，所以第一个都是3的倍数，把左边距设为0
        if (parent.getChildLayoutPosition(view) %2==0) {
            outRect.left = 0;
        }
        int childCount = parent.getChildCount();
    }
}
