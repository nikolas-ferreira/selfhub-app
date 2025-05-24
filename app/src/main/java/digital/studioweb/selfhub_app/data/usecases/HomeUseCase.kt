package digital.studioweb.selfhub_app.data.usecases

import digital.studioweb.selfhub_app.data.models.Category
import digital.studioweb.selfhub_app.data.models.Product

interface HomeUseCase {
    suspend fun getMenuCategoryItems() : List<Category>
    suspend fun getAllProducts() : List<Product>
}