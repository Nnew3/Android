package com.example.mz_focusnews.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.preFontFamily
import com.example.mz_focusnews.core.util.stringSplit

@Composable
fun BreakingSection(breakingState: BreakingState, navigateToContent: (id: Long) -> Unit) {

    val news = breakingState.breakingNews?.breakingNewsRecent

    val (breakingText, newsId) = when {
        breakingState.isError -> "잠시 후 다시 시도해 주세요" to 0L
        breakingState.isLoading -> "속보를 불러오고 있어요" to 0L
        news == null -> "오늘 올라온 속보가 없어요!" to 0L
        else -> stringSplit(max = 35, news.title) to news.id
    }

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Bg_Blue)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(12.dp))
                    .padding(horizontal = 14.dp, vertical = 13.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.icon_breaking),
                    contentDescription = "breaking news icon",
                    modifier = Modifier.size(25.dp)
                )

                Text(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .clickable {
                            if (newsId != 0L) {
                                navigateToContent(newsId)
                            }
                        },
                    text = breakingText,
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.5.sp
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun BreakingPreview() {
    BreakingSection(BreakingState(), { })
}