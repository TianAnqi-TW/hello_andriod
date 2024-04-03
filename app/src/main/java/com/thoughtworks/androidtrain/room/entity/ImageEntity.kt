package com.thoughtworks.androidtrain.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "images")
data class ImageEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val tweetId: String,
    val url: String?
)
