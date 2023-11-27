package com.example.base.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingView()
        initData()
        initView()
    }

    open fun bindingView() {

    }

    open fun initData() {

    }

    open fun initView() {

    }
}