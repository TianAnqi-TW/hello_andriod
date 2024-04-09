package com.thoughtworks.androidtrain.data.model

class Comment(var content: String, var sender: Sender) {
    fun setContent(content: String): Comment {
        this.content = content
        return this
    }

    fun setSender(sender: Sender): Comment {
        this.sender = sender
        return this
    }
}
