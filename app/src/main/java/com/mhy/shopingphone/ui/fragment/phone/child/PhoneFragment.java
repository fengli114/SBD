package com.mhy.shopingphone.ui.fragment.phone.child;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mhy.sdk.adapter.FragmentAdapter;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.rxbus.Subscribe;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.SharedPreferencesHelper;
import com.mhy.sdk.utils.Util;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.constant.TabFragmentIndex;
import com.mhy.shopingphone.contract.phone.PhoneMainContract;
import com.mhy.shopingphone.model.bean.GroupMemberBean;
import com.mhy.shopingphone.presenter.phone.PhoneMainPresenter;
import com.mhy.shopingphone.ui.activity.ContactsActivity;
import com.mhy.shopingphone.ui.fragment.addresslist.AddressListFragment;
import com.mhy.shopingphone.ui.fragment.phone.child.tab.top.TOPDialFragment;
import com.mhy.shopingphone.widgets.adresslist.common.PinYinComparator;
import com.mhy.shopingphone.widgets.adresslist.common.Pinyin4jUtil;
//import com.orhanobut.logger.Logger;
import com.umeng.analytics.MobclickAgent;
import com.yuyh.library.EasyGuide;
import com.yuyh.library.constant.Constants;
import com.yuyh.library.support.HShape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述： 首页—电话界面
 */

public class PhoneFragment extends BaseMVPCompatFragment<PhoneMainContract.PhoneMainPresenter,
        PhoneMainContract.IPhoneMainModel> implements PhoneMainContract.IPhoneMainView {

    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.tou)
    ImageView tou;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.add_contact)
    RelativeLayout addPhone;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    Unbinder unbinder;
    private List<Fragment> fragments;
    private EasyGuide easyGuide;
    List<GroupMemberBean> friends;
    private static final String[] PHONES_PROJECTION = new String[]{
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
    /**
     * 电话号码
     **/
    private static final int PHONES_NUMBER_INDEX = 1;
    /**
     * 联系人显示名称
     **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    public static PhoneFragment newInstance() {
        Bundle args = new Bundle();
        PhoneFragment fragment = new PhoneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * RxBus
     */
    @Subscribe(code = 15)
    public void rxBusEvent() {
        tlTabs.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                // 加载完成后回调
                if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
                    tlTabs.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                } else {
                    tlTabs.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                }
                // TODO 显示高亮布局！
                show();//直接显示引导层
            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        fragments = new ArrayList<>();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mPresenter.getTabList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_phone_;
    }


    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        Util.setStatusBarHeigh(mContext, tou);
        try {
            getPhoneContacts();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String url = (String) SharedPreferencesHelper.getInstance().getData("Upic", "");
        LogUtils.e("wosh:" + url);
//        setImg(url);
//        ToolbarAnimManager.animIn(mContext, toolbar);
        // 等待高亮View加载完成之后再调用显示引导层，例如对于toolbar高亮来说：
        addPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ContactsActivity.class);
                startActivity(intent);
            }
        });

    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return PhoneMainPresenter.newInstance();
    }

    @Override
    public void showTabList(String[] tabs) {
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.addTab(tlTabs.newTab().setText(tabs[i]));
            switch (i) {
                case TabFragmentIndex.TAB_ZHIHU_INDEX:
                    fragments.add(TOPDialFragment.newInstance());
                    break;
                case TabFragmentIndex.TAB_WANGYI_INDEX:
                    fragments.add(AddressListFragment.newInstance());
                    break;
                default:
                    fragments.add(TOPDialFragment.newInstance());
                    break;
            }
        }
        vpFragment.setAdapter(new FragmentAdapter(getChildFragmentManager(), fragments));
        vpFragment.setCurrentItem(TabFragmentIndex.TAB_ZHIHU_INDEX);//要设置到viewpager.setAdapter后才起作用
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(TabFragmentIndex.TAB_ZHIHU_INDEX);
        //tlTabs.setupWithViewPager方法内部会remove所有的tabs，这里重新设置一遍tabs的text，否则tabs的text不显示
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.getTabAt(i).setText(tabs[i]);
        }

        tlTabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//              if (tab.getPosition() == 0){
//                  hideKeybord();
//                  addPhone.setVisibility(View.GONE);
//              }else {
//                  addPhone.setVisibility(View.VISIBLE);
//              }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    public void show() {
        int[] loc = new int[2];
        iv.getLocationOnScreen(loc);

        View tipsView = createTipsView();

        if (easyGuide != null && easyGuide.isShowing())
            easyGuide.dismiss();

        easyGuide = new EasyGuide.Builder(mActivity)
                // 增加View高亮区域，可同时显示多个
                .addHightArea(iv, HShape.RECTANGLE)
                // 复杂的提示布局，建议通过此方法，较容易控制
                .addView(tipsView, Constants.CENTER, Constants.CENTER)
                // 若点击作用在高亮区域，是否执行高亮区域的点击事件loc[1] + tlTabs.getHeight()
                .performViewClick(false)
                .addIndicator(R.drawable.tongxunluguide_1, loc[0], loc[1])
                .addIndicator(R.drawable.tongxunluguide_2, Constants.CENTER, iv.getHeight())
                .dismissAnyWhere(false)
                .build();

        easyGuide.show();
    }

    private View createTipsView() {

        View view = LayoutInflater.from(mActivity).inflate(R.layout.phone_guide_view2, null);

        ImageView ivIsee = (ImageView) view.findViewById(R.id.iv_guide_yes);
        ivIsee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (easyGuide != null) {
                    RxBus.get().send(16);
                    easyGuide.dismiss();
                }
            }
        });

        return view;
    }

    /**
     * 得到手机通讯录联系人信息
     **/
    private void getPhoneContacts() {
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        RxBus.get().register(this);
        MobclickAgent.onPageStart("dianhua");
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("dianhua");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
    }
}
