package com.thoughtworks.androidtrain.tweet.room.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.thoughtworks.androidtrain.tweet.room.dao.TweetDao
import com.thoughtworks.androidtrain.tweet.room.entity.TweetEntity

@Database(entities = [TweetEntity::class], version = 1)
abstract class TweetDataBase : RoomDatabase() {
    abstract fun TweetDao(): TweetDao
}