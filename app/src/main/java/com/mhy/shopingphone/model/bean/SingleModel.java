package com.mhy.shopingphone.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/27话单
 */

public class SingleModel implements Serializable {
    /**
     * data : 话单获取成功
     * errorCode : 2000
     * json : [{"id":"f43dbbad06a94a70bad2d36014e6b30f","caller":"17704820771","calle164":"17052369882","calltime":1526987583000,"status":"呼叫成功","parentid":"58162318-deca-4442-8e38-743b7729aa5b","sysnum":"提交记录","fwdtime":1526987583000,"endtime":1526987583000,"money":0,"omoney":0,"timecount":0,"shopid":"1354f527-7b4a-4799-811f-b49d913edf76"},{"id":"2ec99e1551dd4be4ab09f2d36f282523","caller":"17704820771","calle164":"17052369882","calltime":1526977150000,"status":"呼叫成功","parentid":"58162318-deca-4442-8e38-743b7729aa5b","sysnum":"提交记录","fwdtime":1526977150000,"endtime":1526977150000,"money":0,"omoney":0,"timecount":0,"shopid":"1354f527-7b4a-4799-811f-b49d913edf76"}]
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
         * id : f43dbbad06a94a70bad2d36014e6b30f
         * caller : 17704820771
         * calle164 : 17052369882
         * calltime : 1526987583000
         * status : 呼叫成功
         * parentid : 58162318-deca-4442-8e38-743b7729aa5b
         * sysnum : 提交记录
         * fwdtime : 1526987583000
         * endtime : 1526987583000
         * money : 0.0
         * omoney : 0.0
         * timecount : 0
         * shopid : 1354f527-7b4a-4799-811f-b49d913edf76
         */

        private String id;
        private String caller;
        private String calle164;
        private long calltime;
        private String status;
        private String parentid;
        private String sysnum;
        private long fwdtime;
        private long endtime;
        private double money;
        private double omoney;
        private int timecount;
        private String shopid;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCaller() {
            return caller;
        }

        public void setCaller(String caller) {
            this.caller = caller;
        }

        public String getCalle164() {
            return calle164;
        }

        public void setCalle164(String calle164) {
            this.calle164 = calle164;
        }

        public long getCalltime() {
            return calltime;
        }

        public void setCalltime(long calltime) {
            this.calltime = calltime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public String getSysnum() {
            return sysnum;
        }

        public void setSysnum(String sysnum) {
            this.sysnum = sysnum;
        }

        public long getFwdtime() {
            return fwdtime;
        }

        public void setFwdtime(long fwdtime) {
            this.fwdtime = fwdtime;
        }

        public long getEndtime() {
            return endtime;
        }

        public void setEndtime(long endtime) {
            this.endtime = endtime;
        }

        public double getMoney() {
            return money;
        }

        public void setMoney(double money) {
            this.money = money;
        }

        public double getOmoney() {
            return omoney;
        }

        public void setOmoney(double omoney) {
            this.omoney = omoney;
        }

        public int getTimecount() {
            return timecount;
        }

        public void setTimecount(int timecount) {
            this.timecount = timecount;
        }

        public String getShopid() {
            return shopid;
        }

        public void setShopid(String shopid) {
            this.shopid = shopid;
        }
    }

  /*  *//**
     * Code : 0
     * Mess : Success
     * Info : [{"ID":"f3a3df42-cf1f-4e5f-9f3d-0b57df103fa5","SysNum":"推送话单","Caller":"+8617192230001","Calle164":"+8617704820771","CallTime":"/Date(1506522894000)/","FwdTime":"/Date(1506494114000)/","EndTime":"/Date(1506494126000)/","Money":0.3,"OMoney":0.06,"TimeCount":23,"ParentID":"7dd9a07e-9cef-4702-a165-b7033861c1b7","Status":"接通"},{"ID":"d9f4f179-a2d0-4b7e-9cb1-500a071e081d","SysNum":"推送话单","Caller":"+8617192230001","Calle164":"+8617704820771","CallTime":"/Date(1506522324000)/","FwdTime":"/Date(1506493543000)/","EndTime":"/Date(1506493554000)/","Money":0.3,"OMoney":0.06,"TimeCount":21,"ParentID":"7dd9a07e-9cef-4702-a165-b7033861c1b7","Status":"接通"},{"ID":"5b4f4abe-b583-410b-91f7-b854dd2e810f","SysNum":"推送话单","Caller":"+8617192230001","Calle164":"+8617704820771","CallTime":"/Date(1506493482000)/","FwdTime":"/Date(1506493494573)/","EndTime":"/Date(1506493493000)/","Money":0,"OMoney":0,"TimeCount":0,"ParentID":"7dd9a07e-9cef-4702-a165-b7033861c1b7","Status":"未接通"}]
     *//*

    private String Code;
    private String Mess;
    private List<InfoBean> Info;

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

    public List<InfoBean> getInfo() {
        return Info;
    }

    public void setInfo(List<InfoBean> Info) {
        this.Info = Info;
    }

    public static class InfoBean {
        *//**
         * ID : f3a3df42-cf1f-4e5f-9f3d-0b57df103fa5
         * SysNum : 推送话单
         * Caller : +8617192230001
         * Calle164 : +8617704820771
         * CallTime : /Date(1506522894000)/
         * FwdTime : /Date(1506494114000)/
         * EndTime : /Date(1506494126000)/
         * Money : 0.3
         * OMoney : 0.06
         * TimeCount : 23
         * ParentID : 7dd9a07e-9cef-4702-a165-b7033861c1b7
         * Status : 接通
         *//*

        private String ID;
        private String SysNum;
        private String Caller;
        private String Calle164;
        private String CallTime;
        private String FwdTime;
        private String EndTime;
        private double Money;
        private double OMoney;
        private int TimeCount;
        private String ParentID;
        private String Status;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getSysNum() {
            return SysNum;
        }

        public void setSysNum(String SysNum) {
            this.SysNum = SysNum;
        }

        public String getCaller() {
            return Caller;
        }

        public void setCaller(String Caller) {
            this.Caller = Caller;
        }

        public String getCalle164() {
            return Calle164;
        }

        public void setCalle164(String Calle164) {
            this.Calle164 = Calle164;
        }

        public String getCallTime() {
            return CallTime;
        }

        public void setCallTime(String CallTime) {
            this.CallTime = CallTime;
        }

        public String getFwdTime() {
            return FwdTime;
        }

        public void setFwdTime(String FwdTime) {
            this.FwdTime = FwdTime;
        }

        public String getEndTime() {
            return EndTime;
        }

        public void setEndTime(String EndTime) {
            this.EndTime = EndTime;
        }

        public double getMoney() {
            return Money;
        }

        public void setMoney(double Money) {
            this.Money = Money;
        }

        public double getOMoney() {
            return OMoney;
        }

        public void setOMoney(double OMoney) {
            this.OMoney = OMoney;
        }

        public int getTimeCount() {
            return TimeCount;
        }

        public void setTimeCount(int TimeCount) {
            this.TimeCount = TimeCount;
        }

        public String getParentID() {
            return ParentID;
        }

        public void setParentID(String ParentID) {
            this.ParentID = ParentID;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
    }*/
}
