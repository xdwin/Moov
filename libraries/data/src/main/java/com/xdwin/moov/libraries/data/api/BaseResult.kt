package com.xdwin.data.api

sealed class BaseResult<out T: Any> {
    data class Success<out T: Any>(val data: T?): BaseResult<T>()
    data class Error(val error: String): BaseResult<Nothing>()
    object Loading : BaseResult<Nothing>()
}