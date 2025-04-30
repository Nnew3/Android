package com.example.mz_focusnews.feature.content

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.BackBtn
import com.example.mz_focusnews.core.components.BookmarkBtn
import com.example.mz_focusnews.core.components.RoundedCornerBox
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.MyIconPack
import com.example.mz_focusnews.core.theme.Today_Blue
import com.example.mz_focusnews.core.theme.myiconpack.Bookmark
import com.example.mz_focusnews.core.theme.myiconpack.NonBookmark
import com.example.mz_focusnews.core.theme.preFontFamily
import com.example.mz_focusnews.core.util.summaryToThree

@Composable
fun ContentScreen(
    viewModel: ContentViewModel,
    newsId: String,
    navController: NavController
) {
    val contentState = viewModel.contentState.collectAsState().value
    val relatedState = viewModel.relatedState.collectAsState().value

    var isBookmarked by remember { mutableStateOf(false) } // 북마크 여부
    val iconRes = if (isBookmarked) MyIconPack.Bookmark else MyIconPack.NonBookmark

    val context = LocalContext.current

    LaunchedEffect(newsId) {
        val id = newsId.toLongOrNull() ?: 0L
        viewModel.fetchNewsDetail(id) // id가 변경될 때마다 새로운 데이터를 가져오도록 호출
        viewModel.fetchRelatedNews(id)
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Bg_Blue),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Bg_Blue),
                contentAlignment = Alignment.Center
            ) {
                BackBtn(navController, modifier = Modifier.align(Alignment.CenterStart))

                Text(
                    text = "news",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )

                BookmarkBtn(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { isBookmarked = !isBookmarked },
                    iconRes = iconRes
                )
            }

            /**
             * News Detail
             */
            LazyColumn {
                item {
                    when {
                        // 로딩중
                        contentState.isLoading -> {
                            StatusCard(
                                imgRes = R.drawable.img_loading_kitty,
                                msg = "뉴스를 가져오는 중이에요!"
                            )
                        }

                        // 에러 처리
                        contentState.isError -> {
                            StatusCard(
                                bgColor = Today_Blue,
                                imgRes = R.drawable.img_network_kitty,
                                msg = "네트워크 오류가 발생했어요"
                            )
                        }

                        (contentState.newsDetail == null) -> {
                            StatusCard(
                                bgColor = Today_Blue,
                                imgRes = R.drawable.image_error_kitty,
                                msg = "해당 뉴스의 정보가 없습니다"
                            )
                        }

                        else -> {
                            // 뉴스 썸네일
                            AsyncImage(
                                model = contentState.newsDetail.imgUrl,
                                contentDescription = "News Image",
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .height(210.dp)
                                    .clickable {
                                        openWebPage(context, contentState.newsDetail.link)
                                    },
                                contentScale = ContentScale.Crop
                            )

                            // 뉴스 내용
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 15.dp),
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {

                                // 뉴스 제목
                                Text(
                                    text = contentState.newsDetail.title,
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp
                                    )
                                )

                                // 3줄 요약
                                // summary는 총 세 문장으로만 이루어져있다 (온점도 단 세개만 존재)
                                val sentences = summaryToThree(contentState.newsDetail.summary)
                                Column(
                                    verticalArrangement = Arrangement.spacedBy(15.dp)
                                ) {
                                    sentences.forEach { sentence ->
                                        RoundedCornerBox(
                                            radius = 18.dp,
                                            bgColor = Blue_900
                                        ) {
                                            Text(
                                                modifier = Modifier.fillMaxWidth(),
                                                text = sentence,
                                                style = TextStyle(
                                                    fontFamily = preFontFamily,
                                                    fontWeight = FontWeight.SemiBold,
                                                    fontSize = 14.sp
                                                ),
                                                color = Color.White
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        when {
                            // 로딩중
                            relatedState.isLoading -> {
                                StatusCard(
                                    imgRes = R.drawable.img_loading_kitty,
                                    msg = "뉴스를 가져오는 중이에요!"
                                )
                            }

                            // 에러 처리
                            relatedState.isError -> {
                                StatusCard(
                                    imgRes = R.drawable.img_network_kitty,
                                    msg = "네트워크 오류가 발생했어요"
                                )
                            }

                            (relatedState.relatedNewsList == null ||
                                    relatedState.relatedNewsList.relatedNewsResList.isEmpty()) -> {
                                StatusCard(
                                    imgRes = R.drawable.image_error_kitty,
                                    msg = "관련 뉴스가 없습니다"
                                )
                            }

                            // 정상 응답 처리
                            else -> {
                                Text(
                                    text = "의대교수 사직서에 대해 더 알고싶다면?",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    ),
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(start = 10.dp)
                                )

                                relatedState.relatedNewsList.relatedNewsResList.forEach { news ->
                                    RoundedCornerBox(
                                        radius = 18.dp,
                                        bgColor = Color.White
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .clickable {
                                                    navController.navigate("content/${news.id}")
                                                },
                                            text = news.title,
                                            style = TextStyle(
                                                fontFamily = preFontFamily,
                                                fontWeight = FontWeight.SemiBold,
                                                fontSize = 14.sp
                                            )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

// 웹으로 이동하는 함수
private fun openWebPage(context: Context, url: String) {
    if (url.isEmpty()) return

    val customTabsIntent = CustomTabsIntent.Builder()
        .build()

    customTabsIntent.launchUrl(context, Uri.parse(url))
}

@Preview
@Composable
fun ContentPreview() {
    ContentScreen(viewModel(), "0", rememberNavController())
}