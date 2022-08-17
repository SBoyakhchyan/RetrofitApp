package com.example.retrofitapp.api

import com.example.retrofitapp.resources.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getService(): ArticleInfoService = retrofit.create(ArticleInfoService::class.java)
}