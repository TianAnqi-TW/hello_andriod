package com.thoughtworks.androidtrain


import android.content.Intent
import android.os.Build
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import androidx.core.view.allViews
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows.shadowOf

@RunWith(RobolectricTestRunner::class)
class MainActivityTest {

    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java).create().visible().get()
    }

    @Test
    fun testLoginButtonOpensLoginActivity() {
        // 获取所有按钮
        val allButtons = activity.findViewById<ViewGroup>(android.R.id.content).allViews.filterIsInstance<Button>()

        // 根据按钮文本找到登录按钮
        val loginButton = allButtons.find { it.text == "login" }
        // 模拟点击登录按钮
        loginButton?.performClick()

        // 验证是否成功跳转到登录页面
        val expectedIntent = Intent(activity, LoginActivity::class.java)
        val actualIntent = shadowOf(RuntimeEnvironment.application).nextStartedActivity
        assertEquals(expectedIntent.component, actualIntent.component)
    }

    @Test
    fun testRememberMeCheckboxState() {

        // 创建 LoginActivity 实例
        val loginActivityController = Robolectric.buildActivity(LoginActivity::class.java).create().visible()
        val loginActivity = loginActivityController.get()
        // 获取 Remember Me 复选框
        val rememberMeCheckbox = loginActivity.findViewById<CheckBox>(R.id.rememberMeCheckBox)

        // 模拟点击 Remember Me 复选框
        rememberMeCheckbox.performClick()

        // 验证 Remember Me 复选框是否被选中
        assertTrue(rememberMeCheckbox.isChecked)
    }
}