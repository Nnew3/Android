package com.example.mz_focusnews.core.api

data class ApiResponse<T>(
    val success: Boolean,
    val status: Int,
    val msg: String,
    val data: T
)