package digital.studioweb.selfhub_app.data.repositories.home

import digital.studioweb.selfhub_app.data.models.Category
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.service.ApiService
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : HomeRepository {
    override suspend fun getCategories(): List<Category> {
        return apiService.getCategories(
            restaurantId = "6825ffeba5460eebd9b0f8ec"
        ).response.map { it.mapTo() }
    }

    override suspend fun getAllProducts(): List<Product> {
        return apiService.getProducts(
            restaurantId = "6825ffeba5460eebd9b0f8ec"
        ).response.map { it.mapTo() }
    }
}