package com.thoughtworks.androidtrain

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SharedPreferenceActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // 初始化 SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        // 从 SharedPreferences 中读取 isHintShown 数据，默认为 false
        val isHintShown = sharedPreferences.getBoolean("isHintShown", false)

        // 根据 isHintShown 的值设置布局的可见性
        val leftLayout = findViewById<View>(R.id.leftLayout)
        val rightLayout = findViewById<View>(R.id.rightLayout)
        if (isHintShown) {
            leftLayout.visibility = View.GONE
            rightLayout.visibility = View.VISIBLE
        } else {
            leftLayout.visibility = View.VISIBLE
            rightLayout.visibility = View.GONE
        }

        // 设置左侧布局中按钮的点击事件
        val knowButton = findViewById<Button>(R.id.knowButton)
        knowButton.setOnClickListener {
            // 将 isHintShown 的值设置为 true，并保存到 SharedPreferences 中
            sharedPreferences.edit().putBoolean("isHintShown", true).apply()

            // 更新布局可见性
            leftLayout.visibility = View.GONE
            rightLayout.visibility = View.VISIBLE
        }
    }
}