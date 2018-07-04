package com.mhy.sdk.base.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhy.sdk.R;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.IBaseModel;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;


/**
 * Created by Horrarndoo on 2017/10/17.
 * <p>
 * 带RecycleView加载状态view的fragment，主要用于显示加载中、空界面、加载失败等状态界面显示
 */

public abstract class BaseRecycleActivty<P extends BasePresenter, M extends IBaseModel> extends
        BaseMVPCompatActivity<P, M> {
    /**
     * 网络异常View
     */
    protected View errorView;
    /**
     * loadingView
     */
    protected View loadingView;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    /**
     * 网络异常view被点击时触发，由子类实现
     *
     * @param view view
     */
    protected abstract void onErrorViewClick(View view);

    /**
     * 显示加载中view，由子类实现
     */
    protected abstract void showLoading();
}
