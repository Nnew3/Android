package com.example.mz_focusnews.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.theme.Yellow_200
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun QuizResultDialog(
    totalScore: Int,
    navigateToIntro: () -> Unit,
    onDismiss: () -> Unit
) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(20.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(12.dp))
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 40.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            verticalAlignment = Alignment.Bottom,
                            horizontalArrangement = Arrangement.spacedBy(5.dp)

                        ) {
                            Image(
                                painter = painterResource(R.drawable.icon_rule_kitty),
                                contentDescription = "Quiz Kitty",
                                modifier = Modifier.size(40.dp)
                            )

                            Text(
                                text = "퀴즈가 종료되었습니다!",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 16.sp
                                )
                            )
                        }

                        Text(
                            modifier = Modifier.padding(top = 20.dp),
                            text = "획득한 점수는 총 ${totalScore}점입니다",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                        )
                    }
                }

                Button(
                    modifier = Modifier.padding(bottom = 20.dp),
                    onClick = { navigateToIntro() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Yellow_200,
                    )
                ) {
                    Text(
                        text = "처음 화면으로 돌아가기",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 14.sp,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .wrapContentWidth()
                            .padding(vertical = 6.dp, horizontal = 25.dp),
                        textAlign = TextAlign.Center
                    )
                }

            }
        }
    }
}


@Preview
@Composable
fun QuizResultDialogPreview() {
    QuizResultDialog(
        totalScore = 150,
        navigateToIntro = { },
        onDismiss = { }
    )
}