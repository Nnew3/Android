package com.example.mz_focusnews.core.components

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.api.model.NewsRes
import com.example.mz_focusnews.core.theme.Blue_300
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.Gray_600
import com.example.mz_focusnews.core.theme.preFontFamily
import com.example.mz_focusnews.core.util.calculateRelativeDate
import com.example.mz_focusnews.core.util.stringSplit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun UserNewsItem(
    navigateToContent: (Long) -> Unit,
    news: NewsRes
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(310.dp)
            .width(220.dp)
            .background(Color.White)
            .padding(
                start = 16.dp,
                top = 20.dp,
                end = 16.dp,
                bottom = 14.dp
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            AsyncImage(
                model = news.imgUrl,
                contentDescription = "News Image",
                modifier = Modifier
                    .height(130.dp)
                    .background(Color.White)
                    .clickable { navigateToContent(news.id) },
                contentScale = ContentScale.Crop
            )

            Text(
                text = stringSplit(max = 35, news.title),
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp
                ),
                modifier = Modifier
                    .padding(top = 10.dp)
                    .clickable { navigateToContent(news.id) },
            )

            Text(
                text = stringSplit(max = 50, news.content),
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Gray_600,
                    fontSize = 13.sp
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .clickable { navigateToContent(news.id) },
            )

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Blue_300)
                        .padding(
                            vertical = 4.dp,
                            horizontal = 8.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(R.drawable.icon_published_time),
                        contentDescription = "Time Icon",
                        tint = Blue_900
                    )

                    Text(
                        text = calculateRelativeDate(news.newsTime),
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 11.sp,
                            color = Blue_900
                        ),
                        modifier = Modifier.padding(start = 4.dp),
                        textAlign = TextAlign.End
                    )
                }

            }
        }
    }
}