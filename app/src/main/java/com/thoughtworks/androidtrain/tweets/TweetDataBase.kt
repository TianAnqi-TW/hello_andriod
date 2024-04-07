package com.thoughtworks.androidtrain.tweets

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thoughtworks.androidtrain.room.dao.TweetDao
import com.thoughtworks.androidtrain.room.entity.TweetEntity

@Database(entities = [TweetEntity::class], version = 1)
abstract class TweetDataBase : RoomDatabase() {
    abstract fun TweetDao(): TweetDao
}