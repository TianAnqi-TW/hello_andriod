package com.thoughtworks.androidtrain

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LanguageSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_selection_layout)

        val btnAndroid = findViewById<Button>(R.id.btnAndroid)

        // 设置 Android 按钮点击事件
        btnAndroid.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentLayout, AndroidFragment())
                .commit()
        }
        // 默认显示 Android Fragment
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, AndroidFragment())
            .commit()
    }
}