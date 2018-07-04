package com.mhy.shopingphone.model.qiyeguanjia;

/**
 * Created by YH on 2016/10/17.
 */

public class Logdwenglu {

    /**
     * data : 查询成功
     * errorCode : 2000
     * json : {"counttotle":51,"countnew":1,"counttoday":5,"counttodayrechard":0,"countrechard":0,"countcallnum":754,"users":{"id":"95816e99-25a7-47c2-96fc-bd1f66b61554","username":"冯才展","password":"ZmZjY3p6ODYwODEzQ3VzdG9tQmFzZTY0","email":"冯才展","createtime":1526313600000,"logintime":1528296009000,"loginip":"116.29.5.6","role":2,"datastatus":true,"mobile":"","blance":0,"freeroute":"随便打","parentid":"58162318-deca-4442-8e38-743b7729aa5b","agentlogo":"http://admin.sbdznkj.com/Images/848929201851518993865e4873e2-a6aa-46b1-8588-6346098b3300.png","prefix":"1526378949","theme":"","addcard":false,"nextagent":true,"androidversion":"3.4","androidpath":"http://admin.xsroem.com/download/suibianda.apk","iosversion":"1.0.7","iospath":"itms-services://?action=download-manifest,itms-services://?action=download-manifest,itms-services://?action=download-manifest,itms-services://?action=download-manifest","regimoney":0,"endtime":1557849600000,"pic":"http://admin.sbdznkj.comundefined","dkbl":0,"isshop":true,"type":0,"shopid":"58162318-deca-4442-8e38-743b7729aa5b","adid":"418412959","profitratio":0.3,"appkey":"24677383","appsecret":"a567a035357eca0cd7112d43469a5626","linebalance":0,"pid":"mm_101082177_39184040_418412959","dappkey":"5a1e644f86","sqappkey":"f75po5gv","overdraft":0,"appstore":"1","appid":"","keypath":"","agentprefix":"1514355907","oneagent":"58162318-deca-4442-8e38-743b7729aa5b","sxxh":false,"isauth":false,"isalipay":true,"isprofit":true,"isaliprofit":true,"aliprofitratio":0.6,"aliprofitmoney":0,"profitmoney":0,"shopblance":0,"calltype":1}}
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
         * counttotle : 51
         * countnew : 1
         * counttoday : 5
         * counttodayrechard : 0
         * countrechard : 0
         * countcallnum : 754
         * users : {"id":"95816e99-25a7-47c2-96fc-bd1f66b61554","username":"冯才展","password":"ZmZjY3p6ODYwODEzQ3VzdG9tQmFzZTY0","email":"冯才展","createtime":1526313600000,"logintime":1528296009000,"loginip":"116.29.5.6","role":2,"datastatus":true,"mobile":"","blance":0,"freeroute":"随便打","parentid":"58162318-deca-4442-8e38-743b7729aa5b","agentlogo":"http://admin.sbdznkj.com/Images/848929201851518993865e4873e2-a6aa-46b1-8588-6346098b3300.png","prefix":"1526378949","theme":"","addcard":false,"nextagent":true,"androidversion":"3.4","androidpath":"http://admin.xsroem.com/download/suibianda.apk","iosversion":"1.0.7","iospath":"itms-services://?action=download-manifest,itms-services://?action=download-manifest,itms-services://?action=download-manifest,itms-services://?action=download-manifest","regimoney":0,"endtime":1557849600000,"pic":"http://admin.sbdznkj.comundefined","dkbl":0,"isshop":true,"type":0,"shopid":"58162318-deca-4442-8e38-743b7729aa5b","adid":"418412959","profitratio":0.3,"appkey":"24677383","appsecret":"a567a035357eca0cd7112d43469a5626","linebalance":0,"pid":"mm_101082177_39184040_418412959","dappkey":"5a1e644f86","sqappkey":"f75po5gv","overdraft":0,"appstore":"1","appid":"","keypath":"","agentprefix":"1514355907","oneagent":"58162318-deca-4442-8e38-743b7729aa5b","sxxh":false,"isauth":false,"isalipay":true,"isprofit":true,"isaliprofit":true,"aliprofitratio":0.6,"aliprofitmoney":0,"profitmoney":0,"shopblance":0,"calltype":1}
         */

        private int counttotle;
        private int countnew;
        private int counttoday;
        private int counttodayrechard;
        private int countrechard;
        private int countcallnum;
        private UsersBean users;

        public int getCounttotle() {
            return counttotle;
        }

        public void setCounttotle(int counttotle) {
            this.counttotle = counttotle;
        }

        public int getCountnew() {
            return countnew;
        }

        public void setCountnew(int countnew) {
            this.countnew = countnew;
        }

