package com.example.mz_focusnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.mz_focusnews.screen.MainScreen
import com.example.mz_focusnews.ui.theme.Bg_Blue
import com.example.mz_focusnews.ui.theme.MZ_FocusNews_Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MZ_FocusNews_Theme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Bg_Blue)
                ) {
                    MainScreen()
                }
            }
        }
    }
}
