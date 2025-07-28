package digital.studioweb.selfhub_app.presentation.features.productdetails.models

import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderModel
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationOptionModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel

data class ProductDetailsUIState(
    val productModel: ProductModel? = null,
    val groupValidations: Map<String, Boolean> = emptyMap(),
    val isAllValid: Boolean = false,
    val optionQuantities: Map<String, Int> = emptyMap(),
    val productQuantity: Int = 1,
    val observation: String = "",
    val finalPrice: Double = 0.0,
    val selectedCustomizations: List<CustomizationOptionModel> = emptyList(),
    val order: CartOrderModel = CartOrderModel()
) {
    companion object {
        fun initial(productModel: ProductModel): ProductDetailsUIState {
            val groupValidations = productModel.customizationGroupModels.associate { group ->
                group.id to (0 in group.min..group.max)
            }
            val isAllValid =
                if (groupValidations.isEmpty()) true else groupValidations.values.all { it }

            return ProductDetailsUIState(
                productModel = productModel,
                groupValidations = groupValidations,
                isAllValid = isAllValid,
                optionQuantities = emptyMap(),
                productQuantity = 1,
                observation = "",
                finalPrice = productModel.price
            )
        }
    }
}

