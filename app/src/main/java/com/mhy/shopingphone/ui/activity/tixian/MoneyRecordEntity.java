package com.mhy.shopingphone.ui.activity.tixian;

import java.util.List;

/**
 * 作者： "RedRainM" on 2017/11/17 0017.
 * 描述：
 */

public class MoneyRecordEntity {

    /**
     * Code : 0
     * Mess : Success
     * Info : [{"ID":"265ecc23-b48e-48e9-9e20-66e363e90fe0","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510848000000)/","WithdrawalsType":2,"Money":2,"Status":1,"DataStatus":true},{"ID":"0fb9944c-da5c-485f-847b-47d7435677e2","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510848000000)/","WithdrawalsType":1,"Money":2,"Status":1,"DataStatus":true},{"ID":"846bf8ca-3448-4d22-b89f-faedbb7fbcc9","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510761600000)/","WithdrawalsType":2,"Money":2,"Status":1,"DataStatus":true},{"ID":"aefaa40c-3029-4ce7-99f3-a976bc3a367c","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510761600000)/","WithdrawalsType":1,"Money":30,"Status":1,"DataStatus":true},{"ID":"87d94224-7d7c-4562-beb3-822219592ae6","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510761600000)/","WithdrawalsType":1,"Money":2,"Status":1,"DataStatus":true},{"ID":"b9939977-a7dc-4a58-911a-99dc454f3811","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510761600000)/","WithdrawalsType":1,"Money":555,"Status":1,"DataStatus":true},{"ID":"d08def0b-dda8-410a-82d6-012fafdc7382","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510761600000)/","WithdrawalsType":2,"Money":5,"Status":1,"DataStatus":true},{"ID":"912f6910-3388-4ff4-95d0-0f391ee38b08","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510675200000)/","WithdrawalsType":1,"Money":10000,"Status":1,"DataStatus":true},{"ID":"2130f5c5-9a63-4f52-b279-124d118e4c21","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510675200000)/","WithdrawalsType":1,"Money":10000,"Status":1,"DataStatus":true},{"ID":"c1b646dc-9686-42d1-93ee-16e1b156de3b","UserID":"58162318-deca-4442-8e38-743b7729aa5b","CreateTime":"/Date(1510675200000)/","WithdrawalsType":1,"Money":10000,"Status":1,"DataStatus":true}]
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
         * ID : 265ecc23-b48e-48e9-9e20-66e363e90fe0
         * UserID : 58162318-deca-4442-8e38-743b7729aa5b
         * CreateTime : /Date(1510848000000)/
         * WithdrawalsType : 2
         * Money : 2.0
         * Status : 1
         * DataStatus : true
         */

        private String ID;
        private String UserID;
        private String CreateTime;
        private int WithdrawalsType;
        private double Money;
        private int Status;
        private boolean DataStatus;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUserID() {
            return UserID;
        }

        public void setUserID(String UserID) {
            this.UserID = UserID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public int getWithdrawalsType() {
            return WithdrawalsType;
        }

        public void setWithdrawalsType(int WithdrawalsType) {
            this.WithdrawalsType = WithdrawalsType;
        }

        public double getMoney() {
            return Money;
        }

        public void setMoney(double Money) {
            this.Money = Money;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public boolean isDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(boolean DataStatus) {
            this.DataStatus = DataStatus;
        }
    }
}
