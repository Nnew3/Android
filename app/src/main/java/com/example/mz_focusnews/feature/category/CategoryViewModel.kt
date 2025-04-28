package com.example.mz_focusnews.feature.category

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.NewsByCategory
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import com.example.mz_focusnews.core.components.CategoryType
import com.example.mz_focusnews.core.components.SortType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryViewModel : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _categoryState = MutableStateFlow(CategoryState())
    val categoryState: StateFlow<CategoryState> = _categoryState

    init {
        fetchNewsByCategory()
    }

    fun onCategorySelected(category: CategoryType) {
        Log.d("Retrofit", "onCategorySelected called:: ${category.value}")
        _categoryState.update { it.copy(selectedCategory = category, selectedSortType = SortType.BASIC)}
        fetchNewsByCategory()
    }

    fun onSortTypeSelected(sortType: SortType) {
        Log.d("Retrofit", "onSortTypeSelected called:: ${sortType.value}")
        _categoryState.update { it.copy(selectedSortType = sortType) }
        fetchNewsByCategory()
    }

    fun fetchNewsByCategory() {
        _categoryState.update { it.copy(isLoading = true) }
        // 로딩 시작
        Log.d(
            "Retrofit",
            "fetch news category = (${_categoryState.value.selectedCategory.value}, ${_categoryState.value.selectedSortType.value})"
        )
        viewModelScope.launch {
            try {
                val response = service.getNewsByCategory(
                    _categoryState.value.selectedCategory.value,
                    _categoryState.value.selectedSortType.value
                )
                _categoryState.update { it.copy(news = response.data, isLoading = false, isError = false) }
                Log.d("Retrofit", "fetchNewsByCategory called:: ${_categoryState.value}")

            } catch (e: Exception) {
                _categoryState.update { it.copy(isLoading = false, isError = true, errorMsg = e.message) }
                Log.e("Retrofit", "Category News Error: ${_categoryState.value.errorMsg}")
            }
        }
    }
}

data class CategoryState(
    val isLoading: Boolean = false,
    val selectedCategory: CategoryType = CategoryType.POLITICS, // default: politics
    val selectedSortType: SortType = SortType.BASIC, // default: basic
    val news: NewsByCategory? = null,
    val isError: Boolean = false,
    val errorMsg: String? = null
)
