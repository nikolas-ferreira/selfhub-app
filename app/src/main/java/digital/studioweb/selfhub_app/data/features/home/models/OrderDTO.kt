package digital.studioweb.selfhub_app.data.features.home.models

import digital.studioweb.selfhub_app.domain.features.models.PaymentMethod
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderDTO(
    @SerialName("orderNumber")
    val orderNumber: Int? = null,

    @SerialName("tableNumber")
    val tableNumber: Int,

    @SerialName("waiterNumber")
    val waiterNumber: Int,

    @SerialName("paymentMethod")
    val paymentMethod: PaymentMethod,

    @SerialName("totalValue")
    val totalValue: Double,

    @SerialName("restaurantId")
    val restaurantId: String,

    @SerialName("items")
    val items: List<OrderItemDTO>
)
