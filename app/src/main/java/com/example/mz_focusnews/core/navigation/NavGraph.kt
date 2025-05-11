package com.example.mz_focusnews.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.mz_focusnews.LikeManagerViewModel
import com.example.mz_focusnews.core.components.BottomNavItem
import com.example.mz_focusnews.feature.category.CategoryScreen
import com.example.mz_focusnews.feature.category.CategoryViewModel
import com.example.mz_focusnews.feature.content.ContentScreen
import com.example.mz_focusnews.feature.content.ContentViewModel
import com.example.mz_focusnews.feature.home.HomeScreen
import com.example.mz_focusnews.feature.home.HomeViewModel
import com.example.mz_focusnews.feature.like.LikeNewsScreen
import com.example.mz_focusnews.feature.mypage.MyPageScreen
import com.example.mz_focusnews.feature.mypage.MyPageViewModel
import com.example.mz_focusnews.feature.quiz.QuizIntroScreen
import com.example.mz_focusnews.feature.quiz.QuizPlayScreen
import com.example.mz_focusnews.feature.quiz.QuizScreen
import com.example.mz_focusnews.feature.quiz.QuizViewModel
import com.example.mz_focusnews.feature.recent.RecentNewsScreen
import com.example.mz_focusnews.feature.recent.RecentNewsViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NavGraph(
    navController: NavHostController,
    homeViewModel: HomeViewModel,
    myPageViewModel: MyPageViewModel,
    likeManagerViewModel: LikeManagerViewModel,
    onDrawerOpen: () -> Unit
) {
    val quizViewModel: QuizViewModel = viewModel()

    NavHost(navController = navController, startDestination = BottomNavItem.Home.route) {
        composable(BottomNavItem.Home.route) {
            HomeScreen(homeViewModel, navController, onDrawerOpen)
        }

        composable(BottomNavItem.Category.route) {
            val viewModel: CategoryViewModel = viewModel()
            CategoryScreen(viewModel, navController)
        }

        composable(BottomNavItem.Quiz.route) {
            QuizScreen(quizViewModel, navController)
        }

        composable(BottomNavItem.MyPage.route) {
            MyPageScreen(myPageViewModel, navController)
        }

        composable(
            route = ScreenRoute.Content.route,
            arguments = listOf(navArgument("newsId") { type = NavType.StringType })
        ) { backStackEntry ->
            val newsIdStr = backStackEntry.arguments?.getString("newsId") ?: "0"
            val contentViewModel: ContentViewModel = viewModel(backStackEntry)
            ContentScreen(contentViewModel, likeManagerViewModel, newsIdStr, navController)
        }

        composable(
            route = ScreenRoute.Like.route,
        ) {
            LikeNewsScreen(likeManagerViewModel, navController)
        }

        composable(
            route = ScreenRoute.Recent.route,
            arguments = listOf(navArgument("userId") { type = NavType.StringType })
        ) { backStackEntry ->
            val viewModel: RecentNewsViewModel = viewModel(backStackEntry)
            RecentNewsScreen(viewModel, navController)
        }

        composable(ScreenRoute.QuizIntro.route) {
            QuizIntroScreen(quizViewModel, navController)
        }

        composable(ScreenRoute.QuizPlay.route) {
            QuizPlayScreen(quizViewModel, navController)
        }
//
//        composable(ScreenRoute.Ranking.route) {
//            RankingScreen()
//        }
    }
}
