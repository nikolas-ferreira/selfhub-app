package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.base.BaseAsyncUseCase

class GetRestaurantByCNPJUseCase(
    private val repository: AuthRepository
) : BaseAsyncUseCase<Result<Unit>, GetRestaurantByCNPJUseCase.Params>() {
    data class Params(val cnpj: String)

    override suspend fun runAsync(params: Params): Result<Unit> {
        try {
            val restaurant = repository.getRestaurantByCNPJ(params.cnpj)
            return Result.success(restaurant)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
