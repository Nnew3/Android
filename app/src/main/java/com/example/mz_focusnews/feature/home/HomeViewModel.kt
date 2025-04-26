package com.example.mz_focusnews.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.BreakingNews
import com.example.mz_focusnews.core.api.model.NewsGroup
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _newsState = MutableStateFlow(NewsState())
    val newsState: StateFlow<NewsState> = _newsState

    private val _breakingState = MutableStateFlow(BreakingState())
    val breakingState: StateFlow<BreakingState> = _breakingState

    init {
        fetchMainNews()
        fetchBreakingNews()
    }

    fun fetchMainNews() {
        _newsState.value = NewsState(isLoading = true)  // 로딩 시작
        viewModelScope.launch {
            try {
                val response = service.getMainNewsData()
                _newsState.value = NewsState(newsGroup = response.data, isLoading = false)
                Log.d("Retrofit", _newsState.toString())
            } catch (e: Exception) {
                _newsState.value = NewsState(isLoading = false)
                Log.e("Retrofit", "Error")
            }
        }
    }

    fun fetchBreakingNews() {
        _breakingState.value = BreakingState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = service.getBreakingNews()
                _breakingState.value =
                    BreakingState(breakingNews = response.data, isLoading = false)
                Log.d("Retrofit", _breakingState.toString())
            } catch (e: Exception) {
                _breakingState.value = BreakingState(isLoading = false)
                Log.e("Retrofit", "Error")
            }
        }

    }
}

data class NewsState(
    val newsGroup: NewsGroup? = null,
    val isLoading: Boolean = false
)

data class BreakingState(
    val breakingNews: BreakingNews? = null,
    val isLoading: Boolean = false
)