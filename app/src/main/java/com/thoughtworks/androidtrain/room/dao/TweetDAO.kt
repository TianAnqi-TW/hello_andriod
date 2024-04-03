package com.thoughtworks.androidtrain.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.thoughtworks.androidtrain.data.model.Tweet
import com.thoughtworks.androidtrain.room.entity.CommentEntity
import com.thoughtworks.androidtrain.room.entity.ImageEntity
import com.thoughtworks.androidtrain.room.entity.SenderEntity
import com.thoughtworks.androidtrain.room.entity.TweetEntity

@Dao
interface TweetDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTweet(tweetEntity: TweetEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertImage(imageEntity: ImageEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSender(senderEntity: SenderEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertComment(commentEntity: CommentEntity)

}
