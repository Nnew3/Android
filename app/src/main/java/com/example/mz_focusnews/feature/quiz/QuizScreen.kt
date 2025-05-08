package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.components.CustomIndicator
import com.example.mz_focusnews.core.theme.Bg_Blue

@Composable
fun QuizScreen(
    viewModel: QuizViewModel,
    navController: NavController
) {
    val rankingState = viewModel.rankingState.collectAsState().value

    val pagerState = rememberPagerState { 2 }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState,
            userScrollEnabled = true,
        ) { page ->
            when (page) {
                0 -> QuizIntroScreen(viewModel, navController)
                1 -> RankingScreen(rankingState)
            }
        }

        CustomIndicator(pagerState)
    }
}

@Preview
@Composable
fun QuizPreview() {
    QuizScreen(viewModel(), rememberNavController())
}