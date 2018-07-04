//package com.mhy.shopingphone.ui.fragment.addresslist;
//
//import android.content.ContentResolver;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.provider.ContactsContract;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.mhy.sdk.base.BasePresenter;
//import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
//import com.mhy.sdk.utils.LogUtils;
//import com.mhy.shopingphone.R;
//import com.mhy.shopingphone.adapter.RecAdressListAdapter;
//import com.mhy.shopingphone.contract.addresslist.AddressListContract;
//import com.mhy.shopingphone.model.bean.GroupMemberBean;
//import com.mhy.shopingphone.model.bean.phone.ContactBean;
//import com.mhy.shopingphone.widgets.contacts_recycle.CommonUtil;
//import com.mhy.shopingphone.widgets.contacts_recycle.CustomItemDecoration;
//import com.mhy.shopingphone.widgets.contacts_recycle.IndexBar;
//import com.mhy.shopingphone.widgets.contacts_recycle.SideBar;
//import com.mhy.shopingphone.widgets.contacts_recycle.itemAnimator.SlideInOutLeftItemAnimator;
//import com.mhy.shopingphone.widgets.decoration.DividerItemDecoration;
//import com.mhy.shopingphone.widgets.decoration.TitleItemDecoration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import butterknife.Unbinder;
//
///**
// * 作者： "RedRainM" on 2017/12/12 0012.
// * 描述：  通讯录界面
// */
//
//public class AddressListFragment1 extends BaseMVPCompatFragment<AddressListContract.AddressListPresenter,
//        AddressListContract.IAddressListMainModel> implements AddressListContract.IAddressListMainView {
//
//
//    @BindView(R.id.rl_recycle_view)
//    RecyclerView mRecyclerView;
//    @BindView(R.id.side_bar)
//    SideBar side_bar;
//    @BindView(R.id.index_bar)
//    IndexBar indexBar;
//    Unbinder unbinder;
//    /*
//         * 获取库Phon表字段
//         **/
//    private List<GroupMemberBean> mDatas;
//    List<ContactBean> nameList = new ArrayList<>();
//    private static final String[] PHONES_PROJECTION = new String[]{
//            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Photo.PHOTO_ID, ContactsContract.CommonDataKinds.Phone.CONTACT_ID};
//    private TitleItemDecoration mDecoration;
//    private CustomItemDecoration decoration;
//
//    public static AddressListFragment1 newInstance() {
//        Bundle args = new Bundle();
//        AddressListFragment1 fragment = new AddressListFragment1();
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @NonNull
//    @Override
//    public BasePresenter initPresenter() {
//        return null;
//    }
//
//    @Override
//    public void showTabList(String[] tabs) {
//
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.fragment_address_list;
//    }
//
//    @Override
//    public void initUI(View view, @Nullable Bundle savedInstanceState) {
//        mDatas = new ArrayList<>();
//
////        RxPermissions.getInstance(mActivity).request(Manifest.permission.CALL_PHONE,
////                Manifest.permission.READ_PHONE_STATE,
////                Manifest.permission.READ_CONTACTS,
////                Manifest.permission.READ_CALL_LOG
////        )
////                .subscribe(new Action1<Boolean>() {
////                    @Override
////                    public void call(Boolean aBoolean) {
////                        if (aBoolean) {
////                            getMailistdata();
//        getPhoneContacts();// 获取手机联系人
////                        } else {
////                            Toast.makeText(mActivity, "拒绝权限", Toast.LENGTH_SHORT).show();
////                            return;
////                        }
////                    }
////                });
//
//
//        RecAdressListAdapter adapter = new RecAdressListAdapter(mActivity);
//
//        final LinearLayoutManager mManager = new LinearLayoutManager(mActivity);
////        mRecyclerView.addItemDecoration(decoration);
//        mRecyclerView.setLayoutManager(mManager);
//        mRecyclerView.setAdapter(adapter);
//        mRecyclerView.addItemDecoration(decoration = new CustomItemDecoration(mActivity));
//        mRecyclerView.setItemAnimator(new SlideInOutLeftItemAnimator(mRecyclerView));
//        //如果add两个，那么按照先后顺序，依次渲染。
//        //mRv.addItemDecoration(new TitleItemDecoration2(this,mDatas));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
//        //对数据源进行排序
//        CommonUtil.sortData(nameList);
//        //返回一个包含所有Tag字母在内的字符串并赋值给tagsStr
//        String tagsStr = CommonUtil.getTags(nameList);
//        side_bar.setIndexStr(tagsStr);
//        decoration.setDatas(nameList, tagsStr);
//        adapter.addDatas(nameList);
//
//    }
//
//    /**
//     * 电话号码
//     **/
//    private static final int PHONES_NUMBER_INDEX = 1;
//    /**
//     * 联系人显示名称
//     **/
//    private static final int PHONES_DISPLAY_NAME_INDEX = 0;
//
//    /**
//     * 得到手机通讯录联系人信息
//     **/
//    private void getPhoneContacts() {
//        ContentResolver resolver = mActivity.getContentResolver();
//
//        // 获取手机联系人  PHONES_PROJECTION
//        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                PHONES_PROJECTION, null, null, null);
////        showProgressBar();
//        if (phoneCursor != null) {
//            mDatas.clear();
//            while (phoneCursor.moveToNext()) {
//                // 得到手机号码
//                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
//                // 当手机号码为空的或者为空字段 跳过当前循环
//                if (TextUtils.isEmpty(phoneNumber))
//                    continue;
//
//                // 得到联系人名称
//                String contactName = phoneCursor
//                        .getString(PHONES_DISPLAY_NAME_INDEX);
//                if (TextUtils.isEmpty(contactName))
//                    continue;
//                //帅选
//                String phone_cache = phoneNumber;
//                if (phoneNumber.contains(" ")) {
//                    phone_cache = phoneNumber.replace(" ", "").trim();
//                }
//                int contact_id = phoneCursor.getInt(phoneCursor.getColumnIndex("contact_id"));
//                if (TextUtils.isEmpty(contact_id + ""))
//                    continue;
//
//                ContactBean bean = new ContactBean();
//
//                bean.setPhone(phoneNumber);
//                bean.setContact_id(contact_id);
//                bean.setName(contactName);
//                LogUtils.e("phoneNumber:" + phoneNumber + "contactName:" + contactName);
//
//
//                boolean isAgain = false;
//                for (ContactBean groupBean : nameList) {
//                    if (contactName.equals(groupBean.getName())) {
//                        isAgain = true;
//                    }
//                }
//                if (!isAgain) {
//                    nameList.add(bean);
//                }
//            }
//
//        }
//        phoneCursor.close();
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        // TODO: inflate a fragment view
//        View rootView = super.onCreateView(inflater, container, savedInstanceState);
//        unbinder = ButterKnife.bind(this, rootView);
//        return rootView;
//    }
//
//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        unbinder.unbind();
//    }
//}
