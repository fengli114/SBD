package com.mhy.shopingphone.widgets;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zhangyf on 2017/8/24.
 */

public class NewSpacesItemDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public NewSpacesItemDecoration(int space) {
        this.space = space;
    }

    /**
     * 仅支持2列
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    @Override
    public void getItemOffsets(Rect outRect, View view,
                               RecyclerView parent, RecyclerView.State state) {
//        outRect.left=space;
        outRect.right=space;
//        outRect.bottom=space;
        //注释这两行是为了上下间距相同
//        if(parent.getChildAdapterPosition(view)==0){
        outRect.top=space;
//        }
    }

}
