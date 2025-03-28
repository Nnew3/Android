package com.example.mz_focusnews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.mz_focusnews.ui.theme.Bg_Blue
import com.example.mz_focusnews.ui.theme.Blue_900
import com.example.mz_focusnews.ui.theme.Gray_300

@Composable
fun BottomNavBar(
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
            .background(Bg_Blue)
            .clip(RoundedCornerShape(20.dp)),
        backgroundColor = Color.White
    ) {
        // navBackStackEntry를 가져와 목적지의 route를 가져옴
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach {
            /**
             * (1) icon image
             * (2) 언제 selected가 되는지
             * (3) selected 시 색상
             * (4) non selected 시 색상
             * (5) onClick
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
                selected = (currentRoute == it.route),
                selectedContentColor = Blue_900,
                unselectedContentColor = Gray_300,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let {
                            popUpTo(it) { saveState = true }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = false
            )
        }
    }
}