package com.example.otherlibrary.netmvvm

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

open class BaseRepository {

    suspend fun<T: Any> request(call: suspend () -> ResponseData<T>): ResponseData<T> {
        return withContext(Dispatchers.IO) {
            call.invoke()
        }.apply {
            when (errorCode) {
                0, 200 -> this
                100, 401 -> ""
                403 -> ""
                404 -> ""
                500 -> ""
                else -> ""
            }
        }
    }
}