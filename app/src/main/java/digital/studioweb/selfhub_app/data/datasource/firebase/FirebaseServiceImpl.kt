package digital.studioweb.selfhub_app.data.datasource.firebase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import digital.studioweb.selfhub_app.BuildConfig
import digital.studioweb.selfhub_app.data.utils.Result
import kotlinx.coroutines.delay
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Implementation of FirebaseService that handles all Firebase interactions.
 * Includes retry mechanism for critical operations.
 */
@Singleton
class FirebaseServiceImpl @Inject constructor() : FirebaseService {

    private val db: FirebaseFirestore by lazy { FirebaseFirestore.getInstance() }
    
    // Maximum number of retry attempts
    private val maxRetries = 3
    
    // Base delay for exponential backoff (in milliseconds)
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
        // In a real app, this would come from BuildConfig or similar
        // For now, we'll use a fallback value if not available
        return try {
            BuildConfig.FIREBASE_RESTAURANT_ID
        } catch (e: Exception) {
            // Fallback for development/testing
            "PnE5ONo0ipKDDOpfyvBB"
        }
    }

    /**
     * Helper function to implement retry with exponential backoff.
     * @param block The suspend function to retry.
     * @return Result containing the result of the operation or an error.
     */
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
                    currentDelay *= 2 // Exponential backoff
                }
            }
        }

        return Result.Error(lastException ?: Exception("Unknown error occurred"))
    }
}
