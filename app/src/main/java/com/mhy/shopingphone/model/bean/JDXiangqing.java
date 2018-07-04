package com.mhy.shopingphone.model.bean;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class JDXiangqing {
    /**
     * data : 获取数据成功
     * errorCode : 2000
     * json : {"skuId":"19758844287","skuName":"家英 水果削皮器齿型瓜果刨刀不锈钢家用削皮器创意厨房304不锈钢刨皮器","skuDesc":"家英 水果削皮器齿型瓜果刨刀不锈钢家用削皮器创意厨房304不锈钢刨皮器","materiaUrl":"http://item.jd.com/19758844287.html","picUrl":"http://img14.360buyimg.com/n1/jfs/t14059/66/193268365/112578/527b15b3/5a059e39N67818cca.jpg","wlPrice":"3.00","wlPrice_after":"2.00","wlCommissionShare":"45","couponList":"http://coupon.jd.com/ilink/couponSendFront/send_index.action?key=5353a96ce274449e919f5406481f8833&roleId=11692056&to=mall.jd.com/index-205807.html","bindType":"3","discount":"1.00","quota":"2.00","platformType":"0","beginTime":1525363200,"endTime":1533139199,"cnameicon":"","cname":"厨具","cid":6196,"goodstype":0,"goodslx":0,"commission":"0.90","wlCommission":"0.90","spread_count":0,"spread_start_at":0,"spread_end_at":0,"spread_type":"","spread_amount":"0.00","group_count":0,"group_price":0}
     */

    private String data;
    private int errorCode;
    private JsonBean json;

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

    public JsonBean getJson() {
        return json;
    }

    public void setJson(JsonBean json) {
        this.json = json;
    }

    public static class JsonBean {
        /**
         * skuId : 19758844287
         * skuName : 家英 水果削皮器齿型瓜果刨刀不锈钢家用削皮器创意厨房304不锈钢刨皮器
         * skuDesc : 家英 水果削皮器齿型瓜果刨刀不锈钢家用削皮器创意厨房304不锈钢刨皮器
         * materiaUrl : http://item.jd.com/19758844287.html
         * picUrl : http://img14.360buyimg.com/n1/jfs/t14059/66/193268365/112578/527b15b3/5a059e39N67818cca.jpg
         * wlPrice : 3.00
         * wlPrice_after : 2.00
         * wlCommissionShare : 45
         * couponList : http://coupon.jd.com/ilink/couponSendFront/send_index.action?key=5353a96ce274449e919f5406481f8833&roleId=11692056&to=mall.jd.com/index-205807.html
         * bindType : 3
         * discount : 1.00
         * quota : 2.00
         * platformType : 0
         * beginTime : 1525363200
         * endTime : 1533139199
         * cnameicon :
         * cname : 厨具
         * cid : 6196
         * goodstype : 0
         * goodslx : 0
         * commission : 0.90
         * wlCommission : 0.90
         * spread_count : 0
         * spread_start_at : 0
         * spread_end_at : 0
         * spread_type :
         * spread_amount : 0.00
         * group_count : 0
         * group_price : 0
         */

        private String skuId;
        private String skuName;
        private String skuDesc;
        private String materiaUrl;
        private String picUrl;
        private String wlPrice;
        private String wlPrice_after;
        private String wlCommissionShare;
        private String couponList;
        private String bindType;
        private String discount;
        private String quota;
        private String platformType;
        private int beginTime;
        private int endTime;
        private String cnameicon;
        private String cname;
        private int cid;
        private int goodstype;
        private int goodslx;
        private String commission;
        private String wlCommission;
        private int spread_count;
        private int spread_start_at;
        private int spread_end_at;
        private String spread_type;
        private String spread_amount;
        private int group_count;
        private int group_price;

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

        public String getBindType() {
            return bindType;
        }

        public void setBindType(String bindType) {
            this.bindType = bindType;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getQuota() {
            return quota;
        }

        public void setQuota(String quota) {
            this.quota = quota;
        }

        public String getPlatformType() {
            return platformType;
        }

        public void setPlatformType(String platformType) {
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

        public String getCnameicon() {
            return cnameicon;
        }

        public void setCnameicon(String cnameicon) {
            this.cnameicon = cnameicon;
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
