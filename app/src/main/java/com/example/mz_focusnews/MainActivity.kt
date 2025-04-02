package com.example.mz_focusnews

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.mz_focusnews.core.theme.Bg_Blue
import com.example.mz_focusnews.core.theme.MZ_FocusNews_Theme
import com.example.mz_focusnews.main.MainScreen

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
