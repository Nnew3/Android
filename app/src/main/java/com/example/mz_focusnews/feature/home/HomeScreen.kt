package com.example.mz_focusnews.feature.home

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Gray_200

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navController: NavController,
    onDrawerOpen: () -> Unit
) {
    val newsState = viewModel.newsState.collectAsState().value  // StateFlow 관찰
    val breakingState = viewModel.breakingState.collectAsState().value
    val userNewsState = viewModel.userNewsState.collectAsState().value
    val mainInfoState = viewModel.mainInfoState.collectAsState().value

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg_Blue),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Breaking Drawer Open Button
            UserGuideSection(mainInfoState, onDrawerOpen)

            BreakingSection(breakingState) { newsId ->
                navigateToContent(newsId, navController)
            }

            NewsSection(newsState) { newsId ->
                navigateToContent(newsId, navController)
            }

            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Gray_200)
            )

            UserNewsSection(userNewsState) { newsId ->
                navigateToContent(newsId, navController)
            }
        }
    }
}

private fun navigateToContent(newsId: Long, navController: NavController) {
    navController.navigate("content/$newsId")
    Log.d("Retrofit", "clicked news id = $newsId")
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun HomePreview() {
    HomeScreen(viewModel(), rememberNavController(), {})
}