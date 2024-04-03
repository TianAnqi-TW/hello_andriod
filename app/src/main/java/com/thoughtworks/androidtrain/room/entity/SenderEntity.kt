package com.thoughtworks.androidtrain.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "senders")
data class SenderEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userName: String,
    val nick: String,
    val avatar: String
)

