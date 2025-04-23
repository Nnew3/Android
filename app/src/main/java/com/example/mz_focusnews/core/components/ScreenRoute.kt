package com.example.mz_focusnews.core.components

import com.example.mz_focusnews.core.theme.MyIconPack
import com.example.mz_focusnews.core.theme.myiconpack.Category
import com.example.mz_focusnews.core.theme.myiconpack.Home
import com.example.mz_focusnews.core.theme.myiconpack.Quiz
import com.example.mz_focusnews.core.theme.myiconpack.User

sealed class ScreenRoute(val route: String) {
    object Content : ScreenRoute("content/{newsId}")
    object Like : ScreenRoute("like")
    object Recent : ScreenRoute("recent")
    object QuizIntro : ScreenRoute("quiz_intro")
    object QuizPlay : ScreenRoute("quiz_play")
    object Ranking : ScreenRoute("ranking")
}

sealed class BottomNavItem(
    val title: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val route: String
) {
    object Home :
        BottomNavItem(NavigationType.HOME, MyIconPack.Home, NavigationType.HOME)

    object Category :
        BottomNavItem(NavigationType.CATEGORY, MyIconPack.Category, NavigationType.CATEGORY)

    object Quiz :
        BottomNavItem(NavigationType.QUIZ, MyIconPack.Quiz, NavigationType.QUIZ)

    object MyPage :
        BottomNavItem(NavigationType.MY_PAGE, MyIconPack.User, NavigationType.MY_PAGE)
}

class NavigationType {
    companion object {
        const val HOME = "home"
        const val CATEGORY = "category"
        const val QUIZ = "quiz"
        const val MY_PAGE = "my_page"
    }
}
