package com.mhy.shopingphone.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class AdvertisingBean implements Serializable{

    /**
     * data : 查询成功
     * errorCode : 2000
     * json : {"shopTexts":[{"id":"debde1b0e1954bc3994a1f719bffd6ee","text":"百万商品已更新 京东即将上线 快来海淘商品体验吧~","pic":null,"datastatus":true,"type":2,"parentid":"58162318-deca-4442-8e38-743b7729aa5b","url":null}],"shopinfoes":[{"id":"78c6c26908f24ae1807e66fcce81df70","agentid":"58162318-deca-4442-8e38-743b7729aa5b","logo":null,"text":"关注微信公众号【随便打免费电话平台】 转发任意文章加评论手机号、姓名，立即领取百元购物券~","url":null,"type":2,"datastatus":true,"grade":0}]}
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

    public static class JsonBean implements Serializable{
        private List<ShopTextsBean> shopTexts;
        private List<ShopinfoesBean> shopinfoes;

        public List<ShopTextsBean> getShopTexts() {
            return shopTexts;
        }

        public void setShopTexts(List<ShopTextsBean> shopTexts) {
            this.shopTexts = shopTexts;
        }

        public List<ShopinfoesBean> getShopinfoes() {
            return shopinfoes;
        }

        public void setShopinfoes(List<ShopinfoesBean> shopinfoes) {
            this.shopinfoes = shopinfoes;
        }

        public static class ShopTextsBean implements Serializable{
            /**
             * id : debde1b0e1954bc3994a1f719bffd6ee
             * text : 百万商品已更新 京东即将上线 快来海淘商品体验吧~
             * pic : null
             * datastatus : true
             * type : 2
             * parentid : 58162318-deca-4442-8e38-743b7729aa5b
             * url : null
             */

            private String id;
            private String text;
            private Object pic;
            private boolean datastatus;
            private int type;
            private String parentid;
            private Object url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public Object getPic() {
                return pic;
            }

            public void setPic(Object pic) {
                this.pic = pic;
            }

            public boolean isDatastatus() {
                return datastatus;
            }

            public void setDatastatus(boolean datastatus) {
                this.datastatus = datastatus;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public String getParentid() {
                return parentid;
            }

            public void setParentid(String parentid) {
                this.parentid = parentid;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
                this.url = url;
            }
        }

        public static class ShopinfoesBean implements Serializable{
            /**
             * id : 78c6c26908f24ae1807e66fcce81df70
             * agentid : 58162318-deca-4442-8e38-743b7729aa5b
             * logo : null
             * text : 关注微信公众号【随便打免费电话平台】 转发任意文章加评论手机号、姓名，立即领取百元购物券~
             * url : null
             * type : 2
             * datastatus : true
             * grade : 0
             */

            private String id;
            private String agentid;
            private Object logo;
            private String text;
            private Object url;
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

            public Object getLogo() {
                return logo;
            }

            public void setLogo(Object logo) {
                this.logo = logo;
            }

            public String getText() {
                return text;
            }

            public void setText(String text) {
                this.text = text;
            }

            public Object getUrl() {
                return url;
            }

            public void setUrl(Object url) {
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
}
