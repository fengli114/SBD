package com.mhy.shopingphone.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class UserGuanliBean implements Serializable{

    /**
     * data : 查询成功
     * errorCode : 2000
     * json : {"totlenum":35,"curPage":1,"users":[{"id":"2ba86ff5-ea54-4d3e-b4c2-5cccf68b4bd7","password":"198882","createtime":1519833600000,"logintime":1519833600000,"loginip":"183.67.55.217","role":4,"datastatus":true,"mobile":"13883160362","parentid":"随便打","endtime":1551369600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"5f46c94a-a95f-42ad-8c15-c7e8d7a1c6a8","password":"xu13521","createtime":1519833600000,"logintime":1519833600000,"loginip":"180.104.25.180","role":4,"datastatus":true,"mobile":"18762282977","parentid":"随便打","endtime":1551369600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"b840107f-385e-45fa-92ad-d097dc4854a0","password":"tt928106","createtime":1519833600000,"logintime":1519833600000,"loginip":"14.106.163.87","role":4,"datastatus":true,"mobile":"15736089692","parentid":"随便打","endtime":1551369600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"d1c290f1-5b68-4c24-9ce4-7e23a60ffb43","password":"13585377961","createtime":1519833600000,"logintime":1519833600000,"loginip":"180.104.25.180","role":4,"datastatus":true,"mobile":"13585377961","parentid":"随便打","endtime":1551369600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"8163648a-2582-462d-a062-1701897626ea","password":"081021","createtime":1519660800000,"logintime":1519660800000,"loginip":"112.96.176.90","role":4,"datastatus":true,"mobile":"13160960601","parentid":"随便打","endtime":1551196800000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"2f6f9d2b-b2aa-4026-9605-d7031d328f4a","password":"660309","createtime":1519574400000,"logintime":1519574400000,"loginip":"171.106.34.38","role":4,"datastatus":true,"mobile":"15347854659","parentid":"随便打","endtime":1551110400000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"3e48235e-dca8-4109-b4e0-0b81af236bf2","password":"123457","createtime":1519574400000,"logintime":1519574400000,"loginip":"223.91.14.125","role":4,"datastatus":true,"mobile":"15856702520","parentid":"随便打","endtime":1551110400000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"27bf74d7-5348-423e-8f8a-24108743a7b9","password":"267199","createtime":1519401600000,"logintime":1519401600000,"loginip":"116.114.223.128","role":4,"datastatus":true,"mobile":"15598885000","parentid":"随便打","endtime":1550937600000,"pic":"http://admin.sbdznkj.com/Images/2195392018224102338983d918eca4-adf7-458d-8bfd-edcfaba0d7ca.jpg","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":"","gender":"1","email":"45101386@qq.com","address":"内蒙古自治区-巴彦淖尔市-临河区","username":"李亭","isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"b122aac6-9aea-450b-ba26-8374cd57f953","password":"a123456","createtime":1519401600000,"logintime":1519401600000,"loginip":"117.164.170.30","role":4,"datastatus":true,"mobile":"15579133438","parentid":"随便打","endtime":1550937600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"c20c58aa-3ed1-4833-b56a-131ad35e3202","password":"qwe225533","createtime":1519401600000,"logintime":1519401600000,"loginip":"223.104.101.82","role":4,"datastatus":true,"mobile":"15027639884","parentid":"随便打","endtime":1550937600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"}]}
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
         * totlenum : 35
         * curPage : 1
         * users : [{"id":"2ba86ff5-ea54-4d3e-b4c2-5cccf68b4bd7","password":"198882","createtime":1519833600000,"logintime":1519833600000,"loginip":"183.67.55.217","role":4,"datastatus":true,"mobile":"13883160362","parentid":"随便打","endtime":1551369600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"5f46c94a-a95f-42ad-8c15-c7e8d7a1c6a8","password":"xu13521","createtime":1519833600000,"logintime":1519833600000,"loginip":"180.104.25.180","role":4,"datastatus":true,"mobile":"18762282977","parentid":"随便打","endtime":1551369600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"b840107f-385e-45fa-92ad-d097dc4854a0","password":"tt928106","createtime":1519833600000,"logintime":1519833600000,"loginip":"14.106.163.87","role":4,"datastatus":true,"mobile":"15736089692","parentid":"随便打","endtime":1551369600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"d1c290f1-5b68-4c24-9ce4-7e23a60ffb43","password":"13585377961","createtime":1519833600000,"logintime":1519833600000,"loginip":"180.104.25.180","role":4,"datastatus":true,"mobile":"13585377961","parentid":"随便打","endtime":1551369600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"8163648a-2582-462d-a062-1701897626ea","password":"081021","createtime":1519660800000,"logintime":1519660800000,"loginip":"112.96.176.90","role":4,"datastatus":true,"mobile":"13160960601","parentid":"随便打","endtime":1551196800000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"2f6f9d2b-b2aa-4026-9605-d7031d328f4a","password":"660309","createtime":1519574400000,"logintime":1519574400000,"loginip":"171.106.34.38","role":4,"datastatus":true,"mobile":"15347854659","parentid":"随便打","endtime":1551110400000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"3e48235e-dca8-4109-b4e0-0b81af236bf2","password":"123457","createtime":1519574400000,"logintime":1519574400000,"loginip":"223.91.14.125","role":4,"datastatus":true,"mobile":"15856702520","parentid":"随便打","endtime":1551110400000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"27bf74d7-5348-423e-8f8a-24108743a7b9","password":"267199","createtime":1519401600000,"logintime":1519401600000,"loginip":"116.114.223.128","role":4,"datastatus":true,"mobile":"15598885000","parentid":"随便打","endtime":1550937600000,"pic":"http://admin.sbdznkj.com/Images/2195392018224102338983d918eca4-adf7-458d-8bfd-edcfaba0d7ca.jpg","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":"","gender":"1","email":"45101386@qq.com","address":"内蒙古自治区-巴彦淖尔市-临河区","username":"李亭","isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"b122aac6-9aea-450b-ba26-8374cd57f953","password":"a123456","createtime":1519401600000,"logintime":1519401600000,"loginip":"117.164.170.30","role":4,"datastatus":true,"mobile":"15579133438","parentid":"随便打","endtime":1550937600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"},{"id":"c20c58aa-3ed1-4833-b56a-131ad35e3202","password":"qwe225533","createtime":1519401600000,"logintime":1519401600000,"loginip":"223.104.101.82","role":4,"datastatus":true,"mobile":"15027639884","parentid":"随便打","endtime":1550937600000,"pic":"http://admin.sbdznkj.com/Images/logo.png","type":7,"shopid":"随便打销售部","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":null,"gender":"0","email":null,"address":null,"username":null,"isyear":false,"ismonth":false,"isquarter":false,"freeroute":"随便打"}]
         */

        private int totlenum;
        private int curPage;
        private List<UsersBean> users;

        public int getTotlenum() {
            return totlenum;
        }

        public void setTotlenum(int totlenum) {
            this.totlenum = totlenum;
        }

        public int getCurPage() {
            return curPage;
        }

        public void setCurPage(int curPage) {
            this.curPage = curPage;
        }

        public List<UsersBean> getUsers() {
            return users;
        }

        public void setUsers(List<UsersBean> users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * id : 2ba86ff5-ea54-4d3e-b4c2-5cccf68b4bd7
             * password : 198882
             * createtime : 1519833600000
             * logintime : 1519833600000
             * loginip : 183.67.55.217
             * role : 4
             * datastatus : true
             * mobile : 13883160362
             * parentid : 随便打
             * endtime : 1551369600000
             * pic : http://admin.sbdznkj.com/Images/logo.png
             * type : 7
             * shopid : 随便打销售部
             * appstore : 1
             * integral : 0
             * aliaccount : null
             * alipay : false
             * shopBalance : 0
             * shopEnddate : 2019-03-09
             * birthday : null
             * gender : 0
             * email : null
             * address : null
             * username : null
             * isyear : false
             * ismonth : false
             * isquarter : false
             * freeroute : 随便打
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
            private int integral;
            private Object aliaccount;
            private boolean alipay;
            private int shopBalance;
            private String shopEnddate;
            private Object birthday;
            private String gender;
            private Object email;
            private Object address;
            private Object username;
            private boolean isyear;
            private boolean ismonth;
            private boolean isquarter;
            private String freeroute;

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

            public int getIntegral() {
                return integral;
            }

            public void setIntegral(int integral) {
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

            public Object getBirthday() {
                return birthday;
            }

            public void setBirthday(Object birthday) {
                this.birthday = birthday;
            }

            public String getGender() {
                return gender;
            }

            public void setGender(String gender) {
                this.gender = gender;
            }

            public Object getEmail() {
                return email;
            }

            public void setEmail(Object email) {
                this.email = email;
            }

            public Object getAddress() {
                return address;
            }

            public void setAddress(Object address) {
                this.address = address;
            }

            public Object getUsername() {
                return username;
            }

            public void setUsername(Object username) {
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

            public String getFreeroute() {
                return freeroute;
            }

            public void setFreeroute(String freeroute) {
                this.freeroute = freeroute;
            }
        }
    }
}
