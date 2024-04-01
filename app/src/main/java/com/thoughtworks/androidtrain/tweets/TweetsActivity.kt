package com.thoughtworks.androidtrain.tweets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.data.model.Tweet
import java.io.IOException

class TweetsActivity : AppCompatActivity() {

    private val tweetsList = ArrayList<Tweet>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)


        loadTweetsFromJson()

        val filteredTweets = filterTweets(tweetsList)
        val adapter = TweetsAdapter(filteredTweets)
        val recyclerView = findViewById<RecyclerView>(R.id.tweets_layout)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    private fun loadTweetsFromJson() {
        try {
            val inputStream = assets.open("tweets.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            val json = String(buffer, Charsets.UTF_8)

            val gson = Gson()
            val tweets = gson.fromJson(json, Array<Tweet>::class.java)

            tweetsList.addAll(tweets)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    private fun filterTweets(tweets: List<Tweet>): List<Tweet> {
        return tweets.filter { it.content?.contains("error", ignoreCase = true) != true }
    }
}