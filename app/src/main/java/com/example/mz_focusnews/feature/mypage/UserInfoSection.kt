package com.example.mz_focusnews.feature.mypage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.theme.Blue_400
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun UserInfoSection(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 55.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(R.drawable.example),
            contentDescription = "user image",
            modifier = Modifier
                .size(125.dp)
                .clip(RoundedCornerShape(100.dp))
                .background(Blue_400)
        )
        Text(
            modifier = Modifier.padding(top = 14.dp),
            text = "래로미",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        )
        Text(
            modifier = Modifier.padding(top = 4.dp),
            text = "fofhal@gmail.com",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        )
    }
}