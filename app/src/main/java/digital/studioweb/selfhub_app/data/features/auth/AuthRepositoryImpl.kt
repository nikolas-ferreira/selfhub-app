package digital.studioweb.selfhub_app.data.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.AuthRepository
import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceRequestModel
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authAPIDataSource: AuthAPIDataSource,
    private val authLocalDataSource: AuthLocalDataSource
) : AuthRepository {
    override suspend fun getAssociation(): String {
        TODO("Not yet implemented")
    }

    override suspend fun associateDevice(associateDeviceRequest: AssociateDeviceRequestModel) {
        return authAPIDataSource.associateDevice(
            associateDeviceRequest.mapToDTO()
        ).response
    }

    override fun getCNPJ(): String {
        return authLocalDataSource.getCNPJ()
    }

    override fun saveCNPJ(cnpj: String) {
        return authLocalDataSource.saveCNPJ(cnpj = cnpj)
    }

    override suspend fun getRestaurantByCNPJ(cnpj: String) {
        return authAPIDataSource.getRestaurantByCNPJ(cnpj).response
    }
}