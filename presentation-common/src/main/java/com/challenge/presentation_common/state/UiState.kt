package com.challenge.presentation_common.state

sealed class UiState<out T : Any> {
    object Loading : UiState<Nothing>()
    object Idle : UiState<Nothing>()
    data class Error(val errorMessage: String) : UiState<Nothing>()
    data class Success<T : Any>(val data: T) : UiState<T>()
}