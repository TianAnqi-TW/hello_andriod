package com.thoughtworks.androidtrain.utils

import com.google.gson.Gson

object JSONUtil {
    fun <T> fromJson(json: String?, clazz: Class<T>?): T? {
        val gson = Gson()
        try {
            return gson.fromJson(json, clazz)
        } catch (e: Exception) {
        }
        return null
    }

    fun toJson(`object`: Any?): String? {
        val gson = Gson()
        try {
            return gson.toJson(`object`)
        } catch (e: Exception) {
        }
        return null
    }
}
