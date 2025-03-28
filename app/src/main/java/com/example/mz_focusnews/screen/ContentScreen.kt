package com.example.mz_focusnews.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.example.mz_focusnews.ui.theme.Bg_Blue

@Composable
fun ContentScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
    ) {
        Text(
            text = "Content",
            style = MaterialTheme.typography.h1,
            textAlign = TextAlign.Center,
            color = Color.Black,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview
@Composable
fun ContentPreview() {
    ContentScreen()
}