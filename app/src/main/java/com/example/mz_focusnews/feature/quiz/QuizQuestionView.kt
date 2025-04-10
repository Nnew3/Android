package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
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
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Blue
import com.example.mz_focusnews.core.theme.Gray_250
import com.example.mz_focusnews.core.theme.Gray_400
import com.example.mz_focusnews.core.theme.Red
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun QuizQuestionView(
    quiz: Quiz,
    userSelectedIndex: Int,
    isAnswered: Boolean,
    currentQuestionIndex: Int, // 현재 문제 인덱스 추가
    onAnswerSelected: (index: Int) -> Unit
) {
    val questionList = listOf("50점", "10점", "20점", "30점", "40점")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Bg_Blue),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(top = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            questionList.forEachIndexed { index, score ->
                // 현재 문제 인덱스와 일치하고 답변했으면 색상 변경
                val boxColor = if (index == currentQuestionIndex && isAnswered) {
                    if (userSelectedIndex == quiz.correctAnswer) Blue else Red
                } else {
                    Gray_250
                }
                ScoreBox(score, boxColor)
            }
        }

        // 이미지 - 정답/오답에 따라 다른 이미지 표시
        val imageRes = when {
            !isAnswered -> R.drawable.img_quiz_play // 기본 이미지
            userSelectedIndex == quiz.correctAnswer -> R.drawable.img_quiz_answer // 정답 이미지
            else -> R.drawable.img_quiz_wrong // 오답 이미지
        }

        Image(
            painter = painterResource(imageRes),
            contentDescription = "Quiz Kitty",
            modifier = Modifier
                .padding(top = 80.dp)
                .size(180.dp)
        )

        Text(
            modifier = Modifier.padding(top = 30.dp),
            text = "Q. ${quiz.question}",
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            ),
            textAlign = TextAlign.Center
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, start = 40.dp, end = 40.dp)
                .clickable {

                },
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            quiz.options.forEachIndexed { index, option ->
                val bgColor = if (isAnswered) {
                    when {
                        index == quiz.correctAnswer -> Blue // 정답: 파란색
                        index == userSelectedIndex && index != quiz.correctAnswer -> Red // 틀린 선택: 빨간색
                        else -> Gray_400
                    }
                } else {
                    Gray_400 // 아직 답변하지 않았으면 회색
                }

                Button(
                    onClick = { onAnswerSelected(index) },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = bgColor,
                        disabledContainerColor = bgColor
                    )
                ) {
                    Text(
                        text = option,
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (isAnswered) {
            Text(
                text = "5초 뒤 다음 문제로 넘어갑니다",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                textAlign = TextAlign.Center
            )
        }
    }
}

// TODO: 색상 유지하도록 수정할 것
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
                    fontSize = 10.sp
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
        val sampleQuizzes = listOf(
            Quiz(
                question = "다음 중 코틀린(Kotlin)의 특징이 아닌 것은?",
                options = listOf(
                    "Null 안정성 지원",
                    "스마트 캐스팅",
                    "JVM에서 실행 가능",
                    "수동 메모리 관리 필요"
                ),
                correctAnswer = 3
            ),
            Quiz(
                question = "안드로이드에서 UI 스레드로 작업하는 방법은?",
                options = listOf(
                    "Handler 사용",
                    "Runnable 사용",
                    "Coroutine 사용",
                    "Dispatchers.Main 사용"
                ),
                correctAnswer = 0
            ),
            Quiz(
                question = "다음 중 코틀린에서 'null safety'를 제공하는 기능은?",
                options = listOf(
                    "NullPointerException",
                    "?.",
                    "?!!.",
                    "None of the above"
                ),
                correctAnswer = 1
            )
        )

        // 첫 번째 퀴즈 표시(실제 앱에서는 상태에 따라 변경)
        QuizQuestionView(
            quiz = sampleQuizzes[0],
            userSelectedIndex = -1,  // 아무것도 선택되지 않음
            isAnswered = true,
            currentQuestionIndex = 0, // 현재 인덱스 추가
            onAnswerSelected = { index -> /* 프리뷰에서는 아무 작업 없음 */ }
        )
    }
}