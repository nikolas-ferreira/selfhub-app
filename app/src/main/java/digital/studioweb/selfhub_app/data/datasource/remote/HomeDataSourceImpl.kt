package digital.studioweb.selfhub_app.data.datasource.remote

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import digital.studioweb.selfhub_app.data.models.MenuCategoryItem
import digital.studioweb.selfhub_app.data.models.Product
import digital.studioweb.selfhub_app.data.utils.FirebaseUtils.FIREBASE_CATEGORIES_COLLECTION_NAME
import digital.studioweb.selfhub_app.data.utils.FirebaseUtils.FIREBASE_PRODUCTS_COLLECTION_NAME
import digital.studioweb.selfhub_app.data.utils.FirebaseUtils.FIREBASE_RESTAURANTS_COLLECTION_NAME
import digital.studioweb.selfhub_app.data.utils.FirebaseUtils.FIREBASE_RESTAURANT_ID
import kotlinx.coroutines.tasks.await

class HomeDataSourceImpl : HomeDataSource {
    override suspend fun getMenuCategoryItems(): List<MenuCategoryItem> {
        val db = FirebaseFirestore.getInstance()
        val restaurantRef: DocumentReference = db.collection(FIREBASE_RESTAURANTS_COLLECTION_NAME)
            .document(FIREBASE_RESTAURANT_ID)

        return try {
            val documents = db.collection(FIREBASE_CATEGORIES_COLLECTION_NAME)
                .whereEqualTo(FIREBASE_RESTAURANTS_COLLECTION_NAME, restaurantRef)
                .get()
                .await()

            documents.documents.mapNotNull { document ->
                document.toObject(MenuCategoryItem::class.java)?.apply {
                    id = document.id
                }
            }
        } catch (exception: Exception) {
            println("Erro ao buscar dados: ${exception.message}")
            emptyList()
        }
    }

    override suspend fun getAllProducts(): List<Product> {
        val db = FirebaseFirestore.getInstance()

        return try {
            val documents = db.collection(FIREBASE_PRODUCTS_COLLECTION_NAME)
                .get()
                .await()

            documents.documents.mapNotNull { document ->
                document.toObject(Product::class.java)
            }
        } catch (exception: Exception) {
            println("Erro ao buscar dados: ${exception.message}")
            emptyList()
        }
    }
}
