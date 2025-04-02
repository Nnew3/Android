package com.example.mz_focusnews.feature.mypage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.components.NavItemBox
import com.example.mz_focusnews.core.components.ToggleItemBox
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.Blue_400
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.Gray_500
import com.example.mz_focusnews.core.theme.Gray_600
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun MyPageScreen(navController: NavController) {

    var alarmChecked by remember { mutableStateOf(true) }
    var locationChecked by remember { mutableStateOf(true) }

    val keywordList = remember { mutableStateListOf("산불", "폭싹 속았수다", "미세먼지 위험 경보") }

    var openDialog by remember { mutableStateOf(false) }


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

            UserInfoSection()

            QuizScoreSection()

            KeywordSetSection(keywordList, onAddBtnClick = { openDialog = true })

            NavItemBox("최근 본 뉴스", onClick = { navController.navigate("recent") })

            NavItemBox("내가 좋아하는 뉴스", onClick = { navController.navigate("like") })

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

        if (openDialog) {
            AddKeywordDialog(
                onDismiss = { openDialog = false },
                onKeywordAdded = { keywordList.add(it) })
        }
    }
}

@Preview
@Composable
fun MyPagePreview() {
    MyPageScreen(rememberNavController())
}