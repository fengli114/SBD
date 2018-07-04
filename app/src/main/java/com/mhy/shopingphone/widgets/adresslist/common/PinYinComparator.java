package com.mhy.shopingphone.widgets.adresslist.common;


import com.mhy.shopingphone.model.bean.GroupMemberBean;
import com.mhy.shopingphone.widgets.adresslist.bean.Friend;

import java.util.Comparator;

/**
 * Created by LinJinFeng on 2016/9/29.
 */

public class PinYinComparator implements Comparator<GroupMemberBean> {

    @Override
    public int compare(GroupMemberBean o1, GroupMemberBean o2) {
        if (o1.getPinyin().equals("#")){
            return 1;
        }else if (o2.getPinyin().equals("#")){
            return -1;
        }
        return o1.getPinyin().compareToIgnoreCase(o2.getPinyin());
    }
}
