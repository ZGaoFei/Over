package com.example.otherlibrary.net

import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface UserApi {

    // =================== RxJava方式 ========================
    @POST("/getUser")
    fun getUser(@Body requestBody: RequestBody): Observable<User>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String?): Observable<User>

    // ===================== 普通方式 =========================
    @POST("/getUser")
    fun getUserNormal(@Body requestBody: RequestBody): Call<User>

    @GET("users/{id}")
    fun getUserNormal(@Path("id") userId: String?): Call<User>

    // ===================== 协程方式 =========================
    @POST("/getUser")
    suspend fun getUserSuspend(@Body requestBody: RequestBody): Call<User>

    @GET("users/{id}")
    suspend fun getUserSuspend(@Path("id") userId: String?): Call<User>
}