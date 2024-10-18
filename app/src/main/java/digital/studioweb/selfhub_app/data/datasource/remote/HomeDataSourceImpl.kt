package digital.studioweb.selfhub_app.data.datasource.remote

import digital.studioweb.selfhub_app.data.models.MenuCategoryItem

class HomeDataSourceImpl() : HomeDataSource {
    override suspend fun getMenuCategoryItems(): List<MenuCategoryItem> {
        return listOf(
            MenuCategoryItem(
                id = "1",
                title = "Hamburguer",
                itemsCount = 8,
                iconUrl = "https://example.com/icon1.png"
            ),
            MenuCategoryItem(
                id = "2",
                title = "Xis",
                itemsCount = 9,
                iconUrl = "https://example.com/icon2.png"
            ),
            MenuCategoryItem(
                id = "3",
                title = "Dogs",
                itemsCount = 8,
                iconUrl = "https://example.com/icon3.png"
            ),
            MenuCategoryItem(
                id = "4",
                title = "Pizzas",
                itemsCount = 12,
                iconUrl = "https://example.com/icon4.png"
            ),
            MenuCategoryItem(
                id = "5",
                title = "Bebidas",
                itemsCount = 27,
                iconUrl = "https://example.com/icon5.png"
            )
        )
    }
}