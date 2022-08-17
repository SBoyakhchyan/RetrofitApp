package com.example.retrofitapp.model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapp.model.ActionResult.ERROR
import com.example.retrofitapp.model.ActionResult.SUCCESS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val repo = MainRepository()
    var articlesLiveData = MutableLiveData<ArrayList<ArticelInfoData>?>()
    var errorLiveData = MutableLiveData<String>()

    suspend fun getArticles(source: String) {
            val response = repo.requestData(source)
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

