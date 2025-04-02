package com.example.mz_focusnews.feature.recent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.components.NewsListItem
import com.example.mz_focusnews.core.components.TopSection
import com.example.mz_focusnews.core.theme.Bg_Blue

@Composable
fun RecentNewsScreen(navController: NavController) {

    val newsList = List(10) { "최근 본 뉴스 제목: $it" }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier.background(Bg_Blue)
        ) {
            // top section
            TopSection("최근 본 뉴스", navController)

            // news list
            LazyColumn(
                modifier = Modifier
                    .padding(top = 25.dp)
                    .fillMaxWidth()
                    .background(Bg_Blue),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                items(newsList) { news ->
                    NewsListItem({ navController.navigate("content") }, news)
                }
            }
        }

    }
}

@Preview
@Composable
fun RecentPreview() {
    RecentNewsScreen(rememberNavController())
}
