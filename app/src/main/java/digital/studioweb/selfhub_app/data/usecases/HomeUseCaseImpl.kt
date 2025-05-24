package digital.studioweb.selfhub_app.data.usecases

import digital.studioweb.selfhub_app.data.models.Category
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.repositories.home.HomeRepository
import javax.inject.Inject

class HomeUseCaseImpl @Inject constructor(
    private val homeRepository: HomeRepository
) : HomeUseCase {
    override suspend fun getMenuCategoryItems(): (List<Category>) {
        return homeRepository.getCategories()
    }

    override suspend fun getAllProducts(): List<Product> {
        return homeRepository.getAllProducts()
    }
}