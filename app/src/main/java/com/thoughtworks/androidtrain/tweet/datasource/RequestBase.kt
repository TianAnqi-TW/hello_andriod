package com.thoughtworks.androidtrain.tweet.datasource

import com.thoughtworks.androidtrain.tweet.model.Tweet

interface RequestBase {
    fun fetchTweets(): List<Tweet>?
}
