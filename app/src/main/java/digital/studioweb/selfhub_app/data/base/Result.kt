package digital.studioweb.selfhub_app.data.base

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

inline fun <T> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) action(data)
    return this
}

inline fun <T> Result<T>.onFailure(action: (Exception) -> Unit): Result<T> {
    if (this is Result.Error) action(exception)
    return this
}