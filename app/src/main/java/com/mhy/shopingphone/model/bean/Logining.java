package com.mhy.shopingphone.model.bean;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class Logining {

    /**
     * data : 登陆成功
     * errorCode : 200
     * json : {"id":"dd48d4a3-c308-4d80-a5f4-02be7d97003a","password":"MTIzNDU2Q3VzdG9tQmFzZTY0","createtime":1508860800000,"logintime":1523030400000,"loginip":"58.101.105.208","role":4,"datastatus":true,"mobile":"18867860012","parentid":"58162318-deca-4442-8e38-743b7729aa5b","endtime":1545753600000,"pic":"/Images/5444222018224125726680daa5b085-050f-4a9a-9973-9a8dc64fd3de.jpg","type":7,"shopid":"1354f527-7b4a-4799-811f-b49d913edf76","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":"","gender":"0","email":"1127362735@qq.com","address":"北京市-北京市-东城区","username":"安妮","isyear":false,"ismonth":false,"isquarter":false}
     */

    private String data;
    private int errorCode;
    private JsonBean json;

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

    public JsonBean getJson() {
        return json;
    }

    public void setJson(JsonBean json) {
        this.json = json;
    }

    public static class JsonBean {
        /**
         * id : dd48d4a3-c308-4d80-a5f4-02be7d97003a
         * password : MTIzNDU2Q3VzdG9tQmFzZTY0
         * createtime : 1508860800000
         * logintime : 1523030400000
         * loginip : 58.101.105.208
         * role : 4
         * datastatus : true
         * mobile : 18867860012
         * parentid : 58162318-deca-4442-8e38-743b7729aa5b
         * endtime : 1545753600000
         * pic : /Images/5444222018224125726680daa5b085-050f-4a9a-9973-9a8dc64fd3de.jpg
         * type : 7
         * shopid : 1354f527-7b4a-4799-811f-b49d913edf76
         * appstore : 1
         * integral : 0.0
         * aliaccount : null
         * alipay : false
         * shopBalance : 0
         * shopEnddate : 2019-03-09
         * birthday :
         * gender : 0
         * email : 1127362735@qq.com
         * address : 北京市-北京市-东城区
         * username : 安妮
         * isyear : false
         * ismonth : false
         * isquarter : false
         */

        private String id;
        private String password;
        private long createtime;
        private long logintime;
        private String loginip;
        private int role;
        private boolean datastatus;
        private String mobile;
        private String parentid;
        private long endtime;
        private String pic;
        private int type;
        private String shopid;
        private String appstore;
        private double integral;
        private Object aliaccount;
        private boolean alipay;
        private int shopBalance;
        private String shopEnddate;
        private String birthday;
        private String gender;
        private String email;
        private String address;
        private String username;
        private boolean isyear;
        private boolean ismonth;
        private boolean isquarter;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public long getLogintime() {
            return logintime;
        }

        public void setLogintime(long logintime) {
            this.logintime = logintime;
        }

        public String getLoginip() {
            return loginip;
        }

        public void setLoginip(String loginip) {
            this.loginip = loginip;
        }

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
        }

        public boolean isDatastatus() {
            return datastatus;
        }

        public void setDatastatus(boolean datastatus) {
            this.datastatus = datastatus;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public long getEndtime() {
            return endtime;
        }

        public void setEndtime(long endtime) {
            this.endtime = endtime;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }

        public String getAppstore() {
            return appstore;
        }

        public void setAppstore(String appstore) {
            this.appstore = appstore;
        }

        public double getIntegral() {
            return integral;
        }

        public void setIntegral(double integral) {
            this.integral = integral;
        }

        public Object getAliaccount() {
            return aliaccount;
        }

        public void setAliaccount(Object aliaccount) {
            this.aliaccount = aliaccount;
        }

        public boolean isAlipay() {
            return alipay;
        }

        public void setAlipay(boolean alipay) {
            this.alipay = alipay;
        }

        public int getShopBalance() {
            return shopBalance;
        }

        public void setShopBalance(int shopBalance) {
            this.shopBalance = shopBalance;
        }

        public String getShopEnddate() {
            return shopEnddate;
        }

        public void setShopEnddate(String shopEnddate) {
            this.shopEnddate = shopEnddate;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public boolean isIsyear() {
            return isyear;
        }

        public void setIsyear(boolean isyear) {
            this.isyear = isyear;
        }

        public boolean isIsmonth() {
            return ismonth;
        }

        public void setIsmonth(boolean ismonth) {
            this.ismonth = ismonth;
        }

        public boolean isIsquarter() {
            return isquarter;
        }

        public void setIsquarter(boolean isquarter) {
            this.isquarter = isquarter;
        }
    }
}
