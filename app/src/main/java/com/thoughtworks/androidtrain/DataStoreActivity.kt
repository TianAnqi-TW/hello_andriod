package com.thoughtworks.androidtrain

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")
class DataStoreActivity : AppCompatActivity() {

    private val dataStore: DataStore<Preferences> by lazy {
        applicationContext.dataStore
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        // 从 Preferences DataStore 读取数据
        val isHintShownFlow: Flow<Boolean> = dataStore.data.map { preferences ->
            preferences[IS_HINT_SHOWN_KEY] ?: false
        }

        val isHintShown = runBlocking { isHintShownFlow.first() }

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
            // 将 isHintShown 的值设置为 true，并保存到 Preferences DataStore 中
            runBlocking {
                dataStore.edit { preferences ->
                    preferences[IS_HINT_SHOWN_KEY] = true
                }
            }
            // 更新布局可见性
            leftLayout.visibility = View.GONE
            rightLayout.visibility = View.VISIBLE
        }
    }
    companion object {
        private val IS_HINT_SHOWN_KEY = booleanPreferencesKey("is_hint_shown")
    }
}