package com.example.retrofitapp.data.api

import com.example.retrofitapp.presentation.model.ResponseResult
import com.example.retrofitapp.presentation.utils.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleInfoService {
    @GET("v2/top-headlines")
    suspend fun getArticles(
        @Query("sources") sources: String,
        @Query("apiKey") apiKey: String = API_KEY
    ): Response<ResponseResult>
}