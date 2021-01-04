package com.newczl.lib_network.cache;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface CacheDao {
    //保存
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long save(Cache cache);

    @Query("Select * from cache where `key` = :key")
    Cache getCache(String key);

    @Delete
    int delete(Cache cache);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int update(Cache cache);
}
