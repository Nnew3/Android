package com.example.mz_focusnews.core.api.service

import com.example.mz_focusnews.core.api.ApiResponse
import com.example.mz_focusnews.core.api.model.BreakingNews
import com.example.mz_focusnews.core.api.model.NewsDetail
import com.example.mz_focusnews.core.api.model.NewsGroup
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("user/v1/main/news")
    suspend fun getMainNewsData(): ApiResponse<NewsGroup>

    @GET("user/v1/newsDetails")
    suspend fun getNewsDetail(@Query("newsId") newsId: Long): ApiResponse<NewsDetail>

    @GET("user/v1/main/breakingNews")
    suspend fun getBreakingNews(): ApiResponse<BreakingNews>
}

