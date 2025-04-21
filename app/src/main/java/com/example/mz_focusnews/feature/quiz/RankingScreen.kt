package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mz_focusnews.core.theme.Bg_Blue

@Composable
fun RankingScreen() {
    val userList = listOf("다지니", "래로미", "고양이", "강아지", "강아지", "강아지", "강아지") // 예시용

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Bg_Blue)
                .padding(top = 65.dp)
        ) {
            // 상위 1~3등 Podium
            RankingPodiumSection()

            // 나머지 유저 랭킹
            RankingListSection(userList)

        }
    }
}

@Preview
@Composable
fun RankingPreview() {
    RankingScreen()
}