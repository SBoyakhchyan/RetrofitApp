package com.example.retrofitapp.data.di

import com.example.retrofitapp.data.repository.MainRepositoryImpl
import com.example.retrofitapp.domain.repository.MainRepository
import org.koin.dsl.module

var repoModule = module {
    factory<MainRepository> { MainRepositoryImpl(get()) }
}