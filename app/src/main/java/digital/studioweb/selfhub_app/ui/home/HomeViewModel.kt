package digital.studioweb.selfhub_app.ui.home

import android.content.Context
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.usecases.HomeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _state = mutableStateOf<HomeState>(HomeState.Loading)
    val state = _state

    val categoriesData: MutableLiveData<List<MenuCategoryItem>> = MutableLiveData()
    val allProducts: MutableLiveData<List<Product>> = MutableLiveData()

    fun getMenuCategoryItems() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            try {
                categoriesData.value = homeUseCase.getMenuCategoryItems()
                allProducts.value = homeUseCase.getAllProducts()
                _state.value = HomeState.Success
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
                _state.value = HomeState.Error
            }
        }
    }

    fun getProductsFromCategory(categoryIndex: Int) : List<Product>{
        return TODO("Provide the return value")
    }

    fun getCategoryProductsCount(category: MenuCategoryItem): Int {
        return allProducts.value?.filter { product ->
            product.category?.id == category.id
        }?.size ?: 0
    }
}