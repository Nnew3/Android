package com.example.mz_focusnews

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mz_focusnews.screen.ContentScreen
import com.example.mz_focusnews.screen.HomeScreen

@Composable
fun NavGraph(navController: NavHostController, onDrawerOpen: () -> Unit) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(onDrawerOpen)
        }
        composable(BottomNavItem.Category.route) {
            CategoryScreen()
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
