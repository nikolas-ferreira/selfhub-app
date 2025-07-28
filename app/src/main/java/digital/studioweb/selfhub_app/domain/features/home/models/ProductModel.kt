package digital.studioweb.selfhub_app.domain.features.home.models

data class ProductModel(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val description: String = "",
    val createdAt: String = "",
    val updatedAt: String = "",
    val categoryId: String = "",
    val createdById: String = "",
    val lastEditedById: String = "",
    val customizationGroupModels: List<CustomizationGroupModel> = emptyList()
)