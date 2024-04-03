package com.thoughtworks.androidtrain.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tweets")
data class TweetEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val content: String,
    val error: String?,
    @SerializedName("unknown error")
    val unknownError: String?
)
