package digital.studioweb.selfhub_app.data.features.home

import digital.studioweb.selfhub_app.data.sources.SecureSharedLocalDataSourceInterface
import digital.studioweb.selfhub_app.data.utils.LocalConstants.RESTAURANT_ID_KEY
import digital.studioweb.selfhub_app.domain.features.home.HomeRepository
import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeAPI: HomeAPI,
    private val secureSharedLocalDataSource: SecureSharedLocalDataSourceInterface
) : HomeRepository {
    override suspend fun getCategories(): List<CategoryModel> {
        return homeAPI.getCategories(
            restaurantId = secureSharedLocalDataSource.retrieve(RESTAURANT_ID_KEY, String::class) ?: ""
        ).response.map { it.mapTo() }
    }

    override suspend fun getAllProducts(): List<ProductModel> {
        return homeAPI.getProducts(
            restaurantId = secureSharedLocalDataSource.retrieve(RESTAURANT_ID_KEY, String::class) ?: ""
        ).response.map { it.mapTo() }
    }
}