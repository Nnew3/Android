package com.example.mz_focusnews

import com.example.mz_focusnews.ui.Category
import com.example.mz_focusnews.ui.Home
import com.example.mz_focusnews.ui.Quiz
import com.example.mz_focusnews.ui.User

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
