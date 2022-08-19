package com.example.retrofitapp.fragments

import com.example.retrofitapp.api.ApiClient
import com.example.retrofitapp.api.ArticleInfoService
import com.example.retrofitapp.model.ActionResult
import com.example.retrofitapp.model.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository {

    private var client = ApiClient.retrofit
    private var service: ArticleInfoService = client.create(ArticleInfoService::class.java)

    suspend fun requestData(source: String): ActionResult<ResponseResult> {
        return withContext(Dispatchers.IO) {
            val result = service.getArticles(source)
            when (result.code()) {
                200 -> {
                    return@withContext ActionResult.SUCCESS("Success", result.body())
                }
                else -> {
                    return@withContext ActionResult.ERROR("Success")
                }
            }
        }
    }
}