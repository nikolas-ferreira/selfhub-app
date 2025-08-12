package digital.studioweb.selfhub_app.domain.features.home

import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel
import digital.studioweb.selfhub_app.domain.features.home.models.CartOrderModel

interface HomeRepository {
    suspend fun getCategories(): List<CategoryModel>
    suspend fun getAllProducts(): List<ProductModel>
    suspend fun createOrder(order: CartOrderModel)
}