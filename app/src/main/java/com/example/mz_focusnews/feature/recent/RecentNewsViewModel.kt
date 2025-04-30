package com.example.mz_focusnews.feature.recent

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

class RecentNewsViewModel(
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _recentNewsState = MutableStateFlow(RecentNewsState())
    val recentNewsState: StateFlow<RecentNewsState> = _recentNewsState

    val userId = savedStateHandle.get<String>("userId")?.toLongOrNull() ?: 0L

    init {
        fetchRecentNewsInfo(userId)
    }

    fun fetchRecentNewsInfo(userId: Long) {
        _recentNewsState.value = RecentNewsState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                val response = service.getRecentNewsList(userId)
                _recentNewsState.value = RecentNewsState(newsList = response.data, isLoading = false, isError = false)

                Log.d("Retrofit", "fetchRecentNewsInfo called:: ${_recentNewsState.value}")
            } catch (e: Exception) {
                _recentNewsState.value = RecentNewsState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "RecentNews Info Error: ${_recentNewsState.value.errorMsg}")
            }
        }
    }
}

data class RecentNewsState(
    val newsList: NewsList? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)
