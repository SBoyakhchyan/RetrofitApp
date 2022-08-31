package com.example.retrofitapp.data.repository

import com.example.retrofitapp.data.api.ArticleInfoService
import com.example.retrofitapp.domain.repository.MainRepository
import com.example.retrofitapp.presentation.model.ActionResult
import com.example.retrofitapp.presentation.model.ResponseResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainRepositoryImpl(var apiService: ArticleInfoService) : MainRepository {

    override suspend fun requestData(source: String): ActionResult<ResponseResult> {
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