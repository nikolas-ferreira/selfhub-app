package digital.studioweb.selfhub_app.presentation.features.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.studioweb.selfhub_app.domain.features.home.HomeGetCategoriesUseCase
import digital.studioweb.selfhub_app.domain.features.home.HomeGetProductsUseCase
import digital.studioweb.selfhub_app.data.base.onFailure
import digital.studioweb.selfhub_app.data.base.onSuccess
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderModel
import digital.studioweb.selfhub_app.domain.features.models.PaymentMethod
import digital.studioweb.selfhub_app.presentation.features.home.models.HomeUIState
import digital.studioweb.selfhub_app.presentation.features.home.models.HomeScreenEvent
import digital.studioweb.selfhub_app.presentation.features.productdetails.models.ProductDetailsEvent
import digital.studioweb.selfhub_app.presentation.utils.StringUtils.formatCurrentTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.annotations.VisibleForTesting
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: HomeGetCategoriesUseCase,
    private val getProductsUseCase: HomeGetProductsUseCase
) : ViewModel() {

    //region Properties

    var uiState by mutableStateOf(HomeUIState())
        private set

    //endregion

    //region Public Functions

    fun init() {
        getCategories()
//        startClock()
    }

    fun onEvent(event: HomeScreenEvent) {
        when (event) {
            is HomeScreenEvent.OnCategorySelected -> handleCategorySelection(event.index)
            is HomeScreenEvent.OnSearchTextChanged -> handleSearchTextChange(event.text)
            is HomeScreenEvent.OnSidebarItemSelected -> handleSidebarItemSelected(event.index)
            is HomeScreenEvent.OnCartClick -> handleCartClick()
            is HomeScreenEvent.AddToCart -> handleAddToCart(event.item)
            is HomeScreenEvent.RemoveItemFromCart -> handleRemoveItemFromCart(event.item)
            is HomeScreenEvent.OnRefreshRequested -> init()
            is HomeScreenEvent.ShowDialogWithProduct -> {
                uiState = uiState.copy(
                        selectedProduct = event.product,
                        showDialog = true
                    )
                }
            is HomeScreenEvent.CloseDialog -> {
                uiState = uiState.copy(showDialog = false, selectedProduct = null)
            }
        }
    }

    //endregion

    //region Visible for Testing

    @VisibleForTesting
    fun handleRemoveItemFromCart(item: CartOrderItemModel) {
        val newItems = uiState.order.items.filter { it.orderItemId != item.orderItemId }

        val updatedTotalValue = newItems.sumOf { it.price * it.quantity }

        uiState = uiState.copy(
            order = uiState.order.copy(
                items = newItems,
                totalValue = updatedTotalValue
            )
        )
    }

    @VisibleForTesting
    fun handleCategorySelection(index: Int) {
        uiState = uiState.copy(selectedCategoryIndex = index)
        updateDisplayedProducts()
    }

    @VisibleForTesting
    fun handleSearchTextChange(text: String) {
        uiState = uiState.copy(searchText = text)
        updateDisplayedProducts()
    }

    @VisibleForTesting
    fun handleSidebarItemSelected(index: Int) {
        uiState = uiState.copy(selectedSidebarIndex = index)
        if (index == 0) {
            uiState = uiState.copy(selectedCategoryIndex = -1)
            updateDisplayedProducts()
        }
    }

    @VisibleForTesting
    private fun handleCartClick() {
    }

    @VisibleForTesting
    fun updateDisplayedProducts() {
        val categoryId = uiState.categories.getOrNull(uiState.selectedCategoryIndex)?.id
        val filtered = uiState.productModels.filter {
            val matchCategory = categoryId == null || it.categoryId == categoryId
            val matchSearch = uiState.searchText.isBlank() || it.name.contains(
                uiState.searchText,
                ignoreCase = true
            )
            matchCategory && matchSearch
        }
        uiState = uiState.copy(displayedProducts = filtered)
    }

    @VisibleForTesting
    fun startClock() {
        viewModelScope.launch {
            while (true) {
                uiState = uiState.copy(currentTime = formatCurrentTime())
                delay(1000)
            }
        }
    }

    @VisibleForTesting
    fun getCategories() {
        isLoading()
        viewModelScope.launch(Dispatchers.IO) {
            getCategoriesUseCase.runAsync()
                .onSuccess { categories ->
                    uiState = uiState.copy(categories = categories)
                    getAllProducts()
                }
                .onFailure {
                    setError()
                }
        }
    }

    @VisibleForTesting
    fun getAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            getProductsUseCase.runAsync()
                .onSuccess { products ->
                    uiState = uiState.copy(
                        productModels = products,
                        isLoading = false,
                        isSuccess = true
                    )
                    updateDisplayedProducts()
                }
                .onFailure {
                    setError()
                }
        }
    }

    @VisibleForTesting
    fun setError() {
        uiState = uiState.copy(
            hasError = true,
            isLoading = false
        )
    }

    @VisibleForTesting
    fun isLoading() {
        uiState = uiState.copy(
            isLoading = true,
            hasError = false
        )
    }

    @VisibleForTesting
    fun handleAddToCart(item: CartOrderItemModel) {
        val hasActiveOrder = uiState.order.orderNumber.isNotBlank()

        val updatedItems = if (hasActiveOrder) {
            uiState.order.items + item
        } else {
            listOf(item)
        }

        val updatedTotalValue = updatedItems.sumOf { it.price * it.quantity }

        val currentOrder = if (hasActiveOrder) {
            uiState.order.copy(
                items = updatedItems,
                totalValue = updatedTotalValue
            )
        } else {
            CartOrderModel(
                orderNumber = (0..9999).random().toString(),
                paymentMethod = PaymentMethod.UNKNOWN,
                totalValue = updatedTotalValue,
                items = updatedItems
            )
        }

        uiState = uiState.copy(
            order = currentOrder,
            snackBarMessage = "Produto adicionado ao carrinho!"
        )
    }

    //endregion
}
