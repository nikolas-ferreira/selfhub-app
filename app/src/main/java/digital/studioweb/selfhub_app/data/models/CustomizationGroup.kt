package digital.studioweb.selfhub_app.data.models

data class CustomizationGroup(
    val id: String,
    val name: String,
    val minSelections: Int,
    val maxSelections: Int,
    val isRequired: Boolean,
    val options: List<CustomizationOption>
)