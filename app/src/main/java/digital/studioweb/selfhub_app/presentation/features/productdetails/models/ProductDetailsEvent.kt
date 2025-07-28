package digital.studioweb.selfhub_app.presentation.features.productdetails.models

import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationGroupModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel

sealed class ProductDetailsEvent {
    data class Init(val productModel: ProductModel) : ProductDetailsEvent()
    object IncrementProductQuantity : ProductDetailsEvent()
    object DecrementProductQuantity : ProductDetailsEvent()
    data class ChangeObservation(val observation: String) : ProductDetailsEvent()
    data class IncrementOption(val group: CustomizationGroupModel, val optionId: String) : ProductDetailsEvent()
    data class DecrementOption(val group: CustomizationGroupModel, val optionId: String) : ProductDetailsEvent()
    data class AddToCart(val item: CartOrderItemModel) : ProductDetailsEvent()
}
