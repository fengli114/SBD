package com.mhy.shopingphone.model.bean.shop;

import java.util.List;

/**
 * Created by RedRainM on 2017/10/13.
 */

public class MyOrderBean {


    /**
     * data : 成功获取用户订单数据
     * errorCode : 200
     * json : [{"name":null,"status":0,"money":"5.88","cid":"11e53178-c45b-41bb-a4e6-0f0a2e80beb5","blance":"1.00","yhStart":null,"discount":null,"salesVolume":0,"detailUrl":null,"createTime":1522756462000,"tgUrl":null,"pic":"http://img.alicdn.com/imgextra/i2/2273278951/TB2goPNa4olyKJjSZFDXXbNfpXa_!!2273278951.jpg","orderNum":"1522756462"},{"name":null,"status":0,"money":"238.00","cid":"52816f57-ac5c-4efe-9915-7c2b64916bfd","blance":"80.00","yhStart":null,"discount":null,"salesVolume":0,"detailUrl":null,"createTime":1522756345000,"tgUrl":null,"pic":"http://img.alicdn.com/imgextra/i1/1088928919/TB2oHDZbtAvbeFjSspbXXbcOFXa_!!1088928919.jpg","orderNum":"1522756346"},{"name":null,"status":0,"money":"33.50","cid":"d88955eb-741f-4de9-b437-c87ac77cf0aa","blance":"5.00","yhStart":null,"discount":null,"salesVolume":0,"detailUrl":null,"createTime":1522029447000,"tgUrl":null,"pic":"http://img.alicdn.com/imgextra/i4/2873655189/TB2_yFLqoUIL1JjSZFrXXb3xFXa_!!2873655189.jpg","orderNum":"1522029448"}]
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

    public static class JsonBean {
        /**
         * name : null
         * status : 0
         * money : 5.88
         * cid : 11e53178-c45b-41bb-a4e6-0f0a2e80beb5
         * blance : 1.00
         * yhStart : null
         * discount : null
         * salesVolume : 0
         * detailUrl : null
         * createTime : 1522756462000
         * tgUrl : null
         * pic : http://img.alicdn.com/imgextra/i2/2273278951/TB2goPNa4olyKJjSZFDXXbNfpXa_!!2273278951.jpg
         * orderNum : 1522756462
         */

        private Object name;
        private int status;
        private String money;
        private String cid;
        private String blance;
        private Object yhStart;
        private Object discount;
        private int salesVolume;
        private Object detailUrl;
        private long createTime;
        private Object tgUrl;
        private String pic;
        private String orderNum;

        public Object getName() {
            return name;
        }

        public void setName(Object name) {
            this.name = name;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String getBlance() {
            return blance;
        }

        public void setBlance(String blance) {
            this.blance = blance;
        }

        public Object getYhStart() {
            return yhStart;
        }

        public void setYhStart(Object yhStart) {
            this.yhStart = yhStart;
        }

        public Object getDiscount() {
            return discount;
        }

        public void setDiscount(Object discount) {
            this.discount = discount;
        }

        public int getSalesVolume() {
            return salesVolume;
        }

        public void setSalesVolume(int salesVolume) {
            this.salesVolume = salesVolume;
        }

        public Object getDetailUrl() {
            return detailUrl;
        }

        public void setDetailUrl(Object detailUrl) {
            this.detailUrl = detailUrl;
        }

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
        }

        public Object getTgUrl() {
            return tgUrl;
        }

        public void setTgUrl(Object tgUrl) {
            this.tgUrl = tgUrl;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getOrderNum() {
            return orderNum;
        }

        public void setOrderNum(String orderNum) {
            this.orderNum = orderNum;
        }
    }
}
