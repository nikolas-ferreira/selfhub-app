package digital.studioweb.selfhub_app.data.repositories

import digital.studioweb.selfhub_app.data.datasource.remote.HomeDataSource
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getMenuCategoryItems(): List<MenuCategoryItem> {
        return homeDataSource.getMenuCategoryItems()
    }
}