package digital.studioweb.selfhub_app.domain.features.home.models

import digital.studioweb.selfhub_app.data.features.cart.models.CartCreateOrderDTO
import digital.studioweb.selfhub_app.domain.features.models.PaymentMethod

data class CartCreateOrderModel(
    val orderNumber: String = "",
    val tableNumber: String = "",
    val waiterNumber: String = "",
    val paymentMethod: PaymentMethod = PaymentMethod.UNKNOWN,
    val totalValue: Double = 0.0,
    val restaurantId: String = "",
    val items: List<CartOrderItemModel> = emptyList()
) {
    fun mapToDTO(): CartCreateOrderDTO {
        return CartCreateOrderDTO(
            orderNumber = orderNumber,
            tableNumber = tableNumber,
            waiterNumber = waiterNumber,
            paymentMethod = paymentMethod,
            totalValue = totalValue,
            restaurantId = restaurantId,
            items = items.map { it.toDTO() }
        )
    }
}