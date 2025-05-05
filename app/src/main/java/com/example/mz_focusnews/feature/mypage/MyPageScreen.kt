package com.example.mz_focusnews.feature.mypage

import android.Manifest
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.platform.LocalContext
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
    val setKeywordState = viewModel.setKeywordState.collectAsState().value
    val delKeywordState = viewModel.delKeywordState.collectAsState().value

    val keywords by viewModel.keywords.collectAsState() // 전역 키워드 구독

    val isAlarm by viewModel.isAlarm.collectAsState()
    var locationChecked by remember { mutableStateOf(false) }

    var openDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    // 등록 성공 시 Toast
    when (setKeywordState.isError) {
        false -> {
            Toast.makeText(context, "키워드를 등록했어요! ☺️", Toast.LENGTH_SHORT).show()
            setKeywordState.isError = null
        }

        true -> {
            Toast.makeText(context, "키워드를 등록하지 못했어요 🥹", Toast.LENGTH_SHORT).show()
            setKeywordState.isError = null
        }

        null -> {}
    }

    // 삭제 성공 시 Toast
    when (delKeywordState.isError) {
        false -> {
            Toast.makeText(context, "키워드를 삭제했어요! ☺️", Toast.LENGTH_SHORT).show()
            delKeywordState.isError = null
        }

        true -> {
            Toast.makeText(context, "키워드를 삭제하지 못했어요 🥹", Toast.LENGTH_SHORT).show()
            delKeywordState.isError = null
        }

        null -> {}
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

                    KeywordSetSection(
                        keyword = keywords,
                        onCloseBtnClick = { keyword ->
                            viewModel.delUserKeyword(mypageState.mypageInfo.id, keyword)
                        },

                        onAddBtnClick = { openDialog = true }
                    )

                    NavItemBox(
                        title = "최근 본 뉴스",
                        onClick = { navController.navigate("recent/${mypageState.mypageInfo.id}") })

                    NavItemBox(
                        title = "내가 좋아하는 뉴스",
                        onClick = { navController.navigate("like/${mypageState.mypageInfo.id}") })

                    ToggleItemBox(
                        title = "속보 알림 수신 동의",
                        checked = isAlarm,
                        onCheckedChange = {
                            viewModel.toggleAlarm(userId = 1, it)
                        }
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
                onKeywordAdded = { prev, new ->
                    mypageState.mypageInfo?.let { viewModel.setUserKeyword(it.id, prev, new) }
                }
            )
        }
    }
}

@Preview
@Composable
fun MyPagePreview() {
    MyPageScreen(MyPageViewModel(), rememberNavController())
}