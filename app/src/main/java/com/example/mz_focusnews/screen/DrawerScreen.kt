package com.example.mz_focusnews.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.ui.theme.Blue_300
import com.example.mz_focusnews.ui.theme.preFontFamily

@Composable
fun DrawerScreen() {
    val notiList = List(10) { "$it 번째 속보입니다" }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 18.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            Text(
                modifier = Modifier
                    .background(Color.White)
                    .padding(start = 4.dp),
                text = "알림",
                style = TextStyle(
                    color = Color.Black,
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            )

            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(top = 20.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                notiList.forEach() { noti ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(12.dp))
                            .background(Blue_300)
                            .padding(horizontal = 18.dp, vertical = 18.dp)
                    ) {
                        Text(
                            text = noti,
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 15.sp
                            ),
                        )
                    }

                }
            }
        }
    }
}

@Preview
@Composable
fun BreakingDrawerPreview() {
    DrawerScreen()
}