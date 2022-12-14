package com.example.retrofitapp.data.di

import com.example.retrofitapp.data.api.ArticleInfoService
import com.example.retrofitapp.presentation.utils.Constants.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var apiModule = module {

    fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .apply {
            client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    })
                    .build()
            )
        }
        .build()

    single<Retrofit> { provideRetrofit() }
    single<ArticleInfoService> { get<Retrofit>().create(ArticleInfoService::class.java) }
}