        public int getCounttoday() {
            return counttoday;
        }

        public void setCounttoday(int counttoday) {
            this.counttoday = counttoday;
        }

        public int getCounttodayrechard() {
            return counttodayrechard;
        }

        public void setCounttodayrechard(int counttodayrechard) {
            this.counttodayrechard = counttodayrechard;
        }

        public int getCountrechard() {
            return countrechard;
        }

        public void setCountrechard(int countrechard) {
            this.countrechard = countrechard;
        }

        public int getCountcallnum() {
            return countcallnum;
        }

        public void setCountcallnum(int countcallnum) {
            this.countcallnum = countcallnum;
        }

        public UsersBean getUsers() {
            return users;
        }

        public void setUsers(UsersBean users) {
            this.users = users;
        }

        public static class UsersBean {
            /**
             * id : 95816e99-25a7-47c2-96fc-bd1f66b61554
             * username : 冯才展
             * password : ZmZjY3p6ODYwODEzQ3VzdG9tQmFzZTY0
             * email : 冯才展
             * createtime : 1526313600000
             * logintime : 1528296009000
             * loginip : 116.29.5.6
             * role : 2
             * datastatus : true
             * mobile :
             * blance : 0.0
             * freeroute : 随便打
             * parentid : 58162318-deca-4442-8e38-743b7729aa5b
             * agentlogo : http://admin.sbdznkj.com/Images/848929201851518993865e4873e2-a6aa-46b1-8588-6346098b3300.png
             * prefix : 1526378949
             * theme :
             * addcard : false
             * nextagent : true
             * androidversion : 3.4
             * androidpath : http://admin.xsroem.com/download/suibianda.apk
             * iosversion : 1.0.7
             * iospath : itms-services://?action=download-manifest,itms-services://?action=download-manifest,itms-services://?action=download-manifest,itms-services://?action=download-manifest
             * regimoney : 0
             * endtime : 1557849600000
             * pic : http://admin.sbdznkj.comundefined
             * dkbl : 0.0
             * isshop : true
             * type : 0
             * shopid : 58162318-deca-4442-8e38-743b7729aa5b
             * adid : 418412959
             * profitratio : 0.3
             * appkey : 24677383
             * appsecret : a567a035357eca0cd7112d43469a5626
             * linebalance : 0.0
             * pid : mm_101082177_39184040_418412959
             * dappkey : 5a1e644f86
             * sqappkey : f75po5gv
             * overdraft : 0
             * appstore : 1
             * appid :
             * keypath :
             * agentprefix : 1514355907
             * oneagent : 58162318-deca-4442-8e38-743b7729aa5b
             * sxxh : false
             * isauth : false
             * isalipay : true
             * isprofit : true
             * isaliprofit : true
             * aliprofitratio : 0.6
             * aliprofitmoney : 0.0
             * profitmoney : 0.0
             * shopblance : 0
             * calltype : 1
             */

            private String id;
            private String username;
            private String password;
            private String email;
            private long createtime;
            private long logintime;
            private String loginip;
            private int role;
            private boolean datastatus;
            private String mobile;
            private double blance;
            private String freeroute;
            private String parentid;
            private String agentlogo;
            private String prefix;
            private String theme;
            private boolean addcard;
            private boolean nextagent;
            private String androidversion;
            private String androidpath;
            private String iosversion;
            private String iospath;
            private int regimoney;
            private long endtime;
            private String pic;
            private double dkbl;
            private boolean isshop;
            private int type;
            private String shopid;
            private String adid;
            private double profitratio;
            private String appkey;
            private String appsecret;
            private double linebalance;
            private String pid;
            private String dappkey;
            private String sqappkey;
            private int overdraft;
            private String appstore;
            private String appid;
            private String keypath;
            private String agentprefix;
            private String oneagent;
            private boolean sxxh;
            private boolean isauth;
            private boolean isalipay;
            private boolean isprofit;
            private boolean isaliprofit;
            private double aliprofitratio;
            private double aliprofitmoney;
            private double profitmoney;
            private int shopblance;
            private int calltype;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
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

            public double getBlance() {
                return blance;
            }

            public void setBlance(double blance) {
                this.blance = blance;
            }

            public String getFreeroute() {
                return freeroute;
            }

