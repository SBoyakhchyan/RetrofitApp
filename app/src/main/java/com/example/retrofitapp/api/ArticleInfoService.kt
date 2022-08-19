package com.example.retrofitapp.api

import com.example.retrofitapp.model.ResponseResult
import com.example.retrofitapp.resources.Constants.Companion.API_KEY
import retrofit2.Call
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