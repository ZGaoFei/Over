package com.example.base.utils

import android.content.Context

object ContextUtil {
    private var context: Context? = null

    fun setContext(context: Context) {
        this.context = context.applicationContext
    }

    fun getContext(): Context {
        return context!!
    }
}