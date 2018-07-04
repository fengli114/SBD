package com.mhy.shopingphone.model.bean;

import java.io.Serializable;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class Ceshi {
    /**
     * Code : 0
     * Mess : Suucess
     */ /*{
        "data":"系统默认启动页", "errorCode":200, "json":{
            "id":"d794bee8-c499-4950-8771-9f0e210928fd", "parentid":
            "7dd9a07e-9cef-4702-a165-b7033861c1b7", "adtype":1, "url":"", "showcount":0, "hitscount":
            0, "datastatus":true, "path":
            "/Images/620821201832114295970ebb888ee-37d9-452c-8235-16f99e2c93b4.jpg", "createtime":
            1521613799000
        }
    }*/

    private String Code;
    private String Mess;
    private String Num;
    private String pic;
    private String Upic;
    private String ShopMoney;
    private String Money;
    private JsonBeans json;
    private int errorCode;
    private String data;

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


    public JsonBeans getJson() {
        return json;
    }

    public void setJson(JsonBeans json) {
        this.json = json;
    }

    public String getUpic() {
        return Upic;
    }

    public void setUpic(String upic) {
        Upic = upic;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

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

    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getShopMoney() {
        return ShopMoney;
    }

    public void setShopMoney(String shopMoney) {
        ShopMoney = shopMoney;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

    public static class JsonBeans implements Serializable {
        private String path;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }
    }
}
