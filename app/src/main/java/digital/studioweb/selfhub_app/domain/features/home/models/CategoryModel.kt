package digital.studioweb.selfhub_app.domain.features.home.models


data class CategoryModel(
    val id: String,
    val name: String,
    val iconUrl: String,
    val createdAt: String,
    val updatedAt: String,
    val restaurantId: String,
    val lastEditedById: String,
)
