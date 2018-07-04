package com.mhy.shopingphone.model.bean.banner;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.轮播图
 */

public class BannerBean implements Serializable {


    /**
     * data : 系统默认banner广告
     * errorCode : 200
     * json : [{"id":"ca33ba8b-6934-4cb0-a4d1-ac865909077a","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":8,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/61343320183231481216499a0e5cb-2276-4169-a017-fd5f64ddc1d3.png","createtime":1521785292000},{"id":"a74d3d3b-0891-4320-a50a-e7275b0425ab","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":6,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/972603201832314856448a2537d0-5146-4896-acab-b6104d9dde63.png","createtime":1521785285000},{"id":"6c618f3b-5a3d-4b17-b033-77fc265654ba","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":4,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/12081220183211720991514b1ca8-b3e0-4b19-8e43-3a6b1b5f6024.jpg","createtime":1521622920000},{"id":"ba26049d-bc60-4f4e-8d5f-7a51f27d8eef","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":3,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/768555201832117155736e051372-d8de-44ce-a717-368c53da0116.jpg","createtime":1521622915000},{"id":"109edbe8-b52c-4d56-9d67-66699e1be074","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":7,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/63493720183211714548742da6799-c87a-4d48-bd92-9c909fd027b5.jpg","createtime":1521622905000},{"id":"6f292e41-1037-4bf3-80a0-fddf3b7b7a6b","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":4,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/183358201832114302168325bf456b-6f1a-4424-a3d6-4835fd86758f.jpg","createtime":1521613821000},{"id":"14f9a7e9-893a-4527-a80c-bd662e6945e4","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":3,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/679863201832114301164705772449-4ea2-48e7-a071-a933632a9ecc.jpg","createtime":1521613811000},{"id":"ab518027-1bcd-459e-86bc-40477a804431","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":2,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/490366201832114305187d85dcff5-a1ca-4967-a01e-c7daa60dae3b.jpg","createtime":1521613805000},{"id":"d794bee8-c499-4950-8771-9f0e210928fd","parentid":"7dd9a07e-9cef-4702-a165-b7033861c1b7","adtype":1,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"/Images/620821201832114295970ebb888ee-37d9-452c-8235-16f99e2c93b4.jpg","createtime":1521613799000}]
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
         * id : ca33ba8b-6934-4cb0-a4d1-ac865909077a
         * parentid : 7dd9a07e-9cef-4702-a165-b7033861c1b7
         * adtype : 8
         * url :
         * showcount : 0
         * hitscount : 0
         * datastatus : true
         * path : /Images/61343320183231481216499a0e5cb-2276-4169-a017-fd5f64ddc1d3.png
         * createtime : 1521785292000
         */

        private String id;
        private String parentid;
        private int adtype;
        private String url;
        private int showcount;
        private int hitscount;
        private boolean datastatus;
        private String path;
        private long createtime;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getParentid() {
            return parentid;
        }

        public void setParentid(String parentid) {
            this.parentid = parentid;
        }

        public int getAdtype() {
            return adtype;
        }

        public void setAdtype(int adtype) {
            this.adtype = adtype;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getShowcount() {
            return showcount;
        }

        public void setShowcount(int showcount) {
            this.showcount = showcount;
        }

        public int getHitscount() {
            return hitscount;
        }

        public void setHitscount(int hitscount) {
            this.hitscount = hitscount;
        }

        public boolean isDatastatus() {
            return datastatus;
        }

        public void setDatastatus(boolean datastatus) {
            this.datastatus = datastatus;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }
    }
}
