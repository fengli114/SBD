package com.mhy.shopingphone.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.mhy.sdk.RxManager;
import com.mhy.sdk.base.activity.BaseCompatActivity;
import com.mhy.sdk.contant.Contant;
import com.mhy.sdk.helper.RetrofitCreateHelper;
import com.mhy.sdk.helper.RxHelper;
import com.mhy.sdk.utils.HttpUtils;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.NSRBase64;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.StringUtil;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.ContactsListAdapter;
import com.mhy.shopingphone.adapter.RecAdressListAdapter;
import com.mhy.shopingphone.api.Api;
import com.mhy.shopingphone.model.bean.GroupMemberBean;
import com.mhy.shopingphone.model.bean.banner.BannerBean;
import com.mhy.shopingphone.model.bean.greendao.bean.BannerListBean;
import com.mhy.shopingphone.model.bean.indexbar.PhoneNameBean;
import com.mhy.shopingphone.model.bean.phone.MailistCallBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.ui.activity.phone.PhoneDetailsActivity;
import com.mhy.shopingphone.ui.fragment.addresslist.AddressListFragment;
import com.mhy.shopingphone.utils.UpdateIndexUIListener;
import com.mhy.shopingphone.view.SideBar;
import com.mhy.shopingphone.widgets.ClearEditText;
import com.mhy.shopingphone.widgets.adresslist.adapter.ContactAdapter;
import com.mhy.shopingphone.widgets.adresslist.common.PinYinComparator;
import com.mhy.shopingphone.widgets.adresslist.common.Pinyin4jUtil;
import com.mhy.shopingphone.widgets.adresslist.widget.CircleTextView;
import com.mhy.shopingphone.widgets.adresslist.widget.PinYinSlideView;
import com.mhy.shopingphone.widgets.decoration.DividerItemDecoration;
import com.youth.xframe.utils.XEmptyUtils;
import com.youth.xframe.utils.log.XLog;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Call;

/**
 * 作者： "RedRainM" on 2018/4/26 0026.
 * 描述：
 */

