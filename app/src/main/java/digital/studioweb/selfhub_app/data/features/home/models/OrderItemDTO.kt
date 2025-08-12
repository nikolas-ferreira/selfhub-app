package digital.studioweb.selfhub_app.data.features.home.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemDTO(
    @SerialName("productId")
    val productId: String,

    @SerialName("quantity")
    val quantity: Int,

    @SerialName("observation")
    val observation: String,

    @SerialName("ratingStar")
    val ratingStar: Double,

    @SerialName("imageUrl")
    val imageUrl: String,

    @SerialName("customizationOptions")
    val customizationOptions: List<CustomizationOptionDTO> = emptyList()
)
