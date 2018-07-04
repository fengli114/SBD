package com.mhy.shopingphone.model.bean.shop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RedRainM on 2017/10/13.
 */

public class GoodsBean implements Serializable{

    /**
     * data : 获取数据成功
     * errorCode : 2000
     * json :
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

    public static class JsonBean implements Serializable{
        private List<CommoditiesBean> commodities;
        private List<HotsBean> hots;
        private List<ShopTextsBean> shopTexts;
        private List<CategoriesBean> categories;

        public List<CommoditiesBean> getCommodities() {
            return commodities;
        }

        public void setCommodities(List<CommoditiesBean> commodities) {
            this.commodities = commodities;
        }

        public List<HotsBean> getHots() {
            return hots;
        }

        public void setHots(List<HotsBean> hots) {
            this.hots = hots;
        }

        public List<ShopTextsBean> getShopTexts() {
            return shopTexts;
        }

        public void setShopTexts(List<ShopTextsBean> shopTexts) {
            this.shopTexts = shopTexts;
        }

        public List<CategoriesBean> getCategories() {
            return categories;
        }

        public void setCategories(List<CategoriesBean> categories) {
            this.categories = categories;
        }

        public static class CommoditiesBean implements Serializable{
            /**
             * id : ddf4488eb93a4257a2a98733b291ff97
             * tmid : 527011579947
             * name : 皎洁电热蚊香液无味加热器插电式家用驱蚊防蚊非婴儿孕妇灭蚊液体
             * pic : http://img.alicdn.com/imgextra/i1/3432962706/TB25Gv4kY1YBuNjSszhXXcUsFXa_!!3432962706.jpg
             * detailurl : http://detail.tmall.com/item.htm?id=527011579947
             * category : 洗护清洁剂/卫生巾/纸/香薰
             * tbkurl : http://detail.tmall.com/item.htm?id=527011579947
             * money : 24.9
             * salesvolume : 452690
             * commission : 25.0
             * endtime : 1527955200000
             * tgurl : https://uland.taobao.com/coupon/edetail?e=VY6eSXyKP3kGQASttHIRqXsNThWBjmj5oxVLisljS4AL%2FD%2FeJHZrYZi7mQwiTTvwBgaA0DDJmY2MtjrbnC%2FUfL9fwBwwUiqldqt9eaSObunJnadaiBmiH27PVn13QcLNcISolD8QJ4pe3LZZWm0Yzg%3D%3D&traceId=0bfacaaf15275790716654810e
             * datastatus : true
             * discount : 10
             * isby : false
             * isjx : true
             * isall : true
             * ishot : true
             * createtime : 1527436800000
             * yhstart : e87b64ab128f4d6a9fec456d0489d73e
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
            private String tgurl;
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

            public String getTgurl() {
                return tgurl;
            }

            public void setTgurl(String tgurl) {
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

        public static class HotsBean implements Serializable{
            /**
             * id : 4dcbde6b7452438a9add544aa52b67cf
             * tmid : 569386563087
             * name : 200*230标准尺寸4斤 蚕丝夏凉被
             * pic : http://gd1.alicdn.com/imgextra/i3/140721824/TB2vG04s_tYBeNjy1XdXXXXyVXa_!!140721824.jpg
             * detailurl : http://detail.tmall.com/item.htm?id=569386563087
             * category : 床上用品
             * tbkurl : http://detail.tmall.com/item.htm?id=569386563087
             * money : 1195.0
             * salesvolume : 1972
             * commission : 30.0
             * endtime : 1527523200000
             * tgurl : null
             * datastatus : true
             * discount : 1000
             * isby : false
             * isjx : true
             * isall : true
             * ishot : true
             * createtime : 1527177600000
             * yhstart : b39ba4e335c44560b1ee239413bfbf84
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

        public static class ShopTextsBean implements Serializable{
            /**
             * id : 40c8a535-dd93-497e-95ad-b3c7304d88ad
             * text : 号外号外，话费可以抵扣现金券了！每年帮您省一半的网购费！
             * pic :
             * datastatus : true
             * type : 2
             */

            private String id;
            private String text;
            private String pic;
            private String url;
            private boolean datastatus;
            private int type;
            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public boolean isDatastatus() {
                return datastatus;
            }

            public void setDatastatus(boolean datastatus) {
                this.datastatus = datastatus;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }

        public static class CategoriesBean implements Serializable{
            /**
             * id : 698a37b5-498b-4fa2-9c77-bb31253992ae
             * alicate : 女装/女士精品|女鞋|女士内衣/男士内衣/家居服
             * cate : 女装
             * grade : 1
             * pic : /Images/973081201812917431289516f7ea7d-0752-4522-a4f4-a663d824057b.png
             * datastatus : true
             * indexpic : /Images/3672422018131142851490df33d84b-e353-4b86-904f-5cce11847d5d.png
             * dpic : null
             */

            private String id;
            private String alicate;
            private String cate;
            private String grade;
            private String pic;
            private boolean datastatus;
            private String indexpic;
            private Object dpic;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAlicate() {
                return alicate;
            }

            public void setAlicate(String alicate) {
                this.alicate = alicate;
            }

            public String getCate() {
                return cate;
            }

            public void setCate(String cate) {
                this.cate = cate;
            }

            public String getGrade() {
                return grade;
            }

            public void setGrade(String grade) {
                this.grade = grade;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public boolean isDatastatus() {
                return datastatus;
            }

            public void setDatastatus(boolean datastatus) {
                this.datastatus = datastatus;
            }

            public String getIndexpic() {
                return indexpic;
            }

            public void setIndexpic(String indexpic) {
                this.indexpic = indexpic;
            }

            public Object getDpic() {
                return dpic;
            }

            public void setDpic(Object dpic) {
                this.dpic = dpic;
            }
        }
    }
}
