package com.thoughtworks.androidtrain.tweet.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.androidtrain.R
import android.widget.Toast
import androidx.activity.viewModels

class TweetsActivity : AppCompatActivity() {

    private val viewModel: TweetsViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tweets_layout)

        val recyclerView = findViewById<RecyclerView>(R.id.tweets_layout)
        recyclerView.layoutManager = LinearLayoutManager(this@TweetsActivity)

        val adapter = TweetsAdapter()
        recyclerView.adapter = adapter

        viewModel.fetchTweets()

        viewModel.tweets.observe(this) { tweets ->
            adapter.updateData(tweets)
        }

        viewModel.errorMessage.observe(this) { errorMessage ->
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

}