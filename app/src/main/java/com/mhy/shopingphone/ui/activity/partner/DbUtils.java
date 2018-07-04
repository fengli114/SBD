package com.mhy.shopingphone.ui.activity.partner;


import com.mhy.sdk.utils.FileUtils;

import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.DbManager.DbUpgradeListener;
import org.xutils.x;

public class DbUtils {

    static DbManager.DaoConfig daoConfig;

    public static DaoConfig getDaoConfig() {
        if (daoConfig == null) {
            daoConfig = new DbManager.DaoConfig()
                    .setDbName("newman.db")
                    .setDbDir(FileUtils.getDirRoot())
                    .setDbVersion(4)
                    .setAllowTransaction(true)
                    .setDbOpenListener(new DbManager.DbOpenListener() {
                        @Override
                        public void onDbOpened(DbManager db) {
                            // 开启WAL, 对写入加速提升巨大
                            db.getDatabase().enableWriteAheadLogging();
                        }
                    })
                    .setDbUpgradeListener(new DbUpgradeListener() {
                        @Override
                        public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        }
                    });
        }
        return daoConfig;
    }

    public static DbManager getDB() {
        return x.getDb(getDaoConfig());
    }
}
