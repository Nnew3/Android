package com.example.mz_focusnews.core.api.model

data class SetLocationRequest(
    val userId: Long,
    val lat: Double,
    val lon: Double
)