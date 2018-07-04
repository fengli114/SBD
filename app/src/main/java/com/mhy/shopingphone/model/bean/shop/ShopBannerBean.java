package com.mhy.shopingphone.model.bean.shop;

import java.io.Serializable;
import java.util.List;

/**
 * Created by RedRainM on 2017/10/13.
 */

public class ShopBannerBean implements Serializable {

    /**
     * Code : 0
     * Mess : Success
     * Info : [{"ID":"55c31764-2937-48d1-9fbf-15e34d027721","Text":"","Pic":"/Images/36178120171010948468382d5ab283-1fbe-4bde-90dc-ab99d4aa868e.png","DataStatus":true,"Type":1},{"ID":"01632fff-304c-49b9-8d20-eaa5fea8d3e8","Text":"sdfasfsadfasfdasfsadf","Pic":"","DataStatus":true,"Type":2},{"ID":"0a8f999b-80f9-4202-97e2-ea66e9c3822f","Text":"","Pic":"/Images/76837320171012104651630490ead62-f7c6-48fa-a0c4-008da732f897.jpg","DataStatus":true,"Type":1}]
     * CInfo : [{"Category":"家居日用","SalesVolume":13309129},{"Category":"女装","SalesVolume":8387038},{"Category":"医疗保健","SalesVolume":5228793},{"Category":"数码周边","SalesVolume":4609400},{"Category":"美容美体","SalesVolume":4219620},{"Category":"化妆品","SalesVolume":3460704},{"Category":"瓜果熟食","SalesVolume":3016162},{"Category":"钟表饰品","SalesVolume":2783493}]
     */

    private String Code;
    private String Mess;
    private List<InfoBean> Info;
    private List<CInfoBean> CInfo;

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

    public List<CInfoBean> getCInfo() {
        return CInfo;
    }

    public void setCInfo(List<CInfoBean> CInfo) {
        this.CInfo = CInfo;
    }

    public static class InfoBean{
        /**
         * ID : 55c31764-2937-48d1-9fbf-15e34d027721
         * Text :
         * Pic : /Images/36178120171010948468382d5ab283-1fbe-4bde-90dc-ab99d4aa868e.png
         * DataStatus : true
         * Type : 1
         */

        private String ID;
        private String Text;
        private String Pic;
        private boolean DataStatus;
        private int Type;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getText() {
            return Text;
        }

        public void setText(String Text) {
            this.Text = Text;
        }

        public String getPic() {
            return Pic;
        }

        public void setPic(String Pic) {
            this.Pic = Pic;
        }

        public boolean isDataStatus() {
            return DataStatus;
        }

        public void setDataStatus(boolean DataStatus) {
            this.DataStatus = DataStatus;
        }

        public int getType() {
            return Type;
        }

        public void setType(int Type) {
            this.Type = Type;
        }
    }

    public static class CInfoBean {
        /**
         * Category : 家居日用
         * SalesVolume : 13309129
         */

        private String Category;
        private int SalesVolume;

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public int getSalesVolume() {
            return SalesVolume;
        }

        public void setSalesVolume(int SalesVolume) {
            this.SalesVolume = SalesVolume;
        }
    }
}
