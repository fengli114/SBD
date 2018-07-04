package com.mhy.shopingphone.adapter;

import java.util.List;

/**
 * 作者： "RedRainM" on 2017/12/26 0026.
 * 描述：
 */

public class RecordEntity {


    /**
     * data : 充值数据
     * errorCode : 200
     * json : [{"id":"40c9e883-f65b-4268-815d-38f385e6dec4","mobile":"18557542013","cardno":"-","pwd":"-","parentid":"58162318-deca-4442-8e38-743b7729aa5b","status":true,"money":100,"datastatus":true,"paytime":1522650982000,"type":2,"alistatus":null,"ordernum":null,"alimoney":null,"cardtype":1},{"id":"190cce03-339f-4316-8b9d-d85cac8a799e","mobile":"18557542013","cardno":"-","pwd":"-","parentid":"58162318-deca-4442-8e38-743b7729aa5b","status":true,"money":70,"datastatus":true,"paytime":1522639349000,"type":3,"alistatus":"2","ordernum":"1522639349b22964e1-5055-43a9-9444-6e1919438cb2","alimoney":"30","cardtype":0},{"id":"7425ab7b-fda7-4d58-97f0-6f55ecedff67","mobile":"18557542013","cardno":"-","pwd":"-","parentid":"58162318-deca-4442-8e38-743b7729aa5b","status":true,"money":70,"datastatus":true,"paytime":1522389657000,"type":3,"alistatus":"2","ordernum":"152238965712faff41-be1b-44d9-8898-40dcc5330b37","alimoney":"30","cardtype":0}]
     */

    private String data;
    private int errorCode;
    private List<JsonBean> json;

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

    public List<JsonBean> getJson() {
        return json;
    }

    public void setJson(List<JsonBean> json) {
        this.json = json;
    }

    public static class JsonBean {
        /**
         * id : 40c9e883-f65b-4268-815d-38f385e6dec4
         * mobile : 18557542013
         * cardno : -
         * pwd : -
         * parentid : 58162318-deca-4442-8e38-743b7729aa5b
         * status : true
         * money : 100
         * datastatus : true
         * paytime : 1522650982000
         * type : 2
         * alistatus : null
         * ordernum : null
         * alimoney : null
         * cardtype : 1
         */

        private String id;
        private String mobile;
        private String cardno;
        private String pwd;
        private String parentid;
        private boolean status;
        private int money;
        private boolean datastatus;
        private long paytime;
        private int type;
        private Object alistatus;
        private Object ordernum;
        private Object alimoney;
        private int cardtype;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getCardno() {
            return cardno;
        }

        public void setCardno(String cardno) {
            this.cardno = cardno;
        }

        public String getPwd() {
            return pwd;
        }

        public void setPwd(String pwd) {
            this.pwd = pwd;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public boolean isStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public boolean isDatastatus() {
            return datastatus;
        }

        public void setDatastatus(boolean datastatus) {
            this.datastatus = datastatus;
        }

        public long getPaytime() {
            return paytime;
        }

        public void setPaytime(long paytime) {
            this.paytime = paytime;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public Object getAlistatus() {
            return alistatus;
        }

        public void setAlistatus(Object alistatus) {
            this.alistatus = alistatus;
        }

        public Object getOrdernum() {
            return ordernum;
        }

        public void setOrdernum(Object ordernum) {
            this.ordernum = ordernum;
        }

        public Object getAlimoney() {
            return alimoney;
        }

        public void setAlimoney(Object alimoney) {
            this.alimoney = alimoney;
        }

        public int getCardtype() {
            return cardtype;
        }

        public void setCardtype(int cardtype) {
            this.cardtype = cardtype;
        }
    }
}
