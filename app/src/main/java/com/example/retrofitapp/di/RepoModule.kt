package com.example.retrofitapp.di

import com.example.retrofitapp.fragments.MainRepository
import org.koin.dsl.module

var repoModule = module {
    factory { MainRepository(get()) }
}