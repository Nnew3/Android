package com.example.mz_focusnews.core.api.service

import com.example.mz_focusnews.core.api.ApiResponse
import com.example.mz_focusnews.core.api.model.AlarmRequest
import com.example.mz_focusnews.core.api.model.BreakingNews
import com.example.mz_focusnews.core.api.model.DelKeywordRequest
import com.example.mz_focusnews.core.api.model.LocationRequest
import com.example.mz_focusnews.core.api.model.MainInfo
import com.example.mz_focusnews.core.api.model.MyPageInfo
import com.example.mz_focusnews.core.api.model.NewsActionRequest
import com.example.mz_focusnews.core.api.model.NewsDetail
import com.example.mz_focusnews.core.api.model.NewsGroup
import com.example.mz_focusnews.core.api.model.NewsList
import com.example.mz_focusnews.core.api.model.NewsResponse
import com.example.mz_focusnews.core.api.model.Question
import com.example.mz_focusnews.core.api.model.QuizScoreRequest
import com.example.mz_focusnews.core.api.model.QuizUserInfo
import com.example.mz_focusnews.core.api.model.Ranking
import com.example.mz_focusnews.core.api.model.RelatedNewsList
import com.example.mz_focusnews.core.api.model.SetKeywordRequest
import com.example.mz_focusnews.core.api.model.SetLocationRequest
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("user/v1/main/news")
    suspend fun getMainNews(): ApiResponse<NewsGroup>

    @GET("user/v1/newsDetails")
    suspend fun getNewsDetail(@Query("newsId") newsId: Long): ApiResponse<NewsDetail>

    @GET("user/v1/main/breakingNews")
    suspend fun getBreakingNews(): ApiResponse<BreakingNews>

    @GET("user/v1")
    suspend fun getNewsByCategory(
        @Query("category") category: String,
        @Query("sort") sort: String
    ): ApiResponse<NewsResponse>

    @GET("user/v1/main/userNews")
    suspend fun getUserNews(): ApiResponse<NewsResponse>

    @GET("user/v1/relatedNews")
    suspend fun getRelatedNews(@Query("newsId") newsId: Long): ApiResponse<RelatedNewsList>

    @GET("/user/v1/main/info")
    suspend fun getMainInfo(): ApiResponse<MainInfo>

    @GET("/user/v1/mypage/info")
    suspend fun getMyPageInfo(): ApiResponse<MyPageInfo>

    @GET("/user/v1/mypage")
    suspend fun getLikeNewsList(
        @Query("userId") userId: Long,
        @Query("type") sort: String = "like"
    ): ApiResponse<NewsList>

    @GET("/user/v1/mypage")
    suspend fun getRecentNewsList(
        @Query("userId") userId: Long,
        @Query("type") sort: String = "recent"
    ): ApiResponse<NewsList>

    @POST("/user/v1/keyword")
    suspend fun setUserKeyword(
        @Body req: SetKeywordRequest
    ): ApiResponse<Unit>

    @HTTP(method = "DELETE", path = "/user/v1/keyword", hasBody = true)
    suspend fun deleteUserKeyword(
        @Body req: DelKeywordRequest
    ): ApiResponse<Unit>

    @POST("/user/v1/news/view")
    suspend fun logWatchNews(
        @Body req: NewsActionRequest
    ): ApiResponse<Unit>

    @POST("/user/v1/news/like")
    suspend fun likeNews(
        @Body req: NewsActionRequest
    ): ApiResponse<Unit>

    @HTTP(method = "DELETE", path = "/user/v1/news/like", hasBody = true)
    suspend fun unlikeNews(
        @Body req: NewsActionRequest
    ): ApiResponse<Unit>

    @PATCH("/user/v1/alarm")
    suspend fun updateAlarmSetting(
        @Body req: AlarmRequest
    ): ApiResponse<Unit>

    @PATCH("/user/v1/location")
    suspend fun updateLocationSetting(
        @Body req: LocationRequest
    ): ApiResponse<Unit>

    @GET("/user/v1/quiz/info")
    suspend fun getQuizUserInfo(): ApiResponse<QuizUserInfo>

    @POST("/user/v1/quiz/score")
    suspend fun setQuizScore(
        @Body req: QuizScoreRequest
    ): ApiResponse<Unit>

    @GET("/user/v1/quiz/ranking")
    suspend fun getQuizRanking(): ApiResponse<Ranking>

    @GET("/user/v1/quiz")
    suspend fun getQuizQuestions(
        @Query("num") num: Int,
    ): ApiResponse<Question>

    @POST("/user/v1/location")
    suspend fun setLocation(
        @Body req: SetLocationRequest
    ): ApiResponse<Unit>
}

