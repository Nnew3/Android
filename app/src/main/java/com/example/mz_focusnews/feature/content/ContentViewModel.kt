package com.example.mz_focusnews.feature.content

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.NewsDetail
import com.example.mz_focusnews.core.api.model.RelatedNewsList
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

    private val _relatedState = MutableStateFlow(RelatedState())
    val relatedState: StateFlow<RelatedState> = _relatedState

    val newsId = savedStateHandle.get<String>("newsId")?.toLongOrNull() ?: 0L

    init {
        fetchNewsDetail(newsId)
        fetchRelatedNews(newsId)
    }

    fun fetchNewsDetail(newsId: Long) {
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
                _relatedState.value = RelatedState(relatedNewsList = response.data, isLoading = false, isError = false
                )
                Log.d("Retrofit", "fetchRelatedNews called:: ${_relatedState.value}")
            } catch (e: Exception) {
                _relatedState.value =
                    RelatedState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "Related News Error: ${_relatedState.value.errorMsg}")
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
