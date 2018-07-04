package com.mhy.shopingphone.model.qiyeguanjia;

import java.io.Serializable;
import java.util.List;

/**
 * Created by YH on 2016/10/17.
 */

public class Modelers implements Serializable {
    /**
     * data : 查询成功
     * errorCode : 2000
     * json : [{"id":"98aa892d0b0841b59c01323411d74afa","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":"http://smms.sbdznkj.com:2018/file/2018-06-19/3508c7351b874113a772aa52321487e8滴滴出行@2x.png","text":"滴滴出行","url":"https://common.diditaxi.com.cn/general/webEntry?wx=true","type":1,"datastatus":true,"grade":8},{"id":"796c499aa140484abcf07ea4414c5ed7","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":"http://smms.sbdznkj.com:2018/file/2018-06-19/0da71a3120e24e2083bbd4c615d80ade随便看@2x.png","text":"随便看","url":"http://tv.sbdznkj.com/","type":1,"datastatus":true,"grade":7},{"id":"27dcc18701354e7d8c4ec3a3f165bece","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":"http://smms.sbdznkj.com:2018/file/2018-06-19/0ab805e1c9c747f98d972445455887a2公司官网@2x.png","text":"集团介绍","url":"https://mp.weixin.qq.com/s/D-qYengA1sqdZowOk-_yHw","type":1,"datastatus":true,"grade":6},{"id":"4af7fe4b055148d4a6d199a16a12a43c","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":"http://smms.sbdznkj.com:2018/file/2018-06-19/e56c260f1204481dba9e606a93bb8256案例中心@2x.png","text":"下载介绍","url":"http://m.qpic.cn/psb?/V13GzhpV3Fzkex/rOrPzT4ON9yEfKEiU2sehY7rant6va6utUXRkpsbySQ!/b/dJEAAAAAAAAA","type":1,"datastatus":true,"grade":5},{"id":"88e50fddf71a4eed808a80a3f30f9f36","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":"http://smms.sbdznkj.com:2018/file/2018-06-19/487810115b124d779cb9e94259747762产品介绍@2x.png","text":"产品案例","url":"http://r.photo.store.qq.com/psb?/V13GzhpV3Fzkex/E1EXxVhmqpFiS.FFu8L9.t1pFkZcY681RhsOpMQM2uA!/r/dDIBAAAAAAAA","type":1,"datastatus":true,"grade":4},{"id":"9947acec143d48bbacd7fd7421e3ca45","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":"http://smms.sbdznkj.com:2018/file/2018-06-19/df1e6f0e7a8e48b7ba7f2f15d1d32034客服拷贝@2x.png","text":"联系我们","url":"057185176992","type":1,"datastatus":true,"grade":3},{"id":"34d99af430824c38be92c1563c167245","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":"http://smms.sbdznkj.com:2018/file/2018-06-19/5fcf6a2c5cda4919ba8f3df2fc1f5d8a公司介绍@2x.png","text":"公司介绍","url":"http://m.qpic.cn/psb?/V13GzhpV3Fzkex/0irf4iMY2e0U8T7p61qROfxhwjHfQ53SYOxn4kNi78o!/b/dAgBAAAAAAAA","type":1,"datastatus":true,"grade":2},{"id":"f229fcdf8feb4b7ab27d404c0315e906","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":"http://smms.sbdznkj.com:2018/file/2018-06-19/88ff834d6ddd41888495b9f9f96b2e12公司荣誉@2x.png","text":"公司荣誉","url":"http://m.qpic.cn/psb?/V13GzhpV3Fzkex/DStZFMbIbZoCwpCVMscS0BLj9btwBSu09nOiIs.SoGY!/b/dDMBAAAAAAAA","type":1,"datastatus":true,"grade":1}]
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

    public static class JsonBean implements Serializable {
        /**
         * id : 98aa892d0b0841b59c01323411d74afa
         * agentid : 58162318-deca-4442-8e38-743b7729aa5b
         * logo : http://smms.sbdznkj.com:2018/file/2018-06-19/3508c7351b874113a772aa52321487e8滴滴出行@2x.png
         * text : 滴滴出行
         * url : https://common.diditaxi.com.cn/general/webEntry?wx=true
         * type : 1
         * datastatus : true
         * grade : 8
         * pic
         */

        private String id;
        private String agentid;
        private String logo;
        private String pic;
        private String text;
        private String url;
        private int type;
        private boolean datastatus;
        private int grade;

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

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
