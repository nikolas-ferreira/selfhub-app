package digital.studioweb.selfhub_app.domain.features.home.models

data class CustomizationOptionModel(
    val id: String,
    val name: String,
    val additionalPrice: Double,
    val quantity: Int = 0
)