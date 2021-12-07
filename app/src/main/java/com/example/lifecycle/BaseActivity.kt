package com.example.lifecycle

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 添加observer  lifecycle在java中表示为 getLifecycle
        // 观察者和被观察者关联
        lifecycle.addObserver(MyObserver())
        lifecycle.addObserver(MyObserverImprover())
    }
}