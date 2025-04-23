package com.example.mz_focusnews.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    init {
        fetchNews()
    }

    fun fetchNews() {
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
}

data class NewsState(
    val newsGroup: NewsGroup? = null,
    val isLoading: Boolean = false  // 로딩 상태
)