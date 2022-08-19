package com.example.retrofitapp.model

sealed class ActionResult<out T> {
    data class SUCCESS<T>(val message: String, val data: T?) : ActionResult<T>()
    data class ERROR(val message: String) : ActionResult<Nothing>()
}
