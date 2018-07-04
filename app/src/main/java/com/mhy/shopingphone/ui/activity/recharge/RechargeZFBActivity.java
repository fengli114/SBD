package com.mhy.shopingphone.ui.activity.recharge;

import android.app.Dialog;
import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.alipay.sdk.app.PayTask;
import com.blankj.utilcode.util.RegexUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.activity.BaseMVPCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.utils.CharacterParser;
import com.mhy.sdk.utils.FirstLetterUtil;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.MD5Util;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.CustomAdapter;
import com.mhy.shopingphone.adapter.LvAdapter_pay;
import com.mhy.shopingphone.adapter.PayTypeAdapter;
import com.mhy.shopingphone.adapter.PayTypeAdapter;
import com.mhy.shopingphone.adapter.SpaceItemDecoration;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.contract.zf.PayTypeContract;
import com.mhy.shopingphone.model.bean.Ceshi;
import com.mhy.shopingphone.model.bean.GroupMemberBean;
import com.mhy.shopingphone.model.bean.PayTypeBean;
import com.mhy.shopingphone.presenter.zf.PayTypePresenter;
import com.mhy.shopingphone.ui.MainActivity;
import com.youth.xframe.utils.XEmptyUtils;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.Result;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;


/**
 * Created by Administrator on 2017/9/23. 充值页面
 */

