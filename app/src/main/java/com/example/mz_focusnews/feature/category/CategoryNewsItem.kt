package com.example.mz_focusnews.feature.category

import androidx.compose.foundation.Image
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
import androidx.navigation.NavController
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.theme.Icon_Gray
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun CategoryNewsItem(navController: NavController, news: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(120.dp)
            .background(Color.White)
            .padding(16.dp)
            .clickable {
                navController.navigate("content")
            },
    ) {
        Row(
            modifier = Modifier.fillMaxHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.example2),
                contentDescription = "exmaple image",
                modifier = Modifier
                    .width(130.dp)
                    .background(Color.White),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier.padding(start = 12.dp)
            ) {
                Text(
                    text = news,
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
                        tint = Icon_Gray
                    )

                    Text(
                        text = "한국 경제",
                        textAlign = TextAlign.Center,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Icon_Gray,
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
                        tint = Icon_Gray
                    )

                    Text(
                        text = "6시간 30분 전",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = Icon_Gray,
                            fontSize = 12.sp
                        ),
                        modifier = Modifier.padding(start = 3.dp)
                    )
                }
            }

        }
    }
}