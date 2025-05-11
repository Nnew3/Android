package com.example.mz_focusnews.main

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mz_focusnews.LikeManagerViewModel
import com.example.mz_focusnews.checkAndRequestPermission
import com.example.mz_focusnews.core.components.BottomNavBar
import com.example.mz_focusnews.core.components.BottomNavItem
import com.example.mz_focusnews.core.components.DrawerScreen
import com.example.mz_focusnews.core.navigation.NavGraph
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.feature.home.HomeViewModel
import com.example.mz_focusnews.feature.mypage.MyPageViewModel
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainScreen() {
    val context = LocalContext.current

    val permissions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        arrayOf(
            Manifest.permission.POST_NOTIFICATIONS,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    } else {
        TODO("VERSION.SDK_INT < TIRAMISU")
    }

    val launcherMultiplePermissions = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissionMap ->
        val allGranted = permissionMap.values.reduce { acc, next -> acc && next }
        if (allGranted) {
            Log.d("Permission", "권한이 동의되었습니다")
        } else {
            Log.d("Permission", "권한이 거부되었습니다")
        }
    }

    val navController = rememberNavController()

    var bottomNavBar = true
    val cur = navController.currentBackStackEntryAsState().value
    cur?.destination?.route?.let { route ->
        bottomNavBar = when (route) {
            BottomNavItem.Home.route, BottomNavItem.Category.route, BottomNavItem.Quiz.route, BottomNavItem.MyPage.route -> true
            else -> false
        }
    }

    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    val homeVm: HomeViewModel = viewModel()
    val breakingState = homeVm.breakingState.collectAsState().value
    val breakingList = breakingState.breakingNews?.breakingNews

    val myPageVm: MyPageViewModel = viewModel()
    val mypageState = myPageVm.mypageState.collectAsState().value

    val likeManagerVm: LikeManagerViewModel = viewModel()

    // 권한 받아오기
    LaunchedEffect(Unit) {
        checkAndRequestPermission(context, permissions, launcherMultiplePermissions)
    }

    // 앱 시작 시점에 MyPage API를 통해 userId를 받아와 SharedPreferences에 저장
    // 로그인 연동 전까지 사용할 임시 코드
    LaunchedEffect(mypageState.mypageInfo) {
        mypageState.mypageInfo?.let { info ->
            val userId = info.id

            Log.d("Retrofit", "now user id: $userId")

            context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                .edit().putLong("userId", userId).apply()

            likeManagerVm.fetchLikeNews(userId)
        }
    }

    HideSystemBars()

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
                if (bottomNavBar) {
                    BottomNavBar(navController = navController)
                }
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
                    homeViewModel = homeVm,
                    myPageViewModel = myPageVm,
                    likeManagerViewModel = likeManagerVm,
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

@Composable
fun HideSystemBars() {
    val view = LocalView.current
    SideEffect {
        val window = (view.context as Activity).window
        val windowInsetsController = WindowCompat.getInsetsController(window, view)
        // 탐색 바(하단 바)만 숨기기
        windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
        // 시스템 UI가 다시 나타나지 않도록 동작 설정 (선택 사항)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun MainPreview() {
    MainScreen()
}