package com.mhy.shopingphone.model.bean.cy;

import org.xutils.db.annotation.Column;
import org.xutils.db.annotation.Table;

/**
 * 作者： "RedRainM" on 2017/11/30 0030.
 * 描述： 常用联系人
 */
@Table(name="CYPhoneEntity")
public class CYPhoneEntity {
    @Column(
            name = "id",
            isId = true,
            autoGen = false
    )
    private String id;

    @Column(name = "phone")
    private String phone;

    @Column(name = "name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
}
