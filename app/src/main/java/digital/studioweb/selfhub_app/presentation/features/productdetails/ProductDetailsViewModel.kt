package digital.studioweb.selfhub_app.presentation.features.productdetails

import androidx.annotation.VisibleForTesting
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationGroupModel
import digital.studioweb.selfhub_app.domain.features.home.models.CustomizationOptionModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import digital.studioweb.selfhub_app.presentation.features.productdetails.models.ProductDetailsEvent
import digital.studioweb.selfhub_app.presentation.features.productdetails.models.ProductDetailsUIState
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(

) : ViewModel() {

    //region Properties

    var uiState by mutableStateOf(ProductDetailsUIState())
        private set

    //endregion

    //region Methods

    fun onEvent(event: ProductDetailsEvent) {
        when (event) {
            is ProductDetailsEvent.Init -> handleInit(event.productModel)
            is ProductDetailsEvent.IncrementProductQuantity -> handleIncrementProductQuantity()
            is ProductDetailsEvent.DecrementProductQuantity -> handleDecrementProductQuantity()
            is ProductDetailsEvent.ChangeObservation -> handleChangeObservation(event.observation)
            is ProductDetailsEvent.IncrementOption -> handleIncrementOption(
                event.group,
                event.optionId
            )

            is ProductDetailsEvent.DecrementOption -> handleDecrementOption(
                event.group,
                event.optionId
            )
            else -> {}
        }
    }

    // endregion

    //region Visible for testing

    @VisibleForTesting
    fun updateSelectedCustomizations() {
        val product = uiState.productModel ?: return
        val updated = mutableListOf<CustomizationOptionModel>()

        product.customizationGroupModels.forEach { group ->
            group.options.forEach { option ->
                val quantity = uiState.optionQuantities[option.id] ?: 0
                if (quantity > 0) {
                    updated.add(option.copy(additionalPrice = option.additionalPrice * quantity))
                }
            }
        }

        uiState = uiState.copy(selectedCustomizations = updated)
    }

    @VisibleForTesting
    fun handleInit(productModel: ProductModel) {
        uiState = ProductDetailsUIState.initial(productModel)
    }

    @VisibleForTesting
    fun handleIncrementProductQuantity() {
        uiState = uiState.copy(productQuantity = uiState.productQuantity + 1)
        updateFinalPrice()
    }

    @VisibleForTesting
    fun handleDecrementProductQuantity() {
        if (uiState.productQuantity > 1) {
            uiState = uiState.copy(productQuantity = uiState.productQuantity - 1)
            updateFinalPrice()
        }
    }

    @VisibleForTesting
    fun handleChangeObservation(observation: String) {
        uiState = uiState.copy(observation = observation)
    }

    @VisibleForTesting
    fun handleIncrementOption(group: CustomizationGroupModel, optionId: String) {
        val current = uiState.optionQuantities[optionId] ?: 0
        val totalGroupSelected = group.options.sumOf {
            uiState.optionQuantities[it.id] ?: 0
        }
        if (totalGroupSelected < group.max) {
            val updated = uiState.optionQuantities.toMutableMap()
            updated[optionId] = current + 1
            uiState = uiState.copy(optionQuantities = updated)
            validateGroup(group)
            updateFinalPrice()
            updateSelectedCustomizations()
        }
    }

    @VisibleForTesting
    fun handleDecrementOption(group: CustomizationGroupModel, optionId: String) {
        val current = uiState.optionQuantities[optionId] ?: 0
        if (current > 0) {
            val updated = uiState.optionQuantities.toMutableMap()
            updated[optionId] = current - 1
            uiState = uiState.copy(optionQuantities = updated)
            validateGroup(group)
            updateFinalPrice()
            updateSelectedCustomizations()
        }
    }

    @VisibleForTesting
    fun validateGroup(group: CustomizationGroupModel) {
        val selectedCount = group.options.sumOf { option ->
            uiState.optionQuantities[option.id] ?: 0
        }
        val updatedValidations = uiState.groupValidations.toMutableMap()
        updatedValidations[group.id] = selectedCount in group.min..group.max
        uiState = uiState.copy(groupValidations = updatedValidations)
        updateValidationState()
    }

    @VisibleForTesting
    fun updateValidationState() {
        val allValid = uiState.groupValidations.values.all { it }
        uiState = uiState.copy(isAllValid = allValid)
    }

    @VisibleForTesting
    fun updateFinalPrice() {
        val basePrice = uiState.productModel?.price ?: 0.0
        val extras = uiState.productModel?.customizationGroupModels?.flatMap { it.options }
            ?.sumOf { option ->
                val quantity = uiState.optionQuantities[option.id] ?: 0
                option.additionalPrice * quantity
            } ?: 0.0
        val total = (basePrice + extras) * uiState.productQuantity
        uiState = uiState.copy(finalPrice = total)
    }

    //endregion
}
