package digital.studioweb.selfhub_app.data.features.auth

import digital.studioweb.selfhub_app.data.base.ApiResponse
import digital.studioweb.selfhub_app.data.features.auth.models.AssociateDeviceRequestDTO

interface AuthAPIDataSource{
    suspend fun associateDevice(
        associateDeviceRequest: AssociateDeviceRequestDTO
    ): ApiResponse<Unit>

    suspend fun getRestaurantByCNPJ(cnpj: String): ApiResponse<Unit>
}

class AuthAPIDataSourceImpl(
    private val authAPI: AuthAPI
) : AuthAPIDataSource {

    override suspend fun associateDevice(
        associateDeviceRequest: AssociateDeviceRequestDTO
    ): ApiResponse<Unit> {
        return authAPI.associateDevice(associateDeviceRequest)
    }

    override suspend fun getRestaurantByCNPJ(cnpj: String): ApiResponse<Unit> {
        return authAPI.getRestaurantByCNPJ(cnpj)
    }
}