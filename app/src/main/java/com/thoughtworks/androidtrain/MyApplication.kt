package com.thoughtworks.androidtrain

import android.app.Application
import androidx.room.Room
import com.thoughtworks.androidtrain.tweet.datasource.DataCache
import com.thoughtworks.androidtrain.tweet.datasource.RequestBase
import com.thoughtworks.androidtrain.tweet.datasource.TweetDataSource
import com.thoughtworks.androidtrain.tweet.datasource.impl.DataCacheImpl
import com.thoughtworks.androidtrain.tweet.datasource.impl.RequestBaseRetrofitImpl
import com.thoughtworks.androidtrain.tweet.datasource.impl.TweetDataSourceImpl
import com.thoughtworks.androidtrain.tweet.room.database.CacheDatabase

class MyApplication: Application() {
    lateinit var cacheDatabase: CacheDatabase
    var tweetDataSource: TweetDataSource = TweetDataSourceImpl(this)
    var dataCache: DataCache = DataCacheImpl(this)
    var requestBase: RequestBase = RequestBaseRetrofitImpl(this)

    companion object {
        lateinit var instance: MyApplication
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this

        cacheDatabase = Room.databaseBuilder(
            applicationContext,
            CacheDatabase::class.java, "database-cache"
        ).build()
    }
}