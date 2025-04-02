package com.example.mz_focusnews.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mz_focusnews.core.components.BottomNavItem
import com.example.mz_focusnews.feature.category.CategoryScreen
import com.example.mz_focusnews.feature.content.ContentScreen
import com.example.mz_focusnews.feature.home.HomeScreen
import com.example.mz_focusnews.feature.mypage.MyPageScreen
import com.example.mz_focusnews.feature.quiz.QuizScreen

@Composable
fun NavGraph(navController: NavHostController, onDrawerOpen: () -> Unit) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(onDrawerOpen)
        }
        composable(BottomNavItem.Category.route) {
            CategoryScreen(navController)
        }
        composable(BottomNavItem.Quiz.route) {
            QuizScreen()
        }
        composable(BottomNavItem.MyPage.route) {
            MyPageScreen()
        }
        composable("content") {
            ContentScreen()
        }
    }
}
