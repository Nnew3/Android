package com.example.mz_focusnews.core.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun BookmarkBtn(modifier: Modifier = Modifier, onClick: () -> Unit, iconRes: ImageVector) {
    IconButton(
        onClick = {
            onClick()
        },
        modifier = modifier
            .clip(RoundedCornerShape(16.dp))
            .size(48.dp)
            .background(Color.White)
    ) {
        Icon(
            imageVector = iconRes,
            modifier = Modifier.size(17.dp),
            contentDescription = null,
        )
    }
}

