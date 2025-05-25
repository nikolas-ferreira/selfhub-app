package digital.studioweb.selfhub_app.data.dto

import digital.studioweb.selfhub_app.data.models.CustomizationGroup
import digital.studioweb.selfhub_app.data.models.CustomizationOption
import digital.studioweb.selfhub_app.data.models.Product
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomizationGroupDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("minSelections")
    val minSelections: Int,

    @SerialName("maxSelections")
    val maxSelections: Int,

    @SerialName("isRequired")
    val isRequired: Boolean,

    @SerialName("options")
    val options: List<CustomizationOptionDTO>
) {
    fun mapTo(): CustomizationGroup {
        return CustomizationGroup(
            id = id,
            name = name,
            minSelections = minSelections,
            maxSelections = maxSelections,
            isRequired = isRequired,
            options = options.map { it.mapTo() }
        )
    }
}