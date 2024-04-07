package com.thoughtworks.androidtrain.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cache")
data class CacheEntity(
    //存 json
    @PrimaryKey val key: String,
    val value: String
)