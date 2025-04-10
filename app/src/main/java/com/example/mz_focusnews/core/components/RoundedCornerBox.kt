package com.example.mz_focusnews.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCornerBox(
    radius: Dp,
    paddingHorizontal: Dp = 18.dp,
    paddingVertical: Dp = 18.dp,
    bgColor: Color, content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(radius))
            .background(bgColor)
            .padding(horizontal = paddingHorizontal, vertical = paddingVertical)
    ) {
        content()
    }
}