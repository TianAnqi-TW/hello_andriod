package com.thoughtworks.androidtrain.tweet.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.thoughtworks.androidtrain.MyApplication
import com.thoughtworks.androidtrain.tweet.model.Tweet
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TweetsViewModel : ViewModel() {

    private val _tweets = MutableLiveData<List<Tweet>>()
    val tweets: LiveData<List<Tweet>> = _tweets

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    fun fetchTweets() {
        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
            try {
                val tweetsList = (MyApplication.instance).tweetDataSource.fetchTweets() as List<Tweet>
                val filteredTweets = filterTweets(tweetsList)
                _tweets.postValue(filteredTweets)
            } catch (e: Exception) {
                _errorMessage.postValue("Failed to fetch tweets")
            }
        }
    }

    private fun filterTweets(tweets: List<Tweet>): List<Tweet> {
        // 按日期降序排列
        val sortedTweets = tweets.sortedByDescending { it.date?.toLongOrNull() ?: 0 }
        return sortedTweets.filter { it.content?.contains("error", ignoreCase = true) != true }
    }
}