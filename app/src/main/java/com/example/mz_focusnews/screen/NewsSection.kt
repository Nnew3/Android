package com.example.mz_focusnews.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.ui.theme.Gray_500
import com.example.mz_focusnews.ui.theme.Today_Blue
import com.example.mz_focusnews.ui.theme.preFontFamily

@Composable
fun NewsSection() {
    val pagerState = rememberPagerState { 3 } // 총 페이지 수 설정

    var text = ""

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp, bottom = 8.dp)
    ) {
        /**
         * TODO: 페이지 별로 다른 내용 렌더링되게 수정
         */
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            state = pagerState
        ) { page ->
            when (page) {
                0 -> text = "오늘의 뉴스"
                1 -> text = "이주의 뉴스"
                2 -> text = "이달의 뉴스"
            }

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
                        Image(
                            painter = painterResource(R.drawable.example),
                            contentDescription = "exmaple image",
                            modifier = Modifier
                                .width(158.dp)
                                .height(114.dp)
                                .background(Color.White),
                        )

                        /**
                         * news description
                         */
                        Column(
                            modifier = Modifier
                                .fillMaxHeight()
                                .padding(start = 12.dp, top = 4.dp)
                        ) {
                            Text(
                                text = "안동서 누각·재사 전소 추가 확인, 산불 피해 국가유산 27건",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp
                                )
                            )
                            Text(
                                text = "산불 사태로 인한 국가유산 피해 사례가 27건으로 ...",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Gray_500,
                                    fontSize = 13.sp
                                ),
                                modifier = Modifier.padding(top = 6.dp)
                            )
                            Text(
                                text = "2025.03.28",
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

@Preview
@Composable
fun NewsPreview() {
    NewsSection()
}
