package com.example.searchapp.helper

import java.lang.Exception

sealed class ApiResponse<out T> {
    data class Success<T>(var response : T) : ApiResponse<T>()
    data class Error(var exception : Exception) : ApiResponse<Nothing>()
    object LOADING : ApiResponse<Nothing>()
    object COMPLETED : ApiResponse<Nothing>()
}