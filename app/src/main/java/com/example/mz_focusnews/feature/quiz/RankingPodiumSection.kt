package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.theme.Blue_700
import com.example.mz_focusnews.core.theme.Blue_800
import com.example.mz_focusnews.core.theme.Blue_900
import com.example.mz_focusnews.core.theme.Today_Blue
import com.example.mz_focusnews.core.theme.preFontFamily

@Composable
fun RankingPodiumSection() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        listOf(2, 1, 3).forEach { rank ->
            PodiumBox(rank = rank)
        }
    }
}

@Composable
fun PodiumBox(rank: Int) {
    val height = when (rank) {
        1 -> 240.dp
        2 -> 190.dp
        3 -> 130.dp
        else -> 0.dp
    }

    val color = when (rank) {
        1 -> Blue_900
        2 -> Blue_800
        3 -> Blue_700
        else -> Color.LightGray
    }

    val userImage = when (rank) {
        1 -> R.drawable.img_person1
        2 -> R.drawable.img_person2
        3 -> R.drawable.img_person3
        else -> R.drawable.img_person4
    }

    val icon = when (rank) {
        1 -> R.drawable.icon_rank1
        2 -> R.drawable.icon_rank2
        3 -> R.drawable.icon_rank3
        else -> R.drawable.example
    }

    val iconSize = when (rank) {
        1 -> 50.dp
        2 -> 40.dp
        3 -> 30.dp
        else -> 0.dp
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier.padding(bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {

            Image(
                painter = painterResource(userImage),
                contentDescription = "exmaple image",
                modifier = Modifier
                    .padding(bottom = 5.dp)
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop
            )

            Text(
                text = "유저 이름",
                style = TextStyle(
                    fontFamily = preFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp,
                )
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .background(Today_Blue)
                    .padding(vertical = 10.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = "2,500점",
                    style = TextStyle(
                        fontFamily = preFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 12.sp,
                        color = Color.White
                    )
                )
            }
        }

        Box(
            modifier = Modifier
                .height(height)
                .width(112.dp)
                .clip(RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp))
                .background(color),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier.size(iconSize),
                painter = painterResource(icon),
                tint = Color.White,
                contentDescription = null
            )
        }
    }
}


@Composable
@Preview
fun RankingPodiumPreview(){
    RankingPodiumSection()
}