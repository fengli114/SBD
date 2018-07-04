package com.mhy.shopingphone.model.bean;

import java.util.List;

/**
 * 作者： "RedRainM" on 2018/1/19 0019.
 * 描述：
 */

public class PayTypeBean {
    /**
     * data : 获取到在线充值数据
     * errorCode : 200
     * json : [{"id":"1b7e0b6b-6437-404f-85ff-a82492d638b8","agentid":"58162318-deca-4442-8e38-743b7729aa5b","money":298,"alimoney":98,"grade":3,"pic":"/Images/4974862018131171183386dbb38cb-0bc9-4325-9987-977e2ab39f78.png","datastatus":true,"shopmoney":98,"memo":"送200元+98购物金","probationperiod":"180"},{"id":"9e3542ec-5bb4-451d-9056-3487995d30d2","agentid":"58162318-deca-4442-8e38-743b7729aa5b","money":658,"alimoney":198,"grade":2,"pic":"/Images/6342122018131171249378b1fc7d32-4729-4f3c-b286-e3b64b926d57.png","datastatus":true,"shopmoney":198,"memo":"送460元+198购物金","probationperiod":"180"},{"id":"b94e51c5-0f0c-4b75-98c5-3f459fe34457","agentid":"58162318-deca-4442-8e38-743b7729aa5b","money":70,"alimoney":30,"grade":4,"pic":"/Images/531432201826145325221b71087ae-54da-4f4b-8b54-7f83bede1191.png","datastatus":true,"shopmoney":0,"memo":"送70元","probationperiod":"180"},{"id":"cd25b543-ebd9-423f-8371-aa935f319465","agentid":"58162318-deca-4442-8e38-743b7729aa5b","money":998,"alimoney":298,"grade":1,"pic":"/Images/3353522018131171330145fcf12175-5794-4fac-aab4-a85126f575fa.png","datastatus":true,"shopmoney":298,"memo":"送700元+298购物金","probationperiod":"180"}]
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
         * id : 1b7e0b6b-6437-404f-85ff-a82492d638b8
         * agentid : 58162318-deca-4442-8e38-743b7729aa5b
         * money : 298
         * alimoney : 98
         * grade : 3
         * pic : /Images/4974862018131171183386dbb38cb-0bc9-4325-9987-977e2ab39f78.png
         * datastatus : true
         * shopmoney : 98
         * memo : 送200元+98购物金
         * probationperiod : 180
         */

        private String id;
        private String agentid;
        private int money;
        private int alimoney;
        private int grade;
        private String pic;
        private boolean datastatus;
        private int shopmoney;
        private String memo;
        private String probationperiod;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAgentid() {
            return agentid;
        }

        public void setAgentid(String agentid) {
            this.agentid = agentid;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public int getAlimoney() {
            return alimoney;
        }

        public void setAlimoney(int alimoney) {
            this.alimoney = alimoney;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public boolean isDatastatus() {
            return datastatus;
        }

        public void setDatastatus(boolean datastatus) {
            this.datastatus = datastatus;
        }

        public int getShopmoney() {
            return shopmoney;
        }

        public void setShopmoney(int shopmoney) {
            this.shopmoney = shopmoney;
        }

        public String getMemo() {
            return memo;
        }

        public void setMemo(String memo) {
            this.memo = memo;
        }

        public String getProbationperiod() {
            return probationperiod;
        }

        public void setProbationperiod(String probationperiod) {
            this.probationperiod = probationperiod;
        }
    }
}
