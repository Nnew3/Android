package com.example.mz_focusnews.feature.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.MyIconPack
import com.example.mz_focusnews.core.theme.Today_Blue
import com.example.mz_focusnews.core.theme.myiconpack.Bell
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun UserGuideSection(mainInfoState: MainInfoState, onDrawerOpen: () -> Unit) {
    val nickname = mainInfoState.info?.nickname.orEmpty()
    val guideText = if (nickname.isEmpty()) {
        "존재하지 않는 사용자 입니다"
    } else {
        "안녕하세요, ${nickname}님!"
    }

    val todayDate = mainInfoState.info?.todayDate ?: "날짜 정보 없음"

    val weatherIcon = when (mainInfoState.info?.weather) {
        "clear" -> R.drawable.icon_clear
        "clouds" -> R.drawable.icon_scattered_clouds
        "rain" -> R.drawable.icon_rain
        "snow" -> R.drawable.icon_snow
        "thunderstorm" -> R.drawable.icon_thunderstorm
        else -> R.drawable.img_error_kitty
    }

    Surface {
        Column(
            modifier = Modifier
                .background(Bg_Blue)
                .fillMaxWidth()
        ) {
            Row {
                when {
                    mainInfoState.isLoading -> {
                        StatusCard(
                            bgColor = Today_Blue,
                            imgRes = R.drawable.img_loading_kitty,
                            msg = "사용자 정보를 가져오는 중이에요!"
                        )
                    }

                    mainInfoState.isError -> {
                        StatusCard(
                            bgColor = Today_Blue,
                            imgRes = R.drawable.img_network_kitty,
                            msg = "네트워크 오류가 발생했어요"
                        )
                    }

                    else -> {
                        Column {
                            Text(
                                text = guideText,
                                modifier = Modifier
                                    .padding(top = 20.dp, bottom = 12.dp)
                                    .background(Color.Transparent),
                                style = TextStyle(
                                    fontFamily = preFontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 24.sp
                                )
                            )

                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = todayDate,
                                    style = TextStyle(
                                        fontFamily = preFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 16.sp
                                    )
                                )

                                Image(
                                    modifier = Modifier
                                        .size(28.dp)
                                        .padding(start = 4.dp),
                                    painter = painterResource(weatherIcon),
                                    contentDescription = "Weather Image"
                                )
                            }
                        }
                    }
                }

                BreakingDrawerBtn(onDrawerOpen)
            }
        }
    }
}

@Composable
fun BreakingDrawerBtn(onDrawerOpen: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Bg_Blue)
            .padding(top = 10.dp)
    ) {
        IconButton(
            onClick = {
                onDrawerOpen()
            },
            modifier = Modifier
                .clip(RoundedCornerShape(12.dp))
                .size(56.dp)
                .background(Color.White)
                .align(Alignment.CenterEnd)
        ) {
            Icon(
                modifier = Modifier.padding(18.dp),
                imageVector = MyIconPack.Bell,
                contentDescription = null
            )
        }
    }
}