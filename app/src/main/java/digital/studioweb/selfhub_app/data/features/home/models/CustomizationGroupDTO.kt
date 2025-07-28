package digital.studioweb.selfhub_app.data.features.home.models

import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationGroupModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomizationGroupDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("min")
    val min: Int,

    @SerialName("max")
    val max: Int,

    @SerialName("isRequired")
    val isRequired: Boolean,

    @SerialName("options")
    val options: List<CustomizationOptionDTO>
) {
    fun mapTo(): CustomizationGroupModel {
        return CustomizationGroupModel(
            id = id,
            name = name,
            min = min,
            max = max,
            isRequired = isRequired,
            options = options.map { it.mapTo() }
        )
    }
}