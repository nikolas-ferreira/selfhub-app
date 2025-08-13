package digital.studioweb.selfhub_app.data.features.cart.models

import com.google.gson.annotations.SerializedName
import digital.studioweb.selfhub_app.data.features.home.models.OrderItemDTO
import digital.studioweb.selfhub_app.domain.features.home.models.CartCreateOrderModel
import digital.studioweb.selfhub_app.domain.features.models.PaymentMethod

data class CartCreateOrderDTO(
    @SerializedName("orderNumber")
    val orderNumber: String?,
    @SerializedName("tableNumber")
    val tableNumber: String?,
    @SerializedName("waiterNumber")
    val waiterNumber: String?,
    @SerializedName("paymentMethod")
    val paymentMethod: PaymentMethod?,
    @SerializedName("totalValue")
    val totalValue: Double?,
    @SerializedName("restaurantId")
    val restaurantId: String?,
    @SerializedName("items")
    val items: List<OrderItemDTO>?
) {
    fun mapTo(): CartCreateOrderModel{
        return CartCreateOrderModel(
            orderNumber = orderNumber ?: "",
            tableNumber = tableNumber ?: "",
            waiterNumber = waiterNumber ?: "",
            paymentMethod = paymentMethod ?: PaymentMethod.UNKNOWN,
            totalValue = totalValue ?: 0.0,
            restaurantId = restaurantId ?: "",
            items = items?.map { it.mapTo() } ?: emptyList()
        )
    }
}
