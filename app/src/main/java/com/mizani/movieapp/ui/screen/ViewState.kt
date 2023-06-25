package com.mizani.movieapp.ui.screen

sealed class ViewState<out T: Any, out E: Exception> {

    object Loading : ViewState<Nothing, Nothing>()
    data class Success<T: Any>(val data: T) : ViewState<T, Nothing>()
    data class Error<E: Exception>(val error: Exception) : ViewState<Nothing, E>()

}