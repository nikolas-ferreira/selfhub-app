package digital.studioweb.selfhub_app.data.repositories

import android.content.Context
import digital.studioweb.selfhub_app.data.datasource.remote.HomeDataSource
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeDataSource: HomeDataSource
) : HomeRepository {
    override suspend fun getMenuCategoryItems(): List<MenuCategoryItem> {
        return homeDataSource.getMenuCategoryItems()
    }

    override suspend fun getAllProducts(): List<Product> {
        return homeDataSource.getAllProducts()
    }
}