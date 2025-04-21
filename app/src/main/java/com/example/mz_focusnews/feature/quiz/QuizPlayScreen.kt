package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.components.QuizResultDialog
import com.example.mz_focusnews.core.theme.Bg_Blue
import kotlinx.coroutines.delay

@Composable
fun QuizPlayScreen(navController: NavController) {

    var currentQuestionIndex by remember { mutableStateOf(0) } // 현재 풀고 있는 퀴즈의 인덱스

    var userSelectedIndex by remember { mutableStateOf(-1) } // 사용자가 선택한 보기
    var isChecked by remember { mutableStateOf(false) } // 사용자가 보기를 선택했는지 확인
    var isSubmitted by remember { mutableStateOf(false) } // 사용자가 응답을 제출했는지 확인

    var isCorrect by remember { mutableStateOf(false) } // 정답/오답 확인을 위한 상태

    var sec by remember { mutableStateOf(5) } // 다음 문제로 넘어가기까지 남은 시간

    var openDialog by remember { mutableStateOf(false) }

    var totalScore by remember { mutableStateOf(0) }
    val quizScoreMap = mapOf(
        0 to 50,
        1 to 10,
        2 to 20,
        3 to 30,
        4 to 40
    )

    val sampleQuizList = listOf(
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
            question = "다음 중 JVM에서 실행되지 않는 언어는?",
            options = listOf(
                "Java",
                "Kotlin",
                "Python",
                "Scala"
            ),
            correctAnswer = 2
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
            question = "다음 중 비동기 처리 방법이 아닌 것은?",
            options = listOf(
                "Future",
                "Callback",
                "Thread",
                "Wait/Notify"
            ),
            correctAnswer = 2
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

    val currentQuiz = (sampleQuizList[currentQuestionIndex])

    LaunchedEffect(isSubmitted) {
        if (isSubmitted) {  // 응답을 제출했을 때 타이머 시작 (5초)
            for (time in 5 downTo 1) {
                sec = time
                delay(1000) // 1000 = 1초
            }

            if (currentQuestionIndex < sampleQuizList.size - 1) {
                currentQuestionIndex++  // 다음 문제로
                isSubmitted = false      // 상태 초기화
                isChecked = false
                userSelectedIndex = -1 // 사용자 선택 초기화
            } else {
                openDialog = true
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        QuizQuestionView(
            quiz = currentQuiz,
            userSelectedIndex = userSelectedIndex,
            isChecked = isChecked,
            isSubmitted = isSubmitted,
            currentQuestionIndex = currentQuestionIndex,
            onSelected = { selectedIndex -> // 사용자가 선택한 인덱스 변경, hasSelected 값 변경
                userSelectedIndex = selectedIndex
                isChecked = true
            },
            onSubmitted = { submittedIndex ->
                isCorrect = submittedIndex == currentQuiz.correctAnswer
                if (isCorrect) {
                    totalScore += quizScoreMap[currentQuestionIndex] ?: 0
                } else{
                    totalScore -= quizScoreMap[currentQuestionIndex] ?: 0
                }
                isSubmitted = true
            },
            showResultDialog = {
                openDialog = true
            },
            remainingTime = sec
        )

        if(openDialog){
            QuizResultDialog(
                totalScore = totalScore,
                navigateToIntro = {
                    navController.navigate("quiz_intro")
                    openDialog = false
                },
                onDismiss = { } // do nothing
            )
        }

    }
}

@Preview
@Composable
fun QuizPlayPreview() {
    QuizPlayScreen(rememberNavController())
}