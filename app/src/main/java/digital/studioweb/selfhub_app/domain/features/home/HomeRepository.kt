package digital.studioweb.selfhub_app.domain.features.home

import digital.studioweb.selfhub_app.domain.features.home.models.CategoryModel
import digital.studioweb.selfhub_app.domain.features.home.models.ProductModel

interface HomeRepository {
    suspend fun getCategories(): List<CategoryModel>
    suspend fun getAllProducts(): List<ProductModel>
}