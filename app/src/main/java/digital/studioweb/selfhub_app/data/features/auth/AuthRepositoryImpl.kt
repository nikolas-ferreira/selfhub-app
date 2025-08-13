package digital.studioweb.selfhub_app.data.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.AuthRepository
import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceRequestModel
import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceResponseModel
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authAPIDataSource: AuthAPIDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {
    override suspend fun getAssociation(): String {
        TODO("Not yet implemented")
    }

    override suspend fun associateDevice(associateDeviceRequest: AssociateDeviceRequestModel): AssociateDeviceResponseModel {
        val response = authAPIDataSource.associateDevice(
            associateDeviceRequest.mapToDTO()
        )
        return AssociateDeviceResponseModel(
            statusCode = response.statusCode,
            message = response.message,
            restaurant = response.response.mapTo()
        )
    }

    override fun getCNPJ(): String {
        return authLocalDataSource.getCNPJ()
    }

    override fun saveCNPJ(cnpj: String) {
        return authLocalDataSource.saveCNPJ(cnpj = cnpj)
    }

    override fun getRestaurantId(): String {
        return authLocalDataSource.getRestaurantId()
    }

    override fun saveRestaurantId(restaurantId: String) {
        authLocalDataSource.saveRestaurantId(restaurantId)
    }

    override suspend fun getRestaurantByCNPJ(cnpj: String) {
        return authAPIDataSource.getRestaurantByCNPJ(cnpj).response
    }
}