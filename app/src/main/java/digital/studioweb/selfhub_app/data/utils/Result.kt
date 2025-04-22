package digital.studioweb.selfhub_app.data.utils

/**
 * A generic class that holds a value or an error.
 * Used to represent the result of domain operations.
 */
sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}
