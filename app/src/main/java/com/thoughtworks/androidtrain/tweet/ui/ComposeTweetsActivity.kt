package com.thoughtworks.androidtrain.tweet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.tweet.model.Tweet

class ComposeTweetsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            TweetsList()
        }
    }

    @Composable
    fun TweetsList(tweets: List<Tweet> = emptyList()) {
        LazyColumn {
            items(tweets) { tweet ->
                TweetItem(tweet)
            }
            item {
                Box(
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                        .background(color = Color.LightGray)
                ) {
                    Text(
                        text = "到底了",
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

    @Composable
    fun TweetItem(tweet: Tweet) {
        Row(modifier = Modifier.padding(16.dp)) {
            // 头像
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
            )

            // 创建一个空间，以便在图片和文本之间有一些间隔
            Spacer(modifier = Modifier.width(16.dp))

            // 列布局，用于显示姓名标题和内容
            Column {
                // 姓名标题
                Text(
                    text = tweet.sender?.nick ?: "联系人111111111111111111111",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // 描述内容
                Text(
                    text = tweet.content ?: "内容11111111111111111111111111111111111111111111111",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        val tweets = listOf(Tweet())
        TweetsList(tweets)
    }


}