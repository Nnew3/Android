package com.example.mz_focusnews.core.api.model

data class SetKeywordRequest(
    val id: Long, // 사용자 아이디
    val previousKeyword: String,
    val newKeyword: String
)

data class DelKeywordRequest(
    val userId: Long,
    val keyword: String
)
