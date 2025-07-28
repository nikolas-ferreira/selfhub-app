package digital.studioweb.selfhub_app.data.features.home.models

import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderModel
import digital.studioweb.selfhub_app.domain.features.models.PaymentMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDTO(
    @SerialName("id")
    val orderNumber: String? = null,

    @SerialName("id")
    val tableNumber: Int? = null,

    @SerialName("id")
    val paymentMethod: PaymentMethod? = null,

    @SerialName("id")
    val totalValue: Double? = null,

    @SerialName("id")
    val restaurantId: String? = null,

    @SerialName("id")
    val items: List<OrderItemDTO>? = null,
) {
    fun mapTo(): CartOrderModel {
        return CartOrderModel(
            orderNumber = orderNumber ?: "",
            tableNumber = tableNumber ?: 0,
            paymentMethod = paymentMethod ?: PaymentMethod.PIX,
            totalValue = totalValue ?: 0.0,
            restaurantId = restaurantId ?: "",
            items = emptyList()//items?.map { it.mapTo() } ?: emptyList()
        )
    }
}
