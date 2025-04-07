package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.RoundedCornerBox
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Gray_250
import com.example.mz_focusnews.core.theme.Gray_400
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun QuizQuestionView(choices: List<String>) {

    val questionList = listOf("50점", "10점", "20점", "30점", "40점")

    Column(
        modifier = Modifier.fillMaxWidth().background(Bg_Blue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            questionList.forEach { question ->
                ScoreBox("50점", Gray_250)
            }
        }

        Image(
            painter = painterResource(R.drawable.img_quiz_play),
            contentDescription = "Quiz Kitty",
            modifier = Modifier
                .padding(top = 70.dp)
                .size(180.dp)
        )

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "Q. 푸바오의 정체는?",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            )
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 40.dp, end = 40.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            choices.forEach { choice ->
                RoundedCornerBox(radius = 28.dp, paddingHorizontal = 16.dp, paddingVertical = 14.dp, bgColor = Gray_400) {
                    Text(
                        text = choice,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            color = Color.White
                        ),
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}

@Composable
fun ScoreBox(score: String, color: Color) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Image(
            painter = painterResource(R.drawable.img_quiz_score),
            contentDescription = "Score Icon",
            modifier = Modifier.size(32.dp)
        )

        Box(
            modifier = Modifier
                .background(color, RoundedCornerShape(8.dp))
                .border(
                    BorderStroke(1.dp, Color.Black),
                    RoundedCornerShape(8.dp)
                )
                .padding(horizontal = 11.dp, vertical = 5.dp)
        ) {
            Text(
                text = score,
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp
                )
            )
        }
    }
}

@Preview
@Composable
fun QuizQuestionPreview() {
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        QuizQuestionView(listOf("너구리", "곰", "판다", "오스트랄로피테쿠스"))
    }
}