public class RechargeZFBActivity extends BaseMVPCompatActivity<PayTypeContract.PayTypePresenter,
        PayTypeContract.IPayTypeModel> implements PayTypeContract.IPayTypeView {
    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;
    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    @BindView(R.id.al_back)
    RelativeLayout alBack;
    @BindView(R.id.title)
    LinearLayout title;
    @BindView(R.id.rv_pay_)
    RecyclerView rvPay;
    @BindView(R.id.btn_pay)
    Button btnPay;

    private int payPrice;
    private int payRealPrice;

    /**
     * 获取库Phon表字段
     **/
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.edit_phone)
    EditText etPhone;
    @BindView(R.id.iv_clear)
    ImageView ivClear;
    @BindView(R.id.rl_cb_look)
    RelativeLayout rlCbLook;
    @BindView(R.id.lv_phone_list)
    ListView lvPhoneList;
    @BindView(R.id.tv_phone_name)
    TextView tvPhoneName;
    @BindView(R.id.tv_head)
    TextView tvHead;
    @BindView(R.id.tv_real_pay)
    TextView tvRealPay;
    @BindView(R.id.tv_pay_data)
    TextView tvPayData;

    private List<GroupMemberBean> mDatas;
    private List<GroupMemberBean> sourceDateList;
    private Dialog dialog;
    private List<GroupMemberBean> filterDateList;
    private String paramsstring;
    private String paramstr;
    private String phoneStr;
    private String mobileStr;
    private PayTypeAdapter mAdapter;
    private String orderNum;

    @Override
    protected int getLayoutId() {
        return R.layout.frag_recharge_zfb_item;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PayTypePresenter.newInstance();
    }

    @Override
    public void updateContentList(final List<PayTypeBean.JsonBean> list) {

    }


    private void initRecycleView(final List<PayTypeBean.JsonBean> list) {
        mAdapter = new PayTypeAdapter(R.layout.item_my_pay_type, list);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });
        rvPay.setAdapter(mAdapter);
        //构造器中，第一个参数表示列数或者行数，第二个参数表示滑动方向,瀑布流
        rvPay.setLayoutManager(new StaggeredGridLayoutManager(2,
                StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void showNetworkError() {

    }

    private void getPayType() {
        Map<String, String> params = new HashMap<>();
        params.put("AgentID", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.LINEPAy_URL)
                .addHeader("SDB-Authorization", String.valueOf(SharedPreferencesHelper.getInstance().getData("Tokens", "")))
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        try {
                            PayTypeBean typeBean = new Gson().fromJson(response, PayTypeBean.class);
                            if (typeBean.getErrorCode() == 2000) {
                                final List<PayTypeBean.JsonBean> list = typeBean.getJson();
                                if (list != null && list.size() > 0) {
                                    payPrice = list.get(0).getAlimoney();
                                    payRealPrice = list.get(0).getMoney();
                                    tvRealPay.setText(list.get(0).getAlimoney() + "元");
                                    final CustomAdapter adapter = new CustomAdapter(mContext, list);
                                    GridLayoutManager manager = new GridLayoutManager(mContext, 2);
                                    SpaceItemDecoration itemDecoration = new SpaceItemDecoration(10, 20);
                                    rvPay.addItemDecoration(itemDecoration);
                                    rvPay.setLayoutManager(manager);
                                    rvPay.setAdapter(adapter);
                                    adapter.setOnItemClickListener(new CustomAdapter.OnRecyclerViewItemClickListener() {
                                        @Override
                                        public void onItemClick(View view, String data, int position) {
                                            //data为内容，position为点击的位置
                                            payPrice = list.get(position).getAlimoney();
                                            payRealPrice = list.get(position).getMoney();
                                            tvRealPay.setText(list.get(position).getAlimoney() + "元");
                                            LogUtils.e("payPrice: " + payPrice + ",payRealPrice: " + payRealPrice);
                                        }
                                    });
                                }

                            } else if (typeBean.getErrorCode() == 1005) {
                                Map<String, String> parames = new HashMap<>();
                                parames.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
                                parames.put("ParentId", String.valueOf(Contant.PARENTID));
                                OkHttpUtils.post()
                                        .url(Api.NEWGOODS + Api.TOKEN_URL)
                                        .params(parames)
                                        .build()
                                        .execute(new StringCallback() {
                                            @Override
                                            public void onError(Call call, Exception e, int id) {
                                            }

                                            @Override
                                            public void onResponse(String response, int id) {
                                                Ceshi ceshi = new Gson().fromJson(response, Ceshi.class);
                                                if (ceshi.getErrorCode() == 2000) {
                                                    SharedPreferencesHelper.getInstance().saveData("Tokens", ceshi.getData());//代理Id
                                                    getPayType();
                                                } else {
                                                    ToastUtils.showToast("网络异常");
                                                }
                                            }
                                        });
                            } else {
                                ToastUtils.showToast(typeBean.getData());
                            }
                        } catch (JsonSyntaxException e) {
                            e.printStackTrace();
                        }
                    }
                });

      /*  String paramsstring = StringUtil.mapToJson(params);
        String paramstr = (NSRBase64.encodeToString(paramsstring) + Contant.URLKey).replaceAll("\n", "").trim();
        //设置头部
        HttpUtils.headStr = "Vm9pcEFwaQ/GetPayOnline/";
        HttpUtils.LogHeadStr = "冲值类型:";
        Contant.IsDebug = true;
        mPresenter.loadPayTypeList(paramstr);*/

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        tvTitle.setText("在线充值");
//        mAdapter = new PayTypeAdapter(R.layout.item_my_shop_type);
//        rvPay.setLayoutManager(new LinearLayoutManager(this));
//        rvPay.setAdapter(mAdapter);
//
//        View loadingView = getLayoutInflater().inflate(R.layout.view_loading, rvPay, false);
//
//        mAdapter.setEmptyView(loadingView);
        getPayType();
        mobileStr = String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", ""));
        etPhone.setText(mobileStr);
        mobileStr = formatPhoneNum(etPhone.getText().toString());
        etPhone.setSelection(etPhone.getText().toString().length());
