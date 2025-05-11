package com.example.mz_focusnews.core.api.model

data class MyPageInfo(
    val id: Long,
    val nickName: String,
    val email: String,
    val keyword: String,
    val score: Int,
    val alarm: Boolean,
    val location: Boolean
)