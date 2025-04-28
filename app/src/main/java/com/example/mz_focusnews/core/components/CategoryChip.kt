@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mz_focusnews.core.components

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.Category_Blue
import com.example.mz_focusnews.core.theme.preFontFamily

/**
 * category = politics → 정치
 * category = economic → 경제
 * category = region → 지역
 * category = culture → 생활/문화
 * category = science → IT/과학
 * category = entertainment → 연예
 * category = sports → 스포츠
 * category = keyword → 키워드
 */
enum class CategoryType(val label: String, val value: String) { // label: UI 표시, value: API 호출용
    POLITICS("정치", "politics"),
    ECONOMY("경제", "economic"),
    SOCIETY("사회", "society"),
    REGION("지역", "region"),
    LIFE_CULTURE("생활/문화", "culture"),
    IT_SCIENCE("IT/과학", "science"),
    ENTERTAINMENT("연예", "entertainment"),
    SPORTS("스포츠", "sports"),
    KEYWORD("키워드", "keyword")
}


@Composable
fun CategoryChip(
    category: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
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