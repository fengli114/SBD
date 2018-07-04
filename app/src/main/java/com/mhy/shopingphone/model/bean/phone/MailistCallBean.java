package com.mhy.shopingphone.model.bean.phone;

import java.util.List;

/**
 * 作者： "RedRainM" on 2018/1/15 0015.
 * 描述：
 */

public class MailistCallBean {


    /**
     * data : 代理商自定义回拨号码
     * errorCode : 2000
     * json : [{"id":"7161f9f6-e7a8-4896-b5a6-7da339ba3611","agentid":"58162318-deca-4442-8e38-743b7729aa5b","nums":"08386724050,08386724051,08386724061,08386724062","Logo":"/Images/44026420171114144115478ffdbeef-c953-44ee-9b4e-61e09ca7d318.png","Name":"随便打来电"}]
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
         * id : 7161f9f6-e7a8-4896-b5a6-7da339ba3611
         * agentid : 58162318-deca-4442-8e38-743b7729aa5b
         * nums : 08386724050,08386724051,08386724061,08386724062
         * Logo : /Images/44026420171114144115478ffdbeef-c953-44ee-9b4e-61e09ca7d318.png
         * Name : 随便打来电
         */

        private String id;
        private String agentid;
        private String nums;
        private String Logo;
        private String Name;

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

        public String getNums() {
            return nums;
        }

        public void setNums(String nums) {
            this.nums = nums;
        }

        public String getLogo() {
            return Logo;
        }

        public void setLogo(String Logo) {
            this.Logo = Logo;
        }

        public String getName() {
            return Name;
        }

        public void setName(String Name) {
            this.Name = Name;
        }
    }
}
