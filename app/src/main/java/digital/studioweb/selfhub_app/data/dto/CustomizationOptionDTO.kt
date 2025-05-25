package digital.studioweb.selfhub_app.data.dto

import digital.studioweb.selfhub_app.data.models.CustomizationOption
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomizationOptionDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("additionalPrice")
    val additionalPrice: Double
) {
    fun mapTo(): CustomizationOption {
        return CustomizationOption(
            id = id,
            name = name,
            additionalPrice = additionalPrice
        )
    }
}