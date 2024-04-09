package com.thoughtworks.androidtrain.tweet.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thoughtworks.androidtrain.tweet.room.dao.CacheDao
import com.thoughtworks.androidtrain.tweet.room.entity.CacheEntity

@Database(entities = [CacheEntity::class], version = 1)
abstract class CacheDatabase : RoomDatabase(){
    abstract fun cacheDao(): CacheDao
}
