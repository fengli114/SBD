package com.mhy.shopingphone.model.bean.detail;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27.企业动态
 */

public class DynamicBean {

    /**
     * Code : 0
     * Mess : Success
     * Info : [{"ID":"5b0a701c-84b8-4e42-9600-b4d728e3b8ac","Pic":"/Images/7295262017926224027667ad21e1e3-6210-48ed-8996-0165859413db.jpg","Title":"这是一条历史纪录","Content":"asdfasdfasdfasdfasdfasdf","CreateTime":"/Date(1505918427673)/","AgentID":"58162318-deca-4442-8e38-743b7729aa5b","DataStatus":true,"Type":1,"Url":"asdfasfasdfasdfasdf"},{"ID":"b18ce5e5-be8f-4f28-b811-c3eaac3aeacf","Pic":"/Images/7450022017926215385058c93089-16ff-42b9-b383-6d7091369aeb.png","Title":"asdfasdfsadfa","Content":"asdfasdfasfdasdf","CreateTime":"/Date(1506433988053)/","AgentID":"58162318-deca-4442-8e38-743b7729aa5b","DataStatus":true,"Type":1,"Url":"http://www.baidu.com"}]
     * Auth : 随便打
     */

    private String Code;
    private String Mess;
    private String Auth;
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

    public String getAuth() {
        return Auth;
    }

    public void setAuth(String Auth) {
        this.Auth = Auth;
    }

    public List<InfoBean> getInfo() {
        return Info;
    }

    public void setInfo(List<InfoBean> Info) {
        this.Info = Info;
    }

    public static class InfoBean {
        /**
         * ID : 5b0a701c-84b8-4e42-9600-b4d728e3b8ac
         * Pic : /Images/7295262017926224027667ad21e1e3-6210-48ed-8996-0165859413db.jpg
         * Title : 这是一条历史纪录
         * Content : asdfasdfasdfasdfasdfasdf
         * CreateTime : /Date(1505918427673)/
         * AgentID : 58162318-deca-4442-8e38-743b7729aa5b
         * DataStatus : true
         * Type : 1
         * Url : asdfasfasdfasdfasdf
         */

        private String ID;
        private String Pic;
        private String Title;
        private String Content;
        private String CreateTime;
        private String AgentID;
        private boolean DataStatus;
        private int Type;
        private String Url;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String Title) {
            this.Title = Title;
        }

        public String getContent() {
            return Content;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getCreateTime() {
            return CreateTime;
        }

        public void setCreateTime(String CreateTime) {
            this.CreateTime = CreateTime;
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

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }
}
