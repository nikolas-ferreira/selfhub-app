package digital.studioweb.selfhub_app.data.datasource.firebase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import digital.studioweb.selfhub_app.data.utils.Result

/**
 * Interface for Firebase operations.
 * Provides a single abstraction layer for all Firebase interactions.
 */
interface FirebaseService {
    /**
     * Get a collection from Firestore with retry mechanism.
     * @param collectionPath The path to the collection.
     * @param whereField Optional field to filter on.
     * @param whereValue Optional value to filter on.
     * @return Result containing QuerySnapshot or Error.
     */
    suspend fun getCollection(
        collectionPath: String,
        whereField: String? = null,
        whereValue: Any? = null
    ): Result<QuerySnapshot>

    /**
     * Get a document from Firestore with retry mechanism.
     * @param collectionPath The path to the collection.
     * @param documentId The ID of the document.
     * @return Result containing DocumentSnapshot or Error.
     */
    suspend fun getDocument(
        collectionPath: String,
        documentId: String
    ): Result<DocumentSnapshot>

    /**
     * Get a document reference from Firestore.
     * @param collectionPath The path to the collection.
     * @param documentId The ID of the document.
     * @return DocumentReference for the specified document.
     */
    fun getDocumentReference(
        collectionPath: String,
        documentId: String
    ): DocumentReference

    /**
     * Get the restaurant ID from configuration.
     * @return The restaurant ID.
     */
    fun getRestaurantId(): String
}
