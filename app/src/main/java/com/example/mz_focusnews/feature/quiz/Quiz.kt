package com.example.mz_focusnews.feature.quiz

data class Quiz(
    val question: String,
    val options: List<String>,
    val correctAnswer: Int  // 인덱스 0번부터 시작
)