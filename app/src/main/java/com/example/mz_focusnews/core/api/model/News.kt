package com.example.mz_focusnews.core.api.model

data class News(
    val id: Long,
    val title: String,
    val content: String,
    val date: String,
    val imgUrl: String
)