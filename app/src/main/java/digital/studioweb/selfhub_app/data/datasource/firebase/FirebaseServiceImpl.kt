package digital.studioweb.selfhub_app.data.datasource.firebase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import digital.studioweb.selfhub_app.data.utils.FirebaseUtils
import digital.studioweb.selfhub_app.data.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FirebaseServiceImpl @Inject constructor() : FirebaseService {

    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    
    private val maxRetries = 3
    private val baseDelayMs = 1000L

    override suspend fun getCollection(
        collectionPath: String,
        whereField: String?,
        whereValue: Any?
    ): Result<QuerySnapshot> {
        return withRetry {
            val query = db.collection(collectionPath)
            val finalQuery = if (whereField != null && whereValue != null) {
                query.whereEqualTo(whereField, whereValue)
            } else {
                query
            }
            finalQuery.get().await()
        }
    }

    override suspend fun getDocument(
        collectionPath: String,
        documentId: String
    ): Result<DocumentSnapshot> {
        return withRetry {
            db.collection(collectionPath).document(documentId).get().await()
        }
    }

    override fun getDocumentReference(
        collectionPath: String,
        documentId: String
    ): DocumentReference {
        return db.collection(collectionPath).document(documentId)
    }

    override fun getRestaurantId(): String {
        return FirebaseUtils.FIREBASE_RESTAURANT_ID
    }

    private suspend fun <T> withRetry(block: suspend () -> T): Result<T> {
        var currentDelay = baseDelayMs
        var lastException: Exception? = null

        repeat(maxRetries) { attempt ->
            try {
                return Result.Success(block())
            } catch (e: Exception) {
                lastException = e
                if (attempt < maxRetries - 1) {
                    delay(currentDelay)
                    currentDelay *= 2
                }
            }
        }

        return Result.Error(lastException ?: Exception("Unknown error occurred"))
    }
}
