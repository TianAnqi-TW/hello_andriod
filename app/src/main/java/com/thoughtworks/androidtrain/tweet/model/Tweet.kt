package com.thoughtworks.androidtrain.tweet.model

import com.google.gson.annotations.SerializedName

data class Tweet(
    var content: String? = null,
    var images: List<Image>? = null,
    var sender: Sender? = null,
    var comments: List<Comment>? = null,
    var error: String? = null,

    @SerializedName("unknown error")
    var unknownError: String? = null,
    var date: String? = null
) {
    override fun toString(): String {
        return "content $content \n"
    }
}