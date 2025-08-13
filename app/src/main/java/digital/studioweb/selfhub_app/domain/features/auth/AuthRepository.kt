package digital.studioweb.selfhub_app.domain.features.auth

import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceRequestModel
import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceResponseModel

interface AuthRepository {
    suspend fun getAssociation(): String
    suspend fun associateDevice(associateDeviceRequest: AssociateDeviceRequestModel): AssociateDeviceResponseModel
    fun getCNPJ(): String
    fun saveCNPJ(cnpj: String)
    fun getRestaurantId(): String
    fun saveRestaurantId(restaurantId: String)
    suspend fun getRestaurantByCNPJ(cnpj: String)
}