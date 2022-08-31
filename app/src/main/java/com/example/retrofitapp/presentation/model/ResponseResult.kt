package com.example.retrofitapp.presentation.model

import com.google.gson.annotations.SerializedName

data class ResponseResult(
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("totalResults")
    var totalResults: Int? = null,
    @SerializedName("articles")
    var articles: ArrayList<ArticelInfoData>? = null
)
