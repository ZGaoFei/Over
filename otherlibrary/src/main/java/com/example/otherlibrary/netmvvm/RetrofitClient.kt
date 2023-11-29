package com.example.otherlibrary.netmvvm

import com.example.base.utils.ContextUtil
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.OkHttpClient.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object RetrofitClient {
    private const val BASE_URL = "https://wanandroid.com/"

    private var retrofit: Retrofit? = null

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .client(getOkhttp())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }

    private fun getOkhttp(): OkHttpClient {
        val builder = Builder()

        val cacheFile = File(ContextUtil.getContext().cacheDir, "cache")
        val cache = Cache(cacheFile, 50 * 1024 * 1024)

        builder.run {
            cache(cache)
            connectTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            retryOnConnectionFailure(true)
        }
        return builder.build()
    }
}