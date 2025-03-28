package com.example.mz_focusnews.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.mz_focusnews.R

// 외부 폰트 등록
val preFontFamily = FontFamily(
    Font(R.font.pre_regular),
    Font(R.font.pre_medium),
    Font(R.font.pre_semibold, FontWeight.SemiBold),
    Font(R.font.pre_bold, FontWeight.Bold)
)

@Composable
fun MZ_FocusNews_Theme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        typography = Typography(defaultFontFamily = preFontFamily),
        content = content
    )
}