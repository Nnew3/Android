package com.example.mz_focusnews.feature.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.DelKeywordRequest
import com.example.mz_focusnews.core.api.model.MyPageInfo
import com.example.mz_focusnews.core.api.model.SetKeywordRequest
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _mypageState = MutableStateFlow(MyPageState())
    val mypageState: StateFlow<MyPageState> = _mypageState

    private val _setKeywordState = MutableStateFlow(SetKeywordState())
    val setKeywordState: StateFlow<SetKeywordState> = _setKeywordState

    private val _delKeywordState = MutableStateFlow(DelKeywordState())
    val delKeywordState: StateFlow<DelKeywordState> = _delKeywordState

    init {
        fetchMyPageInfo()
    }

    fun fetchMyPageInfo() {
        _mypageState.value = MyPageState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                val response = service.getMyPageInfo()
                _mypageState.value =
                    MyPageState(mypageInfo = response.data, isLoading = false, isError = false)

                Log.d("Retrofit", "fetchMyPageInfo called:: ${_mypageState.value}")
            } catch (e: Exception) {
                _mypageState.value =
                    MyPageState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "MyPage Info Error: ${_mypageState.value.errorMsg}")
            }
        }
    }

    fun setUserKeyword(id: Long, prevKeyword: String, newKeyword: String) {
        _setKeywordState.value = SetKeywordState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                service.setUserKeyword(
                    SetKeywordRequest(
                        id = id,
                        previousKeyword = prevKeyword,
                        newKeyword = newKeyword
                    )
                )

                _setKeywordState.value = SetKeywordState(isLoading = false, isError = false)

                fetchMyPageInfo()

                Log.d("Retrofit", "setKeyword called:: ${_setKeywordState.value}")
            } catch (e: Exception) {
                _setKeywordState.value =
                    SetKeywordState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "Set Keyword Error: ${_setKeywordState.value.errorMsg}")
            }
        }
    }

    fun delUserKeyword(userId: Long, keyword: String) {
        _delKeywordState.value = DelKeywordState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                service.deleteUserKeyword(
                    DelKeywordRequest(
                        userId = userId,
                        keyword = keyword
                    )
                )

                _delKeywordState.value = DelKeywordState(isLoading = false, isError = false)

                fetchMyPageInfo()

                Log.d("Retrofit", "delKeyword called:: ${_delKeywordState.value}")

            } catch (e: Exception) {
                _delKeywordState.value =
                    DelKeywordState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "Delete Keyword Error: ${_delKeywordState.value.errorMsg}")
            }
        }

    }
}

data class MyPageState(
    val mypageInfo: MyPageInfo? = null,
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMsg: String? = null
)

data class SetKeywordState(
    val isLoading: Boolean = false,
    val isError: Boolean? = null,
    val errorMsg: String? = null
)

data class DelKeywordState(
    val isLoading: Boolean = false,
    val isError: Boolean? = null,
    val errorMsg: String? = null
)
