package com.example.mz_focusnews.feature.mypage

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mz_focusnews.core.api.model.MyPageInfo
import com.example.mz_focusnews.core.api.service.ApiService
import com.example.mz_focusnews.core.api.service.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MyPageViewModel : ViewModel() {
    private val service: ApiService = RetrofitClient.getInstance().create(ApiService::class.java)

    private val _mypageState = MutableStateFlow(MyPageState())
    val mypageState: StateFlow<MyPageState> = _mypageState

    init {
        fetchMyPageInfo()
    }

    fun fetchMyPageInfo() {
        _mypageState.value = MyPageState(isLoading = true)  // 로딩 시작

        viewModelScope.launch {
            try {
                val response = service.getMyPageInfo()
                _mypageState.value = MyPageState(mypageInfo = response.data, isLoading = false, isError = false)

                Log.d("Retrofit", "fetchMyPageInfo called:: ${_mypageState.value}")
            } catch (e: Exception) {
                _mypageState.value = MyPageState(isLoading = false, isError = true, errorMsg = e.message)
                Log.e("Retrofit", "MyPage Info Error: ${_mypageState.value.errorMsg}")
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
