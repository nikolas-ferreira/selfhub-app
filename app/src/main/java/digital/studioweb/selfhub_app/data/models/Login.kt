package digital.studioweb.selfhub_app.data.models


data class Login(
    val token: String,
    val user: User
)

data class User(
    val id: String,
    val name: String,
    val email: String,
    val role: String,
    val restaurantId: String
)
