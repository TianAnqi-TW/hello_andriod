package com.thoughtworks.androidtrain.data.model

class Image(var url: String) {
    fun setUrl(url: String): Image {
        this.url = url
        return this
    }
}