public class ContactsActivity extends BaseCompatActivity implements UpdateIndexUIListener
        , SideBar.OnTouchTextChangeListener {
    boolean move;
    int mIndex;
    //    List<GroupMemberBean> friends;
    private List<GroupMemberBean> contactsModelList;
    LinearLayoutManager manager;
    ContactAdapter contactAdapter;
    private RecAdressListAdapter adapter;
    private ContactsListAdapter mAdapter;
    //    private GetPhone getPhone;
    private static final int PERMISSIONS_REQUEST_CODE_ACCESS_READ_CONTACTS = 0x11;
    private static final String PHONE_BOOK_LABLE = "phonebook_label";
    /* private static final String[] PHONES_PROJECTION = {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME
             , ContactsContract.CommonDataKinds.Phone.NUMBER, PHONE_BOOK_LABLE};*/
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.NUMBER, PHONE_BOOK_LABLE,
            ContactsContract.CommonDataKinds.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;
    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
    /*
      * 获取库Phon表字段
      **/
    private List<GroupMemberBean> mDatas;

    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.finsh_contact)
    RelativeLayout finsh;
    @BindView(R.id.add_contact)
    RelativeLayout addPhone;
    @BindView(R.id.iv_banner)    //广告图片
            ImageView iv_banner;
    @BindView(R.id.pinYinSlideView)    //拼音排序
            PinYinSlideView pinYinSlideView;
    @BindView(R.id.circleText)     //动画
            CircleTextView circleText;
    @BindView(R.id.recycler)    //通讯录
            RecyclerView contactList;
    @BindView(R.id.header)     //通讯录的备注
            TextView header;
    @BindView(R.id.filter_edit)  //搜索框的内容
            ClearEditText filterEdit;
    @BindView(R.id.title_tishi)   //提示的内容
            TextView tv_no_friends;
    @BindView(R.id.ll_yincang)   //未查询到数据
            LinearLayout ll_yincang;

    @BindView(R.id.ic_loading)
    LinearLayout ic_loading;
    @BindView(R.id.listview)
    ListView listView;
    @BindView(R.id.index)
    TextView tv_index;
    @BindView(R.id.sideBar)
    SideBar sideBar;

    @Override
    protected void initView(Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
//        ic_loading.setVisibility(View.VISIBLE);
        String bannerUrl = (String) SharedPreferencesHelper.getInstance().getData("AdressList", "");
        if (!XEmptyUtils.isEmpty(bannerUrl)) {
            Glide.with(mContext).load(bannerUrl).crossFade(300).into(iv_banner);
        } else {
            getBanner();
        }
        initViews();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSIONS_REQUEST_CODE_ACCESS_READ_CONTACTS);
            //等待回调 onRequestPermissionsResult(int, String[], int[]) method

        } else {
            //没有获得授权，做相应的处理！
            getData();
        }
        finsh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        addPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addIntent = new Intent(Intent.ACTION_INSERT, Uri.withAppendedPath(Uri.parse("content://com.android.contacts"), "contacts"));
                addIntent.setType("vnd.android.cursor.dir/person");
                addIntent.setType("vnd.android.cursor.dir/contact");
                addIntent.setType("vnd.android.cursor.dir/raw_contact");
                startActivity(addIntent);
            }
        });
    }

    private void initViews() {
        listView = (ListView) findViewById(R.id.listview);
        if (Build.VERSION.SDK_INT > 9) {
            listView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        }
        tv_index = (TextView) findViewById(R.id.index);
        sideBar = (SideBar) findViewById(R.id.sideBar);
        sideBar.setToastTextView((TextView) findViewById(R.id.tv_toast));
        sideBar.setOnTouchTextChangeListener(this);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_CODE_ACCESS_READ_CONTACTS
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // 获得授权后处理方法
            getData();
        }
    }

    private void getData() {
        mWaitPorgressDialog.show();
        new Thread() {
            @Override
            public void run() {
                try {
                    contactsModelList = new ArrayList<>();
                    ContentResolver mResolver = getContentResolver();
                    //查询联系人数据，query的参数Phone.SORT_KEY_PRIMARY表示将结果集按Phone.SORT_KEY_PRIMARY排序
                    Cursor cursor = mResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI
                            , PHONES_PROJECTION, null, null, ContactsContract.CommonDataKinds.Phone.SORT_KEY_PRIMARY);
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            GroupMemberBean model = new GroupMemberBean();
                            model.setPhone(cursor.getString(PHONES_NUMBER_INDEX));
                            if (TextUtils.isEmpty(model.getPhone())) {
                                continue;
                            }
                            String contactid =cursor.getString(cursor.getColumnIndex("contact_id"));
                            boolean isAgain = false;
                            for (GroupMemberBean groupBean : contactsModelList) {
                                if (contactid.equals(groupBean.getContact_id())) {
                                    isAgain = true;
                                }
                            }
                            if (!isAgain) {
                                model.setContact_id(cursor.getString(cursor.getColumnIndex("contact_id")) + "");
                                model.setName(cursor.getString(PHONES_DISPLAY_NAME_INDEX));
                                model.setPhonebook_label(cursor.getString(cursor.getColumnIndex(PHONE_BOOK_LABLE)));
                                contactsModelList.add(model);
                            }

                        }
                        cursor.close();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideProgressDialog();
                            mAdapter = new ContactsListAdapter(ContactsActivity.this
                                    , contactsModelList);
                            mAdapter.setUpdateIndexUIListener(ContactsActivity.this);
                            listView.setAdapter(mAdapter);
                            listView.setOnScrollListener(mAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                    Intent intent = new Intent(ContactsActivity.this, PhoneDetailsActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    intent.putExtra("phone_name", contactsModelList.get(i).getName());
                                    intent.putExtra("phone_number", contactsModelList.get(i).getPhone());
                                    intent.putExtra("contact_id", contactsModelList.get(i).getContact_id());
                                    startActivity(intent);

                                }
                            });
                        }
                    });
                    filterEdit.addTextChangedListener(new TextWatcher() {

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before,
                                                  int count) {
                            // 这个时候不需要挤压效果 就把他隐藏掉

                            // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                            if (!filterEdit.getText().toString().equals("*")) {
                                filterData(s.toString());
                            }
                        }


                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count,
                                                      int after) {

                        }

                        @Override
                        public void afterTextChanged(Editable s) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }.start();
    }

    @OnClick(R.id.tv_refrsh)
    public void onViewClicked() {
        if (contactsModelList != null && contactsModelList.size() > 0) {
            getData();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_address_list;
    }

    private void getBanner() {
        Map<String, String> params = new HashMap<>();
        params.put("Mobile", String.valueOf(SharedPreferencesHelper.getInstance().getData("Mobile", "")));
        params.put("ParentId", String.valueOf(SharedPreferencesHelper.getInstance().getData("AgentId", "")));
        OkHttpUtils.post()
                .url(Api.NEWGOODS + Api.CAROUSEL_URL)
                .params(params)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {

                        BannerBean bean2 = new Gson().fromJson(response, BannerBean.class);
                        if (bean2.getErrorCode() == 200) {
                            List<BannerListBean> listBeans = new ArrayList<>();
                            String logoUrl = null;
                            for (BannerBean.JsonBean bean : bean2.getJson()) {
                                if (bean.getAdtype() == 7) {
                                    SharedPreferencesHelper.getInstance().saveData("AdressList", bean.getPath());//
                                    logoUrl = bean.getPath();
                                }

                            }
                            Glide.with(mContext).load(logoUrl).crossFade(300).into(iv_banner);

                        } else {
//                            ToastUtils.showToast("");
                        }
                    }
                });
    }

    @Override
    public void onUpdatePosition(int position) {
        ViewGroup.MarginLayoutParams mp = (ViewGroup.MarginLayoutParams) tv_index.getLayoutParams();
        mp.topMargin = position;
        tv_index.setLayoutParams(mp);
    }

    @Override
    public void onUpdateText(String mtext) {
        tv_index.setText(mtext);
    }

    @Override
    public void onTouchTextChanged(String s) {
        int position = getPositionForSection(s);
        listView.setSelection(position);
    }

    /**
     * 根据传入的section来找到第一个出现的位置
     */
    private int getPositionForSection(String s) {
        for (int i = 0; i < contactsModelList.size(); i++) {
            if (s.equals(contactsModelList.get(i).getPhonebook_label())) {
                return i;
            } else if (s.equals("↑") || s.equals("☆")) {
                return 0;
            }
        }
        return -1;
    }
  /*  //异步获取通讯录信息
    class GetPhone extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
//            showWaitDialog("通讯录加载中...");
            ll_yincang.setVisibility(View.VISIBLE);
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
//            ic_loading.setVisibility(View.GONE);
            try {
                getPhoneContacts();
//                friends = (List<GroupMemberBean>) getIntent().getSerializableExtra("remen");
//                LogUtils.e("fengli---"+ (List<GroupMemberBean>) getIntent().getSerializableExtra("remen"));
            } catch (Exception e) {
                ll_yincang.setVisibility(View.VISIBLE);
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
//            hideWaitDialog();
            if (aBoolean) {
                ll_yincang.setVisibility(View.GONE);
                pinYinSlideView.setOnShowTextListener(new PinYinSlideView.OnShowTextListener() {
                    @Override
                    public void showText(String text) {
                        circleText.setText(text);
                        if (text != null) {
                            if (!text.equals("↑")) {
                                int position = 0;
                                boolean hasPinyin = false;
                                for (int i = 0; i < friends.size(); i++) {
                                    GroupMemberBean friend = friends.get(i);
                                    if (friend.getFirstPinyin().equals(text)) {
                                        position = i;
                                        hasPinyin = true;
                                        break;
                                    }
                                }
                                if (hasPinyin) {
                                    scrollToPosition(position);
                                }
                            } else {
                                scrollToPosition(0);
                            }
                        }

                    }
                });
                manager = new LinearLayoutManager(mContext);
                contactList.setLayoutManager(manager);
                contactAdapter = new ContactAdapter(mContext, friends);
                contactList.setAdapter(contactAdapter);
                contactList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
                contactList.addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                        if (layoutManager instanceof LinearLayoutManager) {
                            int firstItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();

                            GroupMemberBean friend = contactAdapter.getItem(firstItem);
                            header.setText(friend.getFirstPinyin());
                            if (move) {
                                move = false;
                                int n = mIndex - firstItem;
                                if (n >= 0 && n < contactList.getChildCount()) {
                                    int top = contactList.getChildAt(n).getTop();
                                    contactList.scrollBy(0, top);
                                }
                            }
                        }

                    }

                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                        super.onScrollStateChanged(recyclerView, newState);
                    }
                });
                mDatas = new ArrayList<>();
                //        getPhoneContacts();// 获取手机联系人
                adapter = new RecAdressListAdapter(ContactsActivity.this);
                List<PhoneNameBean> phoneNameList = new ArrayList<>();
                for (GroupMemberBean memberBean : mDatas) {
                    if (!phoneNameList.contains(memberBean.getName())) {
                        phoneNameList.add(new PhoneNameBean(memberBean.getName()));
                    }
                }
                // 根据输入框输入值的改变来过滤搜索
                filterEdit.addTextChangedListener(new TextWatcher() {

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before,
                                              int count) {
                        // 这个时候不需要挤压效果 就把他隐藏掉
                        // 当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                        if (!filterEdit.getText().toString().equals("*")) {
                            filterData(s.toString());
                        }
                    }

                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count,
                                                  int after) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
            } else {
//                Toast.makeText(mContext, "获取通信录失败", Toast.LENGTH_SHORT).show();
            }


        }
    }

    private void scrollToPosition(int position) {
        if (position >= 0 && position <= friends.size() - 1) {
            int firstItem = manager.findFirstVisibleItemPosition();
            int lastItem = manager.findLastVisibleItemPosition();
            if (position <= firstItem) {
                contactList.scrollToPosition(position);
            } else if (position <= lastItem) {
                int top = contactList.getChildAt(position - firstItem).getTop();
                contactList.scrollBy(0, top);
            } else {
                contactList.scrollToPosition(position);
                mIndex = position;
                move = true;
            }
        }
    }

    *//**
     * 得到手机通讯录联系人信息
     **//*
    private void getPhoneContacts() {
        friends = new ArrayList<>();
        ContentResolver resolver = mContext.getContentResolver();

        // 获取手机联系人  PHONES_PROJECTION
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                PHONES_PROJECTION, null, null, null);
//        showProgressBar();
        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                // 得到手机号码
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                // 当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber))
                    continue;

                // 得到联系人名称
                String contactName = phoneCursor
                        .getString(PHONES_DISPLAY_NAME_INDEX);
                if (TextUtils.isEmpty(contactName))
                    continue;
                //筛选
                String phone_cache = phoneNumber;
                if (phoneNumber.contains(" ")) {
                    phone_cache = phoneNumber.replace(" ", "").trim();
                }
                int contact_id = phoneCursor.getInt(phoneCursor.getColumnIndex("contact_id"));
                if (TextUtils.isEmpty(contact_id + ""))
                    continue;

                GroupMemberBean bean = new GroupMemberBean();

                bean.setPhone(phoneNumber);
                bean.setContact_id(contact_id + "");
                bean.setName(contactName);
//                LogUtils.e("phoneNumber:" + phoneNumber + "contactName:" + contactName);

                boolean isAgain = false;
                for (GroupMemberBean groupBean : friends) {
                    if (contactName.equals(groupBean.getName())) {
                        isAgain = true;
                    }
                }
                if (!isAgain) {
                    bean.setAccount(contactName);
                    String pinyin = Pinyin4jUtil.convertToFirstSpell(contactName);
                    if (Pinyin4jUtil.isPinYin(pinyin)) {
                        bean.setPinyin(pinyin);
                    } else {
                        bean.setPinyin("#");
                    }
                    friends.add(bean);
                }
            }

        }
        if (friends != null && friends.size() > 1) {
            try {
                Collections.sort(friends, new PinYinComparator());
            } catch (IllegalArgumentException e) {
                LogUtils.e("排序：" + e.toString());
            }

        }
        if (Build.VERSION.SDK_INT < 14) {
            phoneCursor.close();
        }
    }*/

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<GroupMemberBean> filterDateList = new ArrayList<GroupMemberBean>();
        LogUtils.e("fengliaaaa" + filterStr);
        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = contactsModelList;
            tv_no_friends.setVisibility(View.GONE);
        } else {
            filterDateList.clear();
            for (GroupMemberBean sortModel : contactsModelList) {
                String name = sortModel.getName();
                String phone_search = sortModel.getPhone();
                if (phone_search.contains(" ")) {
                    phone_search = phone_search.replace(" ", "");
                }
             /*   Pattern num = Pattern.compile("[0-9]*");
                Matcher shuzi = num.matcher(filterStr.trim().toString());
                Pattern pinyin = Pattern.compile("[a-zA-Z]");
                Matcher zimu = pinyin.matcher(filterStr.trim().toString());
                Pattern zw = Pattern.compile("[\u4e00-\u9fa5]");
                Matcher hanzi = zw.matcher(filterStr.trim().toString());
                if (shuzi.matches()) {
                    if (phone_search.contains(filterStr)) {
                        filterDateList.add(sortModel);
                    }
                } else if (zimu.matches()) {
                    if (Util.contains(name, filterStr)) {
                        filterDateList.add(sortModel);
                    }
                } else if (hanzi.matches()) {
                    if (name.indexOf(filterStr.toString()) != -1) {
                        filterDateList.add(sortModel);
                    }
                }*/
                Pattern pinyin = Pattern.compile("[a-zA-Z]");
                Matcher zimu = pinyin.matcher(filterStr.trim().toString());
                if (zimu.matches()) {
                    if (Util.contains(name, filterStr)) {
                        filterDateList.add(sortModel);
                    }
                } else
                /**搜索条件分别为中文搜索、首字母简拼搜索、手机号码搜索*/
                    if (name.indexOf(filterStr.toString()) != -1 || phone_search.contains(filterStr)) {
                        filterDateList.add(sortModel);
                    }
            }
        }

     /*   // 根据a-z进行排序
        contactAdapter = new ContactAdapter(mContext, filterDateList);
        contactList.setAdapter(contactAdapter);
        contactList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    int firstItem = ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
                    GroupMemberBean friend = contactAdapter.getItem(firstItem);
                    header.setText(friend.getFirstPinyin());
                    if (move) {
                        move = false;
                        int n = mIndex - firstItem;
                        if (n >= 0 && n < contactList.getChildCount()) {
                            int top = contactList.getChildAt(n).getTop();
                            contactList.scrollBy(0, top);
                        }
                    }

                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });*/
        if (filterDateList != null && filterDateList.size() > 0) {
            mAdapter = new ContactsListAdapter(mContext, filterDateList);
            mAdapter.setUpdateIndexUIListener(ContactsActivity.this);
            listView.setAdapter(mAdapter);
            listView.setOnScrollListener(mAdapter);

            final List<GroupMemberBean> finalFilterDateList = filterDateList;
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(ContactsActivity.this, PhoneDetailsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("phone_name", finalFilterDateList.get(i).getName());
                    intent.putExtra("phone_number", finalFilterDateList.get(i).getPhone());
                    intent.putExtra("contact_id", finalFilterDateList.get(i).getContact_id());
                    startActivity(intent);
                }
            });
        }
        if (filterDateList.size() == 0) {
            tv_no_friends.setVisibility(View.VISIBLE);
        } else {
            tv_no_friends.setVisibility(View.GONE);
        }
    }
}
