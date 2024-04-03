package com.thoughtworks.androidtrain.room.entity


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "comments")
data class CommentEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    val tweetId: Long,
    val content: String?,
    val senderId: Long
)
