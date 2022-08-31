package com.example.retrofitapp.presentation.model

sealed class ActionResult<T>(data: T? = null) {
    data class SUCCESS<T>(val message: String, val data: T?) : ActionResult<T>(data)
    data class ERROR<T>(val message: String) : ActionResult<T>()
}
