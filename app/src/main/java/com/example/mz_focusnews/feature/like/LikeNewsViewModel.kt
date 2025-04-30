package com.example.mz_focusnews.feature.like

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.NewsList
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LikeNewsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _likeNewsState = MutableStateFlow(LikeNewsState())
    val likeNewsState: StateFlow<LikeNewsState> = _likeNewsState

    val userId = savedStateHandle.get<String>("userId")?.toLongOrNull() ?: 0L

    init {
        fetchLikeNewsInfo(userId)
    }

    fun fetchLikeNewsInfo(userId: Long) {
        _likeNewsState.value = LikeNewsState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                val response = service.getLikeNewsList(userId)
                _likeNewsState.value = LikeNewsState(newsList = response.data, isLoading = false, isError = false)

                Log.d("Retrofit", "fetchLikeNewsInfo called:: ${_likeNewsState.value}")
            } catch (e: Exception) {
                _likeNewsState.value = LikeNewsState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "LikeNews Info Error: ${_likeNewsState.value.errorMsg}")
            }
        }
    }
}

data class LikeNewsState(
    val newsList: NewsList? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)