            public void setFreeroute(String freeroute) {
                this.freeroute = freeroute;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public String getAgentlogo() {
                return agentlogo;
            }

            public void setAgentlogo(String agentlogo) {
                this.agentlogo = agentlogo;
            }

            public String getPrefix() {
                return prefix;
            }

            public void setPrefix(String prefix) {
                this.prefix = prefix;
            }

            public String getTheme() {
                return theme;
            }

            public void setTheme(String theme) {
                this.theme = theme;
            }

            public boolean isAddcard() {
                return addcard;
            }

            public void setAddcard(boolean addcard) {
                this.addcard = addcard;
            }

            public boolean isNextagent() {
                return nextagent;
            }

            public void setNextagent(boolean nextagent) {
                this.nextagent = nextagent;
            }

            public String getAndroidversion() {
                return androidversion;
            }

            public void setAndroidversion(String androidversion) {
                this.androidversion = androidversion;
            }

            public String getAndroidpath() {
                return androidpath;
            }

            public void setAndroidpath(String androidpath) {
                this.androidpath = androidpath;
            }

            public String getIosversion() {
                return iosversion;
            }

            public void setIosversion(String iosversion) {
                this.iosversion = iosversion;
            }

            public String getIospath() {
                return iospath;
            }

            public void setIospath(String iospath) {
                this.iospath = iospath;
            }

            public int getRegimoney() {
                return regimoney;
            }

            public void setRegimoney(int regimoney) {
                this.regimoney = regimoney;
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

            public double getDkbl() {
                return dkbl;
            }

            public void setDkbl(double dkbl) {
                this.dkbl = dkbl;
            }

            public boolean isIsshop() {
                return isshop;
            }

            public void setIsshop(boolean isshop) {
                this.isshop = isshop;
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

            public String getAdid() {
                return adid;
            }

            public void setAdid(String adid) {
                this.adid = adid;
            }

            public double getProfitratio() {
                return profitratio;
            }

            public void setProfitratio(double profitratio) {
                this.profitratio = profitratio;
            }

            public String getAppkey() {
                return appkey;
            }

            public void setAppkey(String appkey) {
                this.appkey = appkey;
            }

            public String getAppsecret() {
                return appsecret;
            }

            public void setAppsecret(String appsecret) {
                this.appsecret = appsecret;
            }

            public double getLinebalance() {
                return linebalance;
            }

            public void setLinebalance(double linebalance) {
                this.linebalance = linebalance;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public String getDappkey() {
                return dappkey;
            }

            public void setDappkey(String dappkey) {
                this.dappkey = dappkey;
            }

            public String getSqappkey() {
                return sqappkey;
            }

            public void setSqappkey(String sqappkey) {
                this.sqappkey = sqappkey;
            }

            public int getOverdraft() {
                return overdraft;
            }

            public void setOverdraft(int overdraft) {
                this.overdraft = overdraft;
            }

            public String getAppstore() {
                return appstore;
            }

            public void setAppstore(String appstore) {
                this.appstore = appstore;
            }

            public String getAppid() {
                return appid;
            }

            public void setAppid(String appid) {
                this.appid = appid;
            }

            public String getKeypath() {
                return keypath;
            }

            public void setKeypath(String keypath) {
                this.keypath = keypath;
            }

            public String getAgentprefix() {
                return agentprefix;
            }

            public void setAgentprefix(String agentprefix) {
                this.agentprefix = agentprefix;
            }

            public String getOneagent() {
                return oneagent;
            }

            public void setOneagent(String oneagent) {
                this.oneagent = oneagent;
            }

            public boolean isSxxh() {
                return sxxh;
            }

            public void setSxxh(boolean sxxh) {
                this.sxxh = sxxh;
            }

            public boolean isIsauth() {
                return isauth;
            }

            public void setIsauth(boolean isauth) {
                this.isauth = isauth;
            }

            public boolean isIsalipay() {
                return isalipay;
            }

            public void setIsalipay(boolean isalipay) {
                this.isalipay = isalipay;
            }

            public boolean isIsprofit() {
                return isprofit;
            }

            public void setIsprofit(boolean isprofit) {
                this.isprofit = isprofit;
            }

            public boolean isIsaliprofit() {
                return isaliprofit;
            }

            public void setIsaliprofit(boolean isaliprofit) {
                this.isaliprofit = isaliprofit;
            }

            public double getAliprofitratio() {
                return aliprofitratio;
            }

            public void setAliprofitratio(double aliprofitratio) {
                this.aliprofitratio = aliprofitratio;
            }

            public double getAliprofitmoney() {
                return aliprofitmoney;
            }

            public void setAliprofitmoney(double aliprofitmoney) {
                this.aliprofitmoney = aliprofitmoney;
            }

            public double getProfitmoney() {
                return profitmoney;
            }

            public void setProfitmoney(double profitmoney) {
                this.profitmoney = profitmoney;
            }

            public int getShopblance() {
                return shopblance;
            }

            public void setShopblance(int shopblance) {
                this.shopblance = shopblance;
            }

            public int getCalltype() {
                return calltype;
            }

            public void setCalltype(int calltype) {
                this.calltype = calltype;
            }
        }
    }
}
