package com.thoughtworks.androidtrain.tweet.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "senders")
data class SenderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val userName: String?,
    val nick: String?,
    val avatar: String?
)

