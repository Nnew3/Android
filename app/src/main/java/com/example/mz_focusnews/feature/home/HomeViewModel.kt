package com.example.mz_focusnews.feature.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.BreakingNews
import com.example.mz_focusnews.core.api.model.NewsGroup
import com.example.mz_focusnews.core.api.model.NewsResponse
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

    private val _userNewsState = MutableStateFlow(UserNewsState())
    val userNewsState: StateFlow<UserNewsState> = _userNewsState

    init {
        fetchMainNews()
        fetchBreakingNews()
        fetchUserNews()
    }

    fun fetchMainNews() {
        _newsState.value = NewsState(isLoading = true)  // 로딩 시작
        viewModelScope.launch {
            try {
                val response = service.getMainNews()
                _newsState.value = NewsState(newsGroup = response.data, isLoading = false)
                Log.d("Retrofit", "fetchMainNews called:: ${_newsState.value}")
            } catch (e: Exception) {
                _newsState.value =
                    NewsState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "Main News Error: ${_newsState.value.errorMsg}")
            }
        }
    }

    fun fetchBreakingNews() {
        _breakingState.value = BreakingState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = service.getBreakingNews()
                _breakingState.value =
                    BreakingState(breakingNews = response.data, isLoading = false, isError = false)
                Log.d("Retrofit", "fetchBreakingNews called:: ${_breakingState.value}")
            } catch (e: Exception) {
                _breakingState.value =
                    BreakingState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "Breaking News Error: ${_newsState.value.errorMsg}")
            }
        }
    }

    fun fetchUserNews(){
        _userNewsState.value = UserNewsState(isLoading = true)
        viewModelScope.launch {
            try {
                val response = service.getUserNews()
                _userNewsState.value =
                    UserNewsState(news = response.data, isLoading = false, isError = false)
                Log.d("Retrofit", "fetchRecommendNews called:: ${_userNewsState.value}")

            } catch (e: Exception) {
                _userNewsState.value = UserNewsState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "Recommend News Error: ${_userNewsState.value.errorMsg}")
            }
        }
    }
}

data class NewsState(
    val newsGroup: NewsGroup? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class BreakingState(
    val breakingNews: BreakingNews? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class UserNewsState(
    val news: NewsResponse? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)