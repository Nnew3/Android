package com.example.mz_focusnews.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun StatusCard(
    bgColor: Color = Color.Transparent,
    radius: Dp = 12.dp,
    showImage: Boolean = true,
    imgRes: Int,
    msg: String
) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(radius)),
        colors = CardDefaults.cardColors(
            containerColor = bgColor
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
                .background(Color.Transparent),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showImage) {
                Image(
                    modifier = Modifier
                        .size(60.dp)
                        .padding(bottom = 12.dp),
                    painter = painterResource(imgRes),
                    contentDescription = "Error Kitty Image",
                )
            }

            Text(
                text = msg,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    color = Color.Black,
                    fontSize = 14.sp
                )
            )
        }
    }
}
