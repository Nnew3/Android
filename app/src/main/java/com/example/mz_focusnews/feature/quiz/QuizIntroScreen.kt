package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.QuizRuleDialog
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Gray_400
import com.example.mz_focusnews.core.theme.Today_Blue
import com.example.mz_focusnews.core.theme.Yellow_200
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun QuizIntroScreen(viewModel: QuizViewModel, navController: NavController) {

    val quizInfoState = viewModel.quizInfoState.collectAsState().value

    val checkReadTodayNews = remember { true }
    val gameBtnColor = if (checkReadTodayNews) Yellow_200 else Gray_400
    val btnTextColor = if (checkReadTodayNews) Color.Black else Color.White

    var openDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchQuizUserInfo()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Bg_Blue),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                modifier = Modifier.padding(top = 80.dp),
                text = "오 늘 의 상 식 키 우 기",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Bold,
                    fontSize = 28.sp
                )
            )

            Image(
                painter = painterResource(R.drawable.image_quiz_intro),
                contentDescription = "Quiz Intro image",
                modifier = Modifier
                    .size(220.dp)
                    .padding(top = 40.dp)
            )

            Row(
                modifier = Modifier
                    .padding(top = 14.dp)
                    .clickable { openDialog = true },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    modifier = Modifier.size(14.dp),
                    painter = painterResource(R.drawable.icon_quiz_info),
                    contentDescription = "Quiz Info Icon"
                )

                Text(
                    text = "상식 퀴즈 게임 안내",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp
                    )
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .background(Color.White, RoundedCornerShape(12.dp))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 30.dp)
                        .height(IntrinsicSize.Min),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    when {
                        quizInfoState.isLoading -> {
                            StatusCard(
                                imgRes = R.drawable.img_loading_kitty,
                                msg = "사용자 정보를 가져오는 중이에요"
                            )
                        }

                        quizInfoState.isError -> {
                            StatusCard(
                                imgRes = R.drawable.img_network_kitty,
                                msg = "네트워크 오류가 발생했어요"
                            )
                        }

                        (quizInfoState.quizUserInfo == null) -> {
                            StatusCard(
                                imgRes = R.drawable.img_error_kitty, msg = "사용자 정보가 존재하지 않아요"
                            )
                        }

                        else -> {
                            Text(
                                text = "현재 ${quizInfoState.quizUserInfo.nickname} 님의 상식 점수는",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            )

                            Text(
                                modifier = Modifier.padding(top = 5.dp),
                                text = "${quizInfoState.quizUserInfo.score} 점이에요!",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )
                            )
                        }
                    }
                }
            }

            TextButton(
                onClick = {
                    if (checkReadTodayNews) {
                        navController.navigate("quiz_play")
                    }
                },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .background(gameBtnColor, RoundedCornerShape(18.dp))
            ) {
                Text(
                    text = "게임 시작하기",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = btnTextColor
                    ),
                    modifier = Modifier.padding(horizontal = 28.dp)
                )
            }
        }

        if (!checkReadTodayNews) {
            TodayNewsGuide(navController)
        }
    }

    if (openDialog) {
        QuizRuleDialog(
            onDismiss = { openDialog = false }
        )
    }
}

@Composable
private fun TodayNewsGuide(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "오늘의 뉴스를 봐야 시작 버튼이 활성화 돼요!",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 12.sp
                )
            )

            Icon(
                modifier = Modifier.size(18.dp),
                painter = painterResource(R.drawable.icon_double_arrow),
                contentDescription = "Double Arrow"
            )

            Text(
                text = "오늘의 뉴스 보러가기",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Today_Blue, RoundedCornerShape(12.dp))
                    .clickable { navController.navigate("content") },
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.padding(vertical = 15.dp, horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.example2),
                        contentDescription = "Quiz Intro image",
                        modifier = Modifier.width(70.dp)
                    )

                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        text = "\'인도에 진심\' 크래프톤, 현지 게임사 인수한다",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun QuizIntroPreview() {
    QuizIntroScreen(QuizViewModel(), rememberNavController())
}