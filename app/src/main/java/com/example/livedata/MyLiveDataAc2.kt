package com.example.livedata

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lifecycle.R

class MyLiveDataAc2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ac_mylive2)

        OKLiveDataBusKt.with("data1", String::class.java, false).observe(this, {
            Log.d("xjw", "kotlin 观察者 :$it")
        })
    }
}