package com.example.otherlibrary.netmvvm

data class ResponseData<out T>(val errorCode: Int, val errorMessage: String, val data: T)
