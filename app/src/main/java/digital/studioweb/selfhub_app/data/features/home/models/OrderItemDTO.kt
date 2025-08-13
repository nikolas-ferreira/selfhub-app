package digital.studioweb.selfhub_app.data.features.home.models

import com.google.gson.annotations.SerializedName
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemDTO(
    @SerializedName("productId")
    val productId: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("observation")
    val observation: String?,
    @SerializedName("ratingStar")
    val ratingStar: Double?,
    @SerializedName("imageUrl")
    val imageUrl: String?,
    @SerializedName("customizationOptions")
    val customizationOptions: List<CustomizationOptionDTO>?
){
    fun mapTo(): CartOrderItemModel {
        return CartOrderItemModel(
            productId = productId,
            quantity = quantity,
            observation = observation ?: "",
            ratingStar = ratingStar ?: 0.0,
            imageUrl = imageUrl ?: "",
            customizationOptions = customizationOptions?.map { it.mapTo() } ?: emptyList()
        )
    }
}