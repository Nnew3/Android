package com.example.mz_focusnews.core.api.model

data class Ranking(
    val ranking: List<UserRanking>
)

data class UserRanking(
    val userId: Long,
    val ranking: Long,
    val nickname: String,
    val score: Int,
)