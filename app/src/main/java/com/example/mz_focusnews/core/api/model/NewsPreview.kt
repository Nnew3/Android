package com.example.mz_focusnews.core.api.model

data class NewsPreview(
    val id: Long,
    val title: String,
    val publisher: String = "",
    val imgUrl: String = ""
)
