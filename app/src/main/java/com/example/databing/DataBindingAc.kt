package com.example.databing

import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.databing.model.StudentInfo
import com.example.lifecycle.R
import com.example.lifecycle.databinding.AcDatabindingBinding

class DataBindingAc : AppCompatActivity() {
    private val TAG = "DataBindingAc"
    private val studentInfo = StudentInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.ac_databinding)

        var bind =
            DataBindingUtil.setContentView<AcDatabindingBinding>(this, R.layout.ac_databinding)

        studentInfo.name.set("jaunce")
        studentInfo.pwd.set("111")
        bind.stuInfo = studentInfo
        Log.d(TAG, "name:${studentInfo.name.get()},pwd:${studentInfo.pwd.get()}")

        Handler().postDelayed({
            studentInfo.name.set("xjw")
            studentInfo.pwd.set("123")
        }, 1000)

    }
}