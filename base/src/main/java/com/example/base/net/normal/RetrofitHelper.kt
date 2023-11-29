package com.example.base.net.normal

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// retrofit 默认实现方式
object RetrofitHelper {

    // 一个域名对应一个retrofit对象
    private val retrofits: HashMap<String, Retrofit> = HashMap()

    private fun createRetrofit(baseUrl: String): Retrofit {
        // 可以根据需要添加拦截器
        val client: OkHttpClient = OkHttpClient.Builder().build()

        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
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