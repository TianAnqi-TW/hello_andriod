package com.thoughtworks.androidtrain.room.daoImpl

import com.thoughtworks.androidtrain.room.dao.CacheDao
import com.thoughtworks.androidtrain.room.entity.CacheEntity

class CacheDaoImpl(private val cacheDao: CacheDao) {
    fun get(key: String): CacheEntity? {
        return cacheDao.get(key)
    }

    fun set(key: String, value: String) {
        val cacheEntity = CacheEntity(key, value)
        cacheDao.set(cacheEntity)
    }
}