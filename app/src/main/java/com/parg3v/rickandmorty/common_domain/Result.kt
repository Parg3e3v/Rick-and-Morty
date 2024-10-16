package com.parg3v.rickandmorty.common_domain

sealed interface Result<out T> {
    data class Success<out T>(val data: T) : Result<T>
    data object Loading : Result<Nothing>
    data class Failure(val message: String? = null) : Result<Nothing>
}