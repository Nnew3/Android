package com.example.mz_focusnews.feature.home

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Gray_200

@Composable
fun HomeScreen(navController: NavController, onDrawerOpen: () -> Unit) {
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
            UserGuideSection(onDrawerOpen)

            BreakingSection({ navigateToContent(navController) })

            NewsSection({ navigateToContent(navController) })

            Spacer(
                modifier = Modifier
                    .height(2.dp)
                    .fillMaxWidth()
                    .background(Gray_200)
            )

            RecommendedNewsSection({ navigateToContent(navController) })
        }
    }
}

fun navigateToContent(navController: NavController) {
    navController.navigate("content")
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen(rememberNavController(), {})
}