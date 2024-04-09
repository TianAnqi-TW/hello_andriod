package com.thoughtworks.androidtrain.tweet.model

class Image(var url: String) {
    fun setUrl(url: String): Image {
        this.url = url
        return this
    }
}
