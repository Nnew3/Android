package com.example.mz_focusnews.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.NavItemBox
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.components.ToggleItemBox
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Gray_500
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun MyPageScreen(viewModel: MyPageViewModel, navController: NavController) {

    val mypageState = viewModel.mypageState.collectAsState().value

    var alarmChecked by remember { mutableStateOf(true) }
    var locationChecked by remember { mutableStateOf(false) }


    var openDialog by remember { mutableStateOf(false) }

    LaunchedEffect(mypageState.mypageInfo) {
        mypageState.mypageInfo?.let { info ->
            alarmChecked = info.alarm
            locationChecked = info.location
        }
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
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when {
                mypageState.isLoading -> {
                    StatusCard(
                        imgRes = R.drawable.img_loading_kitty,
                        msg = "사용자 정보를 불러오는 중이에요!"
                    )
                }

                // 에러 처리
                mypageState.isError -> {
                    StatusCard(
                        imgRes = R.drawable.img_network_kitty,
                        msg = "네트워크 오류가 발생했어요"
                    )
                }

                (mypageState.mypageInfo == null) ->
                    StatusCard(
                        imgRes = R.drawable.img_error_kitty,
                        msg = "존재하지 않는 사용자입니다"
                    )

                else -> {
                    UserInfoSection(mypageState)

                    QuizScoreSection()

                    KeywordSetSection(mypageState, onAddBtnClick = { openDialog = true })

                    NavItemBox("최근 본 뉴스", onClick = { navController.navigate("recent/${mypageState.mypageInfo.id}") })

                    NavItemBox("내가 좋아하는 뉴스", onClick = { navController.navigate("like/${mypageState.mypageInfo.id}") })

                    ToggleItemBox(
                        title = "속보 알림 수신 동의",
                        checked = alarmChecked,
                        onCheckedChange = { alarmChecked = it }
                    )

                    ToggleItemBox(
                        title = "위치 정보 이용 동의",
                        checked = locationChecked,
                        onCheckedChange = { locationChecked = it }
                    )

                    Text(
                        modifier = Modifier.clickable {
                            // TODO: logout 구현
                        },
                        text = "로그아웃",
                        style = TextStyle(
                            fontFamily = preFontFamily,
                            fontWeight = FontWeight.Medium,
                            fontSize = 14.sp,
                            color = Gray_500
                        )
                    )
                }
            }
        }

        if (openDialog) {
            AddKeywordDialog(
                onDismiss = { openDialog = false },
                onKeywordAdded = {

                })
        }
    }
}

@Preview
@Composable
fun MyPagePreview() {
    MyPageScreen(MyPageViewModel(), rememberNavController())
}