package com.mhy.shopingphone.model.bean;

import java.io.Serializable;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class UserBean implements Serializable{



    /**
     * Code : 0
     * Mess : Success
     * Money : 4165.25
     * Info : {"ID":"34eb0b8a-5261-4c3f-b30c-8c911d350406","Password":"MTIzNDU2Nzg=Q3VzdG9tQmFzZTY0","CreateTime":"/Date(1508947200000)/","LoginTime":"/Date(1516896000000)/","LoginIp":"36.27.10.101","Role":4,"DataStatus":true,"Mobile":"17760801929","ParentId":"58162318-deca-4442-8e38-743b7729aa5b","EndTime":"/Date(1514304000000)/","Pic":"/Images/60103320181269529423c7c7fad4-6df7-47e6-9eb0-7e3060740a49.jpg","Type":7,"ShopID":"58162318-deca-4442-8e38-743b7729aa5b","AppStore":"1","Integral":0,"AliAccount":null,"AliPay":false,"Shop_balance":0,"Shop_enddate":"/Date(-28800000)/","Birthday":null,"Gender":false,"Email":null,"Address":null,"UserName":null}
     * Mobile : 17760801929
     * Time : 2019-01-22
     * Pic : /Images/60103320181269529423c7c7fad4-6df7-47e6-9eb0-7e3060740a49.jpg
     * Appstore : 1
     * AliAccount : null
     * AliPay : false
     * Integral : 0
     * AndroidVer : 2.5
     * AndroidPa : http://admin.xsroem.com/download/suibianda.apk
     * IOSVer : 1.0.5
     * IOSPa : itms-services://?action=download-manifest&url=https://admin.xsroem.com/download/mainfest.plist
     * Shop_Blance : 0
     * Shop_EndTime : /Date(-28800000)/
     * IsAuth : true
     */

    private String Code;
    private String Mess;
    private String Money;
    private InfoBean Info;
    private String Mobile;
    private String Time;
    private String Pic;
    private String Appstore;
    private String AliAccount;
    private boolean AliPay;
    private int Integral;
    private String AndroidVer;
    private String AndroidPa;
    private String IOSVer;
    private String IOSPa;
    private String Shop_Blance;
    private String Shop_EndTime;
    private boolean IsAuth;

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

    public String getMoney() {
        return Money;
    }

    public void setMoney(String Money) {
        this.Money = Money;
    }

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean Info) {
        this.Info = Info;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getPic() {
        return Pic;
    }

    public void setPic(String Pic) {
        this.Pic = Pic;
    }

    public String getAppstore() {
        return Appstore;
    }

    public void setAppstore(String Appstore) {
        this.Appstore = Appstore;
    }

    public String getAliAccount() {
        return AliAccount;
    }

    public void setAliAccount(String AliAccount) {
        this.AliAccount = AliAccount;
    }

    public boolean isAliPay() {
        return AliPay;
    }

    public void setAliPay(boolean AliPay) {
        this.AliPay = AliPay;
    }

    public int getIntegral() {
        return Integral;
    }

    public void setIntegral(int Integral) {
        this.Integral = Integral;
    }

    public String getAndroidVer() {
        return AndroidVer;
    }

    public void setAndroidVer(String AndroidVer) {
        this.AndroidVer = AndroidVer;
    }

    public String getAndroidPa() {
        return AndroidPa;
    }

    public void setAndroidPa(String AndroidPa) {
        this.AndroidPa = AndroidPa;
    }

    public String getIOSVer() {
        return IOSVer;
    }

    public void setIOSVer(String IOSVer) {
        this.IOSVer = IOSVer;
    }

    public String getIOSPa() {
        return IOSPa;
    }

    public void setIOSPa(String IOSPa) {
        this.IOSPa = IOSPa;
    }

    public String getShop_Blance() {
        return Shop_Blance;
    }

    public void setShop_Blance(String Shop_Blance) {
        this.Shop_Blance = Shop_Blance;
    }

    public String getShop_EndTime() {
        return Shop_EndTime;
    }

    public void setShop_EndTime(String Shop_EndTime) {
        this.Shop_EndTime = Shop_EndTime;
    }

    public boolean isIsAuth() {
        return IsAuth;
    }

    public void setIsAuth(boolean IsAuth) {
        this.IsAuth = IsAuth;
    }

    public static class InfoBean implements Serializable{
        /**
         * ID : 34eb0b8a-5261-4c3f-b30c-8c911d350406
         * Password : MTIzNDU2Nzg=Q3VzdG9tQmFzZTY0
         * CreateTime : /Date(1508947200000)/
         * LoginTime : /Date(1516896000000)/
         * LoginIp : 36.27.10.101
         * Role : 4
         * DataStatus : true
         * Mobile : 17760801929
         * ParentId : 58162318-deca-4442-8e38-743b7729aa5b
         * EndTime : /Date(1514304000000)/
         * Pic : /Images/60103320181269529423c7c7fad4-6df7-47e6-9eb0-7e3060740a49.jpg
         * Type : 7
         * ShopID : 58162318-deca-4442-8e38-743b7729aa5b
         * AppStore : 1
         * Integral : 0
         * AliAccount : null
         * AliPay : false
         * Shop_balance : 0
         * Shop_enddate : /Date(-28800000)/
         * Birthday : null
         * Gender : false
         * Email : null
         * Address : null
         * UserName : null
         */

        private String ID;
        private String Password;
        private String CreateTime;
        private String LoginTime;
        private String LoginIp;
        private int Role;
        private boolean DataStatus;
        private String Mobile;
        private String ParentId;
        private String EndTime;
        private String Pic;
        private int Type;
        private String ShopID;
        private String AppStore;
        private int Integral;
        private String AliAccount;
        private boolean AliPay;
        private int Shop_balance;
        private String Shop_enddate;
        private String Birthday;
        private String Gender;
        private String Email;
        private String Address;
        private String UserName;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getLoginTime() {
            return LoginTime;
        }

        public void setLoginTime(String LoginTime) {
            this.LoginTime = LoginTime;
        }

        public String getLoginIp() {
            return LoginIp;
        }

        public void setLoginIp(String LoginIp) {
            this.LoginIp = LoginIp;
        }

        public int getRole() {
            return Role;
        }

        public void setRole(int Role) {
            this.Role = Role;
        }

        public boolean isDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(boolean DataStatus) {
            this.DataStatus = DataStatus;
        }

        public String getMobile() {
            return Mobile;
        }

        public void setMobile(String Mobile) {
            this.Mobile = Mobile;
        }

        public String getParentId() {
            return ParentId;
        }

        public void setParentId(String ParentId) {
            this.ParentId = ParentId;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getShopID() {
            return ShopID;
        }

        public void setShopID(String ShopID) {
            this.ShopID = ShopID;
        }

        public String getAppStore() {
            return AppStore;
        }

        public void setAppStore(String AppStore) {
            this.AppStore = AppStore;
        }

        public int getIntegral() {
            return Integral;
        }

        public void setIntegral(int Integral) {
            this.Integral = Integral;
        }

        public String getAliAccount() {
            return AliAccount;
        }

        public void setAliAccount(String AliAccount) {
            this.AliAccount = AliAccount;
        }

        public boolean isAliPay() {
            return AliPay;
        }

        public void setAliPay(boolean AliPay) {
            this.AliPay = AliPay;
        }

        public int getShop_balance() {
            return Shop_balance;
        }

        public void setShop_balance(int Shop_balance) {
            this.Shop_balance = Shop_balance;
        }

        public String getShop_enddate() {
            return Shop_enddate;
        }

        public void setShop_enddate(String Shop_enddate) {
            this.Shop_enddate = Shop_enddate;
        }

        public String getBirthday() {
            return Birthday;
        }

        public void setBirthday(String Birthday) {
            this.Birthday = Birthday;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
        }

        public String getAddress() {
            return Address;
        }

        public void setAddress(String Address) {
            this.Address = Address;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }
    }
    /**
     * Code : 0
     * Mess : Suucess
     *
     */


}
