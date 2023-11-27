package com.example.base.net.rxjava

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RxJavaRetrofit {

    // 一个域名对应一个retrofit对象
    private val retrofits: HashMap<String, Retrofit> = HashMap()

    private fun createRetrofit(baseUrl: String): Retrofit {
        // 可以根据需要添加拦截器
        val client: OkHttpClient = OkHttpClient.Builder().build()

        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    fun getRetrofit(baseUrl: String): Retrofit {
        return if (retrofits.containsKey(baseUrl)) {
            retrofits[baseUrl]!!
        } else {
            val retrofit = createRetrofit(baseUrl)
            retrofits[baseUrl] = retrofit
            retrofit
        }
    }

}