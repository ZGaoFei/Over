package com.example.otherlibrary.net

import com.example.base.net.normal.RetrofitHelper

object UserApiClient {
    private var api: UserApi? = null

    val apiClient = api ?: synchronized(this) {
        api ?: RetrofitHelper.getRetrofit("").create(UserApi::class.java).also { api = it }
    }

    val userApi: UserApi by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        RetrofitHelper.getRetrofit("").create(UserApi::class.java)
    }
}