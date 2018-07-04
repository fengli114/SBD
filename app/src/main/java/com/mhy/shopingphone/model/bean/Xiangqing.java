package com.mhy.shopingphone.model.bean;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class Xiangqing {

    /**
     * data : null
     * errorCode : 200
     * json : {"commodities":{"id":"a32bf27d-d836-4dbf-a56c-0572ddce121d","tmid":"552754286100","name":"竹炭去黑头面膜撕拉式鼻贴膜收缩毛孔套装男女通用吸黑头祛粉刺","pic":"http://img.alicdn.com/imgextra/i3/3012913363/TB2qPrIX7fb_uJkHFrdXXX2IVXa_!!3012913363.jpg","detailurl":"http://detail.tmall.com/item.htm?id=552754286100","category":"美容美体","tbkurl":"http://detail.tmall.com/item.htm?id=552754286100","money":39.9,"salesvolume":53790,"commission":31,"endtime":1522684800000,"tgurl":null,"datastatus":true,"discount":0,"isby":false,"isjx":true,"isall":true,"ishot":true,"createtime":1522252800000,"yhstart":"a6a75921c6824d18a94b9cfdbc021c6d","remainCount":0,"shopid":"00000000-0000-0000-0000-000000000000","isnew":true},"tbkInfo":{"id":"2b482a2f-52a3-4ae5-8302-a41143aad121","tmid":"552754286100","tbkurl":"https://uland.taobao.com/coupon/edetail?e=il%2Bf3qArYeAGQASttHIRqSLudSYL7n2q0LuCQfeItbnbbEBX1H%2BT5LIMsFlQMc6q65dJlHmvNH0VONj5nXLMHr9fwBwwUiqldqt9eaSObunJnadaiBmiH0BsXx8cnY%2FDCG%2BBCQ265qQ1QtEhD9S%2B3Q%3D%3D&traceId=0bfa0d6715226803209001332e","discount":20,"remainCount":6730,"commission":31,"updatetime":1522598400000,"shopid":"2473b790-d164-4958-afbd-4d62adece5e8"}}
     */

    private Object data;
    private int errorCode;
    private JsonBean json;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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
         * commodities : {"id":"a32bf27d-d836-4dbf-a56c-0572ddce121d","tmid":"552754286100","name":"竹炭去黑头面膜撕拉式鼻贴膜收缩毛孔套装男女通用吸黑头祛粉刺","pic":"http://img.alicdn.com/imgextra/i3/3012913363/TB2qPrIX7fb_uJkHFrdXXX2IVXa_!!3012913363.jpg","detailurl":"http://detail.tmall.com/item.htm?id=552754286100","category":"美容美体","tbkurl":"http://detail.tmall.com/item.htm?id=552754286100","money":39.9,"salesvolume":53790,"commission":31,"endtime":1522684800000,"tgurl":null,"datastatus":true,"discount":0,"isby":false,"isjx":true,"isall":true,"ishot":true,"createtime":1522252800000,"yhstart":"a6a75921c6824d18a94b9cfdbc021c6d","remainCount":0,"shopid":"00000000-0000-0000-0000-000000000000","isnew":true}
         * tbkInfo : {"id":"2b482a2f-52a3-4ae5-8302-a41143aad121","tmid":"552754286100","tbkurl":"https://uland.taobao.com/coupon/edetail?e=il%2Bf3qArYeAGQASttHIRqSLudSYL7n2q0LuCQfeItbnbbEBX1H%2BT5LIMsFlQMc6q65dJlHmvNH0VONj5nXLMHr9fwBwwUiqldqt9eaSObunJnadaiBmiH0BsXx8cnY%2FDCG%2BBCQ265qQ1QtEhD9S%2B3Q%3D%3D&traceId=0bfa0d6715226803209001332e","discount":20,"remainCount":6730,"commission":31,"updatetime":1522598400000,"shopid":"2473b790-d164-4958-afbd-4d62adece5e8"}
         */

        private CommoditiesBean commodities;
        private TbkInfoBean tbkInfo;

        public CommoditiesBean getCommodities() {
            return commodities;
        }

        public void setCommodities(CommoditiesBean commodities) {
            this.commodities = commodities;
        }

        public TbkInfoBean getTbkInfo() {
            return tbkInfo;
        }

        public void setTbkInfo(TbkInfoBean tbkInfo) {
            this.tbkInfo = tbkInfo;
        }

        public static class CommoditiesBean {
            /**
             * id : a32bf27d-d836-4dbf-a56c-0572ddce121d
             * tmid : 552754286100
             * name : 竹炭去黑头面膜撕拉式鼻贴膜收缩毛孔套装男女通用吸黑头祛粉刺
             * pic : http://img.alicdn.com/imgextra/i3/3012913363/TB2qPrIX7fb_uJkHFrdXXX2IVXa_!!3012913363.jpg
             * detailurl : http://detail.tmall.com/item.htm?id=552754286100
             * category : 美容美体
             * tbkurl : http://detail.tmall.com/item.htm?id=552754286100
             * money : 39.9
             * salesvolume : 53790
             * commission : 31.0
             * endtime : 1522684800000
             * tgurl : null
             * datastatus : true
             * discount : 0
             * isby : false
             * isjx : true
             * isall : true
             * ishot : true
             * createtime : 1522252800000
             * yhstart : a6a75921c6824d18a94b9cfdbc021c6d
             * remainCount : 0
             * shopid : 00000000-0000-0000-0000-000000000000
             * isnew : true
             */

            private String id;
            private String tmid;
            private String name;
            private String pic;
            private String detailurl;
            private String category;
            private String tbkurl;
            private double money;
            private int salesvolume;
            private double commission;
            private long endtime;
            private Object tgurl;
            private boolean datastatus;
            private int discount;
            private boolean isby;
            private boolean isjx;
            private boolean isall;
            private boolean ishot;
            private long createtime;
            private String yhstart;
            private int remainCount;
            private String shopid;
            private boolean isnew;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTmid() {
                return tmid;
            }

            public void setTmid(String tmid) {
                this.tmid = tmid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getDetailurl() {
                return detailurl;
            }

            public void setDetailurl(String detailurl) {
                this.detailurl = detailurl;
            }

            public String getCategory() {
                return category;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public String getTbkurl() {
                return tbkurl;
            }

            public void setTbkurl(String tbkurl) {
                this.tbkurl = tbkurl;
            }

            public double getMoney() {
                return money;
            }

            public void setMoney(double money) {
                this.money = money;
            }

            public int getSalesvolume() {
                return salesvolume;
            }

            public void setSalesvolume(int salesvolume) {
                this.salesvolume = salesvolume;
            }

            public double getCommission() {
                return commission;
            }

            public void setCommission(double commission) {
                this.commission = commission;
            }

            public long getEndtime() {
                return endtime;
            }

            public void setEndtime(long endtime) {
                this.endtime = endtime;
            }

            public Object getTgurl() {
                return tgurl;
            }

            public void setTgurl(Object tgurl) {
                this.tgurl = tgurl;
            }

            public boolean isDatastatus() {
                return datastatus;
            }

            public void setDatastatus(boolean datastatus) {
                this.datastatus = datastatus;
            }

            public int getDiscount() {
                return discount;
            }

            public void setDiscount(int discount) {
                this.discount = discount;
            }

            public boolean isIsby() {
                return isby;
            }

            public void setIsby(boolean isby) {
                this.isby = isby;
            }

            public boolean isIsjx() {
                return isjx;
            }

            public void setIsjx(boolean isjx) {
                this.isjx = isjx;
            }

            public boolean isIsall() {
                return isall;
            }

            public void setIsall(boolean isall) {
                this.isall = isall;
            }

            public boolean isIshot() {
                return ishot;
            }

            public void setIshot(boolean ishot) {
                this.ishot = ishot;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }

            public String getYhstart() {
                return yhstart;
            }

            public void setYhstart(String yhstart) {
                this.yhstart = yhstart;
            }

            public int getRemainCount() {
                return remainCount;
            }

            public void setRemainCount(int remainCount) {
                this.remainCount = remainCount;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }

            public boolean isIsnew() {
                return isnew;
            }

            public void setIsnew(boolean isnew) {
                this.isnew = isnew;
            }
        }

        public static class TbkInfoBean {
            /**
             * id : 2b482a2f-52a3-4ae5-8302-a41143aad121
             * tmid : 552754286100
             * tbkurl : https://uland.taobao.com/coupon/edetail?e=il%2Bf3qArYeAGQASttHIRqSLudSYL7n2q0LuCQfeItbnbbEBX1H%2BT5LIMsFlQMc6q65dJlHmvNH0VONj5nXLMHr9fwBwwUiqldqt9eaSObunJnadaiBmiH0BsXx8cnY%2FDCG%2BBCQ265qQ1QtEhD9S%2B3Q%3D%3D&traceId=0bfa0d6715226803209001332e
             * discount : 20.0
             * remainCount : 6730
             * commission : 31.0
             * updatetime : 1522598400000
             * shopid : 2473b790-d164-4958-afbd-4d62adece5e8
             */

            private String id;
            private String tmid;
            private String tbkurl;
            private double discount;
            private int remainCount;
            private double commission;
            private long updatetime;
            private String shopid;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTmid() {
                return tmid;
            }

            public void setTmid(String tmid) {
                this.tmid = tmid;
            }

            public String getTbkurl() {
                return tbkurl;
            }

            public void setTbkurl(String tbkurl) {
                this.tbkurl = tbkurl;
            }

            public double getDiscount() {
                return discount;
            }

            public void setDiscount(double discount) {
                this.discount = discount;
            }

            public int getRemainCount() {
                return remainCount;
            }

            public void setRemainCount(int remainCount) {
                this.remainCount = remainCount;
            }

            public double getCommission() {
                return commission;
            }

            public void setCommission(double commission) {
                this.commission = commission;
            }

            public long getUpdatetime() {
                return updatetime;
            }

            public void setUpdatetime(long updatetime) {
                this.updatetime = updatetime;
            }

            public String getShopid() {
                return shopid;
            }

            public void setShopid(String shopid) {
                this.shopid = shopid;
            }
        }
    }
}
