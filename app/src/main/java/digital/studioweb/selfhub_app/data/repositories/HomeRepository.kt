package digital.studioweb.selfhub_app.data.repositories

import digital.studioweb.selfhub_app.data.models.MenuCategoryItem

interface HomeRepository {
    suspend fun getMenuCategoryItems() : List<MenuCategoryItem>
}