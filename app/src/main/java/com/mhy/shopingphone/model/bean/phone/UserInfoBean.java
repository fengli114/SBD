package com.mhy.shopingphone.model.bean.phone;

import java.io.Serializable;

/**
 * 作者： "RedRainM" on 2018/1/9 0009.
 * 描述：
 */

public class UserInfoBean {


    /**
     * Code : 0
     * Mess : Success
     * Money : 2197
     * Mobile : 17760801929
     * Time : 2018-03-10
     * Pic : /Images/2396522017102615384118201f000ff-a9b7-43bd-a605-cc7459172d5d.jpg
     * Appstore : 1
     * AliAccount : 15757189059
     * AliPay : true
     * Integral : 1155.568
     * AndroidVer : 1.6
     * AndroidPa : http://admin.xsroem.com/download/lanshugu/lanshugu.apk
     * IOSVer : 1.0.4
     * IOSPa : itms-services://?action=download-manifest&url=https://admin.xsroem.com/download/lanshugu/mainfest.plist
     */

    private String Code;
    private String Mess;
    private String Money;
    private String Mobile;
    private String Time;
    private String Pic;
    private String Appstore;
    private String AliAccount;
    private boolean AliPay;
    private double Integral;
    private String AndroidVer;
    private String AndroidPa;
    private String IOSVer;
    private String IOSPa;

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

    public double getIntegral() {
        return Integral;
    }

    public void setIntegral(double Integral) {
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
}
