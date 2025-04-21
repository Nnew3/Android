package com.example.mz_focusnews.core.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogWindowProvider
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.theme.Blue_300
import com.example.mz_focusnews.core.theme.Gray_600
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun QuizRuleDialog(onDismiss: () -> Unit) {
    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        (LocalView.current.parent as DialogWindowProvider).window.setDimAmount(0f)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Blue_300),
        ) {
            Column(
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 12.dp)
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier.size(25.dp),
                        painter = painterResource(R.drawable.icon_rule_kitty),
                        contentDescription = null
                    )

                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "오늘의 상식 키우기 규칙",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.Black)
                )

                GameRule(R.drawable.icon_one, "게임은 총 5라운드로 오늘의 퀴즈와 상식 퀴즈 네 개로\n구성되어 있어요")

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, top = 4.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "• 오늘의 퀴즈(50점): 오늘의 뉴스를 읽어야 풀 수 있는 문제",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp
                        )
                    )
                    Text(
                        text = "• 상식 퀴즈 (라운드별 배점 10~40점)",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 10.sp
                        )
                    )
                }


                GameRule(R.drawable.icon_two, "틀린 문제의 배점만큼 점수가 차감돼요")

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(14.dp),
                        painter = painterResource(R.drawable.icon_three),
                        contentDescription = null
                    )

                    Row(
                        modifier = Modifier.padding(start = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "게임 도중 ",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = preFontFamily,
                                fontSize = 12.sp
                            )
                        )

                        Box(
                            modifier = Modifier
                                .background(Color.Transparent)
                                .border(
                                    BorderStroke((0.5).dp, Color.Black),
                                    RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 4.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "그만두기",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 8.sp
                                )
                            )
                        }

                        Text(
                            text = "를 통해 현재 점수를 지킬 수 있어요",
                            style = TextStyle(
                                fontWeight = FontWeight.SemiBold,
                                fontFamily = preFontFamily,
                                fontSize = 12.sp
                            )
                        )
                    }
                }

                GameRule(R.drawable.icon_four, "하루에 한 번만 도전 할 수 있어요")
                GameRule(R.drawable.icon_five, "오늘의 퀴즈를 틀릴 시 바로 게임이 종료돼요")

                IconButton (
                    onClick = { onDismiss() },
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .size(16.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.icon_circle_close),
                        contentDescription = "Circle Close Button",
                        tint = Gray_600
                    )
                }
            }
        }
    }
}

@Composable
private fun GameRule(number: Int, rule: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            painter = painterResource(number),
            contentDescription = null
        )

        Text(
            modifier = Modifier.padding(start = 8.dp),
            text = rule,
            style = TextStyle(
                fontFamily = preFontFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp
            )
        )
    }
}

@Preview
@Composable
fun QuizRuleDialogPreview() {
    QuizRuleDialog({})
}