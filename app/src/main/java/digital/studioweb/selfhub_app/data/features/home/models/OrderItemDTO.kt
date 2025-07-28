package digital.studioweb.selfhub_app.data.features.home.models

import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrderItemDTO(
    @SerialName("product")
    val product: ProductDTO? = null,

    @SerialName("quantity")
    val quantity: Int? = null,
) {
//    fun mapTo(): CartOrderItemModel {
//        return CartOrderItemModel(
//
//            productModel = product?.mapTo() ?: ProductModel(),
//            quantity = quantity ?: 0
//        )
//    }
}
