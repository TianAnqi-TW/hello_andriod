package com.thoughtworks.androidtrain.tweet.datasource.impl

import com.thoughtworks.androidtrain.MyApplication
import com.thoughtworks.androidtrain.tweet.model.Tweet
import com.thoughtworks.androidtrain.tweet.datasource.RequestBase
import com.thoughtworks.androidtrain.utils.JSONUtil
import kotlin.text.Charsets.UTF_8

class RequestBaseFileImpl(private val myApplication: MyApplication) : RequestBase {
    override fun fetchTweets(): List<Tweet>? {
        try {
            myApplication.applicationContext.assets.open("tweets.json").use { `in` ->
                val size = `in`.available()
                val buffer = ByteArray(size)
                `in`.read(buffer)
                val array =
                    JSONUtil.fromJson(String(buffer, UTF_8), Array<Tweet>::class.java)
                return if (array != null) listOf(*array) else null
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}
