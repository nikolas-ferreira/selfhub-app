package digital.studioweb.selfhub_app.domain.features.auth.models

import digital.studioweb.selfhub_app.data.features.auth.models.AssociateDeviceRequestDTO

data class AssociateDeviceRequestModel(
    val macAddress: String,
    val restaurantCnpj: String
){
    fun mapToDTO(): AssociateDeviceRequestDTO {
        return AssociateDeviceRequestDTO(
            macAddress = macAddress,
            restaurantCnpj = restaurantCnpj
        )
    }
}