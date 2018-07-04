package com.mhy.shopingphone.model.bean.login;

/**
 * 作者： "RedRainM" on 2018/1/8 0008.
 * 描述：
 */

public class LoginBean {
    /**
     * data : 登陆成功
     * errorCode : 2000
     * json : {"id":"47c95ec4-b7b3-412a-a133-1054e427826a","password":null,"createtime":1517932800000,"logintime":1522684800000,"loginip":"115.198.173.102","role":4,"datastatus":true,"mobile":"18557542013","parentid":"58162318-deca-4442-8e38-743b7729aa5b","endtime":1549468800000,"pic":"/Images/6386522018326113943529aa334088-c94a-48e7-8794-e9a5e89633a3.jpg","type":7,"shopid":"1354f527-7b4a-4799-811f-b49d913edf76","appstore":"1","integral":0,"aliaccount":null,"alipay":false,"shopBalance":0,"shopEnddate":"2019-03-09","birthday":"","gender":"1","email":"","address":"浙江省-杭州市-西湖区","username":"冯立","isyear":false,"ismonth":false,"isquarter":false}
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
         * id : 47c95ec4-b7b3-412a-a133-1054e427826a
         * password : null
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
         * shopBalance : 0
         * shopEnddate : 2019-03-09
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
        private Object password;
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

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
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

  /*  *//**
     * Code : 0
     * Mess : 登陆成功
     * AgentId : 2f4ef68e-3732-4e0a-902a-00cab9da0576
     * Mobile : 17760801929
     * Info : {"ID":"9bb7ea0f-b9f0-45f7-8cd7-d6c12526f1d8","Password":"MTIzNDU2Q3VzdG9tQmFzZTY0","CreateTime":"/Date(1508947200000)/","LoginTime":"/Date(1515390233305)/","LoginIp":"183.128.244.17","Role":4,"DataStatus":true,"Mobile":"17760801929","ParentId":"2f4ef68e-3732-4e0a-902a-00cab9da0576","EndTime":"/Date(1549123200000)/","Pic":"/Images/2396522017102615384118201f000ff-a9b7-43bd-a605-cc7459172d5d.jpg","Type":7,"ShopID":"2f4ef68e-3732-4e0a-902a-00cab9da0576","AppStore":"1","Integral":1155.568,"AliAccount":"15757189059","AliPay":true}
     * Shop : true
     * ShopID : 2f4ef68e-3732-4e0a-902a-00cab9da0576  商户ID
     * Appstore : 1
     *//*

    private String Code;
    private String Mess;
    private String AgentId;
    private String Mobile;
    private InfoBean Info;
    private boolean Shop;
    private String ShopID;
    private String Appstore;

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

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean Info) {
        this.Info = Info;
    }

    public boolean isShop() {
        return Shop;
    }

    public void setShop(boolean Shop) {
        this.Shop = Shop;
    }

    public String getShopID() {
        return ShopID;
    }

    public void setShopID(String ShopID) {
        this.ShopID = ShopID;
    }

    public String getAppstore() {
        return Appstore;
    }

    public void setAppstore(String Appstore) {
        this.Appstore = Appstore;
    }

    public static class InfoBean {
        *//**
         * ID : 9bb7ea0f-b9f0-45f7-8cd7-d6c12526f1d8
         * Password : MTIzNDU2Q3VzdG9tQmFzZTY0
         * CreateTime : /Date(1508947200000)/
         * LoginTime : /Date(1515390233305)/
         * LoginIp : 183.128.244.17
         * Role : 4
         * DataStatus : true
         * Mobile : 17760801929
         * ParentId : 2f4ef68e-3732-4e0a-902a-00cab9da0576
         * EndTime : /Date(1549123200000)/
         * Pic : /Images/2396522017102615384118201f000ff-a9b7-43bd-a605-cc7459172d5d.jpg
         * Type : 7
         * ShopID : 2f4ef68e-3732-4e0a-902a-00cab9da0576
         * AppStore : 1
         * Integral : 1155.568
         * AliAccount : 15757189059
         * AliPay : true
         *//*

        private String ID;
        private String Password;
        private String CreateTime;
        private String LoginTime;
        private String LoginIp;
        private int Role;
        private boolean DataStatus;
        private String Mobile;
        private String ParentId;
        private String EndTime;
        private String Pic;
        private int Type;
        private String ShopID;
        private String AppStore;
        private double Integral;
        private String AliAccount;
        private boolean AliPay;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPassword() {
            return Password;
        }

        public void setPassword(String Password) {
            this.Password = Password;
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

        public String getParentId() {
            return ParentId;
        }

        public void setParentId(String ParentId) {
            this.ParentId = ParentId;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
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

        public String getAppStore() {
            return AppStore;
        }

        public void setAppStore(String AppStore) {
            this.AppStore = AppStore;
        }

        public double getIntegral() {
            return Integral;
        }

        public void setIntegral(double Integral) {
            this.Integral = Integral;
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
    }*/
}
