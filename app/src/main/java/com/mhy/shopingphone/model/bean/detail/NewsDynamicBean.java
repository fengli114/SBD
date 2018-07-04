package com.mhy.shopingphone.model.bean.detail;

import java.util.List;

/**
 * Created by Administrator on 2017/9/27.企业动态
 */

public class NewsDynamicBean {

    /**
     * data : 默认新闻数据
     * errorCode : 200
     * json : [{"id":"c438d9e3-89c5-4704-bb97-cdab362ee5e7","createtime":1522558216000,"title":"我官方罕见再次谈及未来轰炸机：正式面世日期或临近","pic":"http://api.jisuapi.com/news/upload/201804/01150010_29995.jpg","url":"http://mil.news.sina.com.cn/jssd/2018-04-01/doc-ifysuwih9608704.shtml","from":"新浪军事"},{"id":"295fa0fa-0f44-4c25-8622-c29df90f8dda","createtime":1522557036000,"title":"亚洲最大火车站雄安站将开工：占地为96个足球场","pic":"http://api.jisuapi.com/news/upload/201804/01150009_75610.jpg","url":"http://news.sina.com.cn/c/nd/2018-04-01/doc-ifysuwec2074593.shtml","from":"华夏时报"},{"id":"e2a9c091-9bd7-48a2-9722-43b30d3e2dfe","createtime":1522555548000,"title":"备案倒计时 网贷存管银行\u201c白名单\u201d尚未公布","pic":"http://api.jisuapi.com/news/upload/201804/01190008_39008.jpg","url":"http://tech.sina.com.cn/i/2018-04-01/doc-ifysuvxx3213558.shtml","from":"中国经营报"},{"id":"1b818b4e-2a53-4c44-a4d8-a72b734f3b8b","createtime":1522553640000,"title":"英媒称印度巨额军费花得不值：陆军68%装备过时","pic":"http://api.jisuapi.com/news/upload/201804/01150011_43257.jpg","url":"http://news.sina.com.cn/o/2018-04-01/doc-ifysuvtq0798274.shtml","from":"参考消息"},{"id":"fd9621bc-4603-465f-a09b-f18d66a32af9","createtime":1522553584000,"title":"阿富汗媒体：2名中国籍武装分子被阿政府军击毙","pic":"http://api.jisuapi.com/news/upload/201804/01160005_43480.jpg","url":"http://news.sina.com.cn/w/zx/2018-04-01/doc-ifysuvsn2694186.shtml","from":"环球网"},{"id":"debda6d3-2d87-4148-9763-217a404527a6","createtime":1522553225000,"title":"日本海洋政策渲染中国威胁:中国军舰活动范围扩大","pic":"http://api.jisuapi.com/news/upload/201804/01150011_95738.jpg","url":"http://news.sina.com.cn/w/2018-04-01/doc-ifysuvsr6248845.shtml","from":"参考消息"},{"id":"75d4e367-612a-4e0a-a19e-5dd59d19d9cb","createtime":1522551916000,"title":"中国空间站核心舱首次公开 团队平均年龄仅35岁","pic":"http://api.jisuapi.com/news/upload/201804/01150009_80477.jpg","url":"http://mil.news.sina.com.cn/2018-04-01/doc-ifysuvph5159978.shtml","from":"人民日报海外版"},{"id":"359b4115-6110-4f06-895d-fc67272773b0","createtime":1522547947000,"title":"南海撞机事件17年：\u201c81192\u201d再不仅仅是一架飞机","pic":"http://api.jisuapi.com/news/upload/201804/01150009_76984.jpg","url":"http://news.sina.com.cn/o/2018-04-01/doc-ifysuuws8577810.shtml","from":"中国新闻网"},{"id":"4b4f6439-607a-417e-9758-998c09dc4c9b","createtime":1522547040000,"title":"全国首张环境保护税税票今天上午在上海开出(图)","pic":"http://api.jisuapi.com/news/upload/201804/01150008_99191.jpg","url":"http://news.sina.com.cn/o/2018-04-01/doc-ifysuuux6256340.shtml","from":"澎湃新闻"},{"id":"abb63c11-ebb2-4eee-b1e5-373a0cfc4b6f","createtime":1522546634000,"title":"实力科普：美国认定咖啡致癌？别紧张，没多大点事","pic":"http://api.jisuapi.com/news/upload/201804/01150007_36582.jpg","url":"http://tech.sina.com.cn/d/2018-04-01/doc-ifysuusx3603199.shtml","from":""}]
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
         * id : c438d9e3-89c5-4704-bb97-cdab362ee5e7
         * createtime : 1522558216000
         * title : 我官方罕见再次谈及未来轰炸机：正式面世日期或临近
         * pic : http://api.jisuapi.com/news/upload/201804/01150010_29995.jpg
         * url : http://mil.news.sina.com.cn/jssd/2018-04-01/doc-ifysuwih9608704.shtml
         * from : 新浪军事
         */

        private String id;
        private long createtime;
        private String title;
        private String pic;
        private String url;
        private String from;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getCreatetime() {
            return createtime;
        }

        public void setCreatetime(long createtime) {
            this.createtime = createtime;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }
    }
}
