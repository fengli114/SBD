package com.mhy.shopingphone.widgets;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.AudioManager;
import android.media.ToneGenerator;

import android.os.Vibrator;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;

import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

import android.widget.AdapterView;
import android.widget.CheckBox;

import android.widget.ListView;
import android.widget.RelativeLayout;


import com.blankj.utilcode.util.RegexUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.mhy.sdk.rxbus.RxBus;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.CYAdapter;
import com.mhy.shopingphone.model.bean.cy.CYPhoneEntity;
import com.mhy.shopingphone.model.bean.rxbus.EventBusTwo;
import com.mhy.shopingphone.ui.activity.partner.XDButils;
import com.mhy.shopingphone.ui.activity.phone.DialBackActivity;
import com.mhy.shopingphone.ui.activity.start.LoginActivty;
import com.mhy.shopingphone.widgets.dialpadview.IKoyboadLister;
import com.mhy.shopingphone.widgets.dialpadview.ITextListener;
import com.mhy.shopingphone.widgets.dialpadview.UnicodeDialerKeyListener;
import com.mhy.shopingphone.widgets.phone.AnimationController;
import com.youth.xframe.utils.XEmptyUtils;
import com.zyyoona7.lib.EasyPopup;
import com.zyyoona7.lib.HorizontalGravity;
import com.zyyoona7.lib.VerticalGravity;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by LWK
 * TODO 拨号盘
 * 2016/8/18
 */
public class DialPadView extends RelativeLayout implements View.OnClickListener
        , View.OnKeyListener
        , View.OnLongClickListener
        , TextWatcher {

    private ClipboardManager clipboard;

    private DigitsEditText mEdInput;
    //清除按钮
    //  private ImageButton mBtnDelete;
    private boolean mIsFeedBackEnable = true;
    private final Object mToneGeneratorLock = new Object();
    private ToneGenerator mToneGenerator;
    //按键发音时的音量
    private static final int TONE_RELATIVE_VOLUME = 80;
    //按键声音类型
    private static final int DIAL_TONE_STREAM_TYPE = AudioManager.STREAM_DTMF;
    //按键声音时长
    private static final int TONE_LENGTH_INFINITE = 100;
    //震动管理器
    private Vibrator mVibratorMgr;
    //拨打电话监听
    private onCallListener mOnCallListener;
    //文本输入监听
    private onTextChangedListener mTextChangedListener;


    private ITextListener mTextChangelistener;
    //按键监听
    private IKoyboadLister mIKoyboadLister;
    private boolean flag = false;

    //数据库
    private SQLiteDatabase db;
    // private List<DialpadModel> dialpadlist;
    //private DialpadModel model;


    private EasyPopup mQQPop;
    private EasyPopup mAbovePop;
    private CheckBox checkBox;
    private Context context;

    //获取剪贴板内容
//    ClipboardManager clipboardManager;
//    String tempStr;


    public void setOnCallListener(onCallListener listener) {
        this.mOnCallListener = listener;
    }


    public void setITextListener(ITextListener listener) {
        this.mTextChangelistener = listener;
    }

    public void setKeybordListener(IKoyboadLister listener) {
        this.mIKoyboadLister = listener;
    }

    public interface onCallListener {
        void onCall(String phone);
    }

    public void setOnTextChangedListener(onTextChangedListener listener) {
        this.mTextChangedListener = listener;
    }

    public interface onTextChangedListener {
        void onTextChanged(String s);
    }


    public DialPadView(Context context) {
        super(context);
        init(context, null, 0);
    }

    public DialPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(context, attrs, 0);
    }

    public DialPadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        inflate(context, R.layout.include_bo_hao, this);


        mEdInput = (DigitsEditText) findViewById(R.id.ed_dial_input);
        mEdInput.setKeyListener(UnicodeDialerKeyListener.INSTANCE);
        mEdInput.setOnClickListener(this);
        mEdInput.setOnKeyListener(this);
        mEdInput.setOnLongClickListener(this);
        mEdInput.addTextChangedListener(this);

