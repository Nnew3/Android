package com.example.mz_focusnews.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Blue_300
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.Gray_600
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun RecommendedNewsSection() {
    val newsList = List(10) { "뉴스 제목 $it" }

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
                modifier = Modifier.padding(start = 8.dp)
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                horizontalArrangement = Arrangement.spacedBy(22.dp),
            ) {

                items(newsList) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .height(280.dp)
                            .width(210.dp)
                            .background(Color.White)
                            .padding(start = 16.dp, top = 20.dp, end = 16.dp, bottom = 14.dp),
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                        ) {
                            Image(
                                painter = painterResource(R.drawable.example2),
                                contentDescription = "exmaple image",
                                modifier = Modifier
                                    .height(130.dp)
                                    .background(Color.White),
                                contentScale = ContentScale.Crop
                            )

                            Text(
                                text = "\'인도에 진심\' 크래프톤, 현지 게임사 인수한다",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 14.sp
                                ),
                                modifier = Modifier.padding(top = 10.dp)
                            )

                            Text(
                                text = "크래프톤 인도법인, 인도 현지 게임 개발 스튜디오 지분 75% 매입",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    color = Gray_600,
                                    fontSize = 12.sp
                                ),
                                modifier = Modifier.padding(top = 8.dp)
                            )

                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.BottomEnd
                            ) {
                                Text(
                                    text = "1시간 30분 전",
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 9.sp,
                                        color = Blue_900
                                    ),
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(Blue_300)
                                        .padding(vertical = 4.dp, horizontal = 10.dp),
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

@Preview
@Composable
fun RecommendPreview() {
    RecommendedNewsSection()
}
