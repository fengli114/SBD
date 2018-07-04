package com.mhy.shopingphone.model.qiyeguanjia;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YH on 2016/10/17.
 */

public class Logginges implements Serializable {
    /**
     * data : 登陆成功
     * errorCode : 2000
     * json : {"useracc":"随便打","adminUserId":"58162318-deca-4442-8e38-743b7729aa5b","userNick":"随便打","adminUsergrade":1,"adminAddcard":true,"freeroutes":[{"id":"690a9c99-aa0e-47da-b0da-a3f91a2b52fb","name":"随便打","feelroute":0.25,"datastatus":true,"ratelength":60,"createtime":1507433297000},{"id":"3af26bfc-8273-42e5-805c-34d2bb7c0fff","name":"摩登人家/0.3/60","feelroute":0.3,"datastatus":true,"ratelength":60,"createtime":1509076217000},{"id":"78554bb7-c9f3-4874-8c73-1e952b22ff7a","name":"0.2","feelroute":0.2,"datastatus":true,"ratelength":60,"createtime":1516949093000},{"id":"cd021d17-0c22-4b66-83fb-26ccee978853","name":"年卡费率","feelroute":0.2,"datastatus":true,"ratelength":60,"createtime":1521701069000},{"id":"458ebf97-8b4b-4c70-a559-b08763faad2d","name":"季卡费率","feelroute":0.25,"datastatus":true,"ratelength":60,"createtime":1521701080000},{"id":"60eb5a22-b188-47c3-9beb-31272749223d","name":"月卡费率","feelroute":0.25,"datastatus":true,"ratelength":60,"createtime":1521701087000}]}
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

    public static class JsonBean implements Serializable {
        /**
         * useracc : 随便打
         * adminUserId : 58162318-deca-4442-8e38-743b7729aa5b
         * userNick : 随便打
         * adminUsergrade : 1
         * adminAddcard : true
         * freeroutes : [{"id":"690a9c99-aa0e-47da-b0da-a3f91a2b52fb","name":"随便打","feelroute":0.25,"datastatus":true,"ratelength":60,"createtime":1507433297000},{"id":"3af26bfc-8273-42e5-805c-34d2bb7c0fff","name":"摩登人家/0.3/60","feelroute":0.3,"datastatus":true,"ratelength":60,"createtime":1509076217000},{"id":"78554bb7-c9f3-4874-8c73-1e952b22ff7a","name":"0.2","feelroute":0.2,"datastatus":true,"ratelength":60,"createtime":1516949093000},{"id":"cd021d17-0c22-4b66-83fb-26ccee978853","name":"年卡费率","feelroute":0.2,"datastatus":true,"ratelength":60,"createtime":1521701069000},{"id":"458ebf97-8b4b-4c70-a559-b08763faad2d","name":"季卡费率","feelroute":0.25,"datastatus":true,"ratelength":60,"createtime":1521701080000},{"id":"60eb5a22-b188-47c3-9beb-31272749223d","name":"月卡费率","feelroute":0.25,"datastatus":true,"ratelength":60,"createtime":1521701087000}]
         */

        private String useracc;
        private String adminUserId;
        private String userNick;
        private int adminUsergrade;
        private boolean adminAddcard;
        private List<FreeroutesBean> freeroutes;

        public String getUseracc() {
            return useracc;
        }

        public void setUseracc(String useracc) {
            this.useracc = useracc;
        }

        public String getAdminUserId() {
            return adminUserId;
        }

        public void setAdminUserId(String adminUserId) {
            this.adminUserId = adminUserId;
        }

        public String getUserNick() {
            return userNick;
        }

        public void setUserNick(String userNick) {
            this.userNick = userNick;
        }

        public int getAdminUsergrade() {
            return adminUsergrade;
        }

        public void setAdminUsergrade(int adminUsergrade) {
            this.adminUsergrade = adminUsergrade;
        }

        public boolean isAdminAddcard() {
            return adminAddcard;
        }

        public void setAdminAddcard(boolean adminAddcard) {
            this.adminAddcard = adminAddcard;
        }

        public List<FreeroutesBean> getFreeroutes() {
            return freeroutes;
        }

        public void setFreeroutes(List<FreeroutesBean> freeroutes) {
            this.freeroutes = freeroutes;
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
