package com.thoughtworks.androidtrain.room.database

import android.os.Parcelable
import androidx.room.Database
import androidx.room.RoomDatabase
import com.thoughtworks.androidtrain.room.dao.CacheDao
import com.thoughtworks.androidtrain.room.entity.CacheEntity

@Database(entities = [CacheEntity::class], version = 1)
abstract class CacheDatabase : RoomDatabase(){
    abstract fun cacheDao(): CacheDao
}
