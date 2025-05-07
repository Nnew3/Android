package com.example.mz_focusnews.core.api.model

data class AlarmRequest(
    val userId: Long,
    val isAlarm: Boolean
)
data class LocationRequest(
    val userId: Long,
    val isLocation: Boolean
)
