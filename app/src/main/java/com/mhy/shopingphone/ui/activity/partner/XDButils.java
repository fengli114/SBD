package com.mhy.shopingphone.ui.activity.partner;


import com.mhy.shopingphone.model.bean.cy.CYPhoneEntity;
import com.mhy.shopingphone.model.bean.partner.BindEntity;
import com.mhy.shopingphone.model.bean.partner.PartnerLoginBean;
import com.mhy.shopingphone.ui.activity.tixian.BindUserEntity;
import com.youth.xframe.utils.log.XLog;

import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * 作者： "RedRainM" on 2017/11/14 0014.
 * 描述： 数据库操作类
 */

public class XDButils {
    /**
     * 保存用户信息
     */
//    public static void saveUserInfo(UserEntity loginEntity) {
//        DbManager db = DbUtils.getDB();
//        try {
////            UserInfo entity = new UserInfo();
////            entity.setId("1");
////            entity.setUserid(loginEntity.getUser_id());
////            entity.setAccount(loginEntity.getAccount());
////            entity.setNickname(loginEntity.getNickname());
////            entity.setBalance(loginEntity.getBalance());
////            entity.setToken(loginEntity.getToken());
////            entity.setIdcard(loginEntity.getIdcard());
////            entity.setPhone(loginEntity.getPhone());
////            entity.setReal_name(loginEntity.getReal_name());
////            entity.setIcon(loginEntity.getIcon());
//            db.saveOrUpdate(loginEntity);
//        } catch (DbException e) {
//            e.printStackTrace();
//            XLog.e("login#" + e.toString());
//        }
//    }

    /**
     * 获得用户信息
     */
//    public static UserEntity getUserInfo() {
//        DbManager db = DbUtils.getDB();
//        try {
//            UserEntity info = db.findById(UserEntity.class, 1);
//            return info;
//        } catch (DbException e) {
//            e.printStackTrace();
//            XLog.e("login#" + e.toString());
//            return null;
//        }
//    }

    /**
     * 保存合伙人用户信息
     */
    public static void saveBindUserInfo(PartnerLoginBean.PayBean entity) {
        DbManager db = DbUtils.getDB();


        try {
            BindEntity bindEntity = new BindEntity();
            if (entity != null) {
                bindEntity.setId("1");
//            entity.setID(bindEntity.getID());
//            entity.setUserID(bindEntity.getUserID());
                bindEntity.setBankName(entity.getBankName());
                bindEntity.setMobile(entity.getMobile());
                bindEntity.setName(entity.getName());
                bindEntity.setAliAccount(entity.getAliAccount());
                bindEntity.setBankNo(entity.getBankNo());
            }
            db.saveOrUpdate(bindEntity);
        } catch (DbException e) {
            e.printStackTrace();
            XLog.e("login#" + e.toString());
        }
    }

    /**
     * 保存合伙人用户信息
     */
    public static void saveBindUserInfo2(BindUserEntity.InfoBean entity) {
        DbManager db = DbUtils.getDB();
        try {
            BindEntity bindEntity = new BindEntity();
            if (entity != null) {
                bindEntity.setId("1");

//            entity.setID(bindEntity.getID());
//            entity.setUserID(bindEntity.getUserID());
                    bindEntity.setName(entity.getName());
                    bindEntity.setMobile(entity.getMobile());
                    bindEntity.setAliAccount(entity.getAliAccount());
                    bindEntity.setBankName(entity.getBankName());
                    bindEntity.setBankNo(entity.getBankNo());

            }
            db.saveOrUpdate(bindEntity);
        } catch (DbException e) {
            e.printStackTrace();
            XLog.e("login#" + e.toString());
        }
    }

    /**
     * 保存常用联系人
     */
    public static void saveCYUserInfo(CYPhoneEntity cyPhoneEntity) {
        DbManager db = DbUtils.getDB();
        try {
            db.save(cyPhoneEntity);
        } catch (DbException e) {
            e.printStackTrace();
            XLog.e("login#" + e.toString());
        }
    }
    /**
     * 获得常用联系人
     */
    public static List<CYPhoneEntity> getCYPhoneInfo() {
        DbManager db = DbUtils.getDB();
        try {
            List<CYPhoneEntity> info = db.findAll(CYPhoneEntity.class);
            return info;
        } catch (DbException e) {
            e.printStackTrace();
            XLog.e("login#" + e.toString());
            return null;
        }
    }
    /**
     * 获得常用联系人
     */
    public static CYPhoneEntity getCYPhoneInfo2(String id) {
        DbManager db = DbUtils.getDB();
        try {
            CYPhoneEntity info = db.findById(CYPhoneEntity.class,id);
            return info;
        } catch (DbException e) {
            e.printStackTrace();
            XLog.e("login#" + e.toString());
            return null;
        }
    }
    /**
     * 删除常用联系人
     */
    public static void dlCYPhoneInfo(String name) {
        DbManager db = DbUtils.getDB();
        try {
            CYPhoneEntity person = db.selector(CYPhoneEntity.class).where("name", "=", name).findFirst();
            db.delete(person);
        } catch (DbException e) {
            e.printStackTrace();
            XLog.e("login#" + e.toString());
        }
    }
    /**
     * 获得用户信息
     */
    public static BindEntity getBindUserInfo() {
        DbManager db = DbUtils.getDB();
        try {
            BindEntity info = db.findById(BindEntity.class, 1);
            return info;
        } catch (DbException e) {
            e.printStackTrace();
            XLog.e("login#" + e.toString());
            return null;
        }
    }
}
