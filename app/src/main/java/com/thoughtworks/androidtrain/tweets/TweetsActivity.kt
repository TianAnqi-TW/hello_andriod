package com.thoughtworks.androidtrain.tweets

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.androidtrain.MyApplication
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.data.model.Tweet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TweetsActivity : AppCompatActivity() {

    private lateinit var tweetsList:List<Tweet>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)
        var scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            tweetsList = (application as MyApplication).tweetDataSource.fetchTweets() as List<Tweet>
            val filteredTweets = filterTweets(tweetsList)

            runOnUiThread {
                try{
                    val recyclerView = findViewById<RecyclerView>(R.id.tweets_layout)
                    (recyclerView.adapter as TweetsAdapter).updateData(filteredTweets)
                }catch (e:Exception) {
                    e.printStackTrace()
                }
            }
        }


        val adapter = TweetsAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.tweets_layout)
        recyclerView.layoutManager = LinearLayoutManager(this@TweetsActivity)
        recyclerView.adapter = adapter
        adapter.updateData(ArrayList())
    }

    private fun filterTweets(tweets: List<Tweet>): List<Tweet> {
        return tweets.filter { it.content?.contains("error", ignoreCase = true) != true }
    }
}