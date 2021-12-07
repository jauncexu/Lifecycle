package com.example.lifecycle

import android.util.Log
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlin.math.log

// LifecycleObserver的二次封装版，对用户更加友善
// 项目实战中可以拿到  Activity和Fragment 的实例，可进行toast
class MyObserverImprover : DefaultLifecycleObserver {
    private val tag = "MyObserverImprover"

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        Log.d(tag, "onCrate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d(tag, "onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d(tag, "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.d(tag, "onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d(tag, "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.d(tag, "onDestroy")
    }
}