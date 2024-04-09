package com.thoughtworks.androidtrain.data.model

class Sender {
    var userName: String? = null
        private set
    var nick: String? = null
        private set
    var avatar: String? = null
        private set

    constructor()
    constructor(userName: String?, nick: String?, avatar: String?) {
        this.userName = userName
        this.nick = nick
        this.avatar = avatar
    }

    fun setUserName(userName: String?): Sender {
        this.userName = userName
        return this
    }

    fun setNick(nick: String?): Sender {
        this.nick = nick
        return this
    }

    fun setAvatar(avatar: String?): Sender {
        this.avatar = avatar
        return this
    }
}
