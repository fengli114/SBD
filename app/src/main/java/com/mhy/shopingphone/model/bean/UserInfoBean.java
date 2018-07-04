package com.mhy.shopingphone.model.bean;

import java.io.Serializable;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class UserInfoBean implements Serializable {


    /**
     * data : 获取用户信息成功
     * errorCode : 200
     * json : {"Money":"88.75","Info":{"id":"47c95ec4-b7b3-412a-a133-1054e427826a","password":"OTIwMzE5bGw=Q3VzdG9tQmFzZTY0","createtime":1517932800000,"logintime":1522684800000,"loginip":"115.198.173.102","role":4,"datastatus":true,"mobile":"18557542013","parentid":"58162318-deca-4442-8e38-743b7729aa5b","endtime":1549468800000,"pic":"/Images/6386522018326113943529aa334088-c94a-48e7-8794-e9a5e89633a3.jpg","type":7,"shopid":"1354f527-7b4a-4799-811f-b49d913edf76","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":"","gender":"1","email":"","address":"浙江省-杭州市-西湖区","username":"冯立","isyear":false,"ismonth":false,"isquarter":false},"Time":"2019-05-03","AndroidVer":"3.3","AndroidPa":"http://admin.xsroem.com/download/suibianda.apk","IOSVer":"1.0.7","IOSPa":"itms-services://?action=download-manifest&url=https://admin.xsroem.com/download/mainfest.plist","IsAuth":true}
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

    public static class JsonBean  implements Serializable{
        /**
         * Money : 88.75         花费余额
         * Info : {"id":"47c95ec4-b7b3-412a-a133-1054e427826a","password":"OTIwMzE5bGw=Q3VzdG9tQmFzZTY0","createtime":1517932800000,"logintime":1522684800000,"loginip":"115.198.173.102","role":4,"datastatus":true,"mobile":"18557542013","parentid":"58162318-deca-4442-8e38-743b7729aa5b","endtime":1549468800000,"pic":"/Images/6386522018326113943529aa334088-c94a-48e7-8794-e9a5e89633a3.jpg","type":7,"shopid":"1354f527-7b4a-4799-811f-b49d913edf76","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":"","gender":"1","email":"","address":"浙江省-杭州市-西湖区","username":"冯立","isyear":false,"ismonth":false,"isquarter":false}
         * Time : 2019-05-03         有效期
         * AndroidVer : 3.3
         * AndroidPa : http://admin.xsroem.com/download/suibianda.apk
         * IOSVer : 1.0.7
         * IOSPa : itms-services://?action=download-manifest&url=https://admin.xsroem.com/download/mainfest.plist
         * IsAuth : true
         */

        private String Money;
        private InfoBean Info;
        private String Time;
        private String AndroidVer;
        private String AndroidPa;
        private String IOSVer;
        private String IOSPa;
        private boolean IsAuth;

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

        public String getTime() {
            return Time;
        }

        public void setTime(String Time) {
            this.Time = Time;
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

        public boolean isIsAuth() {
            return IsAuth;
        }

        public void setIsAuth(boolean IsAuth) {
            this.IsAuth = IsAuth;
        }

        public static class InfoBean implements Serializable{
            /**
             * id : 47c95ec4-b7b3-412a-a133-1054e427826a
             * password : OTIwMzE5bGw=Q3VzdG9tQmFzZTY0
             * createtime : 1517932800000
             * logintime : 1522684800000
             * loginip : 115.198.173.102
             * role : 4
             * datastatus : true
             * mobile : 18557542013
             * parentid : 58162318-deca-4442-8e38-743b7729aa5b
             * endtime : 1549468800000
             * pic : /Images/6386522018326113943529aa334088-c94a-48e7-8794-e9a5e89633a3.jpg
             * type : 7
             * shopid : 1354f527-7b4a-4799-811f-b49d913edf76
             * appstore : 1
             * integral : 0.0
             * aliaccount : null
             * alipay : false
             * shopBalance : 0           购物卡余额
             * shopEnddate : 2019-03-09   购物卡
             * birthday :
             * gender : 1
             * email :
             * address : 浙江省-杭州市-西湖区
             * username : 冯立
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
}
