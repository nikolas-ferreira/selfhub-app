package digital.studioweb.selfhub_app.data.features.home.models

import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationOptionModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CustomizationOptionDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("additionalPrice")
    val price: Double,

    @SerialName("quantity")
    val quantity: Int = 0
) {
    fun mapTo(): CustomizationOptionModel {
        return CustomizationOptionModel(
            id = id,
            name = name,
            additionalPrice = price
        )
    }
}