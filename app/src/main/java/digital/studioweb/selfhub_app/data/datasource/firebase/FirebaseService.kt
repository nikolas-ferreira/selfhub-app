package digital.studioweb.selfhub_app.data.datasource.firebase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import digital.studioweb.selfhub_app.data.utils.Result

interface FirebaseService {
    suspend fun getCollection(
        collectionPath: String,
        whereField: String? = null,
        whereValue: Any? = null
    ): Result<QuerySnapshot>

    suspend fun getDocument(
        collectionPath: String,
        documentId: String
    ): Result<DocumentSnapshot>

    fun getDocumentReference(
        collectionPath: String,
        documentId: String
    ): DocumentReference

    fun getRestaurantId(): String
}
