package digital.studioweb.selfhub_app.domain.features.home.models

data class CartOrderItemModel(
    val id: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val quantity: Int = 1,
    val observation: String = "",
    val ratingStar: Double = 4.9,
    val customizationOptions: List<CustomizationOptionModel> = emptyList()
)