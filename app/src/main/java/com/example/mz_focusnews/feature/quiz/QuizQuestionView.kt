package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.example.mz_focusnews.core.components.QuizProgressBar
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Blue_800
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.Gray_400
import com.example.mz_focusnews.core.theme.Green
import com.example.mz_focusnews.core.theme.Red
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun QuizQuestionView(
    quiz: Quiz,
    userSelectedIndex: Int,
    isChecked: Boolean,
    isSubmitted: Boolean,
    currentQuestionIndex: Int, // 현재 문제 인덱스 추가
    onSelected: (selectedIndex: Int) -> Unit,
    onSubmitted: (submittedIndex: Int) -> Unit,
    showResultDialog: () -> Unit,
    remainingTime: Int
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Bg_Blue)
            .padding(top = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // 퀴즈 진행 바
        QuizProgressBar(currentQuestionIndex)

        // 이미지 - 정답/오답에 따라 다른 이미지 표시
        val imageRes = when {
            !isSubmitted -> R.drawable.img_quiz_play // 기본 이미지
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
                .padding(top = 30.dp, start = 30.dp, end = 30.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            quiz.options.forEachIndexed { optionIndex, option ->
                val bgColor = when {
                    isSubmitted && optionIndex == quiz.correctAnswer -> Green    // 정답
                    isSubmitted && optionIndex == userSelectedIndex -> Red  // 오답
                    isChecked && optionIndex == userSelectedIndex -> Blue_800   // 선택한 보기
                    else -> Gray_400    // 기본
                }

                Button(
                    onClick = { onSelected(optionIndex) },
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
                            .padding(vertical = 6.dp, horizontal = 6.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        if (isSubmitted) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomCenter
            ) {
                Text(
                    text = "${remainingTime}초 뒤 다음 문제로 넘어갑니다",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp
                    ),
                    modifier = Modifier.padding(bottom = 30.dp)
                )
            }
        } else {
            Row(
                modifier = Modifier
                    .padding(top = 24.dp, start = 30.dp, end = 30.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                QuizActionButton(
                    onClick = { showResultDialog() },
                    btnText = "그만두기"
                )

                QuizActionButton(
                    onClick = { onSubmitted(userSelectedIndex) },
                    btnText = "제출하기"
                )
            }
        }
    }
}

@Composable
private fun QuizActionButton(
    onClick: () -> Unit,
    btnText: String
) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue_900,
        )
    ) {
        Text(
            text = btnText,
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = Color.White
            ),
            modifier = Modifier
                .wrapContentWidth()
                .padding(vertical = 6.dp, horizontal = 20.dp),
            textAlign = TextAlign.Center
        )
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
                question = "다음 중 코틀린의 특징이 아닌 것은?",
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

        QuizQuestionView(
            quiz = sampleQuizzes[0],
            userSelectedIndex = -1,
            isChecked = false,
            isSubmitted = false,
            currentQuestionIndex = 0,
            onSelected = { },
            onSubmitted = { },
            showResultDialog = { },
            remainingTime = 5
        )
    }
}