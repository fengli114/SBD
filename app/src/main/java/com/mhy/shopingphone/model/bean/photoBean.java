package com.mhy.shopingphone.model.bean;

import java.io.Serializable;

/**
 * Created by RedRainM on 2017/10/12.
 */

public class photoBean implements Serializable{
    /**
     * data : 上传文件成功
     * errorCode : 2000
     * json : http://smms.sbdznkj.com:2018/file/2018-06-04/6fcf96c5a0b94a68b613d5515a85665fyizhi_head_image.jpg
     */

    private String data;
    private int errorCode;
    private String json;

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

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }
}
