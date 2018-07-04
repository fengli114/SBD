package com.mhy.shopingphone.model.qiyeguanjia;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YH on 2016/10/17.
 */

public class UserInfoers implements Serializable {
    /**
     * data : null
     * errorCode : 2000
     * json : {"totlenum":253,"curPage":1,"users":[{"id":  tatus":true,"ratelength":60,"createtime":1521701080000},{"id":"60eb5a22-b188-47c3-9beb-31272749223d","name":"月卡费率","feelroute":0.25,"datastatus":true,"ratelength":60,"createtime":1521701087000}]}
     */

    private Object data;
    private int errorCode;
    private JsonBean json;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
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

    public static class JsonBean implements Serializable {
        /**
         * totlenum : 253
         * curPage : 1
         * users : [{"id":"5e1781d 2749223d","name":"月卡费率","feelroute":0.25,"datastatus":true,"ratelength":60,"createtime":1521701087000}]
         */

        private int totlenum;
        private int curPage;
        private List<UsersBean> users;
        private List<FreeroutesBean> freeroutes;

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

        public List<FreeroutesBean> getFreeroutes() {
            return freeroutes;
        }

        public void setFreeroutes(List<FreeroutesBean> freeroutes) {
            this.freeroutes = freeroutes;
        }

        public static class UsersBean implements Serializable {
            /**
             * id : 5e1781da518546418cce48b062ed36de
             * username : 李桃
             * password : 8888880
             * email : 李桃
             * createtime : 1530201600000
             * logintime : 1530240170000
             * loginip : null
             * role : 2
             * datastatus : true
             * mobile : null
             * blance : 0
             * freeroute : 随便打
             * parentid : 随便打
             * agentlogo : http://admin.sbdznkj.comnull
             * prefix : null专线
             * theme : null
             * addcard : false
             * nextagent : true
             * androidversion : null
             * androidpath : null
             * iosversion : null
             * iospath : null
             * regimoney : 0
             * endtime : 1561737600000
             * pic : http://admin.sbdznkj.comundefined
             * dkbl : 1
             * isshop : null
             * type : null
             * shopid : 随便打17
             * adid : 18248880524
             * profitratio : null
             * appkey : null
             * appsecret : null
             * linebalance : 0
             * pid : lt_1824888
             * dappkey : null
             * sqappkey : f75po5gv
             * overdraft : 0
             * appstore : null
             * appid : null
             * keypath : 0
             * agentprefix : 1514355907
             * oneagent : 58162318-deca-4442-8e38-743b7729aa5b
             * sxxh : false
             * isauth : true
             * isalipay : true
             * isprofit : true
             * isaliprofit : true
             * aliprofitratio : 1
             * aliprofitmoney : 0
             * profitmoney : 0
             * shopblance : 0
             * calltype : 1
             */

            private String id;
            private String username;
            private String password;
            private String email;
            private long createtime;
            private long logintime;
            private Object loginip;
            private int role;
            private boolean datastatus;
            private Object mobile;
            private int blance;
            private String freeroute;
            private String parentid;
            private String agentlogo;
            private String prefix;
            private Object theme;
            private boolean addcard;
            private boolean nextagent;
            private Object androidversion;
            private Object androidpath;
            private Object iosversion;
            private Object iospath;
            private int regimoney;
            private long endtime;
            private String pic;
            private int dkbl;
            private Object isshop;
            private Object type;
            private String shopid;
            private String adid;
            private Object profitratio;
            private Object appkey;
            private Object appsecret;
            private int linebalance;
            private String pid;
            private Object dappkey;
            private String sqappkey;
            private int overdraft;
            private Object appstore;
            private Object appid;
            private String keypath;
            private String agentprefix;
            private String oneagent;
            private boolean sxxh;
            private boolean isauth;
            private boolean isalipay;
            private boolean isprofit;
            private boolean isaliprofit;
            private int aliprofitratio;
            private int aliprofitmoney;
            private int profitmoney;
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

