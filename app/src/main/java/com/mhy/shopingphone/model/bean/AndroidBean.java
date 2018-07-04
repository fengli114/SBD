package com.mhy.shopingphone.model.bean;

import java.io.Serializable;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class AndroidBean implements Serializable{
    /**
     *  Code = 0;
     Info =     {
     AdId = 145912073;
     AddCard = 1;
     AgentLogo = "/Images/44026420171114144115478ffdbeef-c953-44ee-9b4e-61e09ca7d318.png";
     AgentPrefix = 1514355907;
     AliProfitMoney = 0;
     AliProfitRatio = "0.2";
     AndroidPath = "http://admin.xsroem.com/download/suibianda.apk";
     AndroidVersion = "3.2.1";
     AppKey = 24677383;
     AppSecret = a567a035357eca0cd7112d43469a5626;
     AppStore = 1;
     Appid = 2017120800452663;
     Blance = "100277.6";
     CallType = 1;
     CreateTime = "/Date(1506268800000)/";
     DAppKey = 5a1e644f86;
     DKBL = 1;
     DataStatus = 1;
     Email = "\U8d75\U9752\U9706";
     EndTime = "/Date(-2209017600000)/";
     FreeRoute = "\U968f\U4fbf\U6253";
     ID = "58162318-deca-4442-8e38-743b7729aa5b";
     IOSPath = "itms-services://?action=download-manifest&url=https://admin.xsroem.com/download/mainfest.plist";
     IOSVersion = "1.0.7";
     IsAliPay = 0;
     IsAliProfit = 1;
     IsAuth = 1;
     IsProfit = 1;
     IsShop = 1;
     KeyPath = "//Ali//9347122018219144212ff1a83cb-2dc9-4c4d-8f1a-57eb4b3bc27a.pem";
     LineBalance = "4889.05";
     LoginIp = "125.118.177.173";
     LoginTime = "/Date(1522288105210)/";
     Mobile = "18979153637,15757189059,17061999999,17704820771,17087044000,17760801929,18867860012,15968869334";
     NextAgent = 1;
     OneAgent = "58162318-deca-4442-8e38-743b7729aa5b";
     ParentId = "7dd9a07e-9cef-4702-a165-b7033861c1b7";
     Password = c3VpYmlhbmRhMzIxQ3VzdG9tQmFzZTY0;
     Pic = "<null>";
     Pid = "mm_101082177_39184040_145912073";
     Prefix = "\U968f\U4fbf\U6253\U6765\U7535";
     ProfitMoney = 0;
     ProfitRatio = "0.2";
     RegiMoney = 5;
     Role = 1;
     SXXH = 0;
     ShopBlance = 17414;
     ShopID = "58162318-deca-4442-8e38-743b7729aa5b";
     SqAppKey = f75po5gv;
     Theme = "skin-blur-chrome";
     Type = 7;
     UserName = "\U968f\U4fbf\U6253";
     overdraft = 0;
     };
     Mess = Success;
     */
    private int Code;
    private int  errorCode;
    private InfoBean Info;
    private JsonBean json;
    private String Mess;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }
    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        this.Code = code;
    }

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean info) {
        this.Info = info;
    }
    public JsonBean getJson() {
        return json;
    }

    public void setJson(JsonBean json) {
        this.json = json;
    }

    public void setMess(String mess) {
       this.Mess = mess;
    }

    public String getMess() {
        return Mess;
    }

    public static class JsonBean implements Serializable{
        private String androidversion;     //版本号
        private String androidpath;         //下载地址

        public String getAndroidpath() {
            return androidpath;
        }

        public void setAndroidpath(String androidpath) {
            this.androidpath = androidpath;
        }

        public String getAndroidversion() {
            return androidversion;
        }

        public void setAndroidversion(String androidversion) {
            this.androidversion = androidversion;
        }

    }

    public static class InfoBean implements Serializable{
        /**
         *  AdId = 145912073;
         AddCard = 1;
         AgentLogo = "/Images/44026420171114144115478ffdbeef-c953-44ee-9b4e-61e09ca7d318.png";
         AgentPrefix = 1514355907;
         AliProfitMoney = 0;
         AliProfitRatio = "0.2";
         AndroidPath = "http://admin.xsroem.com/download/suibianda.apk";
         AndroidVersion = "3.2.1";
         AppKey = 24677383;
         AppSecret = a567a035357eca0cd7112d43469a5626;
         AppStore = 1;
         Appid = 2017120800452663;
         Blance = "100277.6";
         CallType = 1;
         CreateTime = "/Date(1506268800000)/";
         DAppKey = 5a1e644f86;
         DKBL = 1;
         DataStatus = 1;
         Email = "\U8d75\U9752\U9706";
         EndTime = "/Date(-2209017600000)/";
         FreeRoute = "\U968f\U4fbf\U6253";
         ID = "58162318-deca-4442-8e38-743b7729aa5b";
         IOSPath = "itms-services://?action=download-manifest&url=https://admin.xsroem.com/download/mainfest.plist";
         IOSVersion = "1.0.7";
         IsAliPay = 0;
         IsAliProfit = 1;
         IsAuth = 1;
         IsProfit = 1;
         IsShop = 1;
         KeyPath = "//Ali//9347122018219144212ff1a83cb-2dc9-4c4d-8f1a-57eb4b3bc27a.pem";
         LineBalance = "4889.05";
         LoginIp = "125.118.177.173";
         LoginTime = "/Date(1522288105210)/";
         Mobile = "18979153637,15757189059,17061999999,17704820771,17087044000,17760801929,18867860012,15968869334";
         NextAgent = 1;
         OneAgent = "58162318-deca-4442-8e38-743b7729aa5b";
         ParentId = "7dd9a07e-9cef-4702-a165-b7033861c1b7";
         Password = c3VpYmlhbmRhMzIxQ3VzdG9tQmFzZTY0;
         Pic = "<null>";
         Pid = "mm_101082177_39184040_145912073";
         Prefix = "\U968f\U4fbf\U6253\U6765\U7535";
         ProfitMoney = 0;
         ProfitRatio = "0.2";
         RegiMoney = 5;
         Role = 1;
         SXXH = 0;
         ShopBlance = 17414;
         ShopID = "58162318-deca-4442-8e38-743b7729aa5b";
         SqAppKey = f75po5gv;
         Theme = "skin-blur-chrome";
         Type = 7;
         UserName = "\U968f\U4fbf\U6253";
         overdraft = 0;
         */

        private String AndroidPath;
        private String AndroidVersion;

        public void setAndroidPath(String androidPath) {
            this.AndroidPath = androidPath;
        }

        public String getAndroidPath() {
            return AndroidPath;
        }

        public void setAndroidVersion(String androidVersion) {
            this.AndroidVersion = androidVersion;
        }
        public String getAndroidVersion() {
            return AndroidVersion;
        }
    }
}
