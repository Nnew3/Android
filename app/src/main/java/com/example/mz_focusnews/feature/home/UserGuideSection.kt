package com.example.mz_focusnews.feature.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.core.theme.MyIconPack
import com.example.mz_focusnews.core.theme.myiconpack.Bell
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun UserGuideSection(onDrawerOpen: () -> Unit) {

    Surface {
        Column(
            modifier = Modifier
                .background(Bg_Blue)
                .fillMaxWidth()
        ) {
            Row {
                Text(
                    text = "안녕하세요, 사용자님!",
                    modifier = Modifier
                        .padding(top = 20.dp, bottom = 12.dp)
                        .background(Color.Transparent),
                    style = TextStyle(
                        fontFamily = preFontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp
                    )
                )

                BreakingDrawerBtn(onDrawerOpen)
            }
            Text(
                text = "2025년 3월 11일 ☀\uFE0F",
                style = TextStyle(
                    fontFamily = preFontFamily, fontWeight = FontWeight.Medium, fontSize = 16.sp
                )
            )
        }
    }
}

@Composable
fun BreakingDrawerBtn(onDrawerOpen: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Bg_Blue)
            .padding(top = 10.dp)
    ) {
        IconButton(
            onClick = {
                onDrawerOpen()
            },
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(56.dp)
                .background(Color.White)
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                modifier = Modifier.padding(18.dp),
                imageVector = MyIconPack.Bell,
                contentDescription = null
            )
        }
    }
}