package com.mhy.shopingphone.model.bean.shop;

/**
 * 作者： "RedRainM" on 2018/1/23 0023.
 * 描述：
 */

public class ShopDetailBean {

    /**
     * Code : 0
     * Mess : 操作成功
     * Cinfo : {"ID":"3aaa59bb-deb0-4324-b5cb-e26acaa46f76","TmID":"562866843316","Name":"花花公子贵宾羽绒服男中长款修身加厚17冬季韩版帅气外套学生男装","Pic":"https://img.alicdn.com/imgextra/i4/1731639261/TB2utgVkDvI8KJjSspjXXcgjXXa_!!1731639261.jpg","DetailUrl":"http://detail.tmall.com/item.htm?id=562866843316","Category":"男装","TbkUrl":"http://detail.tmall.com/item.htm?id=562866843316","Money":498,"SalesVolume":77,"Commission":26,"EndTime":"/Date(1516896000000)/","TgUrl":null,"Discount":0,"DataStatus":false,"IsBY":false,"IsJx":true,"IsAll":true,"IsHot":true,"YhStart":"a3f4721b4499442589ae218a719fbe6b","CreateTime":"/Date(1516550400000)/","remain_count":0,"ShopID":"00000000-0000-0000-0000-000000000000","IsNew":true}
     * Tbk : {"ID":"701a1e8f-e3af-499f-9c76-38352c57f1d5","TmID":"562866843316","TbkUrl":"https://uland.taobao.com/coupon/edetail?e=mLllTInGen0GQASttHIRqTn1dDw1luMcalU2OQlUm9kL%2FD%2FeJHZrYZi7mQwiTTvwBgaA0DDJmY2MtjrbnC%2FUfL9fwBwwUiql8uAtEhvV%2BV9ERTUVBheqhW7PVn13QcLNgPRfTgnhrZM%3D&traceId=0bb7501715166715589404622e","Discount":200,"remain_count":49929,"Commission":26,"UpdateTime":"/Date(1516671558948)/","ShopID":"58162318-deca-4442-8e38-743b7729aa5b"}
     */

    private String Code;
    private String Mess;
    private CinfoBean Cinfo;
    private TbkBean Tbk;

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMess() {
        return Mess;
    }

    public void setMess(String Mess) {
        this.Mess = Mess;
    }

    public CinfoBean getCinfo() {
        return Cinfo;
    }

    public void setCinfo(CinfoBean Cinfo) {
        this.Cinfo = Cinfo;
    }

    public TbkBean getTbk() {
        return Tbk;
    }

    public void setTbk(TbkBean Tbk) {
        this.Tbk = Tbk;
    }

    public static class CinfoBean {
        /**
         * ID : 3aaa59bb-deb0-4324-b5cb-e26acaa46f76
         * TmID : 562866843316
         * Name : 花花公子贵宾羽绒服男中长款修身加厚17冬季韩版帅气外套学生男装
         * Pic : https://img.alicdn.com/imgextra/i4/1731639261/TB2utgVkDvI8KJjSspjXXcgjXXa_!!1731639261.jpg
         * DetailUrl : http://detail.tmall.com/item.htm?id=562866843316
         * Category : 男装
         * TbkUrl : http://detail.tmall.com/item.htm?id=562866843316
         * Money : 498.0
         * SalesVolume : 77
         * Commission : 26.0
         * EndTime : /Date(1516896000000)/
         * TgUrl : null
         * Discount : 0
         * DataStatus : false
         * IsBY : false
         * IsJx : true
         * IsAll : true
         * IsHot : true
         * YhStart : a3f4721b4499442589ae218a719fbe6b
         * CreateTime : /Date(1516550400000)/
         * remain_count : 0
         * ShopID : 00000000-0000-0000-0000-000000000000
         * IsNew : true
         */

        private String ID;
        private String TmID;
        private String Name;
        private String Pic;
        private String DetailUrl;
        private String Category;
        private String TbkUrl;
        private double Money;
        private int SalesVolume;
        private double Commission;
        private String EndTime;
        private Object TgUrl;
        private int Discount;
        private boolean DataStatus;
        private boolean IsBY;
        private boolean IsJx;
        private boolean IsAll;
        private boolean IsHot;
        private String YhStart;
        private String CreateTime;
        private int remain_count;
        private String ShopID;
        private boolean IsNew;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTmID() {
            return TmID;
        }

