package com.example.livedata

import androidx.lifecycle.MutableLiveData

object MyLiveData {
    // 懒加载
    val info1: MutableLiveData<String> by lazy { MutableLiveData() }
}