package com.thoughtworks.androidtrain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import coil.load
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.androidtrain.data.model.Tweet

class TweetsAdapter(private val tweets: List<Tweet>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>()  {

    private val TWEET_VIEW_TYPE = 0
    private val BOTTOM_VIEW_TYPE = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TWEET_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet, parent, false)
                TweetViewHolder(view)
            }
            BOTTOM_VIEW_TYPE -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_bottom, parent, false)
                BottomViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is TweetViewHolder) {
            val tweet = tweets[position]
            holder.itemView.apply {

                // 设置昵称
                val nickTextView = findViewById<TextView>(R.id.nickTextView)
                nickTextView.text = if (tweet.sender != null) tweet.sender.nick else ""

                // 设置内容
                val contentTextView = findViewById<TextView>(R.id.contentTextView)
                contentTextView.text = if (tweet.content != null) tweet.content else ""

                // 设置头像
                val avatarImageView = findViewById<ImageView>(R.id.avatarImageView)
                tweet.sender?.avatar?.let { avatarUrl ->
                    avatarImageView.load(avatarUrl)
                }
            }
        } else if (holder is BottomViewHolder) {
            holder.itemView.apply {
                // 设置底部文本
                val bottomTextView = findViewById<TextView>(R.id.bottomTextView)
                bottomTextView.text = "到底了"
            }
        }
    }

    override fun getItemCount(): Int {
        // 多返回一个位置用于显示底部项
        return tweets.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        // 最后一个位置显示底部项
        return if (position == tweets.size) BOTTOM_VIEW_TYPE else TWEET_VIEW_TYPE
    }

    inner class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    inner class BottomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
