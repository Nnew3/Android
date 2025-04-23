package com.example.mz_focusnews.feature.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.BackBtn
import com.example.mz_focusnews.core.components.BookmarkBtn
import com.example.mz_focusnews.core.components.RoundedCornerBox
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.MyIconPack
import com.example.mz_focusnews.core.theme.myiconpack.Bookmark
import com.example.mz_focusnews.core.theme.myiconpack.NonBookmark
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun ContentScreen(navController: NavController) {

    var isBookmarked by remember { mutableStateOf(false) } // 북마크 여부

    val iconRes = if (isBookmarked) MyIconPack.Bookmark else MyIconPack.NonBookmark

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
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Bg_Blue),
                contentAlignment = Alignment.Center
            ) {
                BackBtn(navController, modifier = Modifier.align(Alignment.CenterStart))

                Text(
                    text = "news",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.Medium,
                        fontSize = 22.sp
                    ),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center
                )

                BookmarkBtn(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { isBookmarked = !isBookmarked },
                    iconRes = iconRes
                )
            }

            LazyColumn {
                item {
                    Image(
                        painter = painterResource(R.drawable.example2),
                        contentDescription = "exmaple image",
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .height(210.dp),
                        contentScale = ContentScale.Crop
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 15.dp),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        Text(
                            text = "의대교수 사직서 제출 행렬…정부 \"5월 2천명 증원 마무리\" 쐐기",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )

                        RoundedCornerBox(
                            radius = 18.dp, bgColor = Blue_900
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "정부와 의료계 대화가 둔화되면서 의대 교수들이 사직서를 제출하고 있다.",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                ),
                                color = Color.White
                            )
                        }

                        RoundedCornerBox(
                            radius = 18.dp, bgColor = Blue_900
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "정부와 의료계 대화가 둔화되면서 의대 교수들이 사직서를 제출하고 있다.",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                ),
                                color = Color.White
                            )
                        }

                        RoundedCornerBox(
                            radius = 18.dp, bgColor = Blue_900
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "정부와 의료계 대화가 둔화되면서 의대 교수들이 사직서를 제출하고 있다.",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                ),
                                color = Color.White
                            )
                        }
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 25.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = "의대교수 사직서에 대해 더 알고싶다면?",
                            style = TextStyle(
                                fontFamily = preFontFamily,
                                fontWeight = FontWeight.Medium,
                                fontSize = 16.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp)
                        )

                        RoundedCornerBox(
                            radius = 18.dp, bgColor = Color.White
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "정부와 의료계 대화가 둔화되면서 의대 교수들이 사직서를 제출하고 있다.",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                            )
                        }

                        RoundedCornerBox(
                            radius = 18.dp, bgColor = Color.White
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "정부와 의료계 대화가 둔화되면서 의대 교수들이 사직서를 제출하고 있다.",
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ContentPreview() {
    ContentScreen(rememberNavController())
}