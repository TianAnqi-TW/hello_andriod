package com.thoughtworks.androidtrain

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LanguageSelectionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.language_selection_layout)

        val btnAndroid = findViewById<Button>(R.id.btnAndroid)
        val btnJava = findViewById<Button>(R.id.btnJava)
        // 获取 Toolbar 控件
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)

        // 设置 ActionBar
        setSupportActionBar(toolbar)

        // 设置 Android 按钮点击事件
        btnAndroid.setOnClickListener {
            showAndroidFragment()
        }

        // 设置 Java 按钮点击事件
        btnJava.setOnClickListener {
            showJavaFragment()
        }

        // 默认显示 Android Fragment
        showAndroidFragment()
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_language_selection, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_android -> {
                showAndroidFragment()
                true
            }
            R.id.menu_java -> {
                showJavaFragment()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun showAndroidFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, AndroidFragment())
            .commit()
    }

    private fun showJavaFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentLayout, JavaFragment())
            .commit()
    }

}