package com.example.mz_focusnews

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen()
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
    }
}