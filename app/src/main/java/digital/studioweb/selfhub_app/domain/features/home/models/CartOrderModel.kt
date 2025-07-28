package digital.studioweb.selfhub_app.domain.features.home.models

import digital.studioweb.selfhub_app.domain.features.models.PaymentMethod

data class CartOrderModel(
    val orderNumber: String = "",
    val tableNumber: Int = 0,
    val paymentMethod: PaymentMethod = PaymentMethod.UNKNOWN,
    val totalValue: Double = 0.0,
    val restaurantId: String = "",
    val items: List<CartOrderItemModel> = emptyList()
)