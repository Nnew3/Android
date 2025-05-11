package com.example.mz_focusnews.feature.quiz

import android.content.Context
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.components.QuizResultDialog
import com.example.mz_focusnews.core.theme.Bg_Blue
import kotlinx.coroutines.delay

@Composable
fun QuizPlayScreen(
    viewModel: QuizViewModel,
    navController: NavController
) {
    val questionState = viewModel.questionState.collectAsState().value

    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val userId = sharedPreferences.getLong("userId", -1)

    var currentQuestionIndex by remember { mutableIntStateOf(0) } // 현재 풀고 있는 퀴즈의 인덱스
    LaunchedEffect(currentQuestionIndex) { // 인덱스가 바뀔때마다 해당 인덱스의 문제 가져오기
        viewModel.fetchQuestion(currentQuestionIndex)
    }

    var selectedOptionId by remember { mutableIntStateOf(-1) } // 사용자가 선택한 보기
    var isChecked by remember { mutableStateOf(false) } // 사용자가 보기를 선택했는지 확인
    var isSubmitted by remember { mutableStateOf(false) } // 사용자가 응답을 제출했는지 확인

    var isCorrect by remember { mutableStateOf(false) } // 정답/오답 확인을 위한 상태

    var sec by remember { mutableIntStateOf(5) } // 다음 문제로 넘어가기까지 남은 시간

    var openDialog by remember { mutableStateOf(false) }

    var totalScore by remember { mutableIntStateOf(0) }

    LaunchedEffect(isSubmitted) {
        if (isSubmitted) {  // 응답을 제출했을 때 타이머 시작 (5초)
            for (time in 5 downTo 1) {
                sec = time
                delay(1000) // 1000 = 1초
            }

            if (currentQuestionIndex < 4) {
                currentQuestionIndex++  // 다음 문제로
                isSubmitted = false      // 상태 초기화
                isChecked = false
                selectedOptionId = -1 // 사용자 선택 초기화
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
        val q = questionState.question
        QuizQuestionView(
            quiz = q,
            userSelectedOption = selectedOptionId,
            isChecked = isChecked,
            isSubmitted = isSubmitted,
            currentQuestionIndex = currentQuestionIndex,
            onSelected = { optionId -> // 사용자가 선택한 옵션 id 변경, hasSelected 값 변경
                selectedOptionId = optionId
                isChecked = true
            },
            onSubmitted = { submittedOption ->
                isCorrect = (submittedOption == q?.answer)
                if (q != null) {
                    Log.d("Quiz", "submittedOption = $submittedOption, answer = $q.answer")
                    if (isCorrect) {
                        totalScore += q.score
                    } else {
                        totalScore -= q.score
                    }
                }
                isSubmitted = true
            },
            showResultDialog = {
                openDialog = true
            },
            remainingTime = sec
        )

        if (openDialog) {
            QuizResultDialog(
                totalScore = totalScore,
                navigateToIntro = {
                    viewModel.sendTotalScore(userId, totalScore)
                    navController.navigate("quiz") {
                        popUpTo(0) // 0 == 루트
                    }

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
    QuizPlayScreen(QuizViewModel(), rememberNavController())
}