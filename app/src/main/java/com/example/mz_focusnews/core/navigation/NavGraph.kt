package com.example.mz_focusnews.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mz_focusnews.core.components.BottomNavItem
import com.example.mz_focusnews.feature.category.CategoryScreen
import com.example.mz_focusnews.feature.category.CategoryViewModel
import com.example.mz_focusnews.feature.content.ContentScreen
import com.example.mz_focusnews.feature.content.ContentViewModel
import com.example.mz_focusnews.feature.home.HomeScreen
import com.example.mz_focusnews.feature.home.HomeViewModel
import com.example.mz_focusnews.feature.like.LikeNewsScreen
import com.example.mz_focusnews.feature.mypage.MyPageScreen
import com.example.mz_focusnews.feature.quiz.QuizIntroScreen
import com.example.mz_focusnews.feature.quiz.QuizPlayScreen
import com.example.mz_focusnews.feature.quiz.QuizScreen
import com.example.mz_focusnews.feature.quiz.RankingScreen
import com.example.mz_focusnews.feature.recent.RecentNewsScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    onDrawerOpen: () -> Unit
) {
    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(homeViewModel, navController, onDrawerOpen)
        }

        composable(
            route = BottomNavItem.Category.route,
        ) {
            val viewModel: CategoryViewModel = viewModel()
            CategoryScreen(viewModel, navController)
        }

        composable(BottomNavItem.Quiz.route) {
            QuizScreen(navController)
        }

        composable(BottomNavItem.MyPage.route) {
            MyPageScreen(navController)
        }

        composable(
            route = ScreenRoute.Content.route,
            arguments = listOf(navArgument("newsId") { type = NavType.StringType })
        ) { backStackEntry ->
            val newsIdStr = backStackEntry.arguments?.getString("newsId") ?: "0"
            val viewModel: ContentViewModel = viewModel(backStackEntry)
            ContentScreen(viewModel, newsIdStr, navController)
        }

        composable(ScreenRoute.Like.route) {
            LikeNewsScreen(navController)
        }

        composable(ScreenRoute.Recent.route) {
            RecentNewsScreen(navController)
        }

        composable(ScreenRoute.QuizIntro.route) {
            QuizIntroScreen(navController)
        }

        composable(ScreenRoute.QuizPlay.route) {
            QuizPlayScreen(navController)
        }

        composable(ScreenRoute.Ranking.route) {
            RankingScreen()
        }
    }
}
