package com.example.mz_focusnews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.ui.theme.Bg_Blue
import com.example.mz_focusnews.ui.theme.Blue_900
import com.example.mz_focusnews.ui.theme.Gray_200

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.background(Bg_Blue),
        bottomBar = {
            BottomNavBar(
                navController = navController
            )
        }
    )
    {
        Box(
            modifier = Modifier.padding(it)
        ){
            NavGraph(navController = navController)
        }
    }
}

@Composable
private fun BottomNavBar(
    navController: NavHostController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Category,
        BottomNavItem.Quiz,
        BottomNavItem.MyPage
    )
    BottomNavigation(
        modifier = Modifier
            .height(80.dp)
            .clip(RoundedCornerShape(20.dp)),
        backgroundColor = Color.White
    ) {
        // navBackStackEntry를 가져와 목적지의 route를 가져옴
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            /**
             * TODO: botton navigation item에 관한 정보
             * (1) icon image
             * (2) label text
             * (3) 언제 selected가 되는지
             * (4) selected 시 색상
             * (5) non selected 시 색상
             * (6) onClick
             */
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title,
                        modifier = Modifier
                            .width(23.dp)
                            .height(23.dp),

                        )
                },
                selectedContentColor = Blue_900,
                unselectedContentColor = Gray_200,
                selected = (currentRoute == it.route),
                alwaysShowLabel = false,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    MainScreen()
}