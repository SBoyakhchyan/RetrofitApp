package com.example.retrofitapp.presentation.di

import com.example.retrofitapp.presentation.fragments.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

var viewModule = module {
    viewModel { MainViewModel(get()) }
}