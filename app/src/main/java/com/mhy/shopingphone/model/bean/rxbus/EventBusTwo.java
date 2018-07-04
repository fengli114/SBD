package com.mhy.shopingphone.model.bean.rxbus;

/**
 * Created by Administrator on 2017/9/30.
 */

public class EventBusTwo {
    private boolean istue;

    private boolean isflag;

    private boolean isVisable;

    private String usl;

    public String getUsl() {
        return usl;
    }

    public EventBusTwo setUsl(String usl) {
        this.usl = usl;
        return this;
    }

    public EventBusTwo(boolean isVisable) {
        this.isVisable = isVisable;
    }

    public EventBusTwo() {
    }

    public boolean isVisable() {
        return isVisable;
    }

    public EventBusTwo setVisable(boolean visable) {
        this.isVisable = visable;
        return this;
    }

    public boolean isflag() {
        return isflag;
    }

    public EventBusTwo setIsflag(boolean isflag) {
        this.isflag = isflag;
        return this;
    }

    public boolean istrue() {
        return istue;
    }

    public EventBusTwo setIstue(boolean istrue) {
        this.istue = istrue;
        return this;
    }
}
