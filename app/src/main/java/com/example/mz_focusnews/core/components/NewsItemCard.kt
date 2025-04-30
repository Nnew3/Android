package com.example.mz_focusnews.core.components

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mz_focusnews.core.api.model.News
import com.example.mz_focusnews.core.theme.Gray_600
import com.example.mz_focusnews.core.theme.Today_Blue
import com.example.mz_focusnews.core.theme.preFontFamily
import com.example.mz_focusnews.core.util.stringSplit

@Composable
fun NewsItemCard(
    text: String,
    news: News,
    navigateToContent: (id: Long) -> Unit
) {
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
                        text = stringSplit(max = 25, news.content),
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