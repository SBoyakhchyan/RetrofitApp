package com.example.retrofitapp.domain.di

import com.example.retrofitapp.domain.interactor.RequestDataUseCase
import com.example.retrofitapp.domain.usecase.RequestDataUseCaseImpl
import org.koin.dsl.module

var useCaseModule = module {
    factory<RequestDataUseCase> { RequestDataUseCaseImpl(get()) }
}