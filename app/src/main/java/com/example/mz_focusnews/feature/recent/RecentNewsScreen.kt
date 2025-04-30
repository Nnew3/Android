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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.NewsListItem
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.components.TopSection
import com.example.mz_focusnews.core.theme.Bg_Blue

@Composable
fun RecentNewsScreen(
    viewModel: RecentNewsViewModel,
    navController: NavController
) {
    val recentNewsState = viewModel.recentNewsState.collectAsState().value

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

            when {
                recentNewsState.isLoading -> {
                    StatusCard(
                        imgRes = R.drawable.img_loading_kitty,
                        msg = "뉴스를 불러오는 중이에요!"
                    )
                }

                // 에러 처리
                recentNewsState.isError -> {
                    StatusCard(
                        imgRes = R.drawable.img_network_kitty,
                        msg = "네트워크 오류가 발생했어요"
                    )
                }

                (recentNewsState.newsList == null) ->
                    StatusCard(
                        imgRes = R.drawable.img_error_kitty,
                        msg = "최근 본 뉴스가 없어요"
                    )

                else -> {
                    // news list
                    LazyColumn(
                        modifier = Modifier
                            .padding(top = 25.dp)
                            .fillMaxWidth()
                            .background(Bg_Blue),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(recentNewsState.newsList.newsList) { news ->
                            NewsListItem({ navController.navigate("content/${news.id}") }, news)
                        }
                    }
                }
            }

        }

    }
}
