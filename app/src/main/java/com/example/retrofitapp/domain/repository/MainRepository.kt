package com.example.retrofitapp.domain.repository

import com.example.retrofitapp.presentation.model.ActionResult
import com.example.retrofitapp.presentation.model.ResponseResult

interface MainRepository {
    suspend fun requestData(source: String): ActionResult<ResponseResult>
}