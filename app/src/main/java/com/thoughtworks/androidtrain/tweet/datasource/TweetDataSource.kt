package com.thoughtworks.androidtrain.tweet.datasource

import com.thoughtworks.androidtrain.tweet.model.Tweet

interface TweetDataSource {
    fun fetchTweets(): List<Tweet?>?
}
