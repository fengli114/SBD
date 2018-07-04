package com.mhy.shopingphone.ui.activity.tixian;

/**
 * 作者： "RedRainM" on 2017/11/16 0016.
 * 描述：
 */

public class BindUserEntity {

    /**
     * Code : 0
     * Mess : Success
     * Info : {"ID":"a5cfe2c2-b69e-4db9-ac01-6010c70c6609","UserID":"58162318-deca-4442-8e38-743b7729aa5b","Type":1,"AliAccount":"17760801929","BankName":null,"BankNo":null,"Name":"毛红宇","DataStatus":true}
     */

    private String Code;
    private String Mess;
    private InfoBean Info;

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

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean Info) {
        this.Info = Info;
    }

    public static class InfoBean {
        /**
         * ID : a5cfe2c2-b69e-4db9-ac01-6010c70c6609
         * UserID : 58162318-deca-4442-8e38-743b7729aa5b
         * Type : 1
         * AliAccount : 17760801929
         * BankName : null
         * BankNo : null
         * Name : 毛红宇
         * DataStatus : true
         */

        private String ID;
        private String UserID;
        private int Type;
        private String AliAccount;
        private String BankName;
        private String Mobile;
        private String BankNo;
        private String Name;
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

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getAliAccount() {
            return AliAccount;
        }

        public void setAliAccount(String AliAccount) {
            this.AliAccount = AliAccount;
        }

        public String getBankName() {
            return BankName;
        }

        public void setBankName(String BankName) {
            this.BankName = BankName;
        }

        public String getBankNo() {
            return BankNo;
        }

        public void setBankNo(String BankNo) {
            this.BankNo = BankNo;
        }

        public String getName() {
            return Name;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String mobile) {
            Mobile = mobile;
        }

        public void setName(String Name) {
            this.Name = Name;
        }

        public boolean isDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(boolean DataStatus) {
            this.DataStatus = DataStatus;
        }
    }
}
