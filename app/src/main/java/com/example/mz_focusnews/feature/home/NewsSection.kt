package com.example.mz_focusnews.feature.home

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.NewsItemCard
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.theme.Today_Blue

@Composable
fun NewsSection(newsState: NewsState, navigateToContent: (id: Long) -> Unit) {
    val pagerState = rememberPagerState { 3 } // 총 페이지 수 설정

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 8.dp)
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState
        ) { page ->
            val text = when (page) {
                0 -> "오늘의 뉴스"
                1 -> "이주의 뉴스"
                2 -> "이달의 뉴스"
                else -> ""
            }

            val news = when (page) {
                0 -> newsState.newsGroup?.day
                1 -> newsState.newsGroup?.week
                2 -> newsState.newsGroup?.month
                else -> null

            }

            val statusMsg = when (page) {
                0 -> "오늘은 아직 새로운 소식이 없어요"
                1 -> "이번 주 소식이 준비되는 중 입니다"
                2 -> "이번 달 소식이 준비되는 중 입니다"
                else -> ""
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .height(185.dp),
            ) {

                when {
                    // 로딩중
                    newsState.isLoading -> {
                        StatusCard(
                            bgColor = Today_Blue,
                            imgRes = R.drawable.img_loading_kitty,
                            msg = "${text}를 가져오는 중이에요!"
                        )
                    }

                    // 에러 처리
                    newsState.isError -> {
                        StatusCard(
                            bgColor = Today_Blue,
                            imgRes = R.drawable.img_network_kitty,
                            msg = "네트워크 오류가 발생했어요"
                        )
                    }

                    // news가 null일 때
                    (news == null) -> {
                        StatusCard(
                            bgColor = Today_Blue,
                            imgRes = R.drawable.image_error_kitty,
                            msg = statusMsg
                        )
                    }

                    // 정상 응답 처리
                    else -> {
                        NewsItemCard(text, news, navigateToContent)
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun NewsPreview() {
    NewsSection(NewsState(), { })
}
