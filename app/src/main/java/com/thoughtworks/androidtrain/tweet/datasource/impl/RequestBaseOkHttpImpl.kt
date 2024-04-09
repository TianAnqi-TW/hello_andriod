package com.thoughtworks.androidtrain.tweet.datasource.impl

import com.thoughtworks.androidtrain.MyApplication
import com.thoughtworks.androidtrain.tweet.model.Tweet
import com.thoughtworks.androidtrain.tweet.datasource.RequestBase
import com.thoughtworks.androidtrain.utils.JSONUtil
import okhttp3.OkHttpClient
import okhttp3.Request
import okio.IOException
import java.lang.Exception

class RequestBaseOkHttpImpl
    (private val myApplication: MyApplication) : RequestBase {
    override fun fetchTweets(): List<Tweet>? {
        //todo  从myappcalition中拿到本接口的的url，以常量形式获取
        // 然后调用okhttp获取返回值
        val client = OkHttpClient()
        try {
            val request = Request.Builder()
                .url("https://raw.githubusercontent.com/TW-Android-Junior-Training/android_training_practice/main/json/tweets.json")
                .build()

            val response = client.newCall(request).execute()
            if (!response.isSuccessful) return null

            val responseBody = response.body!!.string()
            val res = JSONUtil.fromJson(responseBody, Array<Tweet>::class.java)
            if (res != null) {
                val tweetListRes = ArrayList<Tweet>()
                tweetListRes.addAll(res)
                return tweetListRes
            }
        }catch (e:Exception){
            e.printStackTrace()
        }

        return null
    }
}
