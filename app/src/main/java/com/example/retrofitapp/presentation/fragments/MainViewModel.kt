package com.example.retrofitapp.presentation.fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitapp.domain.interactor.RequestDataUseCase
import com.example.retrofitapp.presentation.model.ActionResult.ERROR
import com.example.retrofitapp.presentation.model.ActionResult.SUCCESS
import com.example.retrofitapp.presentation.model.ArticelInfoData
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class MainViewModel(var useCase: RequestDataUseCase) : ViewModel() {

    private val _articlesStateFlow = MutableStateFlow<ArrayList<ArticelInfoData>?>(null)
    val articlesStateFlow = _articlesStateFlow.asStateFlow()

    private val _errorStateFlow = MutableSharedFlow<String>()
    val errorStateFlow : SharedFlow<String> = _errorStateFlow.asSharedFlow()

    private val _showProgressStateFlow = MutableStateFlow(false)
    val showProgressStateFlow = _showProgressStateFlow.asStateFlow()


    fun getArticles(source: String) {
        viewModelScope.launch {
            _showProgressStateFlow.emit(true)
            delay(5000)
            val response = async { return@async useCase.invoke(source) }.await()
            when (response) {
                is SUCCESS -> {
                    _articlesStateFlow.emit(response.data?.articles)
                    _showProgressStateFlow.emit(false)
                }
                is ERROR -> {
                    _errorStateFlow.emit(response.message)
                }
            }
        }
    }
}

