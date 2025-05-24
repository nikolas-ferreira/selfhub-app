package digital.studioweb.selfhub_app.data.models

data class ApiResponse<T>(
    val statusCode: Int,
    val message: String,
    val response: T
)
