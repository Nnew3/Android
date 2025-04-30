package com.example.mz_focusnews.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun TopSection(title: String, navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Bg_Blue),
        contentAlignment = Alignment.CenterStart
    ) {
        BackBtn(navController)

        Text(
            text = title,
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent),
            style = TextStyle(
                fontFamily = preFontFamily, fontWeight = FontWeight.SemiBold, fontSize = 20.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}