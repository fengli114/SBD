package com.mhy.shopingphone.cache;

import com.mhy.sdk.utils.SpUtils;
import com.mhy.shopingphone.model.bean.discover.NewsBean;
import com.mhy.shopingphone.model.bean.shop.GoodsBean;
import com.mhy.shopingphone.model.bean.shop.ShopBannerBean;

import java.util.List;

/**
 * Created by Horrarndoo on 2017/10/17.
 * <p>
 */

public class Cache {
    /**
     * 获取豆瓣电影hot cache
     *
     * @return 豆瓣电影hot cache
     */
    public static List<GoodsBean.JsonBean.CommoditiesBean> getHotMovieCache() {
        return SpUtils.getDataList("hot_movie_cache", GoodsBean.JsonBean.CommoditiesBean.class);
    }

    /**
     * 获取豆瓣电影hot cache
     *
     * @return 豆瓣电影hot cache
     */
//    public static NewsBean getNewsCache() {
//        return SpUtils.get("discover_news_cache", NewsBean.class);
//    }
    /**
     * 保存豆瓣电影hot cache
     */
    public static void saveHotMovieCache(List<GoodsBean.JsonBean.CommoditiesBean> list) {
        SpUtils.setDataList("hot_movie_cache", list);
    }

    /**
     * 获取商城Banner cache
     *
     * @return 商城Banner cache
     */
    public static List<ShopBannerBean.InfoBean> getShopBannerCache() {
        return SpUtils.getDataList("hot_shop_bean", ShopBannerBean.InfoBean.class);
    }
    /**
     * 保存商城Banner cache
     */
    public static void saveShopBannerCache(List<ShopBannerBean.InfoBean> list) {
        SpUtils.setDataList("hot_shop_bean", list);
    }
}
