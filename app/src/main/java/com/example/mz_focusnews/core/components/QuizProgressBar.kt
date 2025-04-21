package com.example.mz_focusnews.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.Gray_100
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun QuizProgressBar(currentQuestionIndex: Int) {
    var quizProgress by remember { mutableStateOf(0f) }

    fun calculateProgressWidth(): Float{
        quizProgress = (currentQuestionIndex+1).toFloat() / 5
        return quizProgress
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Transparent),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${currentQuestionIndex+1} / 5",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp
            )
        )

        LinearProgressIndicator(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .padding(horizontal = 30.dp),
            progress = calculateProgressWidth(),
            trackColor = Gray_100,
            color = Blue_900,
            strokeCap = StrokeCap.Round,
        )
    }
}




@Preview
@Composable
fun ProgressBarPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        QuizProgressBar(0)
    }
}