package com.example.mz_focusnews.feature.content

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.NewsActionRequest
import com.example.mz_focusnews.core.api.model.NewsDetail
import com.example.mz_focusnews.core.api.model.RelatedNewsList
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ContentViewModel : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _contentState = MutableStateFlow(ContentState())
    val contentState: StateFlow<ContentState> = _contentState

    private val _relatedState = MutableStateFlow(RelatedState())
    val relatedState: StateFlow<RelatedState> = _relatedState

    private val _logState = MutableStateFlow(LogState())
    val logState: StateFlow<LogState> = _logState

    fun fetchNewsDetail(userId: Long, newsId: Long) {
        logWatchNews(1, newsId)
        _contentState.value = ContentState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                val response = service.getNewsDetail(newsId)
                _contentState.value =
                    ContentState(newsDetail = response.data, isLoading = false, isError = false)
                Log.d("Retrofit", "fetchNewsDetail called:: ${_contentState.value}")
            } catch (e: Exception) {
                _contentState.value =
                    ContentState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "News Detail Error: ${_contentState.value.errorMsg}")
            }
        }
    }

    fun fetchRelatedNews(newsId: Long) {
        _relatedState.value = RelatedState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                val response = service.getRelatedNews(newsId)
                _relatedState.value = RelatedState(
                    relatedNewsList = response.data,
                    isLoading = false,
                    isError = false
                )

                Log.d("Retrofit", "fetchRelatedNews called:: ${_relatedState.value}")
            } catch (e: Exception) {
                _relatedState.value =
                    RelatedState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "Related News Error: ${_relatedState.value.errorMsg}")
            }
        }
    }

    fun logWatchNews(userId: Long, newsId: Long) {
        _logState.value = LogState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                service.logWatchNews(
                    NewsActionRequest(userId, newsId)
                )

                _logState.value = LogState(isLoading = false, isError = false)

                Log.d("Retrofit", "logRecentNews called($userId, $newsId):: ${_logState.value}")
            } catch (e: Exception) {
                _logState.value =
                    LogState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "Log RecentNews News Error: ${_logState.value.errorMsg}")
            }
        }
    }
}

data class ContentState(
    val newsDetail: NewsDetail? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class RelatedState(
    val relatedNewsList: RelatedNewsList? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class LogState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)