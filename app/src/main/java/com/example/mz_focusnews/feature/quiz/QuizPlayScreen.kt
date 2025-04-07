package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mz_focusnews.core.theme.Bg_Blue

@Composable
fun QuizPlayScreen() {

    val choices = listOf("너구리", "곰", "판다", "오스트랄로피테쿠스")

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        QuizQuestionView(choices)
    }
}

@Preview
@Composable
fun QuizPlayPreview() {
    QuizPlayScreen()
}