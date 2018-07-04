package com.mhy.shopingphone.ui.activity.tixian;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.RegexUtils;
import com.google.gson.Gson;

import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.model.bean.partner.BindEntity;
import com.mhy.shopingphone.ui.activity.partner.XDButils;
import com.mhy.shopingphone.widgets.BandCardEditText;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.Call;

public class BindActivity extends BaseActivity {

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.cb_bind_zfb)
    CheckBox cbBindZfb;
    @BindView(R.id.al_bind_zfb)
    LinearLayout alBindZfb;
    @BindView(R.id.edit_bind_zfb_account)
    EditText editBindZfbAccount;
    @BindView(R.id.edit_bind_zfb_phone)
    EditText edit_bind_zfb_phone;
    @BindView(R.id.edit_bind_zfb_name)
    EditText editBindZfbName;
    @BindView(R.id.btn_bind_zfb)
    Button btnBindZfb;
    @BindView(R.id.al_bind_zfb_content)
    LinearLayout alBindZfbContent;
    @BindView(R.id.cb_bind_card)
    CheckBox cbBindCard;
    @BindView(R.id.al_bind_card)
    LinearLayout alBindCard;
    @BindView(R.id.edit_bind_card_name)
    EditText editBindCardName;
    @BindView(R.id.edit_bind_card_number)
    BandCardEditText editBindCardNumber;
    @BindView(R.id.edit_bind_card_name2)
    EditText editBindCardName2;
    @BindView(R.id.btn_bind_card)
    Button btnBindCard;
    @BindView(R.id.al_bind_card_content)
    LinearLayout alBindCardContent;
    @BindView(R.id.tv_bind_zfb_account)
    TextView tvBindZfbAccount;
    @BindView(R.id.tv_bind_zfb_name)
    TextView tvBindZfbName;
    @BindView(R.id.tv_bind_card_name)
    TextView tvBindCardName;
    @BindView(R.id.tv_bind_card_number)
    TextView tvBindCardNumber;
    @BindView(R.id.tv_bind_card_name2)
    TextView tvBindCardName2;

    private static final String TAG = "BindActivity";
    private String zfbAccount;
    private String zfbName;
    private String cardAccount;
    private String cardName;
    private String cardName2;
    private BindEntity bindUserInfo;
    private String phoneStr;

    @Override
    protected int getLayout() {
        return R.layout.activity_bind;
    }

    @Override
    protected void initDate() {
        Util.setStatusBarHeigh(mContext, tou);
        tvTitle.setText("我的账户");
        initViews();

        cbBindZfb.setOnCheckedChangeListener(onCheckedChangeListener);
        cbBindCard.setOnCheckedChangeListener(onCheckedChangeListener);
        editBindCardNumber.setBankCardListener(new BandCardEditText.BankCardListener() {
            @Override
            public void success(String name) {
                editBindCardName2.setText(name);
            }

            @Override
            public void failure() {
                editBindCardName2.setText("没有查到所属银行");
            }
        });


    }

    private void initViews() {
        bindUserInfo = XDButils.getBindUserInfo();

        if (bindUserInfo != null) {
            LogUtils.e("握手" + bindUserInfo.toString());
            if (!XEmptyUtils.isEmpty(bindUserInfo.getAliAccount())) {

                editBindZfbAccount.setText(bindUserInfo.getAliAccount());
                editBindZfbName.setText(bindUserInfo.getName());
            }
            if (!XEmptyUtils.isEmpty(bindUserInfo.getMobile())) {
                edit_bind_zfb_phone.setText(bindUserInfo.getMobile());
                edit_bind_zfb_phone.setSelection(edit_bind_zfb_phone.getText().toString().length());
            }
            if (!XEmptyUtils.isEmpty(bindUserInfo.getBankNo())) {
                editBindCardName.setText(bindUserInfo.getName());
                editBindCardName2.setText(bindUserInfo.getBankName());
                editBindCardNumber.setText(bindUserInfo.getBankNo());
            }

        }
    }


    CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            switch (compoundButton.getId()) {
                case R.id.cb_bind_zfb:
                    if (b) {
                        alBindZfbContent.setVisibility(View.VISIBLE);
                    } else {
                        alBindZfbContent.setVisibility(View.GONE);
                    }
                    break;
                case R.id.cb_bind_card:
                    if (b) {
                        alBindCardContent.setVisibility(View.VISIBLE);
                    } else {
                        alBindCardContent.setVisibility(View.GONE);
                    }
                    break;
            }
        }
    };

    //设置
    private void isXian(TextView textView, EditText edit, boolean b) {
        if (!b) { //
            textView.setVisibility(View.GONE);
            edit.setVisibility(View.VISIBLE); //
        } else { //
            edit.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE); //
        }
    }

    private boolean isPhone(){
        phoneStr = edit_bind_zfb_phone.getText().toString().trim();
        if (XEmptyUtils.isEmpty(phoneStr)){
            ToastUtils.showToast("手机号不能为空");
            return false;
        }
        if (!RegexUtils.isMobileExact(phoneStr)){
            ToastUtils.showToast("请输入正确的手机号");
            return false;
        }
        return true;
    }
    /**
     * 判断zfb
     */
    private boolean isZfb() {
        zfbAccount = editBindZfbAccount.getText().toString().trim();
        zfbName = editBindZfbName.getText().toString().trim();
        if (XEmptyUtils.isEmpty(zfbAccount)) {
            ToastUtils.showToast("支付宝账号不能为空~");
            return false;
        }
        if (XEmptyUtils.isEmpty(zfbAccount)) {
            ToastUtils.showToast("支付宝账号不能为空~");
            return false;
        }
        if (!(RegexUtils.isEmail(zfbAccount) || RegexUtils.isMobileExact(zfbAccount))) {
            ToastUtils.showToast("请输入正确的支付宝账号~");
            return false;
        }
        if (XEmptyUtils.isEmpty(zfbName)) {
            ToastUtils.showToast("真实姓名不能为空~");
            return false;
        }
        return true;
    }

    /**
     * 判断zfb
     */
    private boolean isCard() {
        cardAccount = editBindCardNumber.getText().toString().trim();
        cardName = editBindCardName.getText().toString().trim();
        cardName2 = editBindCardName2.getText().toString().trim();
        if (XEmptyUtils.isEmpty(cardAccount)) {
            ToastUtils.showToast("银行卡号不能为空~");
            return false;
        }
        if (cardAccount.length() > 19 && cardAccount.length() < 16) {
            ToastUtils.showToast("请输入正确的银行卡号~");
            return false;
        }
        if (XEmptyUtils.isEmpty(cardName)) {
            ToastUtils.showToast("真实姓名不能为空~");
            return false;
        }
        if (XEmptyUtils.isEmpty(cardName2)) {
            ToastUtils.showToast("开户行名称不能为空~");
            return false;
        }

        return true;
    }

    //登陆请求
    private void goBind(final String bindType) {
        //时间
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        //String datatime = formatter.format(curDate).replaceFirst("0","");
        String datatime = formatter.format(curDate);
        if (datatime.split(":")[0].length() >= 2 && datatime.startsWith("0")) {
            datatime = datatime.substring(1, datatime.length());
        }
        //请求头
        String headmsg = NSRBase64.encodeToString(MD5Util.MD5Encode(Contant.BIND_USER_URLHead + datatime)).trim();

        //请求参数
        Map<String, String> params = new HashMap<String, String>();
//        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        params.put("UserID", Contant.PARENTID);//代理商id
        params.put("Type", bindType);//体现金额
        params.put("Mobile", edit_bind_zfb_phone.getText().toString());//体现金额
        if (bindType.equals("1")) {
            params.put("AliAccount", zfbAccount);//
            params.put("Name", zfbName);//
        } else {
            params.put("Name", cardName);//
            params.put("BankName", cardName2);//
            params.put("BankNo", cardAccount);//
        }
        LogUtils.e(TAG + params.toString());
        String paramsstring = StringUtil.mapToJson(params);

        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();

        OkHttpUtils
                .post()
                .url(Contant.URL_TEST + Contant.BIND_USER)
                .addHeader("Authorization", headmsg)
                .addParams("A", paramstr)//参数
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        //ToastUtils.showToast("失败");
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        LogUtils.e(TAG + response);
                        BindUserEntity bindUserEntity = new Gson().fromJson(response, BindUserEntity.class);
                        if (bindUserEntity.getCode().equals("0")) {
//                            Contant.profitMoney = money;
                            if (bindType.equals("1")) {
                                bindUserEntity.getInfo().setBankName(bindUserInfo.getBankName());
                                bindUserEntity.getInfo().setBankNo(bindUserInfo.getBankNo());
                            } else {
                                bindUserEntity.getInfo().setAliAccount(bindUserInfo.getAliAccount());
                            }
//                            SharedPreferencesHelper.getInstance().saveData("phoneStr");
                            XDButils.saveBindUserInfo2(bindUserEntity.getInfo());
                            initViews();
                            finish();
                            ToastUtils.showToast("绑定成功");
                        } else {
                            ToastUtils.showToast("绑定失败");
                        }
                    }
                });
    }

    @OnClick({R.id.img_back, R.id.tv_right, R.id.al_bind_zfb, R.id.al_bind_card, R.id.btn_bind_zfb, R.id.btn_bind_card})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                finish();
                break;
            case R.id.tv_right:

                break;
            case R.id.al_bind_zfb:
                if (cbBindZfb.isChecked()) {
                    cbBindZfb.setChecked(false);
                } else {
                    cbBindZfb.setChecked(true);
                }
                break;
            case R.id.al_bind_card:
                if (cbBindCard.isChecked()) {
                    cbBindCard.setChecked(false);
                } else {
                    cbBindCard.setChecked(true);
                }
                break;
            case R.id.btn_bind_zfb:
                if (isPhone()) {
                    if (isZfb()) {
                        goBind(1 + "");
                    }
                }
                break;
            case R.id.btn_bind_card:
                if (isPhone()) {
                    if (isCard()) {
                        goBind(2 + "");
                    }
                }
                break;
        }
    }

}