//        img_hide = (ImageView) findViewById(R.id.btn_dial);//键盘隐藏按钮
//        img_hide.setOnClickListener(this);

        findViewById(R.id.btn_dial_digist_0).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_1).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_2).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_3).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_4).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_5).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_6).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_7).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_8).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_9).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_star).setOnClickListener(this);
        findViewById(R.id.btn_dial_digist_pound).setOnClickListener(this);
        //findViewById(R.id.btn_dial_input_plus).setOnClickListener(this);
        findViewById(R.id.btn_dial_pad_call).setOnClickListener(this);
        checkBox = (CheckBox) findViewById(R.id.cb_cy);
        checkBox.setOnClickListener(this);
        //获取震动管理器对象
        mVibratorMgr = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        //长按清空
        findViewById(R.id.btn_dial_digist_pound).setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                clearInput();
                return false;
            }
        });

    }

    //获取剪贴板内容


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ed_dial_input:
                if (!isInputEmpty())
                    mEdInput.setCursorVisible(true);
                break;
            case R.id.btn_dial_digist_0:
                keyPressed(KeyEvent.KEYCODE_0);
                break;
            case R.id.btn_dial_digist_1:
                keyPressed(KeyEvent.KEYCODE_1);
                break;
            case R.id.btn_dial_digist_2:
                keyPressed(KeyEvent.KEYCODE_2);
                break;
            case R.id.btn_dial_digist_3:
                keyPressed(KeyEvent.KEYCODE_3);
                break;
            case R.id.btn_dial_digist_4:
                keyPressed(KeyEvent.KEYCODE_4);
                break;
            case R.id.btn_dial_digist_5:
                keyPressed(KeyEvent.KEYCODE_5);
                break;
            case R.id.btn_dial_digist_6:
                keyPressed(KeyEvent.KEYCODE_6);
                break;
            case R.id.btn_dial_digist_7:
                keyPressed(KeyEvent.KEYCODE_7);
                break;
            case R.id.btn_dial_digist_8:
                keyPressed(KeyEvent.KEYCODE_8);
                break;
            case R.id.btn_dial_digist_9:
                keyPressed(KeyEvent.KEYCODE_9);
                break;
            //复制功能
            case R.id.btn_dial_digist_star:
                initcata();
                if (TextUtils.isEmpty(textctr)) {
                    ToastUtils.showToast("请选择要粘贴的内容");
                    return;
                }
                Pattern pattern = Pattern.compile("[0-9]*");
                if (!pattern.matcher(textctr).matches()) {
                    ToastUtils.showToast("只能粘贴数字");
                    return;
                }
                mEdInput.setText(textctr);
                mEdInput.setSelection(textctr.length());
                break;
            //清除功能
            case R.id.btn_dial_digist_pound:
                // keyPressed(KeyEvent.KEYCODE_POUND);
                keyPressed(KeyEvent.KEYCODE_DEL);
                // clearInput();
                break;
            case R.id.cb_cy:
                initQQPop();
                initView(v);
                break;

            //电话按钮  调接口
            case R.id.btn_dial_pad_call:
                if (!isInputEmpty()) {
//                    db = Connector.getDatabase();
//                    dataList = new ArrayList<>();
//                    model = new MobileBean();
//                    model.setDate(DateUtils.getCurrentTimes_To());//当前时间如2017/1/12
//                    //保存数据
//                    model.smEdInput(mEdInput.getText().toString().trim());
//                    dataList.add(model);
//                    //保存
//                    DataSupport.saveAll(dataList);
                    // db.close();
//                  CallData();
                    if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                        showDialogUp();
                    } else {
                        Intent intent = new Intent(getContext(), DialBackActivity.class);
                        intent.putExtra("phone_name", "未备注联系人");
                        intent.putExtra("phone_number", mEdInput.getText().toString().trim());
                        getContext().startActivity(intent);
                    }

                } else {
                    ToastUtils.showToast("请输入要呼叫的号码");
                    return;
                }
                break;
        }
    }

    private String formatPhoneNum(String phoneNum) {
        String regex = "(\\+86)|[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.replaceAll("");
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);

    }

    private void initView(View v) {
        ListView listView = mQQPop.getView(R.id.listView);
        final List<CYPhoneEntity> list = XDButils.getCYPhoneInfo();
        CYAdapter adapter = new CYAdapter(getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (XEmptyUtils.isEmpty(SharedPreferencesHelper.getInstance().getData("Mobile", ""))) {
                    showDialogUp();
                } else {
                    Intent intent = new Intent(getContext(), DialBackActivity.class);
                    intent.putExtra("phone_name", list.get(i).getName());
                    intent.putExtra("phone_number", list.get(i).getPhone());
                    getContext().startActivity(intent);
                }
            }
        });
        if (list != null && list.size() > 0) {
            adapter.setList(list);
            mQQPop.showAtAnchorView(v, VerticalGravity.ABOVE, HorizontalGravity.LEFT, SizeUtils.dp2px(90), 0);
        } else {
            ToastUtils.showToast("暂无常用联系人");
        }
    }

    //    private void initAbovePop() {
