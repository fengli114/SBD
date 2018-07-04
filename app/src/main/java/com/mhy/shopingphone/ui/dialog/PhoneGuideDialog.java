package com.mhy.shopingphone.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 描述：领完的礼包会显示的对话框
 * Created by F.L on 2017/4/6.
 */

public class PhoneGuideDialog extends Dialog {


    private View view;

    private View.OnClickListener onClickListener;

    public PhoneGuideDialog(Context context) {
        super(context);
    }

    public PhoneGuideDialog(Context context, int themeResId) {
        super(context, themeResId);
        this.onClickListener = onClickListener;
        view = LinearLayout.inflate(context, R.layout.phone_guide_view2, null);
    }

    protected PhoneGuideDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        ButterKnife.bind(this, view);
        setContentView(view);
        initView();

    }

    private void initView() {


    }


    @OnClick(R.id.iv_guide_yes)
    public void onViewClicked() {
        dismiss();
    }


}
