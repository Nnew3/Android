package com.example.mz_focusnews.feature.mypage

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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.mz_focusnews.core.theme.Blue_300
import com.example.mz_focusnews.core.theme.Gray_200
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun AddKeywordDialog(
    onDismiss: () -> Unit,
    onKeywordAdded: (String) -> Unit
) {

    var text by remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { onDismiss() },
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 25.dp, bottom = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.image_dialog),
                    contentDescription = "Alert Image",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(bottom = 10.dp)
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    text = "키워드를 설정하여 관련 뉴스를 추천받아요!",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 14.sp,
                    ),
                    textAlign = TextAlign.Center
                )

                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, bottom = 12.dp),
                    text = "키워드는 최대 세 개까지 등록할 수 있어요",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp,
                    ),
                    textAlign = TextAlign.Center
                )

                TextField(
                    value = text,
                    onValueChange = { text = it },
                    maxLines = 1,
                    textStyle = TextStyle( // 입력된 텍스트 스타일 지정
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 10.sp, // 입력된 텍스트 크기 조절
                    ),
                    placeholder = {
                        Text(
                            text = "키워드를 입력해주세요",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                            ),
                        )
                    },
                    modifier = Modifier
                        .width(220.dp)
                        .height(42.dp)
                        .border(
                            border = BorderStroke(0.5.dp, Color.Black),
                            shape = RoundedCornerShape(24.dp)
                        ),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    )
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    TextButton(
                        onClick = { onDismiss() },
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .background(Gray_200, RoundedCornerShape(14.dp))
                            .height(28.dp)
                    ) {
                        Text(
                            text = "취소",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }

                    TextButton(
                        onClick = {
                            onKeywordAdded(text)
                            onDismiss()
                        },
                        modifier = Modifier
                            .padding(start = 24.dp)
                            .background(Blue_300, RoundedCornerShape(14.dp))
                            .height(28.dp)
                    ) {
                        Text(
                            text = "등록",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 10.sp,
                                color = Color.Black
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogPreview() {
    val keywordList = remember { mutableStateListOf("산불", "폭싹 속았수다", "미세먼지 위험 경보") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        AddKeywordDialog({ }, { })
    }
}