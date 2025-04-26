package com.example.mz_focusnews.core.api.model

data class BreakingNews(
    val breakingNewsRecent: BreakingItem,
    val breakingNews: List<BreakingItem>
)

data class BreakingItem(
    val id: Long,
    val title: String
)