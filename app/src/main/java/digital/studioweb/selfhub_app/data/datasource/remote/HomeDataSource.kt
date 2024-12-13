package digital.studioweb.selfhub_app.data.datasource.remote

import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product

interface HomeDataSource {
    suspend fun getMenuCategoryItems(): List<MenuCategoryItem>
    suspend fun getAllProducts(): List<Product>
}