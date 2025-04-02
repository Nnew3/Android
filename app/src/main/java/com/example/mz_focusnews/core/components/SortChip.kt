@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mz_focusnews.core.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.core.theme.Blue_400
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.preFontFamily

enum class SortType(val label: String) {
    BASIC("기본순"), // 크롤링순
    POPULAR("인기순"),
    RECENT("최신순")
}

@Composable
fun SortChip(
    sortType: SortType,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    FilterChip(
        selected = isSelected,
        onClick = { onClick() },
        label = {
            Text(
                text = sortType.label,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Blue_400, // 선택된 배경색
            selectedLabelColor = Blue_900, // 선택된 텍스트 색
            containerColor = Color.White, // 기본 배경색
            labelColor = Color.Black // 기본 텍스트 색

        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Color.White,
            selectedBorderColor = Blue_400,
        )
    )
}

@Preview
@Composable
fun ChipPreview() {

}