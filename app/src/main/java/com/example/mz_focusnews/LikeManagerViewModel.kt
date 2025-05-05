package com.example.mz_focusnews

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.NewsActionRequest
import com.example.mz_focusnews.core.api.model.NewsPreview
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * 전역에서 좋아요 상태를 관리하는 뷰 모델
 */
class LikeManagerViewModel : ViewModel() {
    val service = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _likeState = MutableStateFlow(LikeState())
    val likeState: StateFlow<LikeState> = _likeState

    private val _toggleState = MutableStateFlow(ToggleState())

    // 좋아요한 뉴스를 담을 리스트
    private val _likeNewsList = MutableStateFlow<List<NewsPreview>>(emptyList())
    val likeNewsList: StateFlow<List<NewsPreview>> = _likeNewsList

    // 좋아요한 뉴스들의 ID
    private val _likedIds = MutableStateFlow<Set<Long>>(emptySet())
    val likedIds: StateFlow<Set<Long>> = _likedIds


    fun fetchLikeNews(userId: Long) {
        _likeState.value = LikeState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                val response = service.getLikeNewsList(userId)
                val list = response.data.newsList

                _likeState.value = LikeState(isLoading = false, isError = false)

                _likeNewsList.value = list // Preview 객체까지 다 저장
                _likedIds.value = list.map { it.id }.toSet() // id만 저장
            } catch (e: Exception) {
                _likeState.value = LikeState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Like", "fetchLikeNews error: ${_likeState}errorMsg")

            }
        }
    }

    fun toggleLike(userId: Long, newsId: Long) {
        _toggleState.value = ToggleState(isLoading = true)  // 로딩 시작

        val currentSet = _likedIds.value
        val isLike = currentSet.contains(newsId)

        // 이미 좋아요인 상태 -> 좋아요 취소 -> 리스트에서 삭제
        // 아니면 -> 좋아요 등록 -> 리스트에 추가
        _likedIds.value = if (isLike) currentSet - newsId else currentSet + newsId

        Log.d("Like", "TOGGLE: before = $isLike")
        viewModelScope.launch {
            try {
                if (isLike) service.unlikeNews(NewsActionRequest(userId, newsId))
                else service.likeNews(NewsActionRequest(userId, newsId))

                _toggleState.value = ToggleState(isLoading = false, isError = false)

                Log.d("Like", "TOGGLE: after = ${!isLike}")
            } catch (e: Exception) {
                _toggleState.value = ToggleState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Like", "toggleLike error: ${_toggleState}errorMsg")

                fetchLikeNews(userId)
            }
        }
    }
}

data class LikeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class ToggleState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)