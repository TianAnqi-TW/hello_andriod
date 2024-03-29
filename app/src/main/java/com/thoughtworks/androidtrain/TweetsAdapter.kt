package com.thoughtworks.androidtrain

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.thoughtworks.androidtrain.data.model.Tweet

class TweetsAdapter(private val tweets: List<Tweet>) :
    RecyclerView.Adapter<TweetsAdapter.TweetViewHolder>() {

    class TweetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TweetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tweet, parent, false)
        return TweetViewHolder(view)
    }

    override fun onBindViewHolder(holder: TweetViewHolder, position: Int) {
        val tweet = tweets[position]
        holder.itemView.apply {
            // 设置头像
//            avatarImageView.setImageResource(R.drawable.avatar)

            // 设置昵称
            val nickTextView = findViewById<TextView>(R.id.nickTextView)
            nickTextView.text = if (tweet.sender!= null) tweet.sender.nick else ""

            // 设置内容
            val contentTextView = findViewById<TextView>(R.id.contentTextView)
            contentTextView.text = if (tweet.content!= null) tweet.content else ""
        }
    }

    override fun getItemCount(): Int {
        return tweets.size
    }
}
