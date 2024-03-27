package com.thoughtworks.androidtrain

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val buttonContainer = findViewById<LinearLayout>(R.id.buttonContainer)

        for (i in 1..10) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            if (i == 1) {
                button.text = "constraint_layout"
                // 为按钮添加点击事件处理程序，启动 RelativeActivity
                button.setOnClickListener {
                    val intent = Intent(this, ConstraintActivity::class.java)
                    startActivity(intent)
                }
            } else {
                // 其他按钮显示默认的文本
                button.text = getString(R.string.button_text, i)
            }
            buttonContainer.addView(button)
        }
    }
}