//        ivClear.setBackgroundResource(R.drawable.onlinepay_delete);
        mDatas = new ArrayList<>();
        sourceDateList = new ArrayList<>();
        getPhoneContacts();// 获取手机联系人
        initView();
    }

    private String formatPhoneNum(String phoneNum) {
        String regex = "(\\+86)|[^0-9]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNum);
        return matcher.replaceAll("");
    }

    private void initView() {

        etPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = s.toString().length();
                //删除数字
                if (count == 0) {
                    if (length == 4) {
                        etPhone.setText(s.subSequence(0, 3));
                    }
                    if (length == 9) {
                        etPhone.setText(s.subSequence(0, 8));
                    }
                }
                //添加数字
                if (count == 1) {
                    if (length == 4) {
                        String part1 = s.subSequence(0, 3).toString();
                        String part2 = s.subSequence(3, length).toString();
                        etPhone.setText(part1 + " " + part2);
                    }
                    if (length == 9) {
                        String part1 = s.subSequence(0, 8).toString();
                        String part2 = s.subSequence(8, length).toString();

                        etPhone.setText(part1 + " " + part2);
                    }
                }
                LogUtils.e(formatPhoneNum(etPhone.getText().toString()).length() + "size");
                LogUtils.e(formatPhoneNum(etPhone.getText().toString()) + "contant");
                if (formatPhoneNum(etPhone.getText().toString()).length() >= 3 && formatPhoneNum(etPhone.getText().toString()).length() <= 11) {
                    if (mDatas != null && mDatas.size() > 0) {
                        tvPhoneName.setText("");
                        lvPhoneList.setVisibility(View.VISIBLE);
                        filterData(formatPhoneNum(etPhone.getText().toString()));
                        LogUtils.e(formatPhoneNum(etPhone.getText().toString()) + "++++++++++");
                    }
                } else {
                    lvPhoneList.setVisibility(View.GONE);
                }

//                if (XEmptyUtils.isEmpty(formatPhoneNum(etPhone.getText().toString()))) {
//                    ivClear.setBackgroundResource(R.drawable.onlinepay_tongxunlu);
//                    lvPhoneList.setVisibility(View.GONE);
//                } else {
//                    ivClear.setBackgroundResource(R.drawable.onlinepay_delete);
//                    lvPhoneList.setVisibility(View.VISIBLE);
//                }

            }

            @Override
            public void afterTextChanged(Editable s) {
                //将光标移动到末尾
                etPhone.setSelection(etPhone.getText().toString().length());
                if (mDatas != null && mDatas.size() > 0) {
                    filterData2(formatPhoneNum(etPhone.getText().toString()));
                }
                //处理s
                if (formatPhoneNum(etPhone.getText().toString()).length() == 11 && sourceDateList.size() > 0) {
                    lvPhoneList.setVisibility(View.GONE);
                    tvPhoneName.setText(sourceDateList.get(0).getName());
                    tvPhoneName.setTextColor(getResources().getColor(R.color.gray));
                } else if (formatPhoneNum(etPhone.getText().toString()).length() == 11 && sourceDateList.size() == 0) {
                    lvPhoneList.setVisibility(View.GONE);
                    tvPhoneName.setText("不在通讯录");
                    tvPhoneName.setTextColor(getResources().getColor(R.color.red));
                }

                if (formatPhoneNum(etPhone.getText().toString()).equals(formatPhoneNum(mobileStr))) {
                    lvPhoneList.setVisibility(View.GONE);
                    tvPhoneName.setText("默认号码");
                    tvPhoneName.setTextColor(getResources().getColor(R.color.gray));
                }
                LogUtils.e(formatPhoneNum(etPhone.getText().toString()) + mobileStr);
            }
        });
        lvPhoneList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = filterDateList.get(i).getName();
                etPhone.setText(filterDateList.get(i).getPhone());
                tvPhoneName.setText(str);
                lvPhoneList.setVisibility(View.GONE);
            }
        });

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData2(String filterStr) {
        sourceDateList = new ArrayList<GroupMemberBean>();
        if (TextUtils.isEmpty(filterStr)) {
            sourceDateList = mDatas;
        } else {
            sourceDateList.clear();
            for (GroupMemberBean sortModel : mDatas) {
                String name = sortModel.getName();
                String phone_search = sortModel.getPhone();
                if (phone_search.contains(" ")) {
                    phone_search = phone_search.replace(" ", "");
                }
//				if (name.indexOf(filterStr.toString()) != -1
//						|| characterParser.getSelling(name).startsWith(
//								filterStr.toString())) {
                /**搜索条件分别为中文搜索、首字母简拼搜索、手机号码搜索*/
              /*  if (name.indexOf(filterStr.toString()) != -1
                        || contains(name, filterStr) || phone_search.contains(filterStr)) {*/
                if (phone_search.contains(filterStr)) {
                    sourceDateList.add(sortModel);
                }
            }
        }

    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        filterDateList = new ArrayList<GroupMemberBean>();
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = mDatas;
        } else {
            filterDateList.clear();
            for (GroupMemberBean sortModel : mDatas) {
                String name = sortModel.getName();
                String phone_search = sortModel.getPhone();
                if (phone_search.contains(" ")) {
                    phone_search = phone_search.replace(" ", "");
                }
//				if (name.indexOf(filterStr.toString()) != -1
//						|| characterParser.getSelling(name).startsWith(
//								filterStr.toString())) {
                /**搜索条件分别为中文搜索、首字母简拼搜索、手机号码搜索*/
                if (phone_search.contains(filterStr)) {
                    filterDateList.add(sortModel);
                }
            }
        }
        if (filterDateList != null && filterDateList.size() > 0) {
            LvAdapter_pay adapter = new LvAdapter_pay(mContext);
            lvPhoneList.setAdapter(adapter);
            adapter.setmDatas(filterDateList);
            LogUtils.e(filterDateList.size() + "");
        }

