package digital.studioweb.selfhub_app.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.usecases.HomeUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCase: HomeUseCase
) : ViewModel() {
    val menuCategoryItemsData: MutableLiveData<List<MenuCategoryItem>> = MutableLiveData()

    fun getMenuCategoryItems() {
        viewModelScope.launch {
            menuCategoryItemsData.value = homeUseCase.getMenuCategoryItems()
        }
    }
}