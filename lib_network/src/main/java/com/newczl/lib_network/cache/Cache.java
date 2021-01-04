package com.newczl.lib_network.cache;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
@Entity(tableName = "cache")
public class Cache implements Serializable {
    @NotNull
    @PrimaryKey
    public String key;

    @ColumnInfo(name = "data")
    public byte[] data;
}
