package com.example.mz_focusnews.main

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.core.components.BottomNavBar
import com.example.mz_focusnews.core.components.DrawerScreen
import com.example.mz_focusnews.core.navigation.NavGraph
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.feature.home.HomeViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val viewModel: HomeViewModel = viewModel()
    val breakingState = viewModel.breakingState.collectAsState().value

    val breakingList = breakingState.breakingNews?.breakingNews

    ModalDrawer( // TODO: drawer 열리는 방향 RtL로 변경!
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        drawerState = drawerState,
        drawerContentColor = Color.White,
        drawerBackgroundColor = Color.White,
        drawerContent = {
            ModalDrawerSheet {
                DrawerScreen(
                    onDrawerClosed = {
                        coroutineScope.launch {
                            drawerState.close()
                        }
                    },
                    breakingNews = breakingList,
                    navigateToContent = { newsId -> navController.navigate("content/$newsId") })
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
                NavGraph(
                    navController = navController,
                    homeViewModel = viewModel,
                    onDrawerOpen = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MainPreview() {
    MainScreen()
}