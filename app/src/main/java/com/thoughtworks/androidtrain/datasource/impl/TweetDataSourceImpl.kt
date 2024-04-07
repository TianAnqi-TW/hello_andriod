package com.thoughtworks.androidtrain.datasource.impl

import com.thoughtworks.androidtrain.MyApplication
import com.thoughtworks.androidtrain.data.model.Tweet
import com.thoughtworks.androidtrain.datasource.TweetDataSource
import com.thoughtworks.androidtrain.utils.JSONUtil

class TweetDataSourceImpl(private val myApplication: MyApplication) : TweetDataSource {
    override fun fetchTweets(): List<Tweet>? {
        // 1. 从网络获取返回值
        val requestRes = myApplication.requestBase.fetchTweets()

        // 2. 如果网络请求失败，那么从缓存中取
        if (requestRes == null) {
            val value = myApplication.dataCache["FETCH_TWEETS"]
            val array = JSONUtil.fromJson(value, Array<Tweet>::class.java)
            return if (array != null) listOf(*array) else null
        }

        // 3. 网络请求成功，那么缓存本地数据
        val json =  JSONUtil.toJson(requestRes)
        if(json != null){
            myApplication.dataCache.put("FETCH_TWEETS", json)
        }
        return requestRes
    }
}
