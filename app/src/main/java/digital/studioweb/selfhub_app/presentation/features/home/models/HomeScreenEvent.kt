package digital.studioweb.selfhub_app.presentation.features.home.models

import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderItemModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel

sealed class HomeScreenEvent {
    object ShowConfirmDialog : HomeScreenEvent()
    data class ShowDialogWithProduct(val product: ProductModel) : HomeScreenEvent()
    object CloseDialog : HomeScreenEvent()
    object OnConfirmOrder : HomeScreenEvent()
    data class OnCategorySelected(val index: Int) : HomeScreenEvent()
    data class OnSearchTextChanged(val text: String) : HomeScreenEvent()
    data class OnSidebarItemSelected(val index: Int) : HomeScreenEvent()
    object OnCartClick : HomeScreenEvent()
    data class AddToCart(val item: CartOrderItemModel) : HomeScreenEvent()
    data class RemoveItemFromCart(val item: CartOrderItemModel) : HomeScreenEvent()
    object OnRefreshRequested : HomeScreenEvent()
}
