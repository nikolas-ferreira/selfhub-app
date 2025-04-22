package digital.studioweb.selfhub_app.data.usecases

import android.content.Context
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.repositories.HomeRepository
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
) : HomeUseCase {
    override suspend fun getMenuCategoryItems(): (List<MenuCategoryItem>) {
        return homeRepository.getMenuCategoryItems()
    }

    override suspend fun getAllProducts(): List<Product> {
        return homeRepository.getAllProducts()
    }
}