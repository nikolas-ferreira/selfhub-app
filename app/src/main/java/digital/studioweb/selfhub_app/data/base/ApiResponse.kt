package digital.studioweb.selfhub_app.data.base

data class ApiResponse<T>(
    val statusCode: Int,
    val message: String,
    val response: T
)