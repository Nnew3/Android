package com.example.mz_focusnews.feature.category

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
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
import com.example.mz_focusnews.core.theme.Gray_500
import com.example.mz_focusnews.core.theme.preFontFamily
import com.example.mz_focusnews.core.util.stringSplit

@Composable
fun CategoryNewsItem(news: NewsRes, navigateToContent: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(120.dp)
            .background(Color.White)
            .padding(16.dp)
            .clickable {
                navigateToContent()
            },
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = news.imgUrl,
                contentDescription = "News Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .width(130.dp)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = stringSplit(max = 35, news.title) ,
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                )

                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(R.drawable.icon_publisher),
                        contentDescription = "Publisher Icon",
                        tint = Gray_500
                    )

                    Text(
                        text = news.publisher,
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Gray_500,
                            fontSize = 12.sp
                        ),
                        modifier = Modifier.padding(start = 2.dp)
                    )
                }
                Row(
                    modifier = Modifier.padding(top = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(15.dp),
                        painter = painterResource(R.drawable.icon_published_time),
                        contentDescription = "Published Time Icon",
                        tint = Gray_500
                    )

                    Text(
                        text = news.newsTime,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Gray_500,
                            fontSize = 12.sp
                        ),
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }
        }
    }
}