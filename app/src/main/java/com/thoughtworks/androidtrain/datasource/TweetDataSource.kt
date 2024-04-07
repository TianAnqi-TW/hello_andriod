package com.thoughtworks.androidtrain.datasource

import com.thoughtworks.androidtrain.data.model.Tweet

interface TweetDataSource {
    fun fetchTweets(): List<Tweet?>?
}
