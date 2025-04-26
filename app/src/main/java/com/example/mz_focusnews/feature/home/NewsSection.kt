package com.example.mz_focusnews.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mz_focusnews.core.api.model.News
import com.example.mz_focusnews.core.theme.Gray_600
import com.example.mz_focusnews.core.theme.Today_Blue
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun NewsSection(newsState: NewsState, navigateToContent: (id: Long) -> Unit) {
    val pagerState = rememberPagerState { 3 } // 총 페이지 수 설정

    var text = ""
    var news: News? = null

    // 데이터 로딩 중이거나 null인 경우 처리
    if (newsState.newsGroup == null) {
        // 로딩 인디케이터 처리 고려중
        return
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 8.dp)
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> text = "오늘의 뉴스"
                1 -> text = "이주의 뉴스"
                2 -> text = "이달의 뉴스"
            }

            when (page) {
                0 -> news = newsState.newsGroup.day
                1 -> news = newsState.newsGroup.week
                2 -> news = newsState.newsGroup.month
            }

            news?.let { news ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(Today_Blue)
                        .height(185.dp)
                        .padding(start = 18.dp, top = 18.dp, end = 18.dp),

                    ) {
                    Text(
                        text = text,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Today_Blue)
                            .padding(start = 2.dp, bottom = 12.dp)
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Today_Blue)
                    ) {
                        Row {
                            // 뉴스 썸네일
                            AsyncImage(
                                model = news.imgUrl,
                                contentDescription = "News Image",
                                modifier = Modifier
                                    .width(158.dp)
                                    .height(114.dp)
                                    .background(Color.White),
                                contentScale = ContentScale.Crop
                            )

                            /**
                             * news description
                             */
                            Column(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(start = 12.dp, top = 4.dp)
                            ) {
                                // 뉴스 제목
                                Text(
                                    text = news.title,
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 15.sp
                                    ),
                                    modifier = Modifier.clickable { navigateToContent(news.id) }
                                )


                                // 뉴스 내용
                                Text(
                                    text = contentSplit(news.content),
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Gray_600,
                                        fontSize = 13.sp
                                    ),
                                    modifier = Modifier.padding(top = 6.dp)
                                )

                                // 뉴스 발행일
                                Text(
                                    text = news.date,
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 10.sp
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(top = 12.dp),
                                    textAlign = TextAlign.End
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

// 뉴스 content 스트링 자르는 함수
private fun contentSplit(content: String): String {
    val max = 25
    if (content.length <= max) return content

    val index = content.indexOf(' ', startIndex = max)

    return if (index != -1) {
        content.substring(0, index) + "..."
    } else {
        content.substring(0, max) + "..."
    }
}


@Preview
@Composable
fun NewsPreview() {
    NewsSection(NewsState(), { })
}
