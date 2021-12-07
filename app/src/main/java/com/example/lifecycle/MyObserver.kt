package com.example.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver : LifecycleObserver {
    private val tag = "MyObserver"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() = Log.d(tag, "onCreate")


    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() = Log.d(tag, "onStart")


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() = Log.d(tag, "onResume")

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() = Log.d(tag, "onPause")


    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() = Log.d(tag, "onStop")

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() = Log.d(tag, "onDestroy")
}