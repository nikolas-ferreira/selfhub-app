package digital.studioweb.selfhub_app.presentation.features.home.models

import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel

data class HomeUIState(
    val isLoading: Boolean = true,
    val hasError: Boolean = false,
    val isSuccess: Boolean = false,
    val categories: List<CategoryModel> = emptyList(),
    val productModels: List<ProductModel> = emptyList(),
    val displayedProducts: List<ProductModel> = emptyList(),
    val cartProductModels: List<ProductModel> = emptyList(),

    val selectedCategoryIndex: Int = -1,
    val selectedSidebarIndex: Int = 0,
    val searchText: String = "",
    val currentTime: String = ""
)
