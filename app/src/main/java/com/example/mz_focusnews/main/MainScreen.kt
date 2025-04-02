package com.example.mz_focusnews.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.DrawerValue
import androidx.compose.material.ModalDrawer
import androidx.compose.material.rememberDrawerState
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.components.BottomNavBar
import com.example.mz_focusnews.core.components.DrawerScreen
import com.example.mz_focusnews.core.navigation.NavGraph
import com.example.mz_focusnews.core.theme.Bg_Blue
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalDrawer( // TODO: drawer 열리는 방향 RtL로 변경!
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        drawerState = drawerState,
        drawerContentColor = Color.White,
        drawerBackgroundColor = Color.White,
        drawerContent = {
            ModalDrawerSheet {
                DrawerScreen()
            }
        },
        gesturesEnabled = drawerState.isOpen
    ) {
        Scaffold(
            modifier = Modifier.background(Bg_Blue),
            bottomBar = {
                BottomNavBar(navController = navController)
            }
        )
        {
            Box(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(Bg_Blue)
            ) {
                NavGraph(navController = navController, onDrawerOpen = {
                    coroutineScope.launch {
                        drawerState.open()
                    }
                })
            }
        }
    }
}

@Preview
@Composable
fun MainPreview() {
    MainScreen()
}