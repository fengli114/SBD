package com.mhy.shopingphone.model.bean.indexbar;

/**
 * Created by zhangxutong .
 * Date: 16/08/28
 */

public class PhoneNameBean extends BaseIndexPinyinBean {

    private String phoneName;//城市名字

    public PhoneNameBean() {
    }
    public PhoneNameBean(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    @Override
    public String getTarget() {
        return phoneName;
    }
}
