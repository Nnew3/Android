package com.example.mz_focusnews.feature.category

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.CategoryChip
import com.example.mz_focusnews.core.components.CategoryType
import com.example.mz_focusnews.core.components.SortChip
import com.example.mz_focusnews.core.components.SortType
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Category_Blue

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel,
    navController: NavController
) {
    val categoryState = viewModel.categoryState.collectAsState().value

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Bg_Blue)
        ) {

            // 카테고리 선택
            LazyRow(
                modifier = Modifier
                    .padding(top = 15.dp, start = 25.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, bottomStart = 16.dp))
                    .background(Category_Blue),
            ) {
                item { Spacer(modifier = Modifier.width(12.dp)) }

                items(CategoryType.entries) { category ->
                    CategoryChip(
                        category = category.label,
                        isSelected = (category == categoryState.selectedCategory), // 현재 선택된 카테고리인지 체크
                        onClick = { viewModel.onCategorySelected(category) } // 클릭하면 선택된 카테고리 변경)
                    )
                }
            }

            // 정렬 기준 선택
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp, end = 25.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                for (sortType in SortType.entries) {
                    SortChip(
                        sortType = sortType,
                        isSelected = (sortType == categoryState.selectedSortType),
                        onClick = { viewModel.onSortTypeSelected(sortType) }
                    )
                }
            }

            categoryState.news?.let { news ->
                when {
                    categoryState.isLoading -> {
                        StatusCard(
                            imgRes = R.drawable.img_loading_kitty,
                            msg = "뉴스를 가져오는 중이에요"
                        )
                    }

                    categoryState.isError -> {
                        StatusCard(
                            imgRes = R.drawable.img_error_kitty,
                            msg = "네트워크 오류가 발생했어요"
                        )
                    }

                    // newsResList가 없을 떄 null이 아닌 빈 배열로 옴
                    news.newsResList.isEmpty() -> {
                        StatusCard(
                            imgRes = R.drawable.img_error_kitty, msg = "해당 카테고리의 뉴스가 없어요"
                        )
                    }

                    else -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 25.dp, top = 8.dp, end = 25.dp, bottom = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(news.newsResList) { item ->
                                CategoryNewsItem(
                                    news = item,
                                    navigateToContent = {
                                        Log.d("Retrofit", "clicked news id = ${item.id}")
                                        navController.navigate("content/${item.id}")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun CategoryPreview() {
    CategoryScreen(viewModel(), navController = rememberNavController())
}