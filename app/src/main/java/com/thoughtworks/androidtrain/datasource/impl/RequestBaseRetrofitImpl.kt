package com.thoughtworks.androidtrain.datasource.impl

import com.thoughtworks.androidtrain.MyApplication
import com.thoughtworks.androidtrain.data.model.Tweet
import com.thoughtworks.androidtrain.datasource.RequestBase
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.lang.Exception


class RequestBaseRetrofitImpl (private val myApplication: MyApplication?) : RequestBase {
    override fun fetchTweets(): List<Tweet>? {

        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(DataService::class.java)
        try {
           val callRes =  service.fetchTweets()
            val response = callRes.execute()
            return response.body()
        }catch (e: Exception){
            e.printStackTrace()
        }
        return null
    }

    interface DataService {
        @GET("TW-Android-Junior-Training/android_training_practice/main/json/tweets.json")
        fun fetchTweets(): Call<List<Tweet>>
    }

}