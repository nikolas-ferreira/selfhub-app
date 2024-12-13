package digital.studioweb.selfhub_app.data.models

import com.google.firebase.firestore.DocumentReference

data class Product(
    val id: String = "",
    val category: DocumentReference? = null,
    val iconUrl: String = "",
    val name: String = "",
    val price: Double = 0.0,
)