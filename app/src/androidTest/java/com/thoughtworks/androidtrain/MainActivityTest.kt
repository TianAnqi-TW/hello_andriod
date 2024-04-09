package com.thoughtworks.androidtrain

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testLoginButtonOpensLoginActivity() {
        // 点击登录按钮
        onView(withText("login")).perform(click())

        // 验证是否成功跳转到登录页面
        onView(withId(R.id.login_layout)).check(matches(isDisplayed()))
    }

    @Test
    fun testRememberMeCheckboxState() {
        // 点击登录按钮
        onView(withText("login")).perform(click())

        // 点击 Remember Me 复选框
        onView(withId(R.id.rememberMeCheckBox)).perform(click())

        // 验证 Remember Me 复选框是否被选中
        onView(withId(R.id.rememberMeCheckBox)).check(matches(isChecked()))
    }
}