package com.example.mz_focusnews.feature.content

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.NewsDetail
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContentViewModel(
    savedStateHandle: SavedStateHandle // NavController로 넘긴 인자를 받기 위함
) : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _contentState = MutableStateFlow(ContentState())
    val contentState: StateFlow<ContentState> = _contentState

    val newsId = savedStateHandle.get<String>("newsId")?.toLongOrNull() ?: 0L

    init {
        fetchNewsDetail(newsId)
        Log.d("Retrofit", "init news id = $newsId")
    }

    fun fetchNewsDetail(newsId: Long) {
        _contentState.value = ContentState(isLoading = true)  // 로딩 시작

        Log.d("Retrofit", "fetch news id = $newsId")
        viewModelScope.launch {
            try {
                val response = service.getNewsDetail(newsId)
                _contentState.value = ContentState(news = response.data, isLoading = false)
                Log.d("Retrofit", _contentState.toString())
            } catch (e: Exception) {
                _contentState.value = ContentState(isLoading = false)
                Log.e("Retrofit", "Error")
            }
        }
    }
}

data class ContentState(
    val news: NewsDetail? = null,
    val isLoading: Boolean = false
)
