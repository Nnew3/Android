@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mz_focusnews.feature.mypage

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.theme.Yellow
import com.example.mz_focusnews.core.theme.preFontFamily
import com.example.mz_focusnews.core.util.keywordSplit

@Composable
fun KeywordSetSection(keyword: String, onCloseBtnClick:(keyword: String) -> Unit, onAddBtnClick: () -> Unit) {

    val keywordList = keywordSplit(keyword)
    val btnCounter = when (keywordList.size) {
        0, 2 -> 1
        1 -> 2
        else -> 0
    }

    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .height(106.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 2.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "이런 뉴스가 좋아요!",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.Medium,
                    fontSize = 15.sp
                )
            )

            Row(
                modifier = Modifier.height(32.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                keywordList.forEach { keyword ->
                    InputChip(
                        modifier = Modifier.clip(RoundedCornerShape(20.dp)),
                        onClick = { },
                        label = {
                            Text(
                                text = keyword,
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                )
                            )
                        },
                        selected = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "close icon",
                                modifier = Modifier
                                    .size(12.dp)
                                    .clickable {
                                        Log.d("Retrofit", "keyword = $keyword")
                                        onCloseBtnClick(keyword)
                                    }
                            )
                        },
                        colors = InputChipDefaults.inputChipColors(
                            selectedContainerColor = Yellow,
                            containerColor = Yellow,
                        )
                    )
                }

                repeat(btnCounter) {
                    IconButton(
                        onClick = {
                            onAddBtnClick()
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(50.dp))
                            .size(32.dp)
                            .background(Yellow)
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.icon_plus),
                            modifier = Modifier.padding(10.dp),
                            contentDescription = null
                        )
                    }
                }
            }
        }

    }
}

@Preview
@Composable
fun KeywordPreview() {
//    KeywordSetSection(listOf()) {}
}