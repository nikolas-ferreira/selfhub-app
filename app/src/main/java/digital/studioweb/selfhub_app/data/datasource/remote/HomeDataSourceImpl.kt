package digital.studioweb.selfhub_app.data.datasource.remote

import android.util.Log
import digital.studioweb.selfhub_app.data.datasource.firebase.FirebaseService
import digital.studioweb.selfhub_app.data.models.AddItem
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.utils.FirebaseUtils.FIREBASE_CATEGORIES_COLLECTION_NAME
import digital.studioweb.selfhub_app.data.utils.FirebaseUtils.FIREBASE_PRODUCTS_COLLECTION_NAME
import digital.studioweb.selfhub_app.data.utils.FirebaseUtils.FIREBASE_RESTAURANTS_COLLECTION_NAME
import digital.studioweb.selfhub_app.data.utils.Result
import javax.inject.Inject

class HomeDataSourceImpl @Inject constructor(
    private val firebaseService: FirebaseService
) : HomeDataSource {
    override suspend fun getMenuCategoryItems(): List<MenuCategoryItem> {
        val restaurantId = firebaseService.getRestaurantId()
        val restaurantRef = firebaseService.getDocumentReference(
            FIREBASE_RESTAURANTS_COLLECTION_NAME,
            restaurantId
        )

        return when (val result = firebaseService.getCollection(
            FIREBASE_CATEGORIES_COLLECTION_NAME,
            FIREBASE_RESTAURANTS_COLLECTION_NAME,
            restaurantRef
        )) {
            is Result.Success -> {
                result.data.documents.mapNotNull { document ->
                    document.toObject(MenuCategoryItem::class.java)?.apply {
                        id = document.id
                    }
                }
            }
            is Result.Error -> {
                Log.e("HomeDataSourceImpl", "Error fetching menu categories: ${result.exception.message}")
                emptyList()
            }
        }
    }

    override suspend fun getAllProducts(): List<Product> {
        return when (val result = firebaseService.getCollection(FIREBASE_PRODUCTS_COLLECTION_NAME)) {
            is Result.Success -> {
                result.data.documents.mapNotNull { document ->
                    document.data?.let { data ->
                        val addItemsList = (data["addItems"] as? List<*>)?.mapNotNull { item ->
                            (item as? Map<*, *>)?.let { map ->
                                AddItem(
                                    name = map["name"] as? String ?: "",
                                    price = (map["price"] as? Number)?.toDouble() ?: 0.0
                                )
                            }
                        } ?: emptyList()
                        document.toObject(Product::class.java)?.apply {
                            id = document.id
                            addItems = addItemsList
                        }
                    }
                }
            }
            is Result.Error -> {
                Log.e("HomeDataSourceImpl", "Error fetching products: ${result.exception.message}")
                emptyList()
            }
        }
    }
}
