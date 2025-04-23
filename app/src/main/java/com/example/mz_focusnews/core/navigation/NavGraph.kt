package com.example.mz_focusnews.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mz_focusnews.core.components.BottomNavItem
import com.example.mz_focusnews.feature.category.CategoryScreen
import com.example.mz_focusnews.feature.content.ContentScreen
import com.example.mz_focusnews.feature.home.HomeScreen
import com.example.mz_focusnews.feature.like.LikeNewsScreen
import com.example.mz_focusnews.feature.mypage.MyPageScreen
import com.example.mz_focusnews.feature.quiz.QuizIntroScreen
import com.example.mz_focusnews.feature.quiz.QuizPlayScreen
import com.example.mz_focusnews.feature.quiz.QuizScreen
import com.example.mz_focusnews.feature.quiz.RankingScreen
import com.example.mz_focusnews.feature.recent.RecentNewsScreen

@Composable
fun NavGraph(navController: NavHostController, onDrawerOpen: () -> Unit) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(navController, onDrawerOpen)
        }
        composable(BottomNavItem.Category.route) {
            CategoryScreen(navController)
        }
        composable(BottomNavItem.Quiz.route) {
            QuizScreen(navController)
        }
        composable(BottomNavItem.MyPage.route) {
            MyPageScreen(navController)
        }
        composable("content") {
            ContentScreen(navController)
        }

        composable("like") {
            LikeNewsScreen(navController)
        }

        composable("recent") {
            RecentNewsScreen(navController)
        }

        composable("quiz_intro") {
            QuizIntroScreen(navController)
        }

        composable("quiz_play") {
            QuizPlayScreen(navController)
        }

        composable("ranking") {
            RankingScreen()
        }
    }
}
