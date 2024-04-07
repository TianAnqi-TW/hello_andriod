package com.thoughtworks.androidtrain.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.thoughtworks.androidtrain.room.entity.TweetEntity

@Dao
interface TweetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweet(tweetEntity: TweetEntity)

    @Query("SELECT * FROM tweets")
    fun getTweet():List<TweetEntity>

    @Delete
    fun delete(tweetEntity: TweetEntity)

}
