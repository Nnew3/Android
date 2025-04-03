package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mz_focusnews.core.components.CustomIndicator
import com.example.mz_focusnews.core.theme.Bg_Blue

@Composable
fun QuizScreen() {

    val pagerState = rememberPagerState { 2 }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp),

        ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            userScrollEnabled = true,
        ) { page ->
            when (page) {
                0 -> QuizIntroScreen()
                1 -> RankingScreen()
            }
        }

        CustomIndicator(pagerState)

    }
}

@Preview
@Composable
fun QuizPreview() {
    QuizScreen()
}