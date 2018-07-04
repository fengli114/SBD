package com.mhy.shopingphone.model.bean.shop;

import java.util.List;

/**
 * 作者： "RedRainM" on 2018/1/19 0019.
 * 描述：
 */

public class ShopTypesBean {

    /**
     * data : 成功获取分类信息数据
     * errorCode : 200
     * json : [{"id":"698a37b5-498b-4fa2-9c77-bb31253992ae","alicate":"女装/女士精品|女鞋|女士内衣/男士内衣/家居服","cate":"女装","grade":"1","pic":"/Images/973081201812917431289516f7ea7d-0752-4522-a4f4-a663d824057b.png","datastatus":true,"indexpic":"/Images/3672422018131142851490df33d84b-e353-4b86-904f-5cce11847d5d.png","dpic":null},{"id":"26566368-190c-425a-a7de-ad1775ce485e","alicate":"彩妆/香水/美妆工具","cate":"化妆品","grade":"2","pic":"/Images/65874120181291742534978c56d9fa-4bf2-47e6-ba02-92e5dd0df63d.png","datastatus":true,"indexpic":"/Images/238872201813114297694b25fbe53-ae4b-4cd8-b10c-4dea93f380f3.png","dpic":null},{"id":"e0222a76-b74e-4610-95f3-0c958a66edfe","alicate":"男装|流行男鞋","cate":"男装","grade":"3","pic":"/Images/67443320181291740201254adb441e-c515-46ed-bcae-6d9754d2ac9e.png","datastatus":true,"indexpic":"/Images/5085892018131143125949b01d0c59-af36-4b97-8382-b3ad83569bf1.png","dpic":null},{"id":"f7f7c93b-07e4-46db-9c38-577621bdd587","alicate":"水产肉类/新鲜蔬果/熟食|零食/坚果/特产|粮油米面/南北干货/调味品|咖啡/麦片/冲饮","cate":"瓜果熟食","grade":"4","pic":"/Images/4778112018129174110755253faa07-ea7c-48c6-b21d-f15c0f51e61a.png","datastatus":true,"indexpic":null,"dpic":"/Images/3769362018131143111601f5986f77-8071-4773-82af-d50717042ebb.png"},{"id":"bbc6e8a0-9c91-49c8-b132-099f9c6f5fc6","alicate":"玩具/童车/益智/积木/模型","cate":"玩具","grade":"5","pic":"/Images/318044201812917401029816937e34-0a8c-4396-8c92-045a898fb33e.png","datastatus":true,"indexpic":null,"dpic":"/Images/4248412018131142943281b8d5c9a5-daf5-4147-9008-a4b2043cc8a7.png"},{"id":"0e9356b4-427a-4f89-822d-9eebd0ec1b67","alicate":"箱包皮具/热销女包/男包","cate":"箱包皮具","grade":"23","pic":"/Images/1961352018129174229685ca35ca71-577d-4edb-8b26-c903c9d04ed4.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"0401dfaf-f145-4230-a8bc-aa15988387f6","alicate":"收纳整理|影音电器|生活电器|居家日用|居家布艺|家装主材|家装灯饰光源|家庭/个人清洁工具|家居饰品|基础建材|大家电|床上用品|厨房电器|厨房/烹饪用具|餐","cate":"家居日用","grade":"100","pic":"/Images/67926120181291742464931b14b0c6-edfa-49c2-8c0f-fe19eb29760f.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"143e01bd-1b21-4633-b917-f1fff63adeb6","alicate":"宠物/宠物食品及用品","cate":"宠物周边","grade":"100","pic":"/Images/569343201812917442541aefaf818-c48c-42ed-8b4b-78574cba99a7.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"213c8b33-40a8-44f8-9f69-65231b9f6ec6","alicate":"手表|饰品/流行首饰/时尚饰品新|珠宝/钻石/翡翠/黄金","cate":"钟表饰品","grade":"100","pic":"/Images/328920201812917413395787ba95c6-bd78-4831-8a7a-4a75eb0f1c99.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"288e4d1c-5f06-4366-abee-f7af8d3a5965","alicate":"孕妇装/孕产妇用品/营养|尿片/洗护/喂哺/推车床|奶粉/辅食/营养品/零食","cate":"育婴用品","grade":"100","pic":"/Images/614553201812917448703a680002a-4be9-4fd6-a86f-3854e8eeb356.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"2c203776-35bb-47d1-86d5-8ed23f6bb123","alicate":"酒类|茶","cate":"烟酒糖茶","grade":"100","pic":"/Images/6572822018129174215797a6cf32c9-80e2-4792-9dca-e1cc4b97fe98.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"2ccbe6b0-81fc-481d-888c-bbcbdbe5bf2a","alicate":"书籍/杂志/报纸|电子词典/电纸书/文化用品","cate":"文字阅读","grade":"100","pic":"/Images/2077072018129174326923987c9351-b96f-425f-927d-0e8e5775d2f4.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"42f70f18-0304-4f72-b0f1-b8d995ff7582","alicate":"鲜花速递/花卉仿真/绿植园艺","cate":"花卉周边","grade":"100","pic":"/Images/9749052018129174357335aba4a95-edd4-48dc-936c-2c1c0083ed50.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"4d086d8f-88d2-47a6-9191-78ed0d476fc5","alicate":"五金/工具|电子/电工|ZIPPO/瑞士军刀/眼镜|电子元器件市场","cate":"五金耗材","grade":"100","pic":"/Images/4638062018129174252164e187e5a-091e-4d88-a669-99f71e744eb5.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"7a24dfd7-f3e9-41ef-ac4e-3665de05387c","alicate":"汽车/用品/配件/改装|摩托车/装备/配件|电动车/配件/交通工具","cate":"有车一族","grade":"100","pic":"/Images/5724562018129174027503d9e99ba8-7222-4500-8306-e04f62ea26f0.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"82935493-bc8d-4598-8c72-368c306aa4ea","alicate":"洗护清洁剂/卫生巾/纸/香薰|个人护理/保健/按摩器材|成人用品/情趣用品|保健食品/膳食营养补充食品|OTC药品/医疗器械/计生用品|隐形眼镜/护理液|医疗及","cate":"医疗保健","grade":"100","pic":"/Images/197632201812917403474330504902-0f1a-44dc-8723-0793e3b4d48f.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"83d7b6ee-82bc-4cb4-84b5-4f57467ec191","alicate":"童装/婴儿装/亲子装|童鞋/婴儿鞋/亲子鞋","cate":"童装","grade":"100","pic":"/Images/88490220181291740528438556990b-b971-49e1-a1a8-a961ba9c0a56.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"9740166a-44bc-4642-a5e5-37361c7b0237","alicate":"智能设备|网络设备/网络相关|手机号码/套餐/增值业务|电玩/配件/游戏/攻略|电脑硬件/显示器/电脑周边|MP3/MP4/iPod/录音笔|DIY电脑|3C数","cate":"数码周边","grade":"100","pic":"/Images/5284432018129174041808652cb977-89ca-4775-8fc6-23c945f3d780.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"a391ed94-75b4-4471-b04c-6680d7569439","alicate":"特色手工艺","cate":"手工艺","grade":"100","pic":"/Images/88547420181291741409388aa76c6d-f60f-4064-8652-cafd51e6367f.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"a9a0df79-43c4-436d-beea-f84682e1f779","alicate":"商业/办公家具|节庆用品/礼品|包装|办公设备/耗材/相关服务|购物提货券","cate":"商业办公","grade":"100","pic":"/Images/656863201812917441637336fdcf31-47b1-4f28-bc1e-058a335d4275.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"e8d0a7e3-0bbf-4bb4-ad7b-a523e0fc3139","alicate":"音乐/影视/明星/音像|模玩/动漫/周边/cos/桌游|乐器/吉他/钢琴/配件","cate":"影音周边","grade":"100","pic":"/Images/3385272018129174235936e68fd28c-fa56-4b2a-a32b-3b2e44ab3c66.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"e9806b59-79e6-427b-8c93-671dbb6fce64","alicate":"美容美体仪器|美容护肤/美体/精油|美发护发/假发|个人护理/保健/按摩器材|传统滋补营养品|保健食品/膳食营养补充食品","cate":"美容美体","grade":"100","pic":"/Images/43425020181291741583186e3047ff-d23e-4363-810c-50b82593399e.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"f4d83d38-d2ae-4a2a-b25c-d22b74930cef","alicate":"服饰配件/皮带/帽子/围巾","cate":"服饰配件","grade":"100","pic":"/Images/7663462018129174343978543d3391-d8ce-460c-91fc-c8da3abad620.png","datastatus":true,"indexpic":null,"dpic":null},{"id":"fd546305-05bf-4eea-8633-54edb3606cfc","alicate":"运动鞋new|运动服/休闲服装|运动包/户外包/配件|运动/瑜伽/健身/球迷用品|户外/登山/野营/旅行用品|自行车/骑行装备/零配件","cate":"休闲运动","grade":"100","pic":"/Images/5012332018129174122407753fa96-26ee-4baf-9a7c-13987a353afd.png","datastatus":true,"indexpic":null,"dpic":null}]
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
         * id : 698a37b5-498b-4fa2-9c77-bb31253992ae
         * alicate : 女装/女士精品|女鞋|女士内衣/男士内衣/家居服
         * cate : 女装
         * grade : 1
         * pic : /Images/973081201812917431289516f7ea7d-0752-4522-a4f4-a663d824057b.png
         * datastatus : true
         * indexpic : /Images/3672422018131142851490df33d84b-e353-4b86-904f-5cce11847d5d.png
         * dpic : null
         */

        private String id;
        private String alicate;
        private String cate;
        private String grade;
        private String pic;
        private boolean datastatus;
        private String indexpic;
        private Object dpic;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAlicate() {
            return alicate;
        }

        public void setAlicate(String alicate) {
            this.alicate = alicate;
        }

        public String getCate() {
            return cate;
        }

        public void setCate(String cate) {
            this.cate = cate;
        }

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public boolean isDatastatus() {
            return datastatus;
        }

        public void setDatastatus(boolean datastatus) {
            this.datastatus = datastatus;
        }

        public String getIndexpic() {
            return indexpic;
        }

        public void setIndexpic(String indexpic) {
            this.indexpic = indexpic;
        }

        public Object getDpic() {
            return dpic;
        }

        public void setDpic(Object dpic) {
            this.dpic = dpic;
        }
    }
}
