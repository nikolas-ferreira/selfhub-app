package digital.studioweb.selfhub_app.data.features.cart

import digital.studioweb.selfhub_app.domain.features.cart.CartRepository
import digital.studioweb.selfhub_app.domain.features.home.models.CartCreateOrderModel

class CartRepositoryImpl(
    private val api: CartAPI
) : CartRepository {
    override suspend fun createOrder(cartCreateOrderModel: CartCreateOrderModel) {
        return api.createOrder(
            cartCreateOrderModel.mapToDTO()
        ).response
    }
}