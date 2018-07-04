package com.mhy.shopingphone.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;


import com.mhy.shopingphone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 描述：领完的礼包会显示的对话框
 * Created by F.L on 2017/4/6.
 */

public class BeginDialog extends Dialog {
    @BindView(R.id.btn_shopping_go)
    Button btnDialogOk;


    private View view;

    private View.OnClickListener onClickListener;

    public BeginDialog(Context context) {
        super(context);
    }

    public BeginDialog(Context context, int themeResId, View.OnClickListener onClickListener) {
        super(context, themeResId);
        this.onClickListener = onClickListener;
        view = LinearLayout.inflate(context, R.layout.begin_dialog, null);
    }

    protected BeginDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
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

        btnDialogOk.setOnClickListener(onClickListener);
    }


    @OnClick(R.id.iv_shopping_close)
    public void onViewClicked() {
        dismiss();
    }


}
