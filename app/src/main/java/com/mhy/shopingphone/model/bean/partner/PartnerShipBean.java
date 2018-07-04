package com.mhy.shopingphone.model.bean.partner;

import java.util.List;

/**
 * 作者： "RedRainM" on 2017/11/14 0014.
 * 描述：合伙人中心实体类
 */

public class PartnerShipBean {


    /**
     * Code : 0
     * Mess : Success
     * Ad : /Images/66699820171113103555548bb99d132-47bf-4014-b9b3-3fdc42b64491.jpg
     * Month : [{"ID":"3c6e9f4f-9047-4297-8d5b-182cbd2a6452","CreateTime":"/Date(1510411192000)/","ClickTime":"/Date(1510382679000)/","Title":"【双11狂欢价】奇美口琴24孔复音C调口琴初学者儿童成人学生入门练习乐器口琴","CID":"530610813645","Money":48,"OutMoney":12.5,"JsMoney":3.62,"YjBl":30,"YjMoney":1.875,"OrderNum":"86290091645257318","AgentID":"58162318-deca-4442-8e38-743b7729aa5b","DataStatus":true},{"ID":"0ac1673e-426a-405b-9321-c7a0aa9cb08b","CreateTime":"/Date(1510109264000)/","ClickTime":"/Date(1510109077000)/","Title":"冬季新款海宁男士皮衣加绒加厚外套中老年皮夹克机车皮衣爸爸装男","CID":"557454473621","Money":538,"OutMoney":99,"JsMoney":5.45,"YjBl":5,"YjMoney":4.35,"OrderNum":"87575865715273098","AgentID":"58162318-deca-4442-8e38-743b7729aa5b","DataStatus":true}]
     * UpMonth : [{"ID":"00000000-0000-0000-0000-000000000000","CreateTime":"/Date(-62135596800000)/","ClickTime":"/Date(-62135596800000)/","Title":null,"CID":null,"Money":0,"OutMoney":0,"JsMoney":0,"YjBl":0,"YjMoney":0,"OrderNum":null,"AgentID":"00000000-0000-0000-0000-000000000000","DataStatus":false}]
     * Money : 0
     * Count : 0
     */

    private String Code;
    private String Mess;
    private String Ad;
    private String Money;
    private String Count;
    private List<MonthBean> Month;
    private List<UpMonthBean> UpMonth;

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

    public String getAd() {
        return Ad;
    }

    public void setAd(String Ad) {
        this.Ad = Ad;
    }

    public String getMoney() {
        return Money;
    }

    public void setMoney(String Money) {
        this.Money = Money;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String Count) {
        this.Count = Count;
    }

    public List<MonthBean> getMonth() {
        return Month;
    }

    public void setMonth(List<MonthBean> Month) {
        this.Month = Month;
    }

    public List<UpMonthBean> getUpMonth() {
        return UpMonth;
    }

    public void setUpMonth(List<UpMonthBean> UpMonth) {
        this.UpMonth = UpMonth;
    }

    public static class MonthBean {
        /**
         * ID : 3c6e9f4f-9047-4297-8d5b-182cbd2a6452
         * CreateTime : /Date(1510411192000)/
         * ClickTime : /Date(1510382679000)/
         * Title : 【双11狂欢价】奇美口琴24孔复音C调口琴初学者儿童成人学生入门练习乐器口琴
         * CID : 530610813645
         * Money : 48.0
         * OutMoney : 12.5
         * JsMoney : 3.62
         * YjBl : 30.0
         * YjMoney : 1.875
         * OrderNum : 86290091645257318
         * AgentID : 58162318-deca-4442-8e38-743b7729aa5b
         * DataStatus : true
         */

        private String ID;
        private String CreateTime;
        private String ClickTime;
        private String Title;
        private String CID;
        private String Money;
        private String OutMoney;
        private String JsMoney;
        private String YjBl;
        private String YjMoney;
        private String OrderNum;
        private String AgentID;
        private boolean DataStatus;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getClickTime() {
            return ClickTime;
        }

        public void setClickTime(String ClickTime) {
            this.ClickTime = ClickTime;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getCID() {
            return CID;
        }

        public void setCID(String CID) {
            this.CID = CID;
        }

        public String getMoney() {
            return Money;
        }

        public void setMoney(String Money) {
            this.Money = Money;
        }

        public String getOutMoney() {
            return OutMoney;
        }

        public void setOutMoney(String OutMoney) {
            this.OutMoney = OutMoney;
        }

        public String getJsMoney() {
            return JsMoney;
        }

        public void setJsMoney(String JsMoney) {
            this.JsMoney = JsMoney;
        }

        public String getYjBl() {
            return YjBl;
        }

        public void setYjBl(String YjBl) {
            this.YjBl = YjBl;
        }

        public String getYjMoney() {
            return YjMoney;
        }

        public void setYjMoney(String YjMoney) {
            this.YjMoney = YjMoney;
        }

        public String getOrderNum() {
            return OrderNum;
        }

        public void setOrderNum(String OrderNum) {
            this.OrderNum = OrderNum;
        }

        public String getAgentID() {
            return AgentID;
        }

        public void setAgentID(String AgentID) {
            this.AgentID = AgentID;
        }

        public boolean isDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(boolean DataStatus) {
            this.DataStatus = DataStatus;
        }
    }

    public static class UpMonthBean {
        /**
         * ID : 00000000-0000-0000-0000-000000000000
         * CreateTime : /Date(-62135596800000)/
         * ClickTime : /Date(-62135596800000)/
         * Title : null
         * CID : null
         * Money : 0
         * OutMoney : 0
         * JsMoney : 0
         * YjBl : 0
         * YjMoney : 0
         * OrderNum : null
         * AgentID : 00000000-0000-0000-0000-000000000000
         * DataStatus : false
         */

        private String ID;
        private String CreateTime;
        private String ClickTime;
        private Object Title;
        private Object CID;
        private int Money;
        private int OutMoney;
        private int JsMoney;
        private int YjBl;
        private int YjMoney;
        private Object OrderNum;
        private String AgentID;
        private boolean DataStatus;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
        }

        public String getClickTime() {
            return ClickTime;
        }

        public void setClickTime(String ClickTime) {
            this.ClickTime = ClickTime;
        }

        public Object getTitle() {
            return Title;
        }

        public void setTitle(Object Title) {
            this.Title = Title;
        }

        public Object getCID() {
            return CID;
        }

        public void setCID(Object CID) {
            this.CID = CID;
        }

        public int getMoney() {
            return Money;
        }

        public void setMoney(int Money) {
            this.Money = Money;
        }

        public int getOutMoney() {
            return OutMoney;
        }

        public void setOutMoney(int OutMoney) {
            this.OutMoney = OutMoney;
        }

        public int getJsMoney() {
            return JsMoney;
        }

        public void setJsMoney(int JsMoney) {
            this.JsMoney = JsMoney;
        }

        public int getYjBl() {
            return YjBl;
        }

        public void setYjBl(int YjBl) {
            this.YjBl = YjBl;
        }

        public int getYjMoney() {
            return YjMoney;
        }

        public void setYjMoney(int YjMoney) {
            this.YjMoney = YjMoney;
        }

        public Object getOrderNum() {
            return OrderNum;
        }

        public void setOrderNum(Object OrderNum) {
            this.OrderNum = OrderNum;
        }

        public String getAgentID() {
            return AgentID;
        }

        public void setAgentID(String AgentID) {
            this.AgentID = AgentID;
        }

        public boolean isDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(boolean DataStatus) {
            this.DataStatus = DataStatus;
        }
    }
}
