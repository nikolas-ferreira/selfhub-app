package digital.studioweb.selfhub_app.data.dto

import digital.studioweb.selfhub_app.data.models.Category
import digital.studioweb.selfhub_app.data.models.Login
import digital.studioweb.selfhub_app.data.models.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CategoryDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("iconUrl")
    val iconUrl: String,

    @SerialName("createdAt")
    val createdAt: String,

    @SerialName("updatedAt")
    val updatedAt: String,

    @SerialName("restaurantId")
    val restaurantId: String,

    @SerialName("lastEditedById")
    val lastEditedById: String
) {
    fun mapTo(): Category {
        return Category(
            id = id,
            name = name,
            iconUrl = iconUrl,
            createdAt = createdAt,
            updatedAt = updatedAt,
            restaurantId = restaurantId,
            lastEditedById = lastEditedById
        )
    }

}
