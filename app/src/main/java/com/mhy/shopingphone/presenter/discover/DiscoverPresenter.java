package com.mhy.shopingphone.presenter.discover;

import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.mhy.sdk.utils.LogUtils;
import com.mhy.sdk.utils.ToastUtils;
import com.mhy.shopingphone.contract.discover.DiscoverContract;
import com.mhy.shopingphone.model.bean.detail.DynamicBean;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.discover.ShoperBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.discover.DiscoverModel;
import com.youth.xframe.utils.log.XLog;

import io.reactivex.functions.Consumer;

/**
 * 作者： "RedRainM" on 2017/12/20 0020.
 * 描述：
 */
public class DiscoverPresenter extends DiscoverContract.DiscoverPresenter {
    @NonNull
    public static DiscoverPresenter newInstance() {
        return new DiscoverPresenter();
    }

    @Override
    public DiscoverContract.IDiscoverModel getModel() {
        return DiscoverModel.newInstance();
    }

    @Override
    public void onStart() {

    }


    @Override
    public void loadShoperList(String string) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getShoperList(string).subscribe(new Consumer<ShoperBean>() {
            @Override
            public void accept(ShoperBean shoperBean) throws Exception {
                if (mIView == null)
                    return;
//                LogUtils.e("商户："+shoperBean.getCode());
//                LogUtils.e("商户："+shoperBean.getMess());

                mIView.updateShoperList(shoperBean.getJson());
//                Cache.saveHotMovieCache(newsBean);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView != null) {
                    if (mIView.isVisiable())
                        mIView.showToast("网络异常，请检查网络~");

//                    if (Cache.getHotMovieCache().size() == 0) {//没有缓存缓存，显示网络错误界面
//                        mIView.showNetworkError();
//                    } else {
//                        mIView.updateContentList(Cache.getHotMovieCache());//加载缓存
//                    }
                }
            }
        }));
    }
    @Override
    public void loadDynamicList(String map) {
        if (mIModel == null || mIView == null)
            return;

        mRxManager.register(mIModel.getDynamicList(map).subscribe(new Consumer<DynamicBean>() {
            @Override
            public void accept(DynamicBean goodsBean) throws Exception {
                if (mIView == null)
                    return;
                LogUtils.e("订单：" + goodsBean.getCode());
                mIView.updateContentsList(goodsBean.getInfo());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                if (mIView == null)
                    return;
                mIView.showToast("网络异常，请检查网络~");
                mIView.showNetworkError();
            }

        }));
    }

    @Override
    public void onItemClick(int position, GoodsBean.JsonBean.CommoditiesBean item, ImageView imageView) {
        ToastUtils.showToast(""+position);
//        MovieDetailActivity.start(mIView.getBindActivity(), item, imageView);
    }
}
