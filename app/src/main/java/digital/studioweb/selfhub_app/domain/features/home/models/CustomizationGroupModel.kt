package digital.studioweb.selfhub_app.domain.features.home.models

data class CustomizationGroupModel(
    val id: String,
    val name: String,
    val min: Int,
    val max: Int,
    val isRequired: Boolean,
    val options: List<CustomizationOptionModel>
)