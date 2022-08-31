package com.example.retrofitapp.domain.interactor

import com.example.retrofitapp.presentation.model.ActionResult
import com.example.retrofitapp.presentation.model.ResponseResult

interface RequestDataUseCase {
    suspend fun invoke(source: String): ActionResult<ResponseResult>
}