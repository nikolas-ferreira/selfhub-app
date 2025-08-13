package digital.studioweb.selfhub_app.domain.features.cart

import digital.studioweb.selfhub_app.domain.features.home.models.CartCreateOrderModel

interface CartRepository {
    suspend fun createOrder(cartCreateOrderModel: CartCreateOrderModel)
}