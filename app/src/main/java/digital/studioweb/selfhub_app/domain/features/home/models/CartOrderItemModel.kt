package digital.studioweb.selfhub_app.domain.features.home.models

import digital.studioweb.selfhub_app.data.features.home.models.CustomizationOptionDTO
import digital.studioweb.selfhub_app.data.features.home.models.OrderItemDTO

data class CartOrderItemModel(
    val orderItemId: String = "",
    val productId: String = "",
    val name: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val quantity: Int = 1,
    val observation: String = "",
    val ratingStar: Double = 4.9,
    val customizationOptions: List<CustomizationOptionModel> = emptyList()
) {
    fun toDTO(): OrderItemDTO {
        return OrderItemDTO(
            productId = productId,
            quantity = quantity,
            observation = observation,
            ratingStar = ratingStar,
            imageUrl = imageUrl,
            customizationOptions = customizationOptions.map {
                CustomizationOptionDTO(
                    id = it.id,
                    name = it.name,
                    additionalPrice = it.additionalPrice,
                    quantity = it.quantity
                )
            }
        )
    }
}