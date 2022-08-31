package com.example.retrofitapp.presentation

import android.app.Application
import com.example.retrofitapp.data.di.apiModule
import com.example.retrofitapp.data.di.repoModule
import com.example.retrofitapp.domain.di.useCaseModule
import com.example.retrofitapp.presentation.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RetrofitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RetrofitApplication)
            modules(listOf(apiModule, repoModule, useCaseModule, viewModule))
        }
    }
}