package com.example.mz_focusnews.core.navigation

sealed class ScreenRoute(val route: String) {
    object Content : ScreenRoute("content/{newsId}")
    object Like : ScreenRoute("like")
    object Recent : ScreenRoute("recent")
    object QuizIntro : ScreenRoute("quiz_intro")
    object QuizPlay : ScreenRoute("quiz_play")
    object Ranking : ScreenRoute("ranking")
}
