package digital.studioweb.selfhub_app.data.datasource.remote

import digital.studioweb.selfhub_app.data.models.MenuCategoryItem

interface HomeDataSource {
    suspend fun getMenuCategoryItems() : List<MenuCategoryItem>
}