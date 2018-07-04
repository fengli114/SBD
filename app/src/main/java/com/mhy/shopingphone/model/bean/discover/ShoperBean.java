package com.mhy.shopingphone.model.bean.discover;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class ShoperBean implements Serializable {


    /**
     * data : 商家自定义八大模块
     * errorCode : 200
     * json : [{"id":"46e7b52c-499c-4715-a3f6-a0ac934433c2","agentid":"1354f527-7b4a-4799-811f-b49d913edf76","logo":"/Images/89890020183271012798374066-bd15-42a7-a379-69e070e00e95.png","text":"联系热线","url":"057185176992","type":1,"datastatus":true,"grade":5},{"id":"5876e9a0-639f-4de7-8156-09cfd53d2323","agentid":"1354f527-7b4a-4799-811f-b49d913edf76","logo":"/Images/2868482018327101302238a41fe94-403c-4c75-9385-67a5f359147b.png","text":"随便看","url":"http://tv.sbdznkj.com/","type":1,"datastatus":true,"grade":4},{"id":"79992665-e5b2-4540-ad58-53fe904c70a1","agentid":"1354f527-7b4a-4799-811f-b49d913edf76","logo":"/Images/56536220183279595557321836496-663b-4905-9bd8-5f8d4e14be9e.png","text":"公司荣誉","url":"http://m.qpic.cn/psb?/V13GzhpV3Fzkex/lA9sin.UepKWogGG5UZeJAytWHlSohKPnK8fr5G.NO8!/b/dF4BAAAAAAAA&bo=7gKKB.4CigcRBzA!&rf=viewer_4","type":1,"datastatus":true,"grade":7},{"id":"7baaa4be-ce3e-46ae-ac01-46fb83b65863","agentid":"1354f527-7b4a-4799-811f-b49d913edf76","logo":"/Images/273001201832710068892e5d0648-b5cb-4b4f-8836-3b0a38cdeead.png","text":"关于我们","url":"http://m.qpic.cn/psb?/V13GzhpV3Fzkex/aWB15t1*OReTNOQElR7Rfko6NkAUP5bmcIGHbbNdyPo!/b/dEABAAAAAAAA&bo=7gIEE.4CBBMRNwA!&rf=viewer_4","type":1,"datastatus":true,"grade":6},{"id":"808cf7f3-4627-4d2e-b37f-08a496447ee0","agentid":"1354f527-7b4a-4799-811f-b49d913edf76","logo":"/Images/296302201832795943542a50387cb-1c1c-421d-a815-e57f7c513dc4.png","text":"产品中心","url":"http://m.qpic.cn/psb?/V13GzhpV3Fzkex/CkBex*7kOVWCQ3ECNPQAX5F.YlsyQFZl4i70.kEQ6Zc!/b/dHIAAAAAAAAA&bo=7gJACu4CQAoRBzA!&rf=viewer_4","type":1,"datastatus":true,"grade":8},{"id":"c97fd5c9-30bc-419d-b160-5a0523e4e65f","agentid":"1354f527-7b4a-4799-811f-b49d913edf76","logo":"/Images/74736920183271014396984ff9c63-0af8-4d2e-8729-67323f01e53f.png","text":"滴滴出行","url":"https://common.diditaxi.com.cn/general/webEntry?wx=true&bizid=257&channel=70365#/","type":1,"datastatus":true,"grade":3}]
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
         * id : 46e7b52c-499c-4715-a3f6-a0ac934433c2
         * agentid : 1354f527-7b4a-4799-811f-b49d913edf76
         * logo : /Images/89890020183271012798374066-bd15-42a7-a379-69e070e00e95.png
         * text : 联系热线
         * url : 057185176992
         * type : 1
         * datastatus : true
         * grade : 5
         */

        private String id;
        private String agentid;
        private String logo;
        private String text;
        private String url;
        private int type;
        private boolean datastatus;
        private int grade;

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

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public boolean isDatastatus() {
            return datastatus;
        }

        public void setDatastatus(boolean datastatus) {
            this.datastatus = datastatus;
        }

        public int getGrade() {
            return grade;
        }

        public void setGrade(int grade) {
            this.grade = grade;
        }
    }
}
