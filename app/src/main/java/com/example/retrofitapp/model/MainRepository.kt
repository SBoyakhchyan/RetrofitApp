package com.example.retrofitapp.model

import com.example.retrofitapp.api.ApiClient
import com.example.retrofitapp.api.ArticleInfoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainRepository {

    private var service: ArticleInfoService
    private var client = ApiClient.retrofit

    init {
        service = client.create(ArticleInfoService::class.java)
    }

    suspend fun requestData(source: String): ActionResult<ResponseResult> {
        var value: ActionResult<ResponseResult> = ActionResult.SUCCESS("", ResponseResult())

        val service = ApiClient.getService()
        service.getArticles("techcrunch").enqueue(object : Callback<ResponseResult> {
            override fun onResponse(
                call: Call<ResponseResult>,
                response: Response<ResponseResult>
            ) {

                value =
                    if (response.isSuccessful && response.body()?.articles?.isNotEmpty() == true) {

                        ActionResult.SUCCESS(response.message(), response.body())
                    } else {
                        ActionResult.ERROR(response.message() ?: "Error")
                    }
            }

            override fun onFailure(call: Call<ResponseResult>, t: Throwable) {
                value = ActionResult.ERROR(t.message ?: "Error")
            }
        })
        return value
    }
}