//        // 根据a-z进行排序
//        sort(filterDateList, pinyinComparator);
////        adapter.updateListView(filterDateList);
//        if (filterDateList.size() == 0) {
//            tvNofriends.setVisibility(View.VISIBLE);
//        }
    }

    private boolean isOk() {
        phoneStr = formatPhoneNum(etPhone.getText().toString());
        if (TextUtils.isEmpty(phoneStr)) {
            ToastUtils.showToast("充值账号不能为空");
            return false;
        }
        if (!RegexUtils.isMobileExact(phoneStr)) {
            ToastUtils.showToast("充值账号不正确");
            return false;
        }
        return true;
    }

    public static boolean contains(String cotacNmae, String search) {
        if (TextUtils.isEmpty(cotacNmae)) {
            return false;
        }

        boolean flag = false;

        // 简拼匹配,如果输入在字符串长度大于6就不按首字母匹配了
        if (search.length() < 6) {
            String firstLetters = FirstLetterUtil.getFirstLetter(cotacNmae);
            // 不区分大小写
            Pattern firstLetterMatcher = Pattern.compile(search,
                    Pattern.CASE_INSENSITIVE);
            flag = firstLetterMatcher.matcher(firstLetters).find();
        }

        if (!flag) { // 如果简拼已经找到了，就不使用全拼了
            // 全拼匹配
            CharacterParser finder = CharacterParser.getInstance();
            finder.setResource(cotacNmae);
            // 不区分大小写
            Pattern pattern2 = Pattern
                    .compile(search, Pattern.CASE_INSENSITIVE);
            Matcher matcher2 = pattern2.matcher(finder.getSpelling());
            flag = matcher2.find();
        }

        return flag;
    }

    /**
     * 得到手机通讯录联系人信息
     **/
    private void getPhoneContacts() {
        try {
            ContentResolver resolver = getContentResolver();

            // 获取手机联系人  PHONES_PROJECTION
            Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    PHONES_PROJECTION, null, null, null);
//        showProgressBar();
            if (phoneCursor != null) {
                mDatas.clear();
                while (phoneCursor.moveToNext()) {
                    // 得到手机号码
                    String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                    // 当手机号码为空的或者为空字段 跳过当前循环
                    if (TextUtils.isEmpty(phoneNumber))
                        continue;

                    // 得到联系人名称
                    String contactName = phoneCursor
                            .getString(PHONES_DISPLAY_NAME_INDEX);
                    //帅选
                    String phone_cache = phoneNumber;
                    if (phoneNumber.contains(" ")) {
                        phone_cache = phoneNumber.replace(" ", "").trim();
                    }
                    int contact_id = phoneCursor.getInt(phoneCursor.getColumnIndex("contact_id"));
                    //                if (PhoneUtils.isMobileNO(phone_cache)) {
                    //                    mContactsName.add(contactName);//人名
                    //                    mContactsNumber.add(phoneNumber);//电话号码
                    //                }
                    GroupMemberBean bean = new GroupMemberBean();
                    bean.setName(contactName);
                    bean.setPhone(phoneNumber);
                    bean.setContact_id(contact_id + "");


                    boolean isAgain = false;
                    for (GroupMemberBean groupBean : mDatas) {
                        if (contactName.equals(groupBean.getName())) {
                            isAgain = true;
                        }
                    }
                    if (!isAgain) {
                        mDatas.add(bean);
                    }
                }

            }
            phoneCursor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.al_back, R.id.tv_right, R.id.btn_pay, R.id.rl_cb_look})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.al_back:
                finish();
                break;
            case R.id.tv_right:
