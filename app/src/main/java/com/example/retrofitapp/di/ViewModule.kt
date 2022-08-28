package com.example.retrofitapp.di

import com.example.retrofitapp.fragments.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModule = module {
    viewModel { MainViewModel(get()) }
}