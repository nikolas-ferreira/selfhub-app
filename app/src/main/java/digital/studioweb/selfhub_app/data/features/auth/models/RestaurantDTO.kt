package digital.studioweb.selfhub_app.data.features.auth.models

import digital.studioweb.selfhub_app.domain.features.auth.models.RestaurantModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RestaurantDTO(
    @SerialName("id")
    val id: String,

    @SerialName("name")
    val name: String,

    @SerialName("cnpj")
    val cnpj: String,

    @SerialName("created_at")
    val createdAt: String,
) {
    fun mapTo(): RestaurantModel {
        return RestaurantModel(
            id = id,
            name = name,
            cnpj = cnpj,
            createdAt = createdAt,
        )
    }
}
