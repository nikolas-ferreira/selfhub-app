package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.base.BaseAsyncUseCase
import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceRequestModel

class AssociateDeviceUseCase(
    private val repository: AuthRepository
) : BaseAsyncUseCase<Result<Unit>, AssociateDeviceUseCase.Params>() {
    data class Params(val cnpj: String, val macAddress: String)

    override suspend fun runAsync(params: Params): Result<Unit> {
        try {
            val request = AssociateDeviceRequestModel(
                macAddress = params.macAddress,
                restaurantCnpj = params.cnpj
            )
            val result = repository.associateDevice(request)
            return Result.success(result)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}
