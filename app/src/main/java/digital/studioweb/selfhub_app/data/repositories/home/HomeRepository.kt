package digital.studioweb.selfhub_app.data.repositories.home

import digital.studioweb.selfhub_app.data.models.Category
import digital.studioweb.selfhub_app.data.models.Product

interface HomeRepository {
    suspend fun getCategories(): List<Category>
    suspend fun getAllProducts(): List<Product>
}