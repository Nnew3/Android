package com.example.mz_focusnews.screen

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.ui.theme.Bg_Blue
import com.example.mz_focusnews.ui.theme.Category_Blue

@Composable
fun CategoryScreen(navController: NavController) {
    var selectedSort by remember { mutableStateOf(SortType.BASIC) } // 선택된 정렬 상태 (Default: 최신순)

    val categories = CategoryType.entries
    var selectedCategory by remember { mutableStateOf(categories[0]) } // 선택된 카테고리

    val newsList = remember(selectedCategory) { getNewsByCategory(selectedCategory) } // 테스트용

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

                items(categories) { category ->
                    CategoryChip(
                        category = category.label,
                        isSelected = (category == selectedCategory), // 현재 선택된 카테고리인지 체크
                        onClick = { selectedCategory = category } // 클릭하면 선택된 카테고리 변경)
                    )
                }
            }

            // 정렬
            // TODO: 정렬 로직 구현하기
            Row(
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 8.dp, end = 25.dp),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                SortChip(
                    sortType = SortType.BASIC,
                    isSelected = (selectedSort == SortType.BASIC),
                    onClick = { selectedSort = SortType.BASIC }
                )
                SortChip(
                    sortType = SortType.RECENT,
                    isSelected = (selectedSort == SortType.RECENT),
                    onClick = { selectedSort = SortType.RECENT }
                )
                SortChip(
                    sortType = SortType.POPULAR,
                    isSelected = (selectedSort == SortType.POPULAR),
                    onClick = { selectedSort = SortType.POPULAR }
                )
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 25.dp, top = 8.dp, end = 25.dp, bottom = 10.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(newsList) { news ->
                    CategoryNewsItem(navController, news)
                }
            }
        }
    }
}

// 테스트용 임시 함수
fun getNewsByCategory(category: CategoryType): List<String> {
    return when (category) {
        CategoryType.POLITICS -> List(10) { "정치 뉴스 제목: $it" }
        CategoryType.ECONOMY -> List(10) { "경제 뉴스 제목: $it" }

        else -> emptyList()
    }
}


@Preview
@Composable
fun CategoryPreview() {
    CategoryScreen(navController = rememberNavController())
}