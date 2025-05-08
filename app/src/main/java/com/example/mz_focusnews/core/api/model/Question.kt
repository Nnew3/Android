package com.example.mz_focusnews.core.api.model

data class Question(
    val question: String, // 퀴즈 문제
    val optionList: List<Option>, // 보기 리스트
    val answer: Int, // 정답
    val score: Int // 점수
)

data class Option(
    val id: Int, // 보기 ID
    val option: String, // 보기
)

