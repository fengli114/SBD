package com.mhy.shopingphone.model.bean;

/**
 * 作者： "RedRainM" on 2018/1/15 0015.
 * 描述：
 */

public class RechargeBean {

    /**
     * Code : 0
     * Mess : 充值成功
     * AgentId : 58162318-deca-4442-8e38-743b7729aa5b
     * ID : 34eb0b8a-5261-4c3f-b30c-8c911d350406
     * ShopID : 58162318-deca-4442-8e38-743b7729aa5b
     */

    private String Code;
    private String Mess;
    private String AgentId;
    private String ID;
    private String ShopID;
    private String Money;
    /**
     * data : 充值成功
     * errorCode : 2000
     * json : null
     */

    private String data;
    private int errorCode;
    private Object json;

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

    public String getAgentId() {
        return AgentId;
    }

    public void setAgentId(String AgentId) {
        this.AgentId = AgentId;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getShopID() {
        return ShopID;
    }

    public void setShopID(String ShopID) {
        this.ShopID = ShopID;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String money) {
        Money = money;
    }

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

    public Object getJson() {
        return json;
    }

    public void setJson(Object json) {
        this.json = json;
    }
}
