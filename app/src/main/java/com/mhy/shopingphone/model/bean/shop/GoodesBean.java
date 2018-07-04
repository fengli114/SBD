package com.mhy.shopingphone.model.bean.shop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RedRainM on 2017/10/13.
 */

public class GoodesBean implements Serializable{


    /**
     * data : 获取数据成功
     * errorCode : 2000
     * json : [{"s_id":37347317,"skuId":"29602106144","skuName""spread_amount":"0.82","group_count":0,"group_price":0}]
     */

    private String data;
    private int errorCode;
    private List<JsonBean> json;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public List<JsonBean> getJson() {
        return json;
    }

    public void setJson(List<JsonBean> json) {
        this.json = json;
    }

    public static class JsonBean implements Serializable{
        /**
         * s_id : 37347317
         * skuId : 29602106144
         * skuName : 【品质旗舰店】夏季新款羊皮贝壳双口链条小包 出行必备
         * skuDesc : 内购价直降70元 羊皮链条双口小包 赠送运费险+精美卡包
         * materiaUrl : http://item.jd.com/29602106144.html
         * picUrl : http://p5z0upvlw.bkt.clouddn.com/c035de81ed2e92bf28e8b9a9d23cf9c2a8d81100984b8b0eSRn5tBISyU.jpg
         * wlPrice : 139.00
         * wlPrice_after : 69.00
         * wlCommissionShare : 20
         * couponList : http://coupon.m.jd.com/coupons/show.action?key=c1c91abe524349f1989196f3a4ab0f9f&roleId=12765694&to=ifinshin.jd.com
         * bindType : null
         * discount : 70.00
         * quota : null
         * platformType : null
         * beginTime : 1529856000
         * endTime : 1532534399
         * cname :
         * cid : 0
         * goodstype : 0
         * goodslx : 0
         * commission : 13.80
         * wlCommission : 13.80
         * two_hour_sales : 0
         * one_day_sales : 0
         * sales : 0
         * spread_count : 5000
         * spread_start_at : 1529856000
         * spread_end_at : 1530720000
         * spread_type : 0
         * spread_amount : 5.00
         * group_count : 2
         * group_price : 0
         */

        private int s_id;
        private String skuId;
        private String skuName;
        private String skuDesc;
        private String materiaUrl;
        private String picUrl;
        private String wlPrice;
        private String wlPrice_after;
        private String wlCommissionShare;
        private String couponList;
        private Object bindType;
        private String discount;
        private Object quota;
        private Object platformType;
        private int beginTime;
        private int endTime;
        private String cname;
        private int cid;
        private int goodstype;
        private int goodslx;
        private String commission;
        private String wlCommission;
        private int two_hour_sales;
        private int one_day_sales;
        private int sales;
        private int spread_count;
        private int spread_start_at;
        private int spread_end_at;
        private String spread_type;
        private String spread_amount;
        private int group_count;
        private int group_price;

        public int getS_id() {
            return s_id;
        }

        public void setS_id(int s_id) {
            this.s_id = s_id;
        }

        public String getSkuId() {
            return skuId;
        }

        public void setSkuId(String skuId) {
            this.skuId = skuId;
        }

        public String getSkuName() {
            return skuName;
        }

        public void setSkuName(String skuName) {
            this.skuName = skuName;
        }

        public String getSkuDesc() {
            return skuDesc;
        }

        public void setSkuDesc(String skuDesc) {
            this.skuDesc = skuDesc;
        }

        public String getMateriaUrl() {
            return materiaUrl;
        }

        public void setMateriaUrl(String materiaUrl) {
            this.materiaUrl = materiaUrl;
        }

        public String getPicUrl() {
            return picUrl;
        }

        public void setPicUrl(String picUrl) {
            this.picUrl = picUrl;
        }

        public String getWlPrice() {
            return wlPrice;
        }

        public void setWlPrice(String wlPrice) {
            this.wlPrice = wlPrice;
        }

        public String getWlPrice_after() {
            return wlPrice_after;
        }

        public void setWlPrice_after(String wlPrice_after) {
            this.wlPrice_after = wlPrice_after;
        }

        public String getWlCommissionShare() {
            return wlCommissionShare;
        }

        public void setWlCommissionShare(String wlCommissionShare) {
            this.wlCommissionShare = wlCommissionShare;
        }

        public String getCouponList() {
            return couponList;
        }

        public void setCouponList(String couponList) {
            this.couponList = couponList;
        }

        public Object getBindType() {
            return bindType;
        }

        public void setBindType(Object bindType) {
            this.bindType = bindType;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public Object getQuota() {
            return quota;
        }

        public void setQuota(Object quota) {
            this.quota = quota;
        }

        public Object getPlatformType() {
            return platformType;
        }

        public void setPlatformType(Object platformType) {
            this.platformType = platformType;
        }

        public int getBeginTime() {
            return beginTime;
        }

        public void setBeginTime(int beginTime) {
            this.beginTime = beginTime;
        }

        public int getEndTime() {
            return endTime;
        }

        public void setEndTime(int endTime) {
            this.endTime = endTime;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public int getGoodstype() {
            return goodstype;
        }

        public void setGoodstype(int goodstype) {
            this.goodstype = goodstype;
        }

        public int getGoodslx() {
            return goodslx;
        }

        public void setGoodslx(int goodslx) {
            this.goodslx = goodslx;
        }

        public String getCommission() {
            return commission;
        }

        public void setCommission(String commission) {
            this.commission = commission;
        }

        public String getWlCommission() {
            return wlCommission;
        }

        public void setWlCommission(String wlCommission) {
            this.wlCommission = wlCommission;
        }

        public int getTwo_hour_sales() {
            return two_hour_sales;
        }

        public void setTwo_hour_sales(int two_hour_sales) {
            this.two_hour_sales = two_hour_sales;
        }

        public int getOne_day_sales() {
            return one_day_sales;
        }

        public void setOne_day_sales(int one_day_sales) {
            this.one_day_sales = one_day_sales;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public int getSpread_count() {
            return spread_count;
        }

        public void setSpread_count(int spread_count) {
            this.spread_count = spread_count;
        }

        public int getSpread_start_at() {
            return spread_start_at;
        }

        public void setSpread_start_at(int spread_start_at) {
            this.spread_start_at = spread_start_at;
        }

        public int getSpread_end_at() {
            return spread_end_at;
        }

        public void setSpread_end_at(int spread_end_at) {
            this.spread_end_at = spread_end_at;
        }

        public String getSpread_type() {
            return spread_type;
        }

        public void setSpread_type(String spread_type) {
            this.spread_type = spread_type;
        }

        public String getSpread_amount() {
            return spread_amount;
        }

        public void setSpread_amount(String spread_amount) {
            this.spread_amount = spread_amount;
        }

        public int getGroup_count() {
            return group_count;
        }

        public void setGroup_count(int group_count) {
            this.group_count = group_count;
        }

        public int getGroup_price() {
            return group_price;
        }

        public void setGroup_price(int group_price) {
            this.group_price = group_price;
        }
    }
}
