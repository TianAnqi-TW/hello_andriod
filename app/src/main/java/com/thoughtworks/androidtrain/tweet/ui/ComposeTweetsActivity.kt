package com.thoughtworks.androidtrain.tweet.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.thoughtworks.androidtrain.MyApplication
import com.thoughtworks.androidtrain.R
import com.thoughtworks.androidtrain.tweet.model.Tweet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import coil.compose.rememberImagePainter

class ComposeTweetsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val tweetsState = remember { mutableStateOf<List<Tweet>>(emptyList()) }

            LaunchedEffect(Unit) {
                // 在后台线程中获取推特列表数据
                var tweets = withContext(Dispatchers.IO) {
                    (MyApplication.instance).tweetDataSource.fetchTweets()
                }
                tweets = filterTweets(tweets as List<Tweet>)
                tweetsState.value = tweets
            }
            TweetsList(tweetsState.value)
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
        var showDialog by remember { mutableStateOf(false) }
        var isEditingComment by remember { mutableStateOf(false) }
        var editedComment by remember { mutableStateOf(tweet.content ?: "") }

        Row(modifier = Modifier.padding(16.dp)) {
            // 头像
            val painter = rememberImagePainter(
                data = tweet.sender?.avatar ?: "",
                builder = {
                    placeholder(R.drawable.avatar) // 默认头像资源
                }
            )
            // 头像
            Image(
                painter = painter,
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        showDialog = true
                    }
            )

            // 创建一个空间，以便在图片和文本之间有一些间隔
            Spacer(modifier = Modifier.width(16.dp))

            // 列布局，用于显示姓名标题和内容
            Column {
                // 姓名标题
                Text(
                    text = tweet.sender?.nick ?: "",
                    style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // 描述内容
                if (isEditingComment) {
                    TextField(
                        value = editedComment,
                        onValueChange = { editedComment = it },
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                        TextButton(onClick = { isEditingComment = false }) {
                            Text(text = "Cancel")
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextButton(onClick = {
                            // 保存编辑结果
                            isEditingComment = false
                            // 执行保存操作，更新原始 Tweet 对象的评论内容
                            tweet.content = editedComment
                        }) {
                            Text(text = "Save")
                        }
                    }
                } else {
                    Text(
                        text = tweet.content ?: "",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .clickable { isEditingComment = true } // 点击文本时切换到编辑模式
                    )
                }
                // 描述date
                Text(
                    text = tweet.date?: "",
                    style = TextStyle(fontSize = 16.sp),
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            // Dialog 展示大图
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text(text = "头像大图") },
                    confirmButton = {
                        Button(
                            onClick = { showDialog = false }
                        ) {
                            Text("关闭")
                        }
                    },
                    modifier = Modifier.wrapContentSize(),
                    text = {
                        Image(
                            painter = painter,
                            contentDescription = "Avatar",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.size(200.dp)
                        )
                    }
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


    private fun filterTweets(tweets: List<Tweet>): List<Tweet> {
        // 按日期降序排列
        val sortedTweets = tweets.sortedByDescending { it.date?.toLongOrNull() ?: 0 }
        return sortedTweets.filter { it.content?.contains("error", ignoreCase = true) != true }
    }


}