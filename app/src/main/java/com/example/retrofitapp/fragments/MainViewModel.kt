package com.example.retrofitapp.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapp.model.ActionResult.ERROR
import com.example.retrofitapp.model.ActionResult.SUCCESS
import com.example.retrofitapp.model.ArticelInfoData
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val repo = MainRepository()
    var articlesLiveData = MutableLiveData<ArrayList<ArticelInfoData>?>()
    var errorLiveData = MutableLiveData<String>()

    fun getArticles(source: String) {
        viewModelScope.launch {
            val response = async { return@async repo.requestData(source) }.await()
            when (response) {
                is SUCCESS -> {
                    Log.d("tttt", "success")
                    articlesLiveData.value = response.data?.articles
                }
                is ERROR -> {
                    errorLiveData.value = response.message
                }
            }
        }
    }
}

