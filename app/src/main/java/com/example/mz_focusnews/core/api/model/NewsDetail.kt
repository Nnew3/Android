package com.example.mz_focusnews.core.api.model

data class NewsDetail(
    val id: Long,
    val title: String,
    val summary: String,
    val imgUrl: String,
    val link: String
)