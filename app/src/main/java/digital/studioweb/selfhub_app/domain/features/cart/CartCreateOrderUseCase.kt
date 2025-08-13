package digital.studioweb.selfhub_app.domain.features.cart

import digital.studioweb.selfhub_app.data.base.Result
import digital.studioweb.selfhub_app.domain.base.BaseAsyncUseCase
import digital.studioweb.selfhub_app.domain.features.home.models.CartCreateOrderModel
import javax.inject.Inject

class CartCreateOrderUseCase @Inject constructor(
    private val cartRepository: CartRepository
) : BaseAsyncUseCase<Result<Unit>, CartCreateOrderUseCase.Params>() {

    data class Params(val cartCreateOrderModel: CartCreateOrderModel)

    override suspend fun runAsync(params: Params): Result<Unit> {
        return try {
            val result = cartRepository.createOrder(params.cartCreateOrderModel)
            Result.Success(result)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}