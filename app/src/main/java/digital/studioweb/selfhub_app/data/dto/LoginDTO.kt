package digital.studioweb.selfhub_app.data.dto

import digital.studioweb.selfhub_app.data.models.Login
import digital.studioweb.selfhub_app.data.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LoginDTO(
    @SerialName("token")
    val token: String,

    @SerialName("user")
    val user: UserDTO
) {
    fun mapTo(): Login {
        return Login(
            token = token,
            user = user.mapTo()
        )
    }
}

@Serializable
data class UserDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("email")
    val email: String,

    @SerialName("role")
    val role: String,

    @SerialName("restaurantId")
    val restaurantId: String
) {
    fun mapTo(): User {
        return User(
            id = id,
            name = name,
            email = email,
            role = role,
            restaurantId = restaurantId
        )
    }
}
