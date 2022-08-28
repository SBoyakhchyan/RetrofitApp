package com.example.retrofitapp.fragments

import com.example.retrofitapp.api.ArticleInfoService
import com.example.retrofitapp.model.ActionResult
import com.example.retrofitapp.model.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepository(var apiService: ArticleInfoService) {

    suspend fun requestData(source: String): ActionResult<ResponseResult> {
        return withContext(Dispatchers.IO) {
            val result = apiService.getArticles(source)
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