        public void setTmID(String TmID) {
            this.TmID = TmID;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public String getDetailUrl() {
            return DetailUrl;
        }

        public void setDetailUrl(String DetailUrl) {
            this.DetailUrl = DetailUrl;
        }

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public String getTbkUrl() {
            return TbkUrl;
        }

        public void setTbkUrl(String TbkUrl) {
            this.TbkUrl = TbkUrl;
        }

        public double getMoney() {
            return Money;
        }

        public void setMoney(double Money) {
            this.Money = Money;
        }

        public int getSalesVolume() {
            return SalesVolume;
        }

        public void setSalesVolume(int SalesVolume) {
            this.SalesVolume = SalesVolume;
        }

        public double getCommission() {
            return Commission;
        }

        public void setCommission(double Commission) {
            this.Commission = Commission;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public Object getTgUrl() {
            return TgUrl;
        }

        public void setTgUrl(Object TgUrl) {
            this.TgUrl = TgUrl;
        }

        public int getDiscount() {
            return Discount;
        }

        public void setDiscount(int Discount) {
            this.Discount = Discount;
        }

        public boolean isDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(boolean DataStatus) {
            this.DataStatus = DataStatus;
        }

        public boolean isIsBY() {
            return IsBY;
        }

        public void setIsBY(boolean IsBY) {
            this.IsBY = IsBY;
        }

        public boolean isIsJx() {
            return IsJx;
        }

        public void setIsJx(boolean IsJx) {
            this.IsJx = IsJx;
        }

        public boolean isIsAll() {
            return IsAll;
        }

        public void setIsAll(boolean IsAll) {
            this.IsAll = IsAll;
        }

        public boolean isIsHot() {
            return IsHot;
        }

        public void setIsHot(boolean IsHot) {
            this.IsHot = IsHot;
        }

        public String getYhStart() {
            return YhStart;
        }

        public void setYhStart(String YhStart) {
            this.YhStart = YhStart;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getRemain_count() {
            return remain_count;
        }

        public void setRemain_count(int remain_count) {
            this.remain_count = remain_count;
        }

        public String getShopID() {
            return ShopID;
        }

        public void setShopID(String ShopID) {
            this.ShopID = ShopID;
        }

        public boolean isIsNew() {
            return IsNew;
        }

        public void setIsNew(boolean IsNew) {
            this.IsNew = IsNew;
        }
    }

    public static class TbkBean {
        /**
         * ID : 701a1e8f-e3af-499f-9c76-38352c57f1d5
         * TmID : 562866843316
         * TbkUrl : https://uland.taobao.com/coupon/edetail?e=mLllTInGen0GQASttHIRqTn1dDw1luMcalU2OQlUm9kL%2FD%2FeJHZrYZi7mQwiTTvwBgaA0DDJmY2MtjrbnC%2FUfL9fwBwwUiql8uAtEhvV%2BV9ERTUVBheqhW7PVn13QcLNgPRfTgnhrZM%3D&traceId=0bb7501715166715589404622e
         * Discount : 200
         * remain_count : 49929
         * Commission : 26.0
         * UpdateTime : /Date(1516671558948)/
         * ShopID : 58162318-deca-4442-8e38-743b7729aa5b
         */

        private String ID;
        private String TmID;
        private String TbkUrl;
        private int Discount;
        private int remain_count;
        private double Commission;
        private String UpdateTime;
        private String ShopID;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getTmID() {
            return TmID;
        }

        public void setTmID(String TmID) {
            this.TmID = TmID;
        }

        public String getTbkUrl() {
            return TbkUrl;
        }

        public void setTbkUrl(String TbkUrl) {
            this.TbkUrl = TbkUrl;
        }

        public int getDiscount() {
            return Discount;
        }

        public void setDiscount(int Discount) {
            this.Discount = Discount;
        }

        public int getRemain_count() {
            return remain_count;
        }

        public void setRemain_count(int remain_count) {
            this.remain_count = remain_count;
        }

        public double getCommission() {
            return Commission;
        }

        public void setCommission(double Commission) {
            this.Commission = Commission;
        }

        public String getUpdateTime() {
            return UpdateTime;
        }

        public void setUpdateTime(String UpdateTime) {
            this.UpdateTime = UpdateTime;
        }

        public String getShopID() {
            return ShopID;
        }

        public void setShopID(String ShopID) {
            this.ShopID = ShopID;
        }
    }
}
