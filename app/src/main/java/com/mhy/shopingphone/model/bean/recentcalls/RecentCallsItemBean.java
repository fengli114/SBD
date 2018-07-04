package com.mhy.shopingphone.model.bean.recentcalls;

/**
 * 作者： "RedRainM" on 2017/12/13 0013.
 * 描述：  通訊錄實體類
 */

public class RecentCallsItemBean {
    private String phone;
    private String name;
    private String data;
    private int id;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
