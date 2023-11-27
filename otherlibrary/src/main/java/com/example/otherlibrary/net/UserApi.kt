package com.example.otherlibrary.net

import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


interface UserApi {

    @POST("/getUser")
    fun getUser(@Body requestBody: RequestBody): Observable<User>

    @GET("users/{id}")
    fun getUser(@Path("id") userId: String?): Observable<User>
}