package com.example.jetpack.livedatatest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TestDataViewModel: ViewModel() {

    val string: MutableLiveData<String> = MutableLiveData("null")
    val i: MutableLiveData<Int> = MutableLiveData(-1)
    val testData: MutableLiveData<TestData> = MutableLiveData()
}