//        mAbovePop = new EasyPopup(getContext())
//                .setContentView(R.layout.layout_right_pop)
//                .setFocusAndOutsideEnable(true)
//                .setOnDismissListener(new PopupWindow.OnDismissListener() {
//                    @Override
//                    public void onDismiss() {
//                        Log.e("y", "onDismiss: mAbovePop");
//                    }
//                })
//                .createPopup();
//    }
    private void initQQPop() {
        mQQPop = new EasyPopup(getContext())
                .setContentView(R.layout.layout_right_pop)
                .setAnimationStyle(R.style.QQPopAnim)
                .setFocusAndOutsideEnable(true)
//                .setBackgroundDimEnable(true)
//                .setDimValue(0.5f)
//                .setDimColor(Color.RED)
//                .setDimView(mTitleBar)
                .createPopup();

    }


    private CharSequence textctr;

    private void initcata() {
        // 获取系统剪贴板
        ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);

        // 获取剪贴板的剪贴数据集
        ClipData clipData = clipboard.getPrimaryClip();

        if (clipData != null && clipData.getItemCount() > 0)

        {
            //从数据集中获取（粘贴）第一条文本数据
            ClipData.Item item = clipData.getItemAt(0);
            textctr = item.getText().toString().replace(" ", "");
//          textctr = clipData.getItemAt(0).getText();

        }

    }

    // 剪切板内容变化的监听器
    ClipboardManager.OnPrimaryClipChangedListener mPrimaryChangeListener = new ClipboardManager.OnPrimaryClipChangedListener() {
        public void onPrimaryClipChanged() {

        }
    };

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        switch (v.getId()) {
            case R.id.ed_dial_input:
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    triggerCall();
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onLongClick(View v) {
        switch (v.getId()) {
            case R.id.ed_dial_input:
                if (mEdInput != null)
                    mEdInput.setCursorVisible(true);
                break;
//            case R.id.btn_dial_input_delete:
//                clearInput();
            //     break;
        }
        return false;
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
//        int length = s.toString().length();
//        //删除数字
//        if (count == 0) {
//            if (length == 4) {
//                mEdInput.setText(s.subSequence(0, 3));
//            }
//            if (length == 9) {
//                mEdInput.setText(s.subSequence(0, 8));
//            }
//        }
//        //添加数字
//        if (count == 1) {
//            if (length == 4) {
//                String part1 = s.subSequence(0, 3).toString();
//                String part2 = s.subSequence(3, length).toString();
//                mEdInput.setText(part1 + " " + part2);
//            }
//            if (length == 9) {
//                String part1 = s.subSequence(0, 8).toString();
//                String part2 = s.subSequence(8, length).toString();
//                mEdInput.setText(part1 + " " + part2);
//            }
//        }
        //这里过滤通讯录数据
        String input_number = mEdInput.getText().toString().trim();
        mTextChangelistener.textChange(input_number);
    }

    @Override
    public void afterTextChanged(Editable s) {
        //更新光标  判断文本框是否有值
        if (isInputEmpty()) {
            mEdInput.setCursorVisible(false);
            RxBus.get().send(new EventBusTwo().setIstue(true));
        } else {
            RxBus.get().send(new EventBusTwo().setIstue(false));
        }

        //更新删除按钮状态
        updateDeleteButtonEnabledState();
        //触发文本监听
        if (mTextChangedListener != null)
            mTextChangedListener.onTextChanged(s.toString().trim());
    }

    //判断输入的文本是否为空
    private boolean isInputEmpty() {
      /*  if (!RegexUtils.isMobileExact(mEdInput.getText().toString().trim())){
            ToastUtils.showToast("请输入正确的号码");
            return true;
        }
        if (!RegexUtils.isTel(mEdInput.getText().toString().trim())){
            ToastUtils.showToast("请输入正确的号码");
            return true;
        }*/
        return mEdInput.length() == 0;

    }


    /**
     * 设置是否允许按键反馈
     * 【开启后按键时有声音和震动】
     */
    public void setFeedBackEnable(boolean enable) {
        this.mIsFeedBackEnable = enable;
    }

    private void keyPressed(int keyCode) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_1:
                triggerFeedBack(ToneGenerator.TONE_DTMF_1, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_2:
                triggerFeedBack(ToneGenerator.TONE_DTMF_2, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_3:
                triggerFeedBack(ToneGenerator.TONE_DTMF_3, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_4:
                triggerFeedBack(ToneGenerator.TONE_DTMF_4, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_5:
                triggerFeedBack(ToneGenerator.TONE_DTMF_5, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_6:
                triggerFeedBack(ToneGenerator.TONE_DTMF_6, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_7:
                triggerFeedBack(ToneGenerator.TONE_DTMF_7, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_8:
                triggerFeedBack(ToneGenerator.TONE_DTMF_8, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_9:
                triggerFeedBack(ToneGenerator.TONE_DTMF_9, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_0:
                triggerFeedBack(ToneGenerator.TONE_DTMF_0, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_POUND:
                triggerFeedBack(ToneGenerator.TONE_DTMF_P, TONE_LENGTH_INFINITE);
                break;
            case KeyEvent.KEYCODE_STAR:
                triggerFeedBack(ToneGenerator.TONE_DTMF_S, TONE_LENGTH_INFINITE);
                break;
            default:
                break;
        }

        KeyEvent event = new KeyEvent(KeyEvent.ACTION_DOWN, keyCode);
        mEdInput.onKeyDown(keyCode, event);

        // 如果光标在文本末尾就隐藏光标
        final int length = mEdInput.length();
        if (length == mEdInput.getSelectionStart() && length == mEdInput.getSelectionEnd())
            mEdInput.setCursorVisible(false);
    }


    //触发按键反馈
    private void triggerFeedBack(int tone, int durationMs) {
        if (!mIsFeedBackEnable)
            return;
//         //易加
//        if (isInputEmpty()) {
//            mEdInput.setCursorVisible(false);
//            EventBus.getDefault().post(new EventBusTwo().setIstue(true));
//        } else {
//            EventBus.getDefault().post(new EventBusTwo().setIstue(false));
//        }

        // 判断系统设置
        AudioManager audioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
        int ringerMode = audioManager.getRingerMode();
        if ((ringerMode == AudioManager.RINGER_MODE_SILENT)) {
            if (ringerMode == AudioManager.RINGER_MODE_VIBRATE)
                vibrate();
            return;
        }


        //震动
        vibrate();
        //发音
        synchronized (mToneGeneratorLock) {
            if (mToneGenerator == null)
                return;
            mToneGenerator.startTone(tone, durationMs);
        }
    }

    //震动一下
    private void vibrate() {
        if (mVibratorMgr != null) {
            long[] l = new long[]{0, 50};
            mVibratorMgr.vibrate(l, -1);
        }
    }

    /**
     * 停止发音
     */
    private void stopTone() {
        if (!mIsFeedBackEnable)
            return;
        synchronized (mToneGeneratorLock) {
            if (mToneGenerator == null)
                return;
            mToneGenerator.stopTone();
        }
    }

    //触发拨打电话的箭筒
    private void triggerCall() {
        if (mOnCallListener != null)
            mOnCallListener.onCall(mEdInput.getText().toString().trim());
    }

    /**
     * 更新删除按钮状态
     */
    private void updateDeleteButtonEnabledState() {
//        if (mBtnDelete != null) {
//            final boolean digitsNotEmpty = !isInputEmpty();
//            mBtnDelete.setEnabled(digitsNotEmpty);
//        }
    }

    /**
     * 清空输入
     */
    public void clearInput() {
        if (mEdInput != null)
            mEdInput.getText().clear();
    }

    /**
     * 获取当前输入的号码
     */
    public String getInput() {
        return mEdInput != null ? mEdInput.getText().toString().trim() : null;
    }

    /**
     * 在Aactivity/Fragment的onStart()调用
     */
    public void onStart() {
        updateDeleteButtonEnabledState();

        synchronized (mToneGeneratorLock) {
            if (mToneGenerator == null) {
                try {
                    mToneGenerator = new ToneGenerator(DIAL_TONE_STREAM_TYPE, TONE_RELATIVE_VOLUME);
                } catch (RuntimeException e) {
                    mToneGenerator = null;
                }
            }
        }
    }

    /**
     * 在Activity/Fragment的onPause()调用
     */
    public void onPause() {
        stopTone();
    }

    /**
     * 在Activity/Fragment的onStop()调用
     */
    public void onStop() {
        synchronized (mToneGeneratorLock) {
            if (mToneGenerator != null) {
                mToneGenerator.release();
                mToneGenerator = null;
            }
        }
    }

    //按键
    public OnGoodsDetailUrl OnGoodsDetailWebUrl;

    public void setOnGoodsDetailWebUrl(OnGoodsDetailUrl onOnGoodsDetailUrl) {
        this.OnGoodsDetailWebUrl = onOnGoodsDetailUrl;
    }

    public interface OnGoodsDetailUrl {
        void OnGoodsDetailUrl(boolean dialpad);
    }

    private void showDialogUp() {

        // 这里的属性可以一直设置，因为每次设置后返回的是一个builder对象
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        // 设置提示框的标题
        builder.setMessage("你还没有登录").setTitle("提示").
                // 设置确定按钮
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getContext(), LoginActivty.class);
                        getContext().startActivity(intent);

                    }
                }).

                // 设置取消按钮,null是什么都不做，并关闭对话框
                        setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

        // 生产对话框
        AlertDialog alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(false);
        // 显示对话框
        alertDialog.show();
    }
}
