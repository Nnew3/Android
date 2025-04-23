package com.example.mz_focusnews.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.core.api.model.BreakingItem
import com.example.mz_focusnews.core.theme.Blue_300
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun DrawerScreen(
    onDrawerClosed: () -> Unit,
    breakingNews: List<BreakingItem>,
    navigateToContent: (id: Long) -> Unit
) {
    if (breakingNews.isEmpty()) {
        return
    }

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
            breakingNews.let { news ->
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .padding(top = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    news.forEach { item ->
                        RoundedCornerBox(
                            radius = 12.dp,
                            bgColor = Blue_300,
                            onClick = {
                                navigateToContent(item.id)
                                onDrawerClosed()
                            }) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = item.title,
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
}

@Preview
@Composable
fun BreakingDrawerPreview() {
    DrawerScreen({ }, listOf()) { }
}