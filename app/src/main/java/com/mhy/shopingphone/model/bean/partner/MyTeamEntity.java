package com.mhy.shopingphone.model.bean.partner;

import java.util.List;

/**
 * 作者： "RedRainM" on 2017/11/16 0016.
 * 描述：
 */

public class MyTeamEntity {

    /**
     * Code : 0
     * Mess : Success
     * Info : [{"ID":"9569f523-e4d8-4179-b254-33eaf687387d","CID":"921826c7-a10b-40a2-93e5-e737bf8d4415","OrderNum":"1510042785","CreateTime":"/Date(1509984000000)/","Pic":"17704820771","Discount":"3","Blance":"0.0000","Money":"33.80","DetailUrl":"http://detail.tmall.com/item.htm?id=40222919414","DataStatus":true,"UserID":"85b32cff-448d-41d4-9f22-bb8b81c0aaf2","ParentID":"58162318-deca-4442-8e38-743b7729aa5b","Commission":12},{"ID":"3e4e5c68-7a8f-4166-bb19-43bc89e8d1f0","CID":"1bb14b92-7fff-4c94-9720-3efda08cbde6","OrderNum":"1510044025","CreateTime":"/Date(1509984000000)/","Pic":"17704820771","Discount":"5","Blance":"0.0000","Money":"49.90","DetailUrl":"http://detail.tmall.com/item.htm?id=16060064759","DataStatus":true,"UserID":"85b32cff-448d-41d4-9f22-bb8b81c0aaf2","ParentID":"58162318-deca-4442-8e38-743b7729aa5b","Commission":12}]
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
         * ID : 9569f523-e4d8-4179-b254-33eaf687387d
         * CID : 921826c7-a10b-40a2-93e5-e737bf8d4415
         * OrderNum : 1510042785
         * CreateTime : /Date(1509984000000)/
         * Pic : 17704820771
         * Discount : 3
         * Blance : 0.0000
         * Money : 33.80
         * DetailUrl : http://detail.tmall.com/item.htm?id=40222919414
         * DataStatus : true
         * UserID : 85b32cff-448d-41d4-9f22-bb8b81c0aaf2
         * ParentID : 58162318-deca-4442-8e38-743b7729aa5b
         * Commission : 12.0
         */

        private String ID;
        private String CID;
        private String OrderNum;
        private String CreateTime;
        private String Pic;
        private String Discount;
        private String Blance;
        private String Money;
        private String DetailUrl;
        private boolean DataStatus;
        private String UserID;
        private String ParentID;
        private String Commission;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCID() {
            return CID;
        }

        public void setCID(String CID) {
            this.CID = CID;
        }

        public String getOrderNum() {
            return OrderNum;
        }

        public void setOrderNum(String OrderNum) {
            this.OrderNum = OrderNum;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public String getDiscount() {
            return Discount;
        }

        public void setDiscount(String Discount) {
            this.Discount = Discount;
        }

        public String getBlance() {
            return Blance;
        }

        public void setBlance(String Blance) {
            this.Blance = Blance;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }

        public String getDetailUrl() {
            return DetailUrl;
        }

        public void setDetailUrl(String DetailUrl) {
            this.DetailUrl = DetailUrl;
        }

        public boolean isDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(boolean DataStatus) {
            this.DataStatus = DataStatus;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getParentID() {
            return ParentID;
        }

        public void setParentID(String ParentID) {
            this.ParentID = ParentID;
        }

        public String getCommission() {
            return Commission;
        }

        public void setCommission(String Commission) {
            this.Commission = Commission;
        }
    }
}
