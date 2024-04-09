package com.thoughtworks.androidtrain.tweet.datasource.impl

import com.thoughtworks.androidtrain.MyApplication
import com.thoughtworks.androidtrain.tweet.datasource.DataCache
import com.thoughtworks.androidtrain.tweet.room.entity.CacheEntity

class DataCacheImpl(private val myApplication: MyApplication) : DataCache {
    override fun put(key: String, value: String) {
        myApplication.cacheDatabase.cacheDao().set(CacheEntity(key, value))
    }

    override fun get(key: String): String? {
        val entity = myApplication.cacheDatabase.cacheDao().get(key)
        return entity?.value
    }
}