            public Object getLoginip() {
                return loginip;
            }

            public void setLoginip(Object loginip) {
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

            public Object getMobile() {
                return mobile;
            }

            public void setMobile(Object mobile) {
                this.mobile = mobile;
            }

            public int getBlance() {
                return blance;
            }

            public void setBlance(int blance) {
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

            public Object getTheme() {
                return theme;
            }

            public void setTheme(Object theme) {
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

            public Object getAndroidversion() {
                return androidversion;
            }

            public void setAndroidversion(Object androidversion) {
                this.androidversion = androidversion;
            }

            public Object getAndroidpath() {
                return androidpath;
            }

            public void setAndroidpath(Object androidpath) {
                this.androidpath = androidpath;
            }

            public Object getIosversion() {
                return iosversion;
            }

            public void setIosversion(Object iosversion) {
                this.iosversion = iosversion;
            }

            public Object getIospath() {
                return iospath;
            }

            public void setIospath(Object iospath) {
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

            public int getDkbl() {
                return dkbl;
            }

            public void setDkbl(int dkbl) {
                this.dkbl = dkbl;
            }

            public Object getIsshop() {
                return isshop;
            }

            public void setIsshop(Object isshop) {
                this.isshop = isshop;
            }

            public Object getType() {
                return type;
            }

            public void setType(Object type) {
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

            public Object getProfitratio() {
                return profitratio;
            }

            public void setProfitratio(Object profitratio) {
                this.profitratio = profitratio;
            }

            public Object getAppkey() {
                return appkey;
            }

            public void setAppkey(Object appkey) {
                this.appkey = appkey;
            }

            public Object getAppsecret() {
                return appsecret;
            }

            public void setAppsecret(Object appsecret) {
                this.appsecret = appsecret;
            }

            public int getLinebalance() {
                return linebalance;
            }

            public void setLinebalance(int linebalance) {
                this.linebalance = linebalance;
            }

            public String getPid() {
                return pid;
            }

            public void setPid(String pid) {
                this.pid = pid;
            }

            public Object getDappkey() {
                return dappkey;
            }

            public void setDappkey(Object dappkey) {
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

            public Object getAppstore() {
                return appstore;
            }

            public void setAppstore(Object appstore) {
                this.appstore = appstore;
            }

            public Object getAppid() {
                return appid;
            }

            public void setAppid(Object appid) {
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

            public int getAliprofitratio() {
                return aliprofitratio;
            }

            public void setAliprofitratio(int aliprofitratio) {
                this.aliprofitratio = aliprofitratio;
            }

            public int getAliprofitmoney() {
                return aliprofitmoney;
            }

            public void setAliprofitmoney(int aliprofitmoney) {
                this.aliprofitmoney = aliprofitmoney;
            }

            public int getProfitmoney() {
                return profitmoney;
            }

            public void setProfitmoney(int profitmoney) {
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

        public static class FreeroutesBean implements Serializable {
            /**
             * id : 690a9c99-aa0e-47da-b0da-a3f91a2b52fb
             * name : 随便打
             * feelroute : 0.25
             * datastatus : true
             * ratelength : 60
             * createtime : 1507433297000
             */
            private String id;
            private String name;
            private double feelroute;
            private boolean datastatus;
            private int ratelength;
            private long createtime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public double getFeelroute() {
                return feelroute;
            }

            public void setFeelroute(double feelroute) {
                this.feelroute = feelroute;
            }

            public boolean isDatastatus() {
                return datastatus;
            }

            public void setDatastatus(boolean datastatus) {
                this.datastatus = datastatus;
            }

            public int getRatelength() {
                return ratelength;
            }

            public void setRatelength(int ratelength) {
                this.ratelength = ratelength;
            }

            public long getCreatetime() {
                return createtime;
            }

            public void setCreatetime(long createtime) {
                this.createtime = createtime;
            }
        }
    }
}
