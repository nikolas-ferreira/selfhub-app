package digital.studioweb.selfhub_app.data.models

import com.google.firebase.firestore.DocumentReference

data class Product(
    var id: String = "",
    val category: DocumentReference? = null,
    val iconUrl: String = "",
    val name: String = "",
    val description: String = "",
    var addItems: List<AddItems>? = emptyList(),
    val price: Double = 0.0,
)

data class AddItems(
    val name: String = "",
    val price: Double = 0.0
)