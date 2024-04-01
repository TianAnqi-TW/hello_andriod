package com.thoughtworks.androidtrain

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ThreadActivity : AppCompatActivity() {

    private lateinit var countButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

        countButton = findViewById(R.id.countButton)
        countButton.setOnClickListener{
            countButton.isEnabled = false //禁用按钮
            CountTask().execute()
            Log.w("CountTask", "执行AsyncTask")
        }
    }
    @SuppressLint("StaticFieldLeak")
    private inner class CountTask: AsyncTask<Void, Int, Void>(){
        @Deprecated("Deprecated in Java")
        override fun doInBackground(vararg params: Void?): Void? {
            Log.d("doInBackground", "doInBackground thread ID = " + Thread.currentThread().id)
            var count = 0
            while ( count <= 10) {
                Thread.sleep(1000)
                count++
                Log.d("doInBackground", count.toString())
                publishProgress(count)
            }
            return null
        }

        @Deprecated("Deprecated in Java")
        override fun onProgressUpdate(vararg values: Int?) {
            super.onProgressUpdate(*values)
            Log.d("onProgressUpdate", values.toString())
            countButton.text = values[0].toString() //更改按钮显示为数字
        }

        @SuppressLint("SetTextI18n")
        @Deprecated("Deprecated in Java")
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            countButton.isEnabled = true //启用按钮
            countButton.text = "Button" //重置为原来的文本
            Log.d("onPostExecute", "countTask任务执行完毕");

        }

    }
}