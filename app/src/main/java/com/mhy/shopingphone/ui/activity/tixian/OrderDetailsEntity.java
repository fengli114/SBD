package com.mhy.shopingphone.ui.activity.tixian;

import java.util.List;

/**
 * 作者： "RedRainM" on 2017/11/16 0016.
 * 描述：
 */

public class OrderDetailsEntity {

    /**
     * Code : 0
     * Mess : Success
     * Info : [{"CID":"921826c7-a10b-40a2-93e5-e737bf8d4415","Blance":"0.0000","CreateTime":"2017/11/7 0:00:00","Money":"33.80","OrderNum":"1510042785","Pic":"http://img.alicdn.com/tfscom/i1/2107752594/TB1gWFkaYYI8KJjy0FaXXbAiVXa_!!0-item_pic.jpg","Name":"丽悦暖贴宝宝贴暖身贴自发热贴保暖贴宫暖贴关节贴发热帖足暖宝贴","SalesVolume":"219094","DetailUrl":"http://detail.tmall.com/item.htm?id=40222919414","TgUrl":"https://uland.taobao.com/coupon/edetail?e=1Een8TTG7TM8Clx5mXPEKpjggBW84%2FsCjNhWWA8N5POMNKzoOkNBlItclnJygZWlsFoBQ%2BDAtshHVIL0H6O%2Fkp4LsFRq8rhwQBDOcCOBLyzS%2BaNSNjK2Dyo8utixtn%2BrcQr4M0Rs8LYoh6cS3OUXu%2FvQFWeqWAJizdxrUWUH3uQy2cj%2FjrDhaA%3D%3D&traceId=0bface6215107136659998827e","Discount":"2","YhStart":"满30元减2元","Commission":67.6},{"CID":"1bb14b92-7fff-4c94-9720-3efda08cbde6","Blance":"0.0000","CreateTime":"2017/11/7 0:00:00","Money":"49.90","OrderNum":"1510044025","Pic":"http://img.alicdn.com/tfscom/i1/TB1QJW8IVXXXXbaXFXXXXXXXXXX_!!0-item_pic.jpg","Name":"广东电信50元手机话费充值 自动快充 即时到帐 快速到账 直充","SalesVolume":"176568","DetailUrl":"http://detail.tmall.com/item.htm?id=16060064759","TgUrl":"https://uland.taobao.com/coupon/edetail?e=gJgcRp09GmdD6TM1M2v5G9Uij0apwAAeumyALssNxnH6Osr7OGPg7RSUBkabYdmv2xTbFI8J0V7lRwHvbMh7%2B3BJNfEfQMi1QBDOcCOBLyzS%2BaNSNjK2Dyo8utixtn%2BrcQr4M0Rs8LYoh6cS3OUXu%2FvQFWeqWAJizdxrUWUH3uQy2cj%2FjrDhaA%3D%3D&traceId=0bb9f7bc15101183404307616e","Discount":"5","YhStart":"满49元减5元","Commission":9.98}]
     */

    private String Code;
    private String Mess;
    private List<InfoBean> Info;

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

    public List<InfoBean> getInfo() {
        return Info;
    }

    public void setInfo(List<InfoBean> Info) {
        this.Info = Info;
    }

    public static class InfoBean {
        /**
         * CID : 921826c7-a10b-40a2-93e5-e737bf8d4415
         * Blance : 0.0000
         * CreateTime : 2017/11/7 0:00:00
         * Money : 33.80
         * OrderNum : 1510042785
         * Pic : http://img.alicdn.com/tfscom/i1/2107752594/TB1gWFkaYYI8KJjy0FaXXbAiVXa_!!0-item_pic.jpg
         * Name : 丽悦暖贴宝宝贴暖身贴自发热贴保暖贴宫暖贴关节贴发热帖足暖宝贴
         * SalesVolume : 219094
         * DetailUrl : http://detail.tmall.com/item.htm?id=40222919414
         * TgUrl : https://uland.taobao.com/coupon/edetail?e=1Een8TTG7TM8Clx5mXPEKpjggBW84%2FsCjNhWWA8N5POMNKzoOkNBlItclnJygZWlsFoBQ%2BDAtshHVIL0H6O%2Fkp4LsFRq8rhwQBDOcCOBLyzS%2BaNSNjK2Dyo8utixtn%2BrcQr4M0Rs8LYoh6cS3OUXu%2FvQFWeqWAJizdxrUWUH3uQy2cj%2FjrDhaA%3D%3D&traceId=0bface6215107136659998827e
         * Discount : 2
         * YhStart : 满30元减2元
         * Commission : 67.6
         */

        private String CID;
        private String Blance;
        private String CreateTime;
        private String Money;
        private String OrderNum;
        private String Pic;
        private String Name;
        private String SalesVolume;
        private String DetailUrl;
        private String TgUrl;
        private String Discount;
        private String YhStart;
        private String Commission;

        public String getCID() {
            return CID;
        }

        public void setCID(String CID) {
            this.CID = CID;
        }

        public String getBlance() {
            return Blance;
        }

        public void setBlance(String Blance) {
            this.Blance = Blance;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }

        public String getOrderNum() {
            return OrderNum;
        }

        public void setOrderNum(String OrderNum) {
            this.OrderNum = OrderNum;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public String getSalesVolume() {
            return SalesVolume;
        }

        public void setSalesVolume(String SalesVolume) {
            this.SalesVolume = SalesVolume;
        }

        public String getDetailUrl() {
            return DetailUrl;
        }

        public void setDetailUrl(String DetailUrl) {
            this.DetailUrl = DetailUrl;
        }

        public String getTgUrl() {
            return TgUrl;
        }

        public void setTgUrl(String TgUrl) {
            this.TgUrl = TgUrl;
        }

        public String getDiscount() {
            return Discount;
        }

        public void setDiscount(String Discount) {
            this.Discount = Discount;
        }

        public String getYhStart() {
            return YhStart;
        }

        public void setYhStart(String YhStart) {
            this.YhStart = YhStart;
        }

        public String getCommission() {
            return Commission;
        }

        public void setCommission(String Commission) {
            this.Commission = Commission;
        }
    }
}
