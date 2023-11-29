package com.example.base.base

import android.app.Application
import com.example.base.utils.ContextUtil

open class BaseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        ContextUtil.setContext(context = this)
    }
}