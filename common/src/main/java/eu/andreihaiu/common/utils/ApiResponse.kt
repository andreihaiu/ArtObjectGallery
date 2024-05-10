package eu.andreihaiu.common.utils

sealed class ApiResponse<out T> {
    data class Success<out T : Any>(val value: T) : ApiResponse<T>()
    data class Error(val exception: Exception) : ApiResponse<Nothing>()
}