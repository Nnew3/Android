package com.example.mz_focusnews.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlin.math.abs

private const val MULTIPLIER_SELECTED_PAGE = 4
private val baseWidth = 10.dp
private val spacing = 10.dp
private val height = 10.dp

@Composable
fun CustomIndicator(pagerState: PagerState) {

    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.Top
    ) {
        repeat(pagerState.pageCount) { iteration ->
            val color =
                if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray

            // 현재 페이지
            val currentPageWidth = baseWidth * (1 + (1 - abs(pagerState.currentPageOffsetFraction)) * MULTIPLIER_SELECTED_PAGE)

            // 이동하려는 페이지
            val targetPageWidth = baseWidth * (1 + abs(pagerState.currentPageOffsetFraction) * MULTIPLIER_SELECTED_PAGE)

            val width = when (iteration) {
                pagerState.currentPage -> currentPageWidth
                pagerState.targetPage -> targetPageWidth
                else -> baseWidth
            }

            Box(
                modifier = Modifier
                    .width(width)
                    .clip(CircleShape)
                    .background(color)
                    .height(height)
            )

            if (iteration != pagerState.pageCount - 1) {
                Spacer(modifier = Modifier.width(spacing))
            }
        }
    }

}