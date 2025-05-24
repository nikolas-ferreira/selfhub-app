package digital.studioweb.selfhub_app.data.models


data class Category(
    val id: String,
    val name: String,
    val iconUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val restaurantId: String,
    val lastEditedById: String,
)
