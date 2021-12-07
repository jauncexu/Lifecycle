package com.example.livedata

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.lifecycle.R
import kotlin.concurrent.thread

class MyLiveDataAc : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv: TextView = findViewById(R.id.tv)

        // 1.观察者  眼睛
        MyLiveData.info1.observe(this, object : Observer<String> {
            override fun onChanged(t: String?) {
                tv.text = t  // 更新UI
            }
        })

        // 2.触发数据改变
        MyLiveData.info1.value = "default"  // 主线程setValue

        // postValue 子线程
        thread {
            Thread.sleep(3000)
            MyLiveData.info1.postValue("三秒钟后，数据改变了")
        }

        // 自定义形式
        OKLiveDataBusKt.with("data1", String::class.java, false).value = "自定义形式的value"
    }

    fun btnClick(view: View) {
        startActivity(Intent(this, MyLiveDataAc2::class.java))
    }
}