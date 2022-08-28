package com.example.retrofitapp

import android.app.Application
import com.example.retrofitapp.di.apiModule
import com.example.retrofitapp.di.repoModule
import com.example.retrofitapp.di.viewModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RetrofitApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RetrofitApplication)
            modules(listOf(apiModule, repoModule, viewModule))
        }
    }
}