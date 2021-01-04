package com.newczl.lib_network.cache;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.newczl.lib_common.AppGlobals;

@Database(entities = {Cache.class},version = 1,exportSchema = true)
public abstract class CacheDatabase extends RoomDatabase {
    private static final CacheDatabase database;

    static {
        //创建内存数据库，只存在内存中，进程被杀就丢失
        database = Room.databaseBuilder(AppGlobals.INSTANCE.getSApplication(), CacheDatabase.class, "ccjoke_cache")
                //是否允许在主线程进行查询
                .allowMainThreadQueries()
                //数据库的总生命周期
                //.addCallback()
                //设置查询线程池
                //.setQueryExecutor()
                //设置开启数据库的帮助工厂类
                //.openHelperFactory()
                //日志模式
//                .setJournalMode()
                //数据库升级异常的回滚
//                .fallbackToDestructiveMigration()
                //数据库升级异常通过版本来回滚
//                .fallbackToDestructiveMigrationFrom()
                //升级入口
//                .addMigrations(CacheDatabase.sMigration)
                .build();

    }



    static Migration sMigration = new Migration(1,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            //数据库的所有操作
            //database.execSQL();
        }
    };


    public abstract CacheDao getCache();

    public static CacheDatabase get() {
        return database;
    }
}
