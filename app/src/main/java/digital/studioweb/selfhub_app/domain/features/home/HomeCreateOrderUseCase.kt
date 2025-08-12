package digital.studioweb.selfhub_app.domain.features.home

import digital.studioweb.selfhub_app.data.base.Result
import digital.studioweb.selfhub_app.domain.base.BaseAsyncUseCase
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderModel
import javax.inject.Inject

class HomeCreateOrderUseCase @Inject constructor(
    private val homeRepository: HomeRepository
) : BaseAsyncUseCase<Result<Unit>, CartOrderModel>() {
    override suspend fun runAsync(params: CartOrderModel): Result<Unit> {
        return try {
            homeRepository.createOrder(params)
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
