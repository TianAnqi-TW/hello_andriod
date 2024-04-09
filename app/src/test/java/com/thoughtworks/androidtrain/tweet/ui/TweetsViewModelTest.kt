package com.thoughtworks.androidtrain.tweet.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.thoughtworks.androidtrain.tweet.model.Tweet
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class TweetsViewModelTest {
    // 使用 InstantTaskExecutorRule 来确保 LiveData 的 postValue() 和 setValue() 可以在单元测试中立即执行
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    // 模拟 Observer
    @Mock
    lateinit var observer: Observer<List<Tweet>>

    // 要测试的 ViewModel
    private lateinit var viewModel: TweetsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = TweetsViewModel()
        viewModel.tweets.observeForever(observer)

    }

    @Test
    fun `test LiveData emits correct list of tweets`() {
        // 设置预期的数据
        val expectedTweets = listOf(Tweet())

        // 设置 LiveData 的值
        viewModel.tweets.value = expectedTweets

        // 验证 Observer 是否收到了正确的数据
        verify(observer).onChanged(expectedTweets)
    }
}
