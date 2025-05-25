package digital.studioweb.selfhub_app.data.models

data class Product(
    val id: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val categoryId: String,
    val createdById: String,
    val lastEditedById: String,
    val customizationGroups: List<CustomizationGroup>
)