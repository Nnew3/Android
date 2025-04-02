package com.example.mz_focusnews.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mz_focusnews.R

@Composable
fun BackBtn(navController: NavController){
    IconButton(
        onClick = {
            navController.popBackStack()
        },
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .size(48.dp)
            .background(Color.White)
    ) {
        Icon( // TODO: 나중에 svg로 바꿀 것(?)
            painter = painterResource(R.drawable.icon_back),
            modifier = Modifier.padding(18.dp),
            contentDescription = null
        )
    }
}