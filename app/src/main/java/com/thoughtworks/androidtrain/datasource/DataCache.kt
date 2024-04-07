package com.thoughtworks.androidtrain.datasource

interface DataCache {
    fun put(key: String, value: String)
    operator fun get(key: String): String?
}
