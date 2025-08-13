package digital.studioweb.selfhub_app.data.features.auth.models

import digital.studioweb.selfhub_app.domain.features.auth.models.AssociateDeviceRequestModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class AssociateDeviceRequestDTO(
    @SerialName("macAddress")
    val macAddress: String,

    @SerialName("restaurantCnpj")
    val restaurantCnpj: String,
) {
    fun mapTo(): AssociateDeviceRequestModel {
        return AssociateDeviceRequestModel(
            macAddress = macAddress,
            restaurantCnpj = restaurantCnpj
        )
    }
}

