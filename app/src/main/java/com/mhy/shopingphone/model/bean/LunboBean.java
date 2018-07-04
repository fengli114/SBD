package com.mhy.shopingphone.model.bean;

import java.util.List;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class LunboBean {
    /**
     * data : 查询成功
     * errorCode : 2000
     * json : [{"id":"0417957393f24eed9b00c6433758b3c3","parentid":"3cc7169f-ead6-4f8b-8b6d-3cfd6713eb85","adtype":6,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"http://smms.sbdznkj.com:2018/file/2018-06-21/56cf5c9524304ad2958c1fe9ffb13f0b话费购物一卡通.png","createtime":1528423215000},{"id":"0c3a98a987b144ce9da188547bd69ea6","parentid":"3cc7169f-ead6-4f8b-8b6d-3cfd6713eb85","adtype":7,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"http://smms.sbdznkj.com:2018/file/2018-06-21/ace522ede74849cbbcefef2e7f9656a9通讯录.jpg","createtime":1528423237000},{"id":"49909ea718784c02bbaf112dea185953","parentid":"3cc7169f-ead6-4f8b-8b6d-3cfd6713eb85","adtype":2,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"http://smms.sbdznkj.com:2018/file/2018-06-21/539b1a5fcc0246ab94a993749c661952回拨等待.jpg","createtime":1528947680000},{"id":"6f3f5ca7-5ee7-4364-aa69-ac610ea0a6b3","parentid":"3cc7169f-ead6-4f8b-8b6d-3cfd6713eb85","adtype":4,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"http://smms.sbdznkj.com:2018/file/2018-06-21/b2e7bee201204207a5d1d286baf60f87发现.jpg","createtime":1521594889000},{"id":"72c33c1ba5e540e38b39a7b2b3fef089","parentid":"3cc7169f-ead6-4f8b-8b6d-3cfd6713eb85","adtype":8,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"http://smms.sbdznkj.com:2018/file/2018-06-21/10d686bb32914fc0ab303d3567bac246购物卡.png","createtime":1528423228000},{"id":"bcb51834b5d8472b932a69a7da8e164c","parentid":"3cc7169f-ead6-4f8b-8b6d-3cfd6713eb85","adtype":1,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"http://smms.sbdznkj.com:2018/file/2018-06-21/8cae60173287417e854a05c6dac3086d启动页.jpg","createtime":1528947665000},{"id":"fddf1c085a4a4966a2abe0114cd0c801","parentid":"3cc7169f-ead6-4f8b-8b6d-3cfd6713eb85","adtype":3,"url":"","showcount":0,"hitscount":0,"datastatus":true,"path":"http://smms.sbdznkj.com:2018/file/2018-06-21/63dd18f1d965472290a2852a51391cd7拨号.jpg","createtime":1528947652000}]
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
         * id : 0417957393f24eed9b00c6433758b3c3
         * parentid : 3cc7169f-ead6-4f8b-8b6d-3cfd6713eb85
         * adtype : 6
         * url :
         * showcount : 0
         * hitscount : 0
         * datastatus : true
         * path : http://smms.sbdznkj.com:2018/file/2018-06-21/56cf5c9524304ad2958c1fe9ffb13f0b话费购物一卡通.png
         * createtime : 1528423215000
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
