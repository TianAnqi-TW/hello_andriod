package com.thoughtworks.androidtrain.datasource

import com.thoughtworks.androidtrain.data.model.Tweet

interface RequestBase {
    fun fetchTweets(): List<Tweet>?
}
