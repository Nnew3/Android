package com.example.mz_focusnews.feature.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.components.UserNewsItem
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Gray_100
import com.example.mz_focusnews.core.theme.preFontFamily

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserNewsSection(userNewsState: UserNewsState, navigateToContent: (Long) -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Column(
            modifier = Modifier.background(Bg_Blue)
        ) {
            Text(
                text = "무슨 일이 일어나고 있나요?",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                ),
                modifier = Modifier.padding(start = 8.dp, bottom = 14.dp)
            )

            userNewsState.news?.let { news ->
                when {
                    userNewsState.isLoading -> {
                        StatusCard(
                            imgRes = R.drawable.img_loading_kitty,
                            msg = "뉴스를 가져오는 중이에요"
                        )
                    }


                    userNewsState.isError -> {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            StatusCard(
                                bgColor = Gray_100,
                                imgRes = R.drawable.img_network_kitty,
                                msg = "네트워크 오류가 발생했어요"
                            )
                        }
                    }

                    news.newsResList.isEmpty() -> {
                        StatusCard(
                            bgColor = Gray_100,
                            imgRes = R.drawable.img_error_kitty,
                            msg = "추천 뉴스가 없어요"

                        )
                    }

                    else -> LazyRow(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(22.dp),
                    ) {
                        items(news.newsResList) { news ->
                            UserNewsItem(navigateToContent, news)
                        }
                    }
                }

            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun UserNewsPreview() {
    UserNewsSection(UserNewsState(), { })
}
