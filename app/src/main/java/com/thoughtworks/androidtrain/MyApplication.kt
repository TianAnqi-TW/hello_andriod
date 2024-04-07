package com.thoughtworks.androidtrain

import android.app.Application
import androidx.room.Room
import com.thoughtworks.androidtrain.datasource.DataCache
import com.thoughtworks.androidtrain.datasource.RequestBase
import com.thoughtworks.androidtrain.datasource.TweetDataSource
import com.thoughtworks.androidtrain.datasource.impl.DataCacheImpl
import com.thoughtworks.androidtrain.datasource.impl.RequestBaseFileImpl
import com.thoughtworks.androidtrain.datasource.impl.TweetDataSourceImpl
import com.thoughtworks.androidtrain.room.database.CacheDatabase

class MyApplication: Application() {
    lateinit var cacheDatabase: CacheDatabase
    var tweetDataSource: TweetDataSource = TweetDataSourceImpl(this)
    var dataCache: DataCache = DataCacheImpl(this)
    var requestBase: RequestBase = RequestBaseFileImpl(this)
    override fun onCreate() {
        super.onCreate()

        cacheDatabase = Room.databaseBuilder(
            applicationContext,
            CacheDatabase::class.java, "database-cache"
        ).build()
//        var t = Thread{
//            val cacheDao = cacheDatabase.cacheDao()
//
//            val cacheEntity = CacheEntity("TEST", "VALUE"+System.currentTimeMillis())
//            System.out.println("122222222")
//            cacheDao.set(cacheEntity)
//            System.out.println(cacheDao.get("TEST"))
//        }
//        t.start()
    }
}