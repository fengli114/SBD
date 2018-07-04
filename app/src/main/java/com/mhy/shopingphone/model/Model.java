package com.mhy.shopingphone.model;

/**
 * Created by YH on 2016/10/17.
 */

public class Model {
    public String name;
    public String iconRes;



    public Model(String name, String iconRes) {
        this.name = name;
        this.iconRes = iconRes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconRes() {
        return iconRes;
    }

    public void setIconRes(String iconRes) {
        this.iconRes = iconRes;
    }
    @Override
    public String toString() {
        return "Model{" +
                "name='" + name + '\'' +
                ", iconRes=" + iconRes +
                '}';
    }
}
