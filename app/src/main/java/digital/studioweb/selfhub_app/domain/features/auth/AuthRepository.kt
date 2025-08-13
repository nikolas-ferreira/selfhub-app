package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceRequestModel

interface AuthRepository {
    suspend fun getAssociation(): String
    suspend fun associateDevice(associateDeviceRequest: AssociateDeviceRequestModel)
    fun getCNPJ(): String
    fun saveCNPJ(cnpj: String)
    suspend fun getRestaurantByCNPJ(cnpj: String)
}