//                Intent intent = new Intent(this, RechargeCardActivity.class);
//                startActivity(intent);
                break;
            case R.id.btn_pay:
                if (isOk()) {
                    showDialog();
                }
                break;
            case R.id.rl_cb_look:
//                etPhone.setText("");
//                tvPhoneName.setText("");
                break;

        }
    }

    private void showDialog() {
        View view = this.getLayoutInflater().inflate(R.layout.pay_choose_type,
                null);
        ImageView payZfb = (ImageView) view.findViewById(R.id.iv_zfb_pay);
        RelativeLayout payClose = (RelativeLayout) view.findViewById(R.id.al_close);

        //支付
        payZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goPay();
            }

        });
        //关闭
        payClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog = new Dialog(this, R.style.transparentFrameWindowStyle2);
        dialog.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        Window window = dialog.getWindow();
        // 设置显示动画
        window.setWindowAnimations(R.style.main_menu_animstyle);
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.x = 0;
        wl.y = getWindowManager().getDefaultDisplay().getHeight();
        // 以下这两句是为了保证按钮可以水平满屏
        wl.width = ViewGroup.LayoutParams.MATCH_PARENT;
        wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);
        // 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    //获取支付所需参数
    private void goPay() {
        //请求参数
        Map<String, String> params = new HashMap<String, String>();
        params.put("ID", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));//代理商id
        params.put("Role", payRealPrice + "");
        params.put("RegiMoney", payPrice + "");
        params.put("Mobile", String.valueOf(mobileStr));

        OkHttpUtils
                .post()
                .url(Api.NEWGOODS + Api.ALIPAY_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {

                    @Override
                    public void onError(Call call, Exception e, int id) {
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        String code = jsonObject.getString("errorCode");
                        String Mess = jsonObject.getString("Mess");
                        LogUtils.e("充值：" + response);
                        if (code.equals("2000")) {
                            orderNum = jsonObject.getString("OrderNum");
                            String Info = jsonObject.getString("Info");
                            if (Info != null && Info.length() > 0) {
                                zfbPay(Info);
                            }
                            ToastUtils.showToast("支付失败");
                            dialog.cancel();
                        } else {
                            dialog.cancel();
                            ToastUtils.showToast("支付失败");
                        }
//                            upadteModel = new Gson().fromJson(response, UpadteModel.class);
                    }
                });

    }

    /**
     * 支付宝支付
     *
     * @param data
     */
    public void zfbPay(final String data) {
        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(RechargeZFBActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(data, true);

                Message msg = new Message();
                msg.what = 1;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     *
     */

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    PayResult payResult = new PayResult((String) msg.obj);
                    /**
                     * 同步返回的结果必须放置到服务端进行验证（验证的规则请看https://doc.open.alipay.com/doc2/
                     * detail.htm?spm=0.0.0.0.xdvAU6&treeId=59&articleId=103665&
                     * docType=1) 建议商户依赖异步通知
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息

                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        Toast.makeText(mContext, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        goPayFail();
                        // 判断resultStatus 为非"9000"则代表可能支付失败
                        // "8000"代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(mContext, "支付结果确认中", Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(mContext, "支付失败", Toast.LENGTH_SHORT).show();

                        }
                    }

                    break;
                default:
                    break;
            }
        }

        ;
    };

    //支付失败时调用的接口
    private void goPayFail() {

        Map<String, String> params = new HashMap<String, String>();
        params.put("OrderNum", orderNum);//代理商id
        OkHttpUtils
                .post()
                .url(Api.NEWGOODS + Api.NOPAY_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        hideWaitDialog();
                        JSONObject jsonObject = JSONObject.parseObject(response);
                        String code = jsonObject.getString("Code");
                        String Mess = jsonObject.getString("Mess");
                        LogUtils.e("充值Fail：" + response);
                        if (code.equals("0")) {
                            LogUtils.e(Mess);
                        } else {
                            LogUtils.e("支付失败~");
                        }
                    }
                });
    }

}
