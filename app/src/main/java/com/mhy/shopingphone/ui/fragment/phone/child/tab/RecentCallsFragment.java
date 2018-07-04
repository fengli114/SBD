package com.mhy.shopingphone.ui.fragment.phone.child.tab;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseRecycleFragment;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.RecentCallsAdapter;
import com.mhy.shopingphone.contract.phone.tabs.RecentCallsContract;
import com.mhy.shopingphone.model.bean.phone.MyPhoneEntity;
import com.mhy.shopingphone.model.bean.phone.PhoneItemBean;
import com.mhy.shopingphone.model.bean.recentcalls.RecentCallsItemBean;
import com.mhy.shopingphone.presenter.phone.tab.RecentCallsParsenter;
import com.mhy.shopingphone.ui.activity.phone.DialBackActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述： 最近通话界面
 */

public class RecentCallsFragment extends BaseRecycleFragment<RecentCallsContract.RecentCallsPresenter,
        RecentCallsContract.IRecentCallsModel> implements RecentCallsContract.IRecentCallsView {

    @BindView(R.id.rv_wangyi)
    RecyclerView rvWangyi;
    Unbinder unbinder;

    private List<RecentCallsItemBean> dataList;
    public static RecentCallsFragment newInstance() {
        Bundle args = new Bundle();
        RecentCallsFragment fragment = new RecentCallsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recent_calls;
    }

    @Override
    public void onResume() {
        super.onResume();
        dataList = new ArrayList<>();
        getDataList();
        //初始化一个空list的adapter，网络错误时使用，第一次加载到数据时重新初始化adapter并绑定recycleview
        RecentCallsAdapter recentCallsAdapter = new RecentCallsAdapter(R.layout.item_recent_calls,dataList);
        rvWangyi.setAdapter(recentCallsAdapter);
        rvWangyi.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {


    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return RecentCallsParsenter.newInstance();
    }

    @Override
    public void updateContentList(List<RecentCallsItemBean> list) {

    }

    @Override
    public void itemNotifyChanged(int position) {

    }

    @Override
    public void showNetworkError() {

    }

    @Override
    public void showLoadMoreError() {

    }

    @Override
    public void showNoMoreData() {

    }

    @Override
    protected void onErrorViewClick(View view) {

    }

    @Override
    protected void showLoading() {

    }
    /**
     * 读取数据通话记录
     *
     * @return 读取到的数据
     */
    private void getDataList() {
        // 1.获得ContentResolver
        ContentResolver resolver = getActivity().getContentResolver();
        // 2.利用ContentResolver的query方法查询通话记录数据库
        /**
         * @param uri
         *            需要查询的URI，（这个URI是ContentProvider提供的）
         * @param projection
         *            需要查询的字段
         * @param selection
         *            sql语句where之后的语句
         * @param selectionArgs
         *            ?占位符代表的数据
         * @param sortOrder
         *            排序方式
         *
         */
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
        }
        Cursor cursor = resolver.query(CallLog.Calls.CONTENT_URI, // 查询通话记录的URI
                new String[]{CallLog.Calls.GEOCODED_LOCATION// 通话记录的归属地
                        , CallLog.Calls.CACHED_NAME// 通话记录的联系人CACHED_NAME
                        , CallLog.Calls.NUMBER// 通话记录的电话号码
                        , CallLog.Calls.DATE// 通话记录的日期
                        , CallLog.Calls.TYPE// 通话记录的类型

                }// 通话类型
                , null, null, CallLog.Calls.DEFAULT_SORT_ORDER// 按照时间逆序排列，最近打的最先显示
        );
        if(cursor != null && cursor.getCount() >= 1) {
            while (cursor.moveToNext()) {
                String phone_name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String phone_number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                String address = cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION));
//            String contact_id = cursor.getString(cursor.getColumnIndex("subscription_id"));
                long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date(dateLong));
                //时长
                // int duration = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.DURATION));
                int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String typeString = "";
                RecentCallsItemBean bean = new RecentCallsItemBean();
                bean.setName((phone_name == null) ? "未知联系人" : phone_name);
                bean.setPhone(phone_number);
//            bean.setId(contact_id);
                bean.setData(date);
                dataList.add(bean);
            }
            cursor.close();
        }

    }


}
