package com.mhy.shopingphone.widgets.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mhy.shopingphone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 描述：领完的礼包会显示的对话框
 * Created by F.L on 2017/4/6.
 */

public class TipDialog2 extends Dialog {
    @BindView(R.id.btn_dialog_ok)
    Button btnDialogOk;
    @BindView(R.id.tv_setting_titile)
    TextView tvSettingTitile;
    @BindView(R.id.tv_setting_content)
    TextView tvSettingContent;
    @BindView(R.id.btn_dialog_cancel)
    TextView btnDialogCancel;
    private ImageView imageView;
    private Button bt_zhu, bt_logn;
    private View view;
    private Context context;
    private String title, content,money;
    private View.OnClickListener onClickListener;

    public TipDialog2(Context context) {
        super(context);
    }

    public TipDialog2(Context context, int themeResId, String content, View.OnClickListener onClickListener) {
        super(context, themeResId);
        this.context = context;
        this.content = content;
        this.onClickListener = onClickListener;
        view = LinearLayout.inflate(context, R.layout.dialog_tip2, null);
    }

    protected TipDialog2(Context context, boolean cancelable, OnCancelListener cancelListener) {
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
        tvSettingContent.setText("该商品已经领取过优惠券，此次操作不扣取任何话费");
        btnDialogOk.setOnClickListener(onClickListener);
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.image_set_meal_dialog_close:
//                dismiss();
//                break;
//            case R.id.button_set_meal_zhu_ce:
//                context.startActivity(new Intent(context, RegisteredActivity.class));
//                dismiss();
//                break;
//            case R.id.button_set_meal_go_logn:
//                context.startActivity(new Intent(context, LoginActivity.class));
//                dismiss();
//                break;
//        }
//    }

    @OnClick(R.id.btn_dialog_cancel)
    public void onViewClicked() {
        dismiss();
    }


}
