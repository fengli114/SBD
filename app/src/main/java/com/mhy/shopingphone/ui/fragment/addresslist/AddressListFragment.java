package com.mhy.shopingphone.ui.fragment.addresslist;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.rxbus.RxBus;
import com.mhy.sdk.utils.LogUtils;
import com.mhy.shopingphone.R;
import com.mhy.shopingphone.adapter.GoodsAdapter;
import com.mhy.shopingphone.adapter.RecentCallsAdapter;
import com.mhy.shopingphone.contract.addresslist.AddressListContract;
import com.mhy.shopingphone.model.bean.recentcalls.RecentCallsItemBean;

import org.xutils.common.util.LogUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;

/**
 * 作者： "RedRainM" on 2017/12/12 0012.
 * 描述：  通话记录界面
 */
public class AddressListFragment extends BaseMVPCompatFragment<AddressListContract.AddressListPresenter,
        AddressListContract.IAddressListMainModel> implements AddressListContract.IAddressListMainView {

    private List<RecentCallsItemBean> dataList;
    @BindView(R.id.rv_wangyi)
    RecyclerView rvWangyi;
    @BindView(R.id.ic_loading)
    LinearLayout ic_loading;
    private GetPhone getPhone;
    RecentCallsAdapter recentCallsAdapter;

    public static AddressListFragment newInstance() {
        Bundle args = new Bundle();
        AddressListFragment fragment = new AddressListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        RxBus.get().register(this);
        hideKeybord();
//        getDataList();
        getPhone = new GetPhone();
        getPhone.execute();
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public void showTabList(String[] tabs) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
//        getDataList();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_tonghua_dial;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
        recentCallsAdapter = new RecentCallsAdapter(R.layout.item_recent_calles);
        rvWangyi.setAdapter(recentCallsAdapter);
        rvWangyi.setLayoutManager(new LinearLayoutManager(mActivity));
    }

    //异步获取通话记录信息
    class GetPhone extends AsyncTask<Void, Integer, Boolean> {
        @Override
        protected void onPreExecute() {
//            Toast.makeText(mContext, "获取通话记录失败", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                ic_loading.setVisibility(View.VISIBLE);
                getDataList();
            } catch (Exception e) {
                if (dataList != null && dataList.size() > 0) {

                } else
                    dataList.clear();
                ic_loading.setVisibility(View.VISIBLE);
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if (dataList != null && dataList.size() > 0) {
                ic_loading.setVisibility(View.GONE);
                recentCallsAdapter = new RecentCallsAdapter(R.layout.item_recent_calles, dataList);
                rvWangyi.setAdapter(recentCallsAdapter);
                rvWangyi.setLayoutManager(new LinearLayoutManager(mActivity));

            } else {
                ic_loading.setVisibility(View.VISIBLE);
                rvWangyi.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 读取数据通话记录
     *
     * @return 读取到的数据
     */
    private void getDataList() {
        dataList = new ArrayList<>();
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

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String phone_name = cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME));
                String phone_number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER));
                String address = cursor.getString(cursor.getColumnIndex(CallLog.Calls.GEOCODED_LOCATION));
                long dateLong = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE));
                String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(dateLong));
                //时长
                int type = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE));
                String typeString = "";
                RecentCallsItemBean bean = new RecentCallsItemBean();
                bean.setName((phone_name == null) ? "未知联系人" : phone_name);
                bean.setPhone(phone_number);
                bean.setData(date);
                dataList.add(bean);
            }
            if (Build.VERSION.SDK_INT < 14) {
                cursor.close();
            }
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unRegister(this);
        if (!getPhone.isCancelled()) {
            getPhone.cancel(true);
        }
    }
}
