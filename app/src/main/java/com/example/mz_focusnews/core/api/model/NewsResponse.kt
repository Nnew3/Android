package com.example.mz_focusnews.core.api.model

data class NewsResponse(
    val newsResList: List<NewsRes>
)

data class NewsRes(
    val id: Long,
    val title: String,
    val content: String,
    val publisher: String,
    val newsTime: String,
    val imgUrl: String
)