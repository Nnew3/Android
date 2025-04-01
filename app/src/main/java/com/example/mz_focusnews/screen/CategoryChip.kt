@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mz_focusnews.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.ui.theme.Blue_900
import com.example.mz_focusnews.ui.theme.Category_Blue
import com.example.mz_focusnews.ui.theme.preFontFamily

enum class CategoryType(val label: String) {
    POLITICS("정치"),
    ECONOMY("경제"),
    SOCIETY("사회"),
    REGION("지역"),
    LIFE_CULTURE("생활/문화"),
    IT_SCIENCE("IT/과학"),
    ENTERTAINMENT("연예"),
    SPORTS("스포츠"),
    KEYWORD("키워드")
}


@Composable
fun CategoryChip(
    category: String, isSelected: Boolean,
    onClick: () -> Unit
) {

    // 인기순 필터칩
    FilterChip(
        modifier = Modifier.padding(vertical = 4.dp),
        selected = isSelected,
        onClick = { onClick() },
        label = {
            Text(
                text = category,
                textAlign = TextAlign.Center,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.5.sp
                )
            )
        },
        colors = FilterChipDefaults.filterChipColors(
            selectedContainerColor = Category_Blue, // 선택된 배경색
            selectedLabelColor = Blue_900, // 선택된 텍스트 색
            containerColor = Category_Blue, // 기본 배경색
            labelColor = Color.Black // 기본 텍스트 색

        ),
        border = FilterChipDefaults.filterChipBorder(
            borderColor = Category_Blue,
            selectedBorderColor = Category_Blue,
        )
    )
}


@Preview
@Composable
fun CategoryChipPreview() {
    val categories = listOf("정치", "경제", "사회", "지역", "생활/문화", "IT/과학", "연예", "스포츠", "키워드")


}