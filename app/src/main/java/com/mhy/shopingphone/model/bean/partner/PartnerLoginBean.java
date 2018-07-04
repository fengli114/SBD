package com.mhy.shopingphone.model.bean.partner;

/**
 * Created by admin on 2017/11/14 0014.
 */

public class PartnerLoginBean {

    /**
     * Code : 0
     * Mess : Login Success
     * Info : {"ID":"58162318-deca-4442-8e38-743b7729aa5b","UserName":"随便打","Password":"YXNkZmFzZGY=Q3VzdG9tQmFzZTY0","Email":"shanpengfei@2017@yandex.ru","CreateTime":"/Date(1506268800000)/","LoginTime":"/Date(1510817780806)/","LoginIp":"192.168.13.111","Role":1,"DataStatus":true,"Mobile":"","Blance":2.000001135E7,"FreeRoute":"随便打","ParentId":"7dd9a07e-9cef-4702-a165-b7033861c1b7","AgentLogo":"/Images/8370422017925181415265f201ef6-80b2-46ed-9015-8bd89f57695b.png","Prefix":"随便打专线","AddCard":false,"NextAgent":true,"Theme":"skin-blur-ocean","AndroidVersion":"1.0","AndroidPath":"http://admin.xsroem.com/download/suibianda.apk","IOSVersion":"1.0","IOSPath":"test","RegiMoney":3,"EndTime":"/Date(-2209017600000)/","Pic":null,"DKBL":1,"IsShop":true,"Type":7,"ShopID":"58162318-deca-4442-8e38-743b7729aa5b","AdId":"145912073","ProfitRatio":0.5,"ProfitMoney":77000}
     * Pay : {"ID":"48e806cf-09a4-4cfd-8412-d80c1d5d06bd","UserID":"58162318-deca-4442-8e38-743b7729aa5b","Type":3,"AliAccount":"17704820771","BankName":"招商","BankNo":"6225751112130848","Name":"随便打你","DataStatus":true}
     */

    private String Code;
    private String Mess;
    private InfoBean Info;
    private PayBean Pay;

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

    public PayBean getPay() {
        return Pay;
    }

    public void setPay(PayBean Pay) {
        this.Pay = Pay;
    }

    public static class InfoBean {
        /**
         * ID : 58162318-deca-4442-8e38-743b7729aa5b
         * UserName : 随便打
         * Password : YXNkZmFzZGY=Q3VzdG9tQmFzZTY0
         * Email : shanpengfei@2017@yandex.ru
         * CreateTime : /Date(1506268800000)/
         * LoginTime : /Date(1510817780806)/
         * LoginIp : 192.168.13.111
         * Role : 1
         * DataStatus : true
         * Mobile :
         * Blance : 2.000001135E7
         * FreeRoute : 随便打
         * ParentId : 7dd9a07e-9cef-4702-a165-b7033861c1b7
         * AgentLogo : /Images/8370422017925181415265f201ef6-80b2-46ed-9015-8bd89f57695b.png
         * Prefix : 随便打专线
         * AddCard : false
         * NextAgent : true
         * Theme : skin-blur-ocean
         * AndroidVersion : 1.0
         * AndroidPath : http://admin.xsroem.com/download/suibianda.apk
         * IOSVersion : 1.0
         * IOSPath : test
         * RegiMoney : 3
         * EndTime : /Date(-2209017600000)/
         * Pic : null
         * DKBL : 1.0
         * IsShop : true
         * Type : 7
         * ShopID : 58162318-deca-4442-8e38-743b7729aa5b
         * AdId : 145912073
         * ProfitRatio : 0.5
         * ProfitMoney : 77000.0
         */

        private String ID;
        private String UserName;
        private String Password;
        private String Email;
        private String CreateTime;
        private String LoginTime;
        private String LoginIp;
        private int Role;
        private boolean DataStatus;
        private String Mobile;
        private double Blance;
        private String FreeRoute;
        private String ParentId;
        private String AgentLogo;
        private String Prefix;
        private boolean AddCard;
        private boolean NextAgent;
        private String Theme;
        private String AndroidVersion;
        private String AndroidPath;
        private String IOSVersion;
        private String IOSPath;
        private int RegiMoney;
        private String EndTime;
        private Object Pic;
        private double DKBL;
        private boolean IsShop;
        private int Type;
        private String ShopID;
        private String AdId;
        private double ProfitRatio;
        private double ProfitMoney;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
        }

        public String getEmail() {
            return Email;
        }

        public void setEmail(String Email) {
            this.Email = Email;
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

        public double getBlance() {
            return Blance;
        }

        public void setBlance(double Blance) {
            this.Blance = Blance;
        }

        public String getFreeRoute() {
            return FreeRoute;
        }

        public void setFreeRoute(String FreeRoute) {
            this.FreeRoute = FreeRoute;
        }

        public String getParentId() {
            return ParentId;
        }

        public void setParentId(String ParentId) {
            this.ParentId = ParentId;
        }

        public String getAgentLogo() {
            return AgentLogo;
        }

        public void setAgentLogo(String AgentLogo) {
            this.AgentLogo = AgentLogo;
        }

        public String getPrefix() {
            return Prefix;
        }

        public void setPrefix(String Prefix) {
            this.Prefix = Prefix;
        }

        public boolean isAddCard() {
            return AddCard;
        }

        public void setAddCard(boolean AddCard) {
            this.AddCard = AddCard;
        }

        public boolean isNextAgent() {
            return NextAgent;
        }

        public void setNextAgent(boolean NextAgent) {
            this.NextAgent = NextAgent;
        }

        public String getTheme() {
            return Theme;
        }

        public void setTheme(String Theme) {
            this.Theme = Theme;
        }

        public String getAndroidVersion() {
            return AndroidVersion;
        }

        public void setAndroidVersion(String AndroidVersion) {
            this.AndroidVersion = AndroidVersion;
        }

        public String getAndroidPath() {
            return AndroidPath;
        }

        public void setAndroidPath(String AndroidPath) {
            this.AndroidPath = AndroidPath;
        }

        public String getIOSVersion() {
            return IOSVersion;
        }

        public void setIOSVersion(String IOSVersion) {
            this.IOSVersion = IOSVersion;
        }

        public String getIOSPath() {
            return IOSPath;
        }

        public void setIOSPath(String IOSPath) {
            this.IOSPath = IOSPath;
        }

        public int getRegiMoney() {
            return RegiMoney;
        }

        public void setRegiMoney(int RegiMoney) {
            this.RegiMoney = RegiMoney;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public Object getPic() {
            return Pic;
        }

        public void setPic(Object Pic) {
            this.Pic = Pic;
        }

        public double getDKBL() {
            return DKBL;
        }

        public void setDKBL(double DKBL) {
            this.DKBL = DKBL;
        }

        public boolean isIsShop() {
            return IsShop;
        }

        public void setIsShop(boolean IsShop) {
            this.IsShop = IsShop;
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

        public String getAdId() {
            return AdId;
        }

        public void setAdId(String AdId) {
            this.AdId = AdId;
        }

        public double getProfitRatio() {
            return ProfitRatio;
        }

        public void setProfitRatio(double ProfitRatio) {
            this.ProfitRatio = ProfitRatio;
        }

        public double getProfitMoney() {
            return ProfitMoney;
        }

        public void setProfitMoney(double ProfitMoney) {
            this.ProfitMoney = ProfitMoney;
        }
    }

    public static class PayBean {
        /**
         * ID : 48e806cf-09a4-4cfd-8412-d80c1d5d06bd
         * UserID : 58162318-deca-4442-8e38-743b7729aa5b
         * Type : 3
         * AliAccount : 17704820771
         * BankName : 招商
         * BankNo : 6225751112130848
         * Name : 随便打你
         * DataStatus : true
         */

        private String ID;
        private String UserID;
        private int Type;
        private String AliAccount;
        private String BankName;
        private String BankNo;
        private String Name;
        private String Mobile;
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

        public void setName(String Name) {
            this.Name = Name;
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

        public void setMobile(String mobile) {
            Mobile = mobile;
        }
    }
}
