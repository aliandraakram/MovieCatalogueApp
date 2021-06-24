package com.bangkit.moviesandtvshowapp.core.data.source.remote.response

sealed class ApiResponse<out R> {
    data class Success<out T> (val data: T): ApiResponse<T>()

    data class Error (val msg: String): ApiResponse<Nothing>()

    object Empty : ApiResponse<Nothing>()
}