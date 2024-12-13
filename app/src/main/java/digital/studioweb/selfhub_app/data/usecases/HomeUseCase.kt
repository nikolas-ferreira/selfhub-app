package digital.studioweb.selfhub_app.data.usecases

import android.content.Context
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product

interface HomeUseCase {
    suspend fun getMenuCategoryItems() : List<MenuCategoryItem>
    suspend fun getAllProducts() : List<Product>
}