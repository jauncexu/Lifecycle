package com.example.databing.model

import androidx.databinding.ObservableField

class StudentInfo {
    val name: ObservableField<String> by lazy { ObservableField<String>() }
    val pwd: ObservableField<String> by lazy { ObservableField<String>() }
}