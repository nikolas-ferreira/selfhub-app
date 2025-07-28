package digital.studioweb.selfhub_app.data.features.home

import digital.studioweb.selfhub_app.domain.features.home.HomeRepository
import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeAPI: HomeAPI
) : HomeRepository {
    override suspend fun getCategories(): List<CategoryModel> {
        return homeAPI.getCategories(
            restaurantId = "6825ffeba5460eebd9b0f8ec"
        ).response.map { it.mapTo() }
    }

    override suspend fun getAllProducts(): List<ProductModel> {
        return homeAPI.getProducts(
            restaurantId = "6825ffeba5460eebd9b0f8ec"
        ).response.map { it.mapTo() }
    }
}