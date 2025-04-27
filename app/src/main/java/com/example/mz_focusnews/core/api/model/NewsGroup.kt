package com.example.mz_focusnews.core.api.model

// 오늘. 이주. 이달의 뉴스
data class NewsGroup(
    val week: News?,
    val month: News?,
    val day: News?
)
