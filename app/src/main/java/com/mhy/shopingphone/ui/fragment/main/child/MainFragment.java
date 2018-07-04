package com.mhy.shopingphone.ui.fragment.main.child;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mhy.sdk.base.BasePresenter;
import com.mhy.sdk.base.fragment.BaseMVPCompatFragment;
import com.mhy.sdk.utils.NetworkConnectionUtils;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者： "RedRainM" on 2018/1/24 0024.
 * 描述：
 */

public class MainFragment extends BaseMVPCompatFragment {

    @BindView(R.id.click)
    AppCompatImageView click;
    Unbinder unbinder;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_no_net;
    }

    @Override
    public void initUI(View view, @Nullable Bundle savedInstanceState) {
              click.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      if (NetworkConnectionUtils.isNetworkConnected(mContext)){
                          Intent intent = mActivity.getIntent();
                          mActivity.finish();
                          startActivity(intent);
                      }else {
                          ToastUtils.showToast("网络走丢了，请检查网络~");
                      }
              }
              });
    }


}
