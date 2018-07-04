package com.mhy.shopingphone.model.bean.greendao.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 作者： "RedRainM" on 2017/12/28 0028.
 * 描述：
 */
@Entity
public class BannerListBean {

    @Id(autoincrement = true) // id自增长
    private Long id; // 学院id
    private String bannerUrl; // URl
    private String strUrl; // URl
    @Generated(hash = 1698832514)
    public BannerListBean(Long id, String bannerUrl, String strUrl) {
        this.id = id;
        this.bannerUrl = bannerUrl;
        this.strUrl = strUrl;
    }
    @Generated(hash = 690706115)
    public BannerListBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBannerUrl() {
        return this.bannerUrl;
    }
    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }
    public String getStrUrl() {
        return this.strUrl;
    }
    public void setStrUrl(String strUrl) {
        this.strUrl = strUrl;
    }
}
