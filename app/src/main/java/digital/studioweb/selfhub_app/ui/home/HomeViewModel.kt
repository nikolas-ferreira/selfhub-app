package digital.studioweb.selfhub_app.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.usecases.FilterProductsByCategoryUseCase
import digital.studioweb.selfhub_app.data.usecases.GetMenuCategoriesUseCase
import digital.studioweb.selfhub_app.data.usecases.GetProductsUseCase
import digital.studioweb.selfhub_app.data.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getMenuCategoriesUseCase: GetMenuCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase,
    private val filterProductsByCategoryUseCase: FilterProductsByCategoryUseCase
) : ViewModel() {

    private val _state = mutableStateOf<HomeState>(HomeState.Loading)
    val state = _state

    val categoriesData: MutableLiveData<List<MenuCategoryItem>> = MutableLiveData()
    val allProducts: MutableLiveData<List<Product>> = MutableLiveData()
    val productsFromCategory: MutableLiveData<List<Product>> = MutableLiveData()

    fun getMenuCategoryItems() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            try {
                when (val categoriesResult = getMenuCategoriesUseCase()) {
                    is Result.Success -> {
                        categoriesData.value = categoriesResult.data
                    }
                    is Result.Error -> {
                        Log.e("ERROR", categoriesResult.exception.message.toString())
                        throw categoriesResult.exception
                    }
                }
                
                when (val productsResult = getProductsUseCase()) {
                    is Result.Success -> {
                        productsFromCategory.value = productsResult.data
                        allProducts.value = productsResult.data
                    }
                    is Result.Error -> {
                        Log.e("ERROR", productsResult.exception.message.toString())
                        throw productsResult.exception
                    }
                }
                
                _state.value = HomeState.Success
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
                _state.value = HomeState.Error
            }
        }
    }

    fun getProductsFromCategory(categoryId: String): List<Product> {
        val result = filterProductsByCategoryUseCase(allProducts.value.orEmpty(), categoryId)
        return when (result) {
            is Result.Success -> {
                productsFromCategory.value = result.data
                result.data
            }
            is Result.Error -> {
                Log.e("ERROR", result.exception.message.toString())
                emptyList()
            }
        }
    }
}
