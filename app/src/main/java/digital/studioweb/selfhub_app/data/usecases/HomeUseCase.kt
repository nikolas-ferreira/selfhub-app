package digital.studioweb.selfhub_app.data.usecases

import digital.studioweb.selfhub_app.data.models.MenuCategoryItem

interface HomeUseCase {
    suspend fun getMenuCategoryItems() : List<MenuCategoryItem>
}