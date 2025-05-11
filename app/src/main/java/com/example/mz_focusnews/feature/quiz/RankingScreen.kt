package com.example.mz_focusnews.feature.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mz_focusnews.R
import com.example.mz_focusnews.core.components.StatusCard
import com.example.mz_focusnews.core.theme.Bg_Blue

@Composable
fun RankingScreen(rankingState: RankingState) {

    /**
     * TODO: 0점도 순위에 포함시킬지 고민중
     */
    val rankingList = rankingState.ranking?.ranking ?: emptyList()

    val topRanking = rankingList
        .filter { it.score > 0 }
        .take(3)

    val remainingRanking = rankingList - topRanking.toSet()

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Bg_Blue)
            .padding(25.dp)
    ) {
        Column(
            modifier = Modifier
                .background(Bg_Blue)
                .padding(top = 65.dp)
        ) {
            when {
                rankingState.isLoading -> {
                    StatusCard(
                        imgRes = R.drawable.img_loading_kitty,
                        msg = "사용자 랭킹을 가져오는 중이에요"
                    )
                }

                rankingState.isError -> {
                    StatusCard(
                        imgRes = R.drawable.img_network_kitty,
                        msg = "네트워크 오류가 발생했어요"
                    )
                }

                (rankingState.ranking == null) -> {
                    StatusCard(
                        imgRes = R.drawable.img_error_kitty, msg = "랭킹이 존재하지 않아요"
                    )
                }

                else -> {
                    // 상위 1~3등 Podium
                    RankingPodiumSection(topRanking)

                    // 나머지 유저 랭킹
                    RankingListSection(remainingRanking)
                }
            }
        }
    }
}

@Preview
@Composable
fun RankingPreview() {
    RankingScreen(RankingState())
}