package com.mhy.shopingphone.model.bean.goods;

import java.util.List;

/**
 * Created by RedRainM on 2017/10/16.
 */

public class GoodsTabBean {

    /**
     * Code : 0
     * Mess : Success
     * Info : [{"Category":"五金耗材"},{"Category":"休闲运动"},{"Category":"住宅家具"},{"Category":"化妆品"},{"Category":"医疗保健"},{"Category":"商业办公"},{"Category":"女装"},{"Category":"宠物周边"},{"Category":"家居日用"},{"Category":"数码周边"},{"Category":"文字阅读"},{"Category":"有车一族"},{"Category":"服饰配件"},{"Category":"玩具"},{"Category":"瓜果熟食"},{"Category":"男装"},{"Category":"童装"},{"Category":"箱包皮具"},{"Category":"美容美体"},{"Category":"育婴用品"},{"Category":"钟表饰品"}]
     */

    private String Code;
    private String Mess;
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

    public List<InfoBean> getInfo() {
        return Info;
    }

    public void setInfo(List<InfoBean> Info) {
        this.Info = Info;
    }

    public static class InfoBean {
        /**
         * Category : 五金耗材
         */

        private String Category;

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }
    }
}
