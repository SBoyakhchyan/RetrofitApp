package com.example.retrofitapp.api

import com.example.retrofitapp.utils.Constants.Companion.BASE_URL
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create(gson))
        .baseUrl(BASE_URL)
        .build()

//    val retrofit: Retrofit = Retrofit.Builder()
//        .baseUrl(Constants.BASE_URL)
//        .addConverterFactory(GsonConverterFactory.create())
//        .build()

    fun getService(): ArticleInfoService = retrofit.create(ArticleInfoService::class.java)
}