package com.example.livedata

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.lang.NullPointerException
import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * 自定义liveData 去除粘性事件
 */
object OKLiveDataBusKt {
    // 存放订阅者
    private val bus: MutableMap<String, BusMutableLiveData<Any>> by lazy {
        HashMap()
    }

    // 暴露一个函数给外界注册  订阅者关系
    @Synchronized // 加个同步锁
    fun <T> with(key: String, type: Class<T>, isStick: Boolean = true): BusMutableLiveData<T> {
        // 放置重复的key
        if (!bus.containsKey(key)) {
            bus[key] = BusMutableLiveData(isStick)
        }
        return bus[key] as BusMutableLiveData<T>
    }


    // bus总线
    class BusMutableLiveData<T> private constructor() : MutableLiveData<T>() {
        var isStick: Boolean = false

        // 次构造
        constructor(isStick: Boolean) : this() {
            this.isStick = isStick
        }

        // 复写observe方法，hook修改去除粘性
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
            if (isStick) {
                hook(observer = observer)
                Log.d("xjw", "KT版本不启用粘性")
            } else {
                Log.d("xjw", "KT版本启用粘性")
            }
        }

        private fun hook(observer: Observer<in T>) {
            // 1. 得到mLastVersion
            // 获取到LiveData的类中的mObservers对象
            val liveDataClass = LiveData::class.java

            val mObserverFile: Field = liveDataClass.getDeclaredField("mObservers")
            mObserverFile.isAccessible = true

            // 获取到这个成员变量的对象
            val mObserversObj: Any = mObserverFile.get(this)

            // 得到map的class对象  private SafeIterableMap<Observer<? super T>,ObserverWrapper> mObservers
            val mObserversClass: Class<*> = mObserversObj.javaClass

            // 获取到mObservers对象的get方法
            val get: Method = mObserversClass.getDeclaredMethod("get", Any::class.java)
            get.isAccessible = true

            // 执行get方法
            val invokeEntry = get.invoke(mObserversObj, observer)

            // 取到entry中的value
            var observerWrap: Any? = null
            if (invokeEntry != null && invokeEntry is Map.Entry<*, *>) {
                observerWrap = invokeEntry.value
            }

            if (observerWrap == null) {
                throw  NullPointerException("observerWrap is null")
            }

            // 得到observerWrapper类对象
            val supperClass: Class<*> = observerWrap.javaClass.superclass
            val mLastVersion: Field = supperClass.getDeclaredField("mLastVersion")
            mLastVersion.isAccessible = true

            // 得到mVersion
            val mVersion: Field = supperClass.getDeclaredField("mVersion")
            mVersion.isAccessible = true

            // mLastVersion == mVersion
            val mVersionValue = mVersion.get(this)
            mLastVersion.set(observerWrap, mVersionValue)
        }
    }
}