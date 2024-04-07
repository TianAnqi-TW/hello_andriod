package com.thoughtworks.androidtrain

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.thoughtworks.androidtrain.room.database.CacheDatabase
import com.thoughtworks.androidtrain.room.entity.CacheEntity
import com.thoughtworks.androidtrain.tweets.TweetDataBase
import com.thoughtworks.androidtrain.tweets.TweetsActivity

class MainActivity : AppCompatActivity() {
    private val pickContactLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val name = data?.getStringExtra("name")
            val phoneNumber = data?.getStringExtra("phoneNumber")
            if (!name.isNullOrEmpty() && !phoneNumber.isNullOrEmpty()) {
//                val toastMessage = "Selected contact:\nName: $name\nPhone: $phoneNumber"
//                Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
                // Show dialog
                showDialog(name, phoneNumber)
            }
        }
    }
    @SuppressLint("SetTextI18n")
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
            when (i) {
                1 -> {
                    button.text = "constraint_layout"
                    button.setOnClickListener {
                        val intent = Intent(this, ConstraintActivity::class.java)
                        startActivity(intent)
                    }
                }
                2 -> {
                    button.text = "login"
                    button.setOnClickListener {
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                }
                3-> {
                    button.text = "pick_contact"
                    button.setOnClickListener {
                        val intent = Intent("com.thoughtworks.androidtrain.CONTACT_ACTION")
                        pickContactLauncher.launch(intent)
                    }
                }
                4 -> {
                    button.text = " fragment"
                    button.setOnClickListener {
                        val intent = Intent(this, LanguageSelectionActivity::class.java)
                        startActivity(intent)
                    }
                }
                5 -> {
                    button.text = " RecyclerView"
                    button.setOnClickListener {
                        val intent = Intent(this, TweetsActivity::class.java)
                        startActivity(intent)
                    }
                }
                6 -> {
                    button.text = " thread"
                    button.setOnClickListener {
                        val intent = Intent(this, ThreadActivity::class.java)
                        startActivity(intent)
                    }
                }
                7 -> {
                    button.text = " DataStore"
                    button.setOnClickListener {
                        val intent = Intent(this, DataStoreActivity::class.java)
                        startActivity(intent)
                    }
                }
                else -> {
                    // 其他按钮显示默认的文本
                    button.text = getString(R.string.button_text, i)
                }
            }
            buttonContainer.addView(button)
        }



    }
    // 显示对话框
    private fun showDialog(name: String, phoneNumber: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Selected contact")
            .setMessage("Name: $name\nPhone: $phoneNumber")
            .setPositiveButton("OK") { dialog: DialogInterface, _: Int ->
                dialog.dismiss() // 关闭对话框
            }
        val dialog = builder.create()
        dialog.setCanceledOnTouchOutside(true)
        dialog.show()
    }
}