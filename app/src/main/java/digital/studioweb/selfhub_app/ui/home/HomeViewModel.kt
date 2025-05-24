package digital.studioweb.selfhub_app.ui.home

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.studioweb.selfhub_app.data.models.Category
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.usecases.GetCategoriesUseCase
import digital.studioweb.selfhub_app.data.usecases.GetProductsUseCase
import digital.studioweb.selfhub_app.data.utils.Result
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val getProductsUseCase: GetProductsUseCase
) : ViewModel() {

    private val _state = mutableStateOf<HomeState>(HomeState.Loading)
    val state = _state

    val categoriesData: MutableLiveData<List<Category>> = MutableLiveData()
    val allProducts: MutableLiveData<List<Product>> = MutableLiveData()

    fun getCategories() {
        viewModelScope.launch {
            _state.value = HomeState.Loading
            try {
                when (val categoriesResult = getCategoriesUseCase()) {
                    is Result.Success -> {
                        categoriesData.value = categoriesResult.data
                        getAllProducts()
                    }

                    is Result.Error -> {
                        Log.e("ERROR", categoriesResult.exception.message.toString())
                        throw categoriesResult.exception
                    }
                }
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
                _state.value = HomeState.Error
            }
        }
    }

    fun getAllProducts() {
        viewModelScope.launch {
            try {
                when (val productsResult = getProductsUseCase()) {
                    is Result.Success -> {
                        allProducts.value = productsResult.data
                        _state.value = HomeState.Success
                    }

                    is Result.Error -> {
                        Log.e("ERROR", productsResult.exception.message.toString())
                        throw productsResult.exception
                    }
                }
            } catch (e: Exception) {
                Log.e("ERROR", e.message.toString())
                _state.value = HomeState.Error

            }
        }
    }

}
