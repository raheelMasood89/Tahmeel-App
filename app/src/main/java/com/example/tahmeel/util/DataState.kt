package com.example.tahmeel.util

sealed class DataState<out R> {
    data class Success<out T>(val value: T) : DataState<T>()
    data class GenericError(val code: Int? = null, val error: ErrorResponse? = null) : DataState<Nothing>()
    object NetworkError : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}