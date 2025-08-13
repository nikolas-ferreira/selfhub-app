package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.base.BaseAsyncUseCase
import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceRequestModel
import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceResponseModel

class AssociateDeviceUseCase(
    private val repository: AuthRepository
) : BaseAsyncUseCase<Result<AssociateDeviceResponseModel>, AssociateDeviceUseCase.Params>() {
    data class Params(val cnpj: String, val macAddress: String)

    override suspend fun runAsync(params: Params): Result<AssociateDeviceResponseModel> {
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
