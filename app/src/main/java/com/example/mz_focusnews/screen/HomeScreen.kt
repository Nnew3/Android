package com.example.mz_focusnews.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mz_focusnews.ui.theme.Bg_Blue

@Composable
fun HomeScreen(onDrawerOpen: () -> Unit) {
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

            BreakingSection()

            NewsSection()

            RecommendedNewsSection()
        }
    }
}

@Preview
@Composable
fun HomePreview() {
    HomeScreen({})
}