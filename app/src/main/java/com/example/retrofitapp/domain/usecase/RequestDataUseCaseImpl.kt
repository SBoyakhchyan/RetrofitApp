package com.example.retrofitapp.domain.usecase

import com.example.retrofitapp.domain.interactor.RequestDataUseCase
import com.example.retrofitapp.domain.repository.MainRepository
import com.example.retrofitapp.presentation.model.ActionResult
import com.example.retrofitapp.presentation.model.ResponseResult

class RequestDataUseCaseImpl(
    var repository: MainRepository
) : RequestDataUseCase {

    override suspend fun invoke(source: String): ActionResult<ResponseResult> {
        return repository.requestData(